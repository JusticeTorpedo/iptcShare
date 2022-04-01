package cn.edu.ncut.controller;

import cn.edu.ncut.domain.SubjectCat1st;
import cn.edu.ncut.domain.SubjectCat2st;
import cn.edu.ncut.service.SubjectCatService;
import cn.edu.ncut.vo.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectCatService subjectCatService;

    /**
     * 根据学科门类id获取一级学科
     * @param parentId
     * @return list
     */
    @RequestMapping("/cat2st/query.do")
    @ResponseBody
    public CommonResult getSubjectCat2st(Integer parentId){
        List<SubjectCat2st> list = subjectCatService.getSubjectByParentId(parentId);
        CommonResult result = null;
        if(list != null && list.size() > 0){
            result = new CommonResult(0,"查询成功",list);
        }else{
            result = new CommonResult(1,"查询失败",list);
        }
        return result;
    }
}
