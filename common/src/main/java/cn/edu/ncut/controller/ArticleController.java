package cn.edu.ncut.controller;

import cn.edu.ncut.domain.Article;
import cn.edu.ncut.domain.ArticleComment;
import cn.edu.ncut.domain.ArticleLike;
import cn.edu.ncut.domain.UserProfile;
import cn.edu.ncut.service.ArticleService;
import cn.edu.ncut.service.SubjectCatService;
import cn.edu.ncut.service.UserProfileService;
import cn.edu.ncut.service.UserService;
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
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;

    @Resource
    private UserProfileService userProfileService;

    @Resource
    private SubjectCatService subjectCatService;


    @RequestMapping("/index.do")
    public ModelAndView articleIndex(){
        ModelAndView modelAndView = new ModelAndView();

        //获取文章列表
        List<Article> articleList = articleService.getArticleAll();

        modelAndView.addObject("articleList", articleList);
        modelAndView.setViewName("content/article/articleIndex");
        return modelAndView;
    }

    /**
     * JWTInterceptor会对其进行preHandle检查
     * 上传文章
     * @param request
     * @return
     */
    @RequestMapping("/upload.do")
    public ModelAndView articleUpload(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        String userName = JWTUtils.getUserName(request.getCookies());
        String catFirst = subjectCatService.getSubjectCat1stName(Integer.valueOf(request.getParameter("subjectCat1st")));
        String catSecond = subjectCatService.getSubjectCat2stName(Integer.valueOf(request.getParameter("subjectCat2st")));
        String title = request.getParameter("articleTitle");
        String content = request.getParameter("articleContent");
        Integer allowComment = 0;
        if(request.getParameter("allowComment") != null){
            allowComment = 1;
        }

        Article article = new Article(
                userName,
                catFirst,
                catSecond,
                title,
                content,
                new Date(),
                new Date(),
                1,
                allowComment,
                0,
                0,
                0
        );

        if (articleService.articleUpload(article)){
            System.out.println("上传成功");
        }

        //这里需要改成跳转到【我的素材】页面
        modelAndView.setViewName("user/profile");
        return modelAndView;
    }

    /**
     * JWTInterceptor会对其进行preHandle检查
     * 上传文章中的图片
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
        String rootPath = "D:\\Uploads\\articleImages";

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
                "/articleImages/" + date.get(Calendar.YEAR)
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
     * 文章详情
     * @param articleId
     * @return ModelAndView
     */
    @RequestMapping("/{articleId}/view.do")
    public ModelAndView articleDetailPage(@PathVariable("articleId") Integer articleId){
        ModelAndView modelAndView = new ModelAndView();

        //获取article
        Article article = articleService.getArticleByArticleId(articleId);
        UserProfile userProfile = userProfileService.getProfile(article.getArticleUserName());
        List<ArticleComment> commentList = articleService.getCommentByArticleId(articleId);

        //若请求的article不存在
        if(article == null){
            modelAndView.setViewName("error/404");
            return modelAndView;
        }

        //增加访问数量
        articleService.addArticleVisit(articleId);

        modelAndView.addObject("article", article);
        modelAndView.addObject("userProfile", userProfile);
        modelAndView.addObject("commentList", commentList);
        modelAndView.setViewName("content/article/articleDetail");
        return modelAndView;
    }

    /**
     * 案例点赞
     * @param articleId
     * @param request
     * @return
     */
    @RequestMapping("/{articleId}/like.do")
    @ResponseBody
    public CommonResult articleLike(@PathVariable("articleId") Integer articleId, HttpServletRequest request){
        CommonResult result = null;
        Integer userId = userService.getUserIdByUserName(JWTUtils.getUserName(request.getCookies()));
        ArticleLike articleLike = new ArticleLike(articleId, userId, new Date());
        if(articleService.addArticleLike(articleLike)){
            result = new CommonResult(0, "点赞成功", null);
        }else{
            result = new CommonResult(1, "只能点一次赞", null);
        }
        return result;
    }

    /**
     * 案例评论
     * @param articleId
     * @param request
     * @return
     */
    @RequestMapping("/{articleId}/comment.do")
    @ResponseBody
    public CommonResult articleComment(@PathVariable("articleId") Integer articleId, HttpServletRequest request){
        CommonResult result = null;
        String userName = JWTUtils.getUserName(request.getCookies());
        Integer userId = userService.getUserIdByUserName(userName);
        String content = request.getParameter("content");
        ArticleComment articleComment = new ArticleComment(articleId, userId, userName, content, new Date());
        if(articleService.addArticleComment(articleComment)){
            result = new CommonResult(0, "评论成功", null);
        }else{
            result = new CommonResult(1, "评论失败", null);
        }
        return result;
    }

    /**
     * 搜索案例
     * @param request
     * @return
     */
    @RequestMapping("/search.do")
    public ModelAndView articleSearch(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        //获取检索模式
        String mode = request.getParameter("search-mode");

        //获取检索关键字
        String keyword = request.getParameter("search-keyword");

        //获取检索结果
        List<Article> result = articleService.queryArticle(mode, keyword);

        modelAndView.addObject("mode", mode);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("result", result);
        modelAndView.setViewName("content/article/articleSearchResult");
        return modelAndView;
    }
}
