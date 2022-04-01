package cn.edu.ncut.service;

import cn.edu.ncut.domain.Article;
import cn.edu.ncut.domain.Material;
import cn.edu.ncut.domain.MaterialLike;

import java.util.List;

public interface MaterialService {

    //============================== article表 ==============================

    //上传article
    Boolean materialUpload(Material material);

    //加载article
    List<Material> getMaterialAll();

    //根据article_id加载article
    Material getMaterialByMaterialId(Integer materialId);

    //增加访问数量
    Boolean addMaterialVisit(Integer materialId);

    //搜索案例
    List<Material> queryMaterial(String mode, String keyword);

    //============================== article_like表 ==============================

    //点赞
    Boolean addMaterialLike(MaterialLike materialLike);

    //取消点赞
    Boolean removeMaterialLike(MaterialLike materialLike);

    //加载点赞数据
    Integer getMaterialLikeCount(Integer materialId);

    //获取当前用户的点赞信息
    List<MaterialLike> getMaterialLikeByUserId(Integer userId);
}
