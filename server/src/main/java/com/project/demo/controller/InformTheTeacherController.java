package com.project.demo.controller;

import com.project.demo.entity.InformTheTeacher;
import com.project.demo.service.InformTheTeacherService;
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
 *通知教师：(InformTheTeacher)表控制层
 *
 */
@RestController
@RequestMapping("/inform_the_teacher")
public class InformTheTeacherController extends BaseController<InformTheTeacher,InformTheTeacherService> {

    /**
     *通知教师对象
     */
    @Autowired
    public InformTheTeacherController(InformTheTeacherService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
