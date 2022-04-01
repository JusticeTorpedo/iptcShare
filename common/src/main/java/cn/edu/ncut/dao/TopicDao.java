package cn.edu.ncut.dao;

import cn.edu.ncut.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicDao {

    //============================== topic表 ==============================

    //插入一条记录
    Integer insertTopic(Topic topic);

    //检索所有记录
    List<Topic> selectTopicAll();

    //根据id检索记录
    Topic selectTopicById(@Param("id") Integer id);

    //根据user_name检索记录
    List<Topic> selectTopicByUserName(@Param("userName") String userName);

    //更新访问数量
    Integer updateVisitCount(@Param("id") Integer id);

    //更新点赞数量
    Integer updateLikeCount(@Param("likeCount") Integer likeCount, @Param("id") Integer id);

    //更新回答数量
    Integer updateAnswerCount(@Param("answerCount") Integer answerCount, @Param("id") Integer id);

    //根据title搜索
    List<Topic> selectTopicWithTitle(@Param("keyword") String keyword);

    //根据content搜索
    List<Topic> selectTopicWithContent(@Param("keyword") String keyword);


    //============================== topic_like表 ==============================

    //插入一条记录
    Integer insertTopicLike(TopicLike topicLike);

    //删除一条记录
    Integer deleteTopicLike(TopicLike topicLike);

    //根据topic_id检索记录
    List<TopicLike> selectTopicLikeByTopicId(@Param("topicId") Integer topicId);

    //根据user_name检索记录
    List<TopicLike> selectTopicLikeByUserName(@Param("userName") String userName);

    //根据topic_id和user_name检索记录
    TopicLike selectTopicLikeByTopicAndUser(@Param("topicId") Integer topicId, @Param("userName") String userName);


    //============================== topic_answer表 ==============================

    //插入一条记录
    Integer insertTopicAnswer(TopicAnswer topicAnswer);

    //根据id检索记录
    TopicAnswer selectAnswerById(@Param("id") Integer id);

    //根据topic_id检索记录
    List<TopicAnswer> selectAnswerByTopicId(@Param("topicId") Integer topicId);

    //根据user_name检索记录
    List<TopicAnswer> selectAnswerByUserName(@Param("userName") String userName);

    //更新点赞数量
    Integer updateAnswerLikeCount(@Param("likeCount") Integer likeCount, @Param("id") Integer id);

    //更新评论数量
    Integer updateCommentCount(@Param("commentCount") Integer commentCount, @Param("id") Integer id);

    //根据content搜索
    List<TopicAnswer> selectAnswerWithContent(@Param("keyword") String keyword);

    //============================== answer_like表 ==============================

    //插入一条记录
    Integer insertAnswerLike(AnswerLike answerLike);

    //删除一条记录
    Integer deleteAnswerLike(AnswerLike answerLike);

    //根据answer_id检索记录
    List<AnswerLike> selectAnswerLikeByAnswerId(@Param("answerId") Integer answerId);

    //根据user_name检索记录
    List<AnswerLike> selectAnswerLikeByUserName(@Param("userName") String userName);

    //根据topic_id和user_name检索记录
    AnswerLike selectAnswerLikeByAnswerAndUser(@Param("answerId") Integer answerId, @Param("userName") String userName);

    //============================== answer_comment表 ==============================

    //插入一条记录
    Integer insertAnswerComment(AnswerComment answerComment);

    //根据answer_id检索记录
    List<AnswerComment> selectCommentByAnswerId(@Param("answerId") Integer answerId);

    //根据user_name检索记录
    List<AnswerComment> selectCommentByUserName(@Param("userName") String userName);

    //更新like_count
    Integer updateCommentLikeCount(@Param("likeCount") Integer likeCount,@Param("id") Integer id);

    //============================== answer_comment_like表 ==============================

    //插入一条记录
    Integer insertAnswerCommentLike(AnswerCommentLike answerCommentLike);

    //删除一条记录
    Integer deleteAnswerCommentLike(AnswerCommentLike answerCommentLike);

    //根据comment_id检索记录
    List<AnswerCommentLike> selectAnswerCommentLikeByCommentId(@Param("commentId") Integer commentId);

    //根据user_name检索记录
    List<AnswerCommentLike> selectAnswerCommentLikeByUserName(@Param("userName") String userName);

    //根据topic_id和user_name检索记录
    AnswerCommentLike selectAnswerCommentLikeByCommentAndUser(@Param("commentId") Integer commentId, @Param("userName") String userName);

}
