package cn.edu.ncut.dao;

import cn.edu.ncut.domain.BannerImage;

import java.util.List;

public interface BannerImageDao {
    //从banner_image中获取所有记录
    List<BannerImage> selectAllImages();
}
