package cn.edu.ncut.controller;

import cn.edu.ncut.domain.Article;
import cn.edu.ncut.domain.BannerImage;
import cn.edu.ncut.service.ArticleService;
import cn.edu.ncut.service.BannerImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private ArticleService articleService;

    @Resource
    private BannerImageService bannerImageService;

    /**
     * 首页加载文章列表
     * @return 文章列表 & 跳转首页
     */
    @RequestMapping("/index.do")
    public ModelAndView initArticleList(){
        ModelAndView modelAndView = new ModelAndView();

        //获取轮播图
        List<BannerImage> bannerImageList = bannerImageService.getAllImages();

        //获取文章列表
        List<Article> articleList = articleService.getArticleAll();

        modelAndView.addObject("articleList", articleList);
        modelAndView.addObject("bannerImages", bannerImageList);
        modelAndView.setViewName("../../index");
        return modelAndView;
    }
}
