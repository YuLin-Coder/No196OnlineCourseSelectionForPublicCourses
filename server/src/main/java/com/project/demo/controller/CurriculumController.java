package com.project.demo.controller;

import com.project.demo.entity.Curriculum;
import com.project.demo.service.CurriculumService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *课程：(Curriculum)表控制层
 *
 */
@RestController
@RequestMapping("/curriculum")
public class CurriculumController extends BaseController<Curriculum,CurriculumService> {

    /**
     *课程对象
     */
    @Autowired
    public CurriculumController(CurriculumService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapcourse_number = new HashMap<>();
        mapcourse_number.put("course_number",String.valueOf(paramMap.get("course_number")));
        List listcourse_number = service.select(mapcourse_number, new HashMap<>()).getResultList();
        if (listcourse_number.size()>0){
            return error(30000, "字段课程编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }
    @RequestMapping("/get_hits_list")
    public Map<String, Object> getHits(HttpServletRequest request) {
        Map<String,String> paramMap = service.readQuery(request);
        if (paramMap.get("user_id")==null||paramMap.get("user_id")==""){
        return this.getList(request);
        }else{
            String sql="SELECT\n"+
            "\t* \n"+
            "FROM\n"+
            "\tcurriculum \n"+
            "WHERE\n"+
            "\tprofessional_type = (\n"+
            "\tSELECT\n"+
            "\t\tprofessional_type \n"+
            "\tFROM\n"+
            "\t\tcurriculum \n"+
            "\tWHERE\n"+
            "\t\tcurriculum_id = (\n"+
            "\t\tSELECT\n"+
            "\t\t\tt1.source_id \n"+
            "\t\tFROM\n"+
            "\t\t\t( SELECT COUNT( hits_id ) AS hits_count, source_id FROM hits WHERE source_table = 'curriculum' AND user_id = "+paramMap.get("user_id")+" GROUP BY source_id ) t1 \n"+
            "\t\tORDER BY\n"+
            "\t\t\thits_count DESC \n"+
            "\t\t\tLIMIT 0,\n"+
            "\t\t\t1 \n"+
            "\t\t)) \n"+
            "ORDER BY\n"+
            "\thits DESC \n"+
            "\tLIMIT 0,4";
            Query select=service.runEntitySql(sql);
            Map<String, Object> map=new HashMap<>();
            map.put("list",select.getResultList());
            map.put("count",select.getResultList().size());
            return success(map);
        }
    }

}
