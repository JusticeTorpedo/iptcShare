package cn.edu.ncut.service.impl;

import cn.edu.ncut.dao.ArticleDao;
import cn.edu.ncut.domain.Article;
import cn.edu.ncut.domain.ArticleComment;
import cn.edu.ncut.domain.ArticleLike;
import cn.edu.ncut.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    //============================== article表 ==============================

    @Override
    public Boolean articleUpload(Article article) {

        if(articleDao.insertArticle(article) == 1){
            return true;
        }

        return false;
    }

    @Override
    public List<Article> getArticleAll() {
        return articleDao.selectAllArticle();
    }

    @Override
    public Article getArticleByArticleId(Integer articleId) {
        return articleDao.selectArticleByArticleId(articleId);
    }

    @Override
    public Boolean addArticleVisit(Integer articleId) {
        if(1 == articleDao.updateVisitCount(articleId)){
            return true;
        }
        return false;
    }

    @Override
    public List<Article> queryArticle(String mode, String keyword) {
        List<Article> result;
        if ("一级学科".equals(mode)){
            //一级学科搜索
            result = articleDao.selectArticleWithCatSecond(keyword);
        }else if ("标题".equals(mode)){
            //标题搜索
            result = articleDao.selectArticleWithTitle(keyword);
        }else {
            //内容搜索
            result = articleDao.selectArticleWithContent(keyword);
        }
        return result;
    }

    //============================== article_like表 ==============================

    @Override
    public Boolean addArticleLike(ArticleLike articleLike) {
        Integer articleId = articleLike.getArticleId();
        Integer userId = articleLike.getUserId();
        ArticleLike temp = articleDao.selectLikeByArticleIdAndUserId(articleId, userId);
        if(temp != null){
            //判断是否重复点赞
            return false;
        }else if(1 != articleDao.insertArticleLike(articleLike)){
            //判断是否点赞成功
            return false;
        }
        //更新article表的article_like_count
        articleDao.updateLikeCount(articleDao.selectLikeByArticleId(articleId).size(), articleId);
        return true;
    }

    @Override
    public Boolean removeArticleLike(ArticleLike articleLike) {
        Integer articleId = articleLike.getArticleId();
        Integer userId = articleLike.getUserId();
        ArticleLike temp = articleDao.selectLikeByArticleIdAndUserId(articleId, userId);
        if(temp == null){
            return false;
        }else if(1 != articleDao.deleteArticleLike(articleLike)){
            return false;
        }
        //更新article表的article_like_count
        articleDao.updateLikeCount(articleDao.selectLikeByArticleId(articleId).size(), articleId);
        return true;
    }

    @Override
    public Integer getArticleLikeCount(Integer articleId) {
        List<ArticleLike> list = articleDao.selectLikeByArticleId(articleId);
        return list.size();
    }

    @Override
    public List<ArticleLike> getArticleLikeByUserId(Integer userId) {
        return articleDao.selectLikeByUserId(userId);
    }


    //============================== article_comment表 ==============================

    @Override
    public Boolean addArticleComment(ArticleComment articleComment) {
        if(1 == articleDao.insertArticleComment(articleComment)){
            Integer articleId = articleComment.getArticleId();
            articleDao.updateCommentCount(articleDao.selectCommentByArticleId(articleId).size(), articleId);
            return true;
        }
        return false;
    }

    @Override
    public List<ArticleComment> getCommentByArticleId(Integer articleId) {
        return articleDao.selectCommentByArticleId(articleId);
    }

    @Override
    public List<ArticleComment> getCommentByUserId(Integer userId) {
        return articleDao.selectCommentByUserId(userId);
    }
}
