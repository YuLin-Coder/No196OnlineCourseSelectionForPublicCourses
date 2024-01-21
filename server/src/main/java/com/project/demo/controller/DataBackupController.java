package com.project.demo.controller;

import com.project.demo.entity.DataBackup;
import com.project.demo.service.DataBackupService;
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
 *数据备份：(DataBackup)表控制层
 *
 */
@RestController
@RequestMapping("/data_backup")
public class DataBackupController extends BaseController<DataBackup,DataBackupService> {

    /**
     *数据备份对象
     */
    @Autowired
    public DataBackupController(DataBackupService service) {
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
