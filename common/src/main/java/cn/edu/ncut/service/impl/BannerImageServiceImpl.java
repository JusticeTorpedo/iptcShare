package cn.edu.ncut.service.impl;

import cn.edu.ncut.dao.BannerImageDao;
import cn.edu.ncut.domain.BannerImage;
import cn.edu.ncut.service.BannerImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BannerImageServiceImpl implements BannerImageService {

    @Resource
    private BannerImageDao bannerImageDao;

    @Override
    public List<BannerImage> getAllImages() {
        return bannerImageDao.selectAllImages();
    }
}
