package com.project.demo.controller;

import com.project.demo.entity.InformStudents;
import com.project.demo.service.InformStudentsService;
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
 *通知学生：(InformStudents)表控制层
 *
 */
@RestController
@RequestMapping("/inform_students")
public class InformStudentsController extends BaseController<InformStudents,InformStudentsService> {

    /**
     *通知学生对象
     */
    @Autowired
    public InformStudentsController(InformStudentsService service) {
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
