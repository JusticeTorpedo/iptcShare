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
@RequestMapping("/topic")
public class TopicController {
    @Resource
    private TopicService topicService;

    @Resource
    private UserService userService;

    @Resource
    private UserProfileService userProfileService;


    //============================== topic ==============================

    /**
     * 发布话题
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView topicIndex(){
        ModelAndView modelAndView = new ModelAndView();

        //获取话题列表
        List<Topic> topicList = topicService.getTopicAll();

        modelAndView.addObject("topicList", topicList);
        modelAndView.setViewName("content/topic/topicIndex");
        return modelAndView;
    }

    /**
     * JWTInterceptor会对其进行preHandle检查
     * 上传
     * @param request
     * @return
     */
    @RequestMapping("/upload.do")
    public ModelAndView topicUpload(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        String userName = JWTUtils.getUserName(request.getCookies());
        String title = request.getParameter("topicTitle");
        String content = request.getParameter("topicContent");

        Topic topic = new Topic(
                userName,
                title,
                content,
                1,
                0,
                0,
                0,
                new Date()
        );

        if (topicService.topicUpload(topic)){
            System.out.println("上传成功");
        }

        //这里需要改成跳转到【我的素材】页面
        modelAndView.setViewName("user/profile");
        return modelAndView;
    }

    /**
     * JWTInterceptor会对其进行preHandle检查
     * 上传话题中的图片
     * @param request
     * @param file
     * @return JSONObject
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
        String rootPath = "D:\\Uploads\\topicImages";

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
                "/topicImages/" + date.get(Calendar.YEAR)
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
     * 话题详情
     * @param topicId
     * @return ModelAndView
     */
    @RequestMapping("/{topicId}/view.do")
    public ModelAndView topicDetailPage(@PathVariable("topicId") Integer topicId){
        ModelAndView modelAndView = new ModelAndView();

        //获取topic
        Topic topic = topicService.getTopicById(topicId);
        UserProfile userProfile = userProfileService.getProfile(topic.getUserName());
        List<TopicAnswer> answerList = topicService.getAnswerByTopicId(topicId);

        //若请求的topic不存在
        if(topic == null){
            modelAndView.setViewName("error/404");
            return modelAndView;
        }

        //增加访问数量
        topicService.addTopicVisit(topicId);

        modelAndView.addObject("topic", topic);
        modelAndView.addObject("userProfile", userProfile);
        modelAndView.addObject("answerList", answerList);
        modelAndView.setViewName("content/topic/topicDetail");
        return modelAndView;
    }

    /**
     * 话题点赞
     * @param topicId
     * @param request
     * @return
     */
    @RequestMapping("/{topicId}/like.do")
    @ResponseBody
    public CommonResult topicLike(@PathVariable("topicId") Integer topicId, HttpServletRequest request){
        CommonResult result = null;
        String userName = JWTUtils.getUserName(request.getCookies());
        TopicLike topicLike = new TopicLike(topicId, userName, new Date());
        if(topicService.addTopicLike(topicLike)){
            result = new CommonResult(0, "点赞成功", null);
        }else{
            result = new CommonResult(1, "只能点一次赞", null);
        }
        return result;
    }

    /**
     * 搜索话题
     * @param request
     * @return
     */
    @RequestMapping("/search.do")
    public ModelAndView topicSearch(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        //获取检索模式
        String mode = request.getParameter("search-mode");

        //获取检索关键字
        String keyword = request.getParameter("search-keyword");

        //获取检索结果
        List<Topic> topicList = topicService.queryTopic(mode, keyword);

        modelAndView.addObject("mode", mode);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("topicList", topicList);
        modelAndView.setViewName("content/topic/topicSearchResult");
        return modelAndView;
    }


    //============================== answer ==============================

    /**
     * 回答
     * @param topicId
     * @param request
     * @return
     */
    @RequestMapping("/{topicId}/answer.do")
    @ResponseBody
    public CommonResult answerUpload(@PathVariable("topicId") Integer topicId, HttpServletRequest request){
        CommonResult result = null;
        String userName = JWTUtils.getUserName(request.getCookies());
        Integer userId = userService.getUserIdByUserName(userName);
        String content = request.getParameter("content");
        TopicAnswer topicAnswer = new TopicAnswer(topicId, userName, content,1,0,0, new Date());
        if(topicService.addTopicAnswer(topicAnswer)){
            result = new CommonResult(0, "发送成功", null);
        }else{
            result = new CommonResult(1, "发送失败", null);
        }
        return result;
    }

    /**
     * 上传回答中的图片
     * JWTInterceptor会对其进行preHandle检查
     * @param request
     * @param file
     * @return JSONObject
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "answer/image/upload.do")
    public String uploadImage(HttpServletRequest request,@Param("file") MultipartFile file) throws IOException {

        String username = JWTUtils.getUserName(request.getCookies());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());

        //服务器上使用
        // String rootPath =request.getServletContext().getRealPath("/resource/uploads/");//target的目录

        //本地使用
        String rootPath = "D:\\Uploads\\answerImages";

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
                "/answerImages/" + date.get(Calendar.YEAR)
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
     * 查看回答
     * @return
     */
    @RequestMapping("/{topicId}/{answerId}/view.do")
    public ModelAndView answerDetailPage(@PathVariable("answerId") Integer answerId){
        ModelAndView modelAndView = new ModelAndView();

        //获取answer
        TopicAnswer answer = topicService.getAnswerById(answerId);
        Topic topic = topicService.getTopicById(answer.getTopicId());
        UserProfile userProfile = userProfileService.getProfile(answer.getUserName());
        List<AnswerComment> commentList = topicService.getCommentByAnswerId(answerId);

        //若请求的answer不存在
        if(answer == null){
            modelAndView.setViewName("error/404");
            return modelAndView;
        }

        modelAndView.addObject("answer", answer);
        modelAndView.addObject("topic", topic);
        modelAndView.addObject("userProfile", userProfile);
        modelAndView.addObject("commentList", commentList);
        modelAndView.setViewName("content/topic/answerDetail");
        return modelAndView;
    }

    /**
     * 点赞回答
     * @param answerId
     * @param request
     * @return
     */
    @RequestMapping("/{topicId}/{answerId}/like.do")
    @ResponseBody
    public CommonResult answerLike(@PathVariable("answerId") Integer answerId, HttpServletRequest request){
        CommonResult result = null;
        String userName = JWTUtils.getUserName(request.getCookies());
        AnswerLike answerLike = new AnswerLike(answerId, userName, new Date());
        if(topicService.addAnswerLike(answerLike)){
            result = new CommonResult(0, "点赞成功", null);
        }else{
            result = new CommonResult(1, "只能点一次赞", null);
        }
        return result;
    }

    //============================== comment ==============================

    /**
     * 评论
     * @param answerId
     * @param request
     * @return
     */
    @RequestMapping("/{topicId}/{answerId}/comment.do")
    @ResponseBody
    public CommonResult answerComment(@PathVariable("answerId") Integer answerId, HttpServletRequest request){
        CommonResult result = null;
        String userName = JWTUtils.getUserName(request.getCookies());
        String content = request.getParameter("content");
        AnswerComment answerComment = new AnswerComment(answerId, userName, content,0, new Date());
        if(topicService.addAnswerComment(answerComment)){
            result = new CommonResult(0, "评论成功", null);
        }else{
            result = new CommonResult(1, "评论失败", null);
        }
        return result;
    }

    /**
     * 点赞评论
     * @param commentId
     * @param request
     * @return
     */
    @RequestMapping("/{topicId}/{answerId}/{commentId}/like.do")
    @ResponseBody
    public CommonResult answerCommentLike(@PathVariable("commentId") Integer commentId, HttpServletRequest request){
        CommonResult result = null;
        String userName = JWTUtils.getUserName(request.getCookies());
        AnswerCommentLike commentLike = new AnswerCommentLike(commentId, userName, new Date());
        if(topicService.addAnswerCommentLike(commentLike)){
            result = new CommonResult(0, "点赞成功", null);
        }else{
            result = new CommonResult(1, "只能点一次赞", null);
        }
        return result;
    }

}
