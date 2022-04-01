package cn.edu.ncut.dao;

import cn.edu.ncut.domain.Article;
import cn.edu.ncut.domain.ArticleComment;
import cn.edu.ncut.domain.ArticleLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {

    //============================== article表 ==============================

    //向表中插入一条数据
    Integer insertArticle(Article article);

    //获取表中所有的记录
    List<Article> selectAllArticle();

    //根据article_user_name查找记录
    List<Article> selectArticleByUserName(@Param("userName") String userName);

    //根据article_id查找记录
    Article selectArticleByArticleId(@Param("articleId") Integer articleId);

    //更新访问记录
    Integer updateVisitCount(@Param("articleId") Integer articleId);

    //更新点赞记录
    Integer updateLikeCount(@Param("likeCount") Integer likeCount, @Param("articleId") Integer articleId);

    //更新评论记录
    Integer updateCommentCount(@Param("commentCount") Integer commentCount, @Param("articleId") Integer articleId);

    //根据category_second搜索
    List<Article> selectArticleWithCatSecond(@Param("keyword") String keyword);

    //根据title搜索
    List<Article> selectArticleWithTitle(@Param("keyword") String keyword);

    //根据content搜索
    List<Article> selectArticleWithContent(@Param("keyword") String keyword);


    //============================== article_like表 ==============================

    //向表中插入一条数据
    Integer insertArticleLike(ArticleLike articleLike);

    //从表中删除一条数据
    Integer deleteArticleLike(ArticleLike articleLike);

    //根据article_id查找记录
    List<ArticleLike> selectLikeByArticleId(@Param("articleId") Integer articleId);

    //根据user_id查找记录
    List<ArticleLike> selectLikeByUserId(@Param("userId") Integer userId);

    //根据article_id和user_id查找记录
    ArticleLike selectLikeByArticleIdAndUserId(@Param("articleId") Integer articleId, @Param("userId") Integer userId);


    //============================== article_comment表 ==============================

    //向表中插入一条记录
    Integer insertArticleComment(ArticleComment articleComment);

    //根据article_id查找记录
    List<ArticleComment> selectCommentByArticleId(@Param("articleId") Integer articleId);

    //根据user_id查找记录
    List<ArticleComment> selectCommentByUserId(@Param("userId") Integer userId);

}
