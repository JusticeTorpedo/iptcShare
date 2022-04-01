package cn.edu.ncut.dao;

import cn.edu.ncut.domain.SubjectCat1st;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectCat1stDao {

    List<SubjectCat1st> selectAll();

    String selectNameById(@Param("id") Integer id);
}
