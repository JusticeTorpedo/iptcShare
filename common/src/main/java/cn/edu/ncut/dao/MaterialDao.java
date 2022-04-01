package cn.edu.ncut.dao;

import cn.edu.ncut.domain.Article;
import cn.edu.ncut.domain.Material;
import cn.edu.ncut.domain.MaterialLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialDao {

    //============================== material表 ==============================

    //向表中插入一条数据
    Integer insertMaterial(Material material);

    //获取表中所有的记录
    List<Material> selectAllMaterial();

    //根据user_name查找记录
    List<Material> selectMaterialByUserName(@Param("userName") String userName);

    //根据material_id查找记录
    Material selectMaterialByMaterialId(@Param("materialId") Integer materialId);

    //更新访问记录
    Integer updateVisitCount(@Param("materialId") Integer materialId);

    //更新点赞记录
    Integer updateLikeCount(@Param("likeCount") Integer likeCount, @Param("materialId") Integer materialId);

    //根据category_second搜索
    List<Material> selectMaterialWithCatSecond(@Param("keyword") String keyword);

    //根据title搜索
    List<Material> selectMaterialWithTitle(@Param("keyword") String keyword);

    //根据content搜索
    List<Material> selectMaterialWithContent(@Param("keyword") String keyword);

    //============================== material_like表 ==============================

    //向表中插入一条数据
    Integer insertMaterialLike(MaterialLike materialLike);

    //从表中删除一条数据
    Integer deleteMaterialLike(MaterialLike materialLike);

    //根据material_id查找记录
    List<MaterialLike> selectLikeByMaterialId(@Param("materialId") Integer materialId);

    //根据user_id查找记录
    List<MaterialLike> selectLikeByUserId(@Param("userId") Integer userId);

    //根据material_id和user_id查找记录
    MaterialLike selectLikeByMaterialIdAndUserId(@Param("materialId") Integer materialId, @Param("userId") Integer userId);

}
