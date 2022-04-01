package cn.edu.ncut.domain;

import java.util.Date;

public class Article {
    private Integer articleId;
    private String articleUserName;
    private String articleCategoryFirst;
    private String articleCategorySecond;
    private String articleTitle;
    private String articleContent;
    private Date articleCreateTime;
    private Date articleUpdateTime;
    private Integer articleIsAvailable;
    private Integer articleAllowComment;
    private Integer articleVisitCount;
    private Integer articleLikeCount;
    private Integer articleCommentCount;

    public Article() {
    }

    public Article(String articleUserName, String articleCategoryFirst, String articleCategorySecond, String articleTitle, String articleContent, Date articleCreateTime, Date articleUpdateTime, Integer articleIsAvailable, Integer articleAllowComment, Integer articleVisitCount, Integer articleLikeCount, Integer articleCommentCount) {
        this.articleUserName = articleUserName;
        this.articleCategoryFirst = articleCategoryFirst;
        this.articleCategorySecond = articleCategorySecond;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleCreateTime = articleCreateTime;
        this.articleUpdateTime = articleUpdateTime;
        this.articleIsAvailable = articleIsAvailable;
        this.articleAllowComment = articleAllowComment;
        this.articleVisitCount = articleVisitCount;
        this.articleLikeCount = articleLikeCount;
        this.articleCommentCount = articleCommentCount;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleUserName() {
        return articleUserName;
    }

    public void setArticleUserName(String articleUserName) {
        this.articleUserName = articleUserName;
    }

    public String getArticleCategoryFirst() {
        return articleCategoryFirst;
    }

    public void setArticleCategoryFirst(String articleCategoryFirst) {
        this.articleCategoryFirst = articleCategoryFirst;
    }

    public String getArticleCategorySecond() {
        return articleCategorySecond;
    }

    public void setArticleCategorySecond(String articleCategorySecond) {
        this.articleCategorySecond = articleCategorySecond;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public Integer getArticleIsAvailable() {
        return articleIsAvailable;
    }

    public void setArticleIsAvailable(Integer articleIsAvailable) {
        this.articleIsAvailable = articleIsAvailable;
    }

    public Integer getArticleAllowComment() {
        return articleAllowComment;
    }

    public void setArticleAllowComment(Integer articleAllowComment) {
        this.articleAllowComment = articleAllowComment;
    }

    public Integer getArticleVisitCount() {
        return articleVisitCount;
    }

    public void setArticleVisitCount(Integer articleVisitCount) {
        this.articleVisitCount = articleVisitCount;
    }

    public Integer getArticleLikeCount() {
        return articleLikeCount;
    }

    public void setArticleLikeCount(Integer articleLikeCount) {
        this.articleLikeCount = articleLikeCount;
    }

    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

}
