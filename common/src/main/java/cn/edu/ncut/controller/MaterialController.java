package cn.edu.ncut.controller;

import cn.edu.ncut.domain.*;
import cn.edu.ncut.service.*;
import cn.edu.ncut.util.JWTUtils;
import cn.edu.ncut.vo.CommonResult;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/material")
public class MaterialController {

    @Resource
    private MaterialService materialService;

    @Resource
    private UserService userService;

    @Resource
    private UserProfileService userProfileService;

    @Resource
    private SubjectCatService subjectCatService;


    /**
     * 素材专区加载
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView materialIndex(){
        ModelAndView modelAndView = new ModelAndView();

        //获取文章列表
        List<Material> materialList = materialService.getMaterialAll();

        modelAndView.addObject("materialList", materialList);
        modelAndView.setViewName("content/material/materialIndex");
        return modelAndView;
    }

    /**
     * 素材上传
     * @param request
     * @return
     */
    @RequestMapping("/upload.do")
    public ModelAndView materialUpload(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        String userName = JWTUtils.getUserName(request.getCookies());
        String catFirst = subjectCatService.getSubjectCat1stName(Integer.valueOf(request.getParameter("subjectCat1st")));
        String catSecond = subjectCatService.getSubjectCat2stName(Integer.valueOf(request.getParameter("subjectCat2st")));
        String title = request.getParameter("materialTitle");
        String content = request.getParameter("materialContent");

        Material material = new Material(
                userName,
                catFirst,
                catSecond,
                title,
                content,
                new Date(),
                new Date(),
                1,
                0,
                0
        );

        if (materialService.materialUpload(material)){
            System.out.println("上传成功");
        }

        //这里需要改成跳转到【我的素材】页面
        modelAndView.setViewName("user/profile");
        return modelAndView;
    }

    /**
     * 接收素材里的图片
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/image/upload.do")
    public String uploadFile(HttpServletRequest request,@Param("file") MultipartFile file) throws IOException {

        String username = JWTUtils.getUserName(request.getCookies());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());

        //服务器上使用
        // String rootPath =request.getServletContext().getRealPath("/resource/uploads/");//target的目录

        //本地使用
        String rootPath = "D:\\Uploads\\materialImages";

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
                "/materialImages/" + date.get(Calendar.YEAR)
                        + "/" + (date.get(Calendar.MONTH)+1)
                        + "/" + newFileName;
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> map2 = new HashMap<String,Object>();
        map.put("code",0);//0表示成功，1失败
        map.put("msg","上传成功");//提示消息
        map.put("data",map2);
        map2.put("src",fileUrl);//图片url
        map2.put("title",newFileName);//图片名称，这个会显示在输入框里
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 加载素材详情
     * @param materialId
     * @return
     */
    @RequestMapping("/{materialId}/view.do")
    public ModelAndView materialDetailPage(@PathVariable("materialId") Integer materialId){
        ModelAndView modelAndView = new ModelAndView();

        //获取material
        Material material = materialService.getMaterialByMaterialId(materialId);
        UserProfile userProfile = userProfileService.getProfile(material.getUserName());

        //若请求的article不存在
        if(material == null){
            modelAndView.setViewName("error/404");
            return modelAndView;
        }

        //增加访问数量
        materialService.addMaterialVisit(materialId);

        modelAndView.addObject("material", material);
        modelAndView.addObject("userProfile", userProfile);
        modelAndView.setViewName("content/material/materialDetail");
        return modelAndView;
    }

    /**
     * 素材点赞
     * @param materialId
     * @param request
     * @return
     */
    @RequestMapping("/{materialId}/like.do")
    @ResponseBody
    public CommonResult materialLike(@PathVariable("materialId") Integer materialId, HttpServletRequest request){
        CommonResult result = null;
        Integer userId = userService.getUserIdByUserName(JWTUtils.getUserName(request.getCookies()));
        MaterialLike materialLike = new MaterialLike(materialId, userId, new Date());
        if(materialService.addMaterialLike(materialLike)){
            result = new CommonResult(0, "点赞成功", null);
        }else{
            result = new CommonResult(1, "只能点一次赞", null);
        }
        return result;
    }

    /**
     * 搜索素材
     * @param request
     * @return
     */
    @RequestMapping("/search.do")
    public ModelAndView materialSearch(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        //获取检索模式
        String mode = request.getParameter("search-mode");

        //获取检索关键字
        String keyword = request.getParameter("search-keyword");

        //获取检索结果
        List<Material> result = materialService.queryMaterial(mode, keyword);

        modelAndView.addObject("mode", mode);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("result", result);
        modelAndView.setViewName("content/material/materialSearchResult");
        return modelAndView;
    }




}
