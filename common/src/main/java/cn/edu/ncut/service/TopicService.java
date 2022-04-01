package cn.edu.ncut.service;

import cn.edu.ncut.domain.*;

import java.util.List;

public interface TopicService {

    //============================== topic表 ==============================

    //发布topic
    Boolean topicUpload(Topic topic);

    //加载topic
    List<Topic> getTopicAll();

    //根据id加载topic
    Topic getTopicById(Integer topicId);

    //增加访问数量
    Boolean addTopicVisit(Integer topicId);

    //搜索topic
    List<Topic> queryTopic(String mode, String keyword);


    //============================== topic_like表 ==============================

    //点赞
    Boolean addTopicLike(TopicLike topicLike);

    //取消点赞
    Boolean removeTopicLike(TopicLike topicLike);

    //加载点赞数据
    Integer getTopicLikeCount(Integer topicId);

    //获取当前用户的点赞信息
    List<TopicLike> getTopicLikeByUserName(String userName);


    //============================== topic_answer表 ==============================

    //发布answer
    Boolean addTopicAnswer(TopicAnswer topicAnswer);

    //获取answer
    TopicAnswer getAnswerById(Integer answerId);

    //获取当前topic的answer
    List<TopicAnswer> getAnswerByTopicId(Integer topicId);

    //获取当前用户的answer
    List<TopicAnswer> getAnswerByUserName(String userName);


    //============================== answer_like表 ==============================

    //点赞answer
    Boolean addAnswerLike(AnswerLike answerLike);

    //取消点赞answer
    Boolean removeAnswerLike(AnswerLike answerLike);

    //加载点赞数据
    Integer getAnswerLikeCount(Integer answerId);

    //获取当前用户的点赞信息
    List<AnswerLike> getAnswerLikeByUserName(String userName);


    //============================== answer_comment表 ==============================

    //评论answer
    Boolean addAnswerComment(AnswerComment answerComment);

    //获取当前answer的评论
    List<AnswerComment> getCommentByAnswerId(Integer answerId);

    //获取当前用户的评论
    List<AnswerComment> getCommentByUserName(String userName);


    //============================== answer_comment_like表 ==============================

    //点赞comment
    Boolean addAnswerCommentLike(AnswerCommentLike answerCommentLike);

    //取消点赞comment
    Boolean removeAnswerCommentLike(AnswerCommentLike answerCommentLike);

    //加载点赞数据
    Integer getAnswerCommentLikeCount(Integer commentId);

    //获取当前用户的点赞信息
    List<AnswerCommentLike> getAnswerCommentLikeByUserName(String userName);
}
