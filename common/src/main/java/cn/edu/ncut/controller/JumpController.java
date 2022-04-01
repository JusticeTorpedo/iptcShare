package cn.edu.ncut.controller;

import cn.edu.ncut.domain.SubjectCat1st;
import cn.edu.ncut.domain.UserProfile;
import cn.edu.ncut.service.AdminService;
import cn.edu.ncut.service.SubjectCatService;
import cn.edu.ncut.service.UserProfileService;
import cn.edu.ncut.service.UserService;
import cn.edu.ncut.util.JWTUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/jump")
public class JumpController {

    @Resource
    private UserService userService;

    @Resource
    private UserProfileService profileService;

    @Resource
    private AdminService adminService;

    @Resource
    private SubjectCatService subjectCatService;


    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login.do")
    public ModelAndView Jump2Login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    /**
     * JWTInterceptor会对其进行preHandle检查
     * @param request
     * @return modelAndView
     */
    @RequestMapping("/home.do")
    public ModelAndView Jump2UserHome(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        String userName = JWTUtils.getUserName(request.getCookies());

        //返回用户名
        modelAndView.addObject("username", userName);

        //判断是否是管理员
        if(adminService.isAdmin(userService.getUserIdByUserName(userName))){
            modelAndView.setViewName("admin/adminHome");
        }else{
            modelAndView.setViewName("user/home");
        }
        return modelAndView;
    }

    /**
     * 跳转到个人资料页面
     * @return
     */
    @RequestMapping("/profile.do")
    public ModelAndView Jump2UserProfile(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        UserProfile profile = profileService.getProfile(JWTUtils.getUserName(request.getCookies()));
        modelAndView.addObject("profile", profile);
        modelAndView.setViewName("user/profile");
        return modelAndView;
    }

    /**
     * 跳转到写案例页面
     * @return
     */
    @RequestMapping("/articleWritten.do")
    public ModelAndView jump2ArticleWritten(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        String userName = JWTUtils.getUserName(request.getCookies());
        System.out.println("跳转中。。。"+userName);

        //加载学科门类
        List<SubjectCat1st> cat1stList = subjectCatService.getAllSubjectCat1st();

        //返回用户名
        modelAndView.addObject("username", userName);
        modelAndView.addObject("cat1stList",cat1stList);
        modelAndView.setViewName("content/article/articleUpload");
        return modelAndView;
    }

    /**
     * 跳转到上传素材页面
     * @param request
     * @return
     */
    @RequestMapping("/materialWritten.do")
    public ModelAndView jump2MaterialWritten(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        String userName = JWTUtils.getUserName(request.getCookies());
        System.out.println("跳转中。。。"+userName);

        //加载学科门类
        List<SubjectCat1st> cat1stList = subjectCatService.getAllSubjectCat1st();

        //返回用户名
        modelAndView.addObject("username", userName);
        modelAndView.addObject("cat1stList",cat1stList);
        modelAndView.setViewName("content/material/materialUpload");
        return modelAndView;
    }

    /**
     * 跳转到话题发布页面
     * @param request
     * @return
     */
    @RequestMapping("/topicWritten.do")
    public ModelAndView jump2TopicWritten(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        String userName = JWTUtils.getUserName(request.getCookies());
        System.out.println("跳转中。。。"+userName);

        //返回用户名
        modelAndView.addObject("username", userName);
        modelAndView.setViewName("content/topic/topicUpload");
        return modelAndView;
    }
}
