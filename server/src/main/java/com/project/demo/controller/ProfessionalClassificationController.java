package com.project.demo.controller;

import com.project.demo.entity.ProfessionalClassification;
import com.project.demo.service.ProfessionalClassificationService;
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
 *专业分类：(ProfessionalClassification)表控制层
 *
 */
@RestController
@RequestMapping("/professional_classification")
public class ProfessionalClassificationController extends BaseController<ProfessionalClassification,ProfessionalClassificationService> {

    /**
     *专业分类对象
     */
    @Autowired
    public ProfessionalClassificationController(ProfessionalClassificationService service) {
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
