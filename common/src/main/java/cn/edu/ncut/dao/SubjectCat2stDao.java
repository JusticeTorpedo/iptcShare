package cn.edu.ncut.dao;

import cn.edu.ncut.domain.SubjectCat2st;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectCat2stDao {
    List<SubjectCat2st> selectSubjectByParentId(@Param("parentId") Integer parentId);

    String selectNameById(@Param("id") Integer id);
}
