package com.project.demo.controller;

import com.project.demo.entity.CourseSelectionManagement;
import com.project.demo.service.CourseSelectionManagementService;
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
 *选课管理：(CourseSelectionManagement)表控制层
 *
 */
@RestController
@RequestMapping("/course_selection_management")
public class CourseSelectionManagementController extends BaseController<CourseSelectionManagement,CourseSelectionManagementService> {

    /**
     *选课管理对象
     */
    @Autowired
    public CourseSelectionManagementController(CourseSelectionManagementService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        String sql = "SELECT MAX(course_selection_management_id) AS max FROM "+"course_selection_management";
        Query select = service.runCountSql(sql);
        Integer max = (Integer) select.getSingleResult();
        sql = ("SELECT count(*) count FROM `curriculum` INNER JOIN `course_selection_management` ON curriculum.course_number=course_selection_management.course_number WHERE curriculum.number_of_people_selected = course_selection_management.limited_number_of_people AND course_selection_management.course_selection_management_id="+max).replaceAll("&#60;","<");
        select = service.runCountSql(sql);
        Integer count = Integer.valueOf(String.valueOf(select.getSingleResult()));
        if(count>0){
            sql = "delete from "+"course_selection_management"+" WHERE "+"course_selection_management_id"+" ="+max;
            select = service.runCountSql(sql);
            select.executeUpdate();
            return error(30000,"名额已满!");
        }
        sql = "UPDATE `curriculum` INNER JOIN `course_selection_management` ON curriculum.course_number=course_selection_management.course_number SET curriculum.number_of_people_selected = curriculum.number_of_people_selected + course_selection_management.number_of_people WHERE course_selection_management.course_selection_management_id="+max;
        select = service.runCountSql(sql);
        select.executeUpdate();
        return success(1);
    }

}
