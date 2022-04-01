package cn.edu.ncut.service.impl;

import cn.edu.ncut.dao.TopicDao;
import cn.edu.ncut.domain.*;
import cn.edu.ncut.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicDao topicDao;

    //============================== topic表 ==============================

    @Override
    public Boolean topicUpload(Topic topic) {
        if (topicDao.insertTopic(topic) == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<Topic> getTopicAll() {
        return topicDao.selectTopicAll();
    }

    @Override
    public Topic getTopicById(Integer topicId) {
        return topicDao.selectTopicById(topicId);
    }

    @Override
    public Boolean addTopicVisit(Integer topicId) {
        if (1 == topicDao.updateVisitCount(topicId)){
            return true;
        }
        return false;
    }

    @Override
    public List<Topic> queryTopic(String mode, String keyword) {
        List<Topic> result;
        if("标题".equals(mode)){
            //按照标题搜索
            result = topicDao.selectTopicWithTitle(keyword);
        }else{
            //按照内容搜索
            result = topicDao.selectTopicWithContent(keyword);
        }
        return result;
    }


    //============================== topic_like表 ==============================

    @Override
    public Boolean addTopicLike(TopicLike topicLike) {
        Integer topicId = topicLike.getTopicId();
        String userName = topicLike.getUserName();
        TopicLike temp = topicDao.selectTopicLikeByTopicAndUser(topicId, userName);
        if(null != temp){
            //重复点赞
            return false;
        }else if(1 != topicDao.insertTopicLike(topicLike)){
            //插入记录失败
            return false;
        }
        //更新topic表的like_count
        topicDao.updateLikeCount(topicDao.selectTopicLikeByTopicId(topicId).size(), topicId);
        return true;
    }

    @Override
    public Boolean removeTopicLike(TopicLike topicLike) {
        Integer topicId = topicLike.getTopicId();
        String userName = topicLike.getUserName();
        TopicLike temp = topicDao.selectTopicLikeByTopicAndUser(topicId, userName);
        if(null != temp){
            //记录不存在
            return false;
        }else if(1 != topicDao.deleteTopicLike(topicLike)){
            //删除记录失败
            return false;
        }
        //更新topic表的like_count
        topicDao.updateLikeCount(topicDao.selectTopicLikeByTopicId(topicId).size(), topicId);
        return true;
    }

    @Override
    public Integer getTopicLikeCount(Integer topicId) {
        return topicDao.selectTopicLikeByTopicId(topicId).size();
    }

    @Override
    public List<TopicLike> getTopicLikeByUserName(String userName) {
        return topicDao.selectTopicLikeByUserName(userName);
    }


    //============================== topic_answer表 ==============================

    @Override
    public Boolean addTopicAnswer(TopicAnswer topicAnswer) {
        if (1 == topicDao.insertTopicAnswer(topicAnswer)){
            Integer topicId = topicAnswer.getTopicId();
            topicDao.updateAnswerCount(topicDao.selectAnswerByTopicId(topicId).size(), topicId);
            return true;
        }
        return false;
    }

    @Override
    public TopicAnswer getAnswerById(Integer answerId) {
        return topicDao.selectAnswerById(answerId);
    }

    @Override
    public List<TopicAnswer> getAnswerByTopicId(Integer topicId) {
        return topicDao.selectAnswerByTopicId(topicId);
    }

    @Override
    public List<TopicAnswer> getAnswerByUserName(String userName) {
        return topicDao.selectAnswerByUserName(userName);
    }


    //============================== answer_like表 ==============================

    @Override
    public Boolean addAnswerLike(AnswerLike answerLike) {
        Integer answerId = answerLike.getAnswerId();
        String userName = answerLike.getUserName();
        AnswerLike temp = topicDao.selectAnswerLikeByAnswerAndUser(answerId, userName);
        if(null != temp){
            //重复点赞
            return false;
        }else if(1 != topicDao.insertAnswerLike(answerLike)){
            return false;
        }
        //更新topic_answer表的like_count
        topicDao.updateAnswerLikeCount(topicDao.selectAnswerLikeByAnswerId(answerId).size(), answerId);
        return true;
    }

    @Override
    public Boolean removeAnswerLike(AnswerLike answerLike) {
        Integer answerId = answerLike.getAnswerId();
        String userName = answerLike.getUserName();
        AnswerLike temp = topicDao.selectAnswerLikeByAnswerAndUser(answerId, userName);
        if (null == temp){
            return false;
        }else if (1 != topicDao.deleteAnswerLike(answerLike)){
            return false;
        }
        //更新topic_answer表的like_count
        topicDao.updateAnswerLikeCount(topicDao.selectAnswerLikeByAnswerId(answerId).size(), answerId);
        return true;
    }

    @Override
    public Integer getAnswerLikeCount(Integer answerId) {
        return topicDao.selectAnswerLikeByAnswerId(answerId).size();
    }

    @Override
    public List<AnswerLike> getAnswerLikeByUserName(String userName) {
        return topicDao.selectAnswerLikeByUserName(userName);
    }


    //============================== answer_comment表 ==============================

    @Override
    public Boolean addAnswerComment(AnswerComment answerComment) {
        if(1 == topicDao.insertAnswerComment(answerComment)){
            Integer answerId = answerComment.getAnswerId();
            topicDao.updateCommentCount(topicDao.selectCommentByAnswerId(answerId).size(), answerId);
            return true;
        }
        return false;
    }

    @Override
    public List<AnswerComment> getCommentByAnswerId(Integer answerId) {
        return topicDao.selectCommentByAnswerId(answerId);
    }

    @Override
    public List<AnswerComment> getCommentByUserName(String userName) {
        return topicDao.selectCommentByUserName(userName);
    }


    //============================== answer_comment_like表 ==============================

    @Override
    public Boolean addAnswerCommentLike(AnswerCommentLike answerCommentLike) {
        Integer commentId = answerCommentLike.getCommentId();
        String userName = answerCommentLike.getUserName();
        AnswerCommentLike temp = topicDao.selectAnswerCommentLikeByCommentAndUser(commentId, userName);
        if (null != temp){
            //重复点赞
            return false;
        }else if (1 != topicDao.insertAnswerCommentLike(answerCommentLike)){
            //插入记录失败
            return false;
        }
        //更新answer_comment表的like_count
        topicDao.updateCommentLikeCount(topicDao.selectAnswerCommentLikeByCommentId(commentId).size(), commentId);
        return true;
    }

    @Override
    public Boolean removeAnswerCommentLike(AnswerCommentLike answerCommentLike) {
        Integer commentId = answerCommentLike.getCommentId();
        String userName = answerCommentLike.getUserName();
        AnswerCommentLike temp = topicDao.selectAnswerCommentLikeByCommentAndUser(commentId, userName);
        if (null != temp){
            //点赞记录不存在
            return false;
        }else if (1 != topicDao.deleteAnswerCommentLike(answerCommentLike)){
            //删除记录失败
            return false;
        }
        //更新answer_comment表的like_count
        topicDao.updateCommentLikeCount(topicDao.selectAnswerCommentLikeByCommentId(commentId).size(), commentId);
        return true;
    }

    @Override
    public Integer getAnswerCommentLikeCount(Integer commentId) {
        return topicDao.selectAnswerCommentLikeByCommentId(commentId).size();
    }

    @Override
    public List<AnswerCommentLike> getAnswerCommentLikeByUserName(String userName) {
        return topicDao.selectAnswerCommentLikeByUserName(userName);
    }
}
