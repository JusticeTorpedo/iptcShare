package cn.edu.ncut.controller;

import cn.edu.ncut.domain.User;
import cn.edu.ncut.domain.UserProfile;
import cn.edu.ncut.service.AdminService;
import cn.edu.ncut.service.UserProfileService;
import cn.edu.ncut.service.UserService;
import cn.edu.ncut.util.JWTUtils;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserProfileService userProfileService;

    @Resource
    private AdminService adminService;

    //收到来自浏览器的用户登录请求
    @RequestMapping("/login.do")
    public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();

        //从请求中获取登录信息
        String userName = request.getParameter("username");
        String userPwd = request.getParameter("password");
        String keepLogin = request.getParameter("keepLogin");

        //登录验证
        if(userService.userLogin(userName, userPwd)){
            System.out.println("登录成功");

            //token存活时间
            Calendar instance = Calendar.getInstance();

            //cookie存活时间
            int cookieLife;

            //判断是否选中了7天免登录
            if (keepLogin != null) {
                //token 7天内有效
                instance.add(Calendar.DATE, 7);
                cookieLife = 60 * 60 * 24 * 7;
            }else{
                //token 12小时内有效
                instance.add(Calendar.MINUTE, 30);
                cookieLife = 60 * 60 * 12;
            }

            //为当前用户创建token
            Map<String, String> payload = new HashMap<>();
            payload.put("userName",userName);
            payload.put("userPwd",userPwd);
            String token = JWTUtils.createTokenWithTime(payload, instance);

            //将token封装到cookie中
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/common");
            cookie.setMaxAge(cookieLife);

            //将token响应给浏览器
            response.addCookie(cookie);

            modelAndView.addObject("status",true);
            modelAndView.addObject("username",userName);

            //判断是否是管理员，为管理员跳转到后台管理页面
            if(adminService.isAdmin(userService.getUserIdByUserName(userName))){
                modelAndView.setViewName("admin/adminHome");
            }else{
                modelAndView.setViewName("user/home");
            }

        }else{
            System.out.println("登陆失败");
            modelAndView.addObject("status",false);
            modelAndView.addObject("msg","登录失败，用户名或密码错误");
        }
        return modelAndView;
    }

    //接收来自浏览器的用户注册请求
    @RequestMapping("/register.do")
    public ModelAndView userRegister(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        //从请求中获取注册信息
        User user = new User(request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("email")
        );

        //用户注册
        if(userService.userRegister(user)){
            //操作user_profile表
            userProfileService.initProfile(user.getUserName());
            System.out.println("注册成功");
            modelAndView.addObject("msg","注册成功，请返回登录");
        }else{
            System.out.println("注册失败");
            modelAndView.addObject("msg","注册失败，用户名或邮箱重复！");
        }

        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    //用户登出
    @RequestMapping("/logout.do")
    public ModelAndView userLogout(HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();

        //创建一个空cookie，覆盖掉原有的
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/common");
        cookie.setMaxAge(0);

        //将空cookie发送到浏览器
        response.addCookie(cookie);

        //跳转到登录页面
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    //上传头像
    @RequestMapping("/avatar/upload.do")
    @ResponseBody
    public String userAvatarUpload(HttpServletRequest request, @Param("file") MultipartFile file) throws IOException{

        String username = JWTUtils.getUserName(request.getCookies());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());

        //服务器上使用
        // String rootPath =request.getServletContext().getRealPath("/resource/uploads/");//target的目录

        //本地使用
        String rootPath = "D:\\Uploads\\avatarImages";

        //原始名称
        String originalFilename = file.getOriginalFilename();

        //新的文件名称
        String newFileName = username + "_" + res + originalFilename.substring(originalFilename.lastIndexOf("."));

        //创建年月文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR)
                + File.separator + (date.get(Calendar.MONTH)+1));

        //新文件
        File newFile = new File(rootPath+File.separator+dateDirs+File.separator+newFileName);

        //判断目标文件所在的目录是否存在
        if(!newFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        System.out.println(newFile);

        //将内存中的数据写入磁盘
        file.transferTo(newFile);

        //完整的url
        String fileUrl =
                "/avatarImages/" + date.get(Calendar.YEAR)
                        + "/" + (date.get(Calendar.MONTH)+1)
                        + "/" + newFileName;
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> map2 = new HashMap<String,Object>();
        map.put("code",0);//0表示成功，1失败
        map.put("msg","上传成功");//提示消息
        map.put("data",map2);
        map2.put("src",fileUrl);//图片url
        //map2.put("title",newFileName);//图片名称，这个会显示在输入框里
        String result = new JSONObject(map).toString();

        //操作user_profile表
        userProfileService.setAvatar(username, fileUrl);

        return result;
    }

    @RequestMapping("/profile/upload.do")
    public ModelAndView userProfileUpload(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        String username = JWTUtils.getUserName(request.getCookies());

        UserProfile userProfile = new UserProfile();
        userProfile.setUserName(username);
        userProfile.setUserRealName(request.getParameter("realName"));
        userProfile.setUserGender(request.getParameter("gender"));
        userProfile.setUserUniversity(request.getParameter("university"));
        userProfile.setUserTitle(request.getParameter("title"));
        userProfile.setUserSignature(request.getParameter("signature"));

        userProfileService.setProfile(userProfile);
        modelAndView.setViewName("user/home");
        return modelAndView;
    }
}
