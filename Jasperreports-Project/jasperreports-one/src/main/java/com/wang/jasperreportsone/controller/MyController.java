package com.wang.jasperreportsone.controller;

import com.wang.jasperreportsone.result.Result;
import com.wang.jasperreportsone.service.MyService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2020-06-13 20:33
 */
@RestController
@RequestMapping("/getReport")
public class MyController {

    @Resource
    private MyService service;

    @GetMapping(value = "/test1")
    public Result getReportTest() throws IOException, JRException {
        Result result=new Result();
        result.setStatusCode("200");
        result.setStatusName("success");
        HttpServletResponse reportTest = service.getReportTest();
        result.setData(reportTest);

        return result;
    }

}
