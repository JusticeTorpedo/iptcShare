package cn.edu.ncut.service.impl;

import cn.edu.ncut.dao.SubjectCat1stDao;
import cn.edu.ncut.dao.SubjectCat2stDao;
import cn.edu.ncut.domain.SubjectCat1st;
import cn.edu.ncut.domain.SubjectCat2st;
import cn.edu.ncut.service.SubjectCatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectCatServiceImpl implements SubjectCatService {

    @Resource
    private SubjectCat1stDao subjectCat1stDao;

    @Resource
    private SubjectCat2stDao subjectCat2stDao;

    @Override
    public List<SubjectCat1st> getAllSubjectCat1st() {
        return subjectCat1stDao.selectAll();
    }

    @Override
    public List<SubjectCat2st> getSubjectByParentId(Integer parentId) {
        return subjectCat2stDao.selectSubjectByParentId(parentId);
    }

    @Override
    public String getSubjectCat1stName(Integer id) {
        return subjectCat1stDao.selectNameById(id);
    }

    @Override
    public String getSubjectCat2stName(Integer id) {
        return subjectCat2stDao.selectNameById(id);
    }
}
