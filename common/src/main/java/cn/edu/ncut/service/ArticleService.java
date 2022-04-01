package cn.edu.ncut.service;

import cn.edu.ncut.domain.Article;
import cn.edu.ncut.domain.ArticleComment;
import cn.edu.ncut.domain.ArticleLike;

import java.util.List;

public interface ArticleService {

    //============================== article表 ==============================

    //上传article
    Boolean articleUpload(Article article);

    //加载article
    List<Article> getArticleAll();

    //根据article_id加载article
    Article getArticleByArticleId(Integer articleId);

    //增加访问数量
    Boolean addArticleVisit(Integer articleId);

    //搜索案例
    List<Article> queryArticle(String mode, String keyword);

    //============================== article_like表 ==============================

    //点赞
    Boolean addArticleLike(ArticleLike articleLike);

    //取消点赞
    Boolean removeArticleLike(ArticleLike articleLike);

    //加载点赞数据
    Integer getArticleLikeCount(Integer articleId);

    //获取当前用户的点赞信息
    List<ArticleLike> getArticleLikeByUserId(Integer userId);

    //============================== article_comment表 ==============================

    //评论
    Boolean addArticleComment(ArticleComment articleComment);

    //获取当前案例的评论
    List<ArticleComment> getCommentByArticleId(Integer articleId);

    //获取当前用户的评论
    List<ArticleComment> getCommentByUserId(Integer userId);
}
