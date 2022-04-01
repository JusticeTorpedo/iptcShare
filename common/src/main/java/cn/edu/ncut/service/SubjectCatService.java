package cn.edu.ncut.service;

import cn.edu.ncut.domain.SubjectCat1st;
import cn.edu.ncut.domain.SubjectCat2st;

import java.util.List;

public interface SubjectCatService {
    //获取全部学科门类
    List<SubjectCat1st> getAllSubjectCat1st();

    //根据学科门类获取一级学科列表
    List<SubjectCat2st> getSubjectByParentId(Integer parentId);

    //从学科门类里，根据id获取name
    String getSubjectCat1stName(Integer id);

    //从一级学科里，根据id获取name
    String getSubjectCat2stName(Integer id);
}
