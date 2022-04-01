package cn.edu.ncut.service.impl;

import cn.edu.ncut.dao.MaterialDao;
import cn.edu.ncut.domain.Article;
import cn.edu.ncut.domain.Material;
import cn.edu.ncut.domain.MaterialLike;
import cn.edu.ncut.service.MaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Resource
    private MaterialDao materialDao;

    //============================== article表 ==============================

    @Override
    public Boolean materialUpload(Material material) {

        if(1 == materialDao.insertMaterial(material)){
            return true;
        }

        return false;
    }

    @Override
    public List<Material> getMaterialAll() {
        return materialDao.selectAllMaterial();
    }

    @Override
    public Material getMaterialByMaterialId(Integer materialId) {
        return materialDao.selectMaterialByMaterialId(materialId);
    }

    @Override
    public Boolean addMaterialVisit(Integer materialId) {
        if(1 == materialDao.updateVisitCount(materialId)){
            return true;
        }
        return false;
    }

    @Override
    public List<Material> queryMaterial(String mode, String keyword) {
        List<Material> result;
        if ("一级学科".equals(mode)){
            //一级学科搜索
            result = materialDao.selectMaterialWithCatSecond(keyword);
        }else if ("标题".equals(mode)){
            //标题搜索
            result = materialDao.selectMaterialWithTitle(keyword);
        }else {
            //内容搜索
            result = materialDao.selectMaterialWithContent(keyword);
        }
        return result;
    }

    //============================== article_like表 ==============================

    @Override
    public Boolean addMaterialLike(MaterialLike materialLike) {
        Integer materialId = materialLike.getMaterialId();
        Integer userId = materialLike.getUserId();
        MaterialLike temp = materialDao.selectLikeByMaterialIdAndUserId(materialId, userId);
        if(temp != null){
            //判断是否重复点赞
            return false;
        }else if(1 != materialDao.insertMaterialLike(materialLike)){
            //判断是否点赞成功
            return false;
        }
        //更新material表的like_count
        materialDao.updateLikeCount(materialDao.selectLikeByMaterialId(materialId).size(), materialId);
        return true;
    }

    @Override
    public Boolean removeMaterialLike(MaterialLike materialLike) {
        Integer materialId = materialLike.getMaterialId();
        Integer userId = materialLike.getUserId();
        MaterialLike temp = materialDao.selectLikeByMaterialIdAndUserId(materialId, userId);
        if(temp == null){
            return false;
        }else if(1 != materialDao.deleteMaterialLike(materialLike)){
            return false;
        }
        //更新material表的like_count
        materialDao.updateLikeCount(materialDao.selectLikeByMaterialId(materialId).size(), materialId);
        return true;
    }

    @Override
    public Integer getMaterialLikeCount(Integer materialId) {
        List<MaterialLike> list = materialDao.selectLikeByMaterialId(materialId);
        return list.size();
    }

    @Override
    public List<MaterialLike> getMaterialLikeByUserId(Integer userId) {
        return materialDao.selectLikeByUserId(userId);
    }

}
