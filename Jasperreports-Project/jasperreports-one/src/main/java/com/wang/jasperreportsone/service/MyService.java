package com.wang.jasperreportsone.service;

import com.wang.jasperreportsone.dao.JDBC;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.bouncycastle.util.test.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2020-06-13 20:41
 */
@Service
public class MyService {

    @Resource
    private HttpServletResponse response;
    public HttpServletResponse getReportTest() throws JRException, IOException {
        File file=new File("D:\\Mr-Wang-jihui\\Study2\\Jasperreports-Project\\jasperreports-one\\src\\main\\java\\com\\wang\\jasperreportsone\\reports\\report_three.jrxml");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getAbsoluteFile());
        File file1 = new File(".");
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getAbsoluteFile());

        String s = JasperCompileManager.compileReportToFile(file.getAbsolutePath());
        System.out.println("s="+s);

        File file2=new File(s);
        JasperReport jasperReport =(JasperReport) JRLoader.loadObject(file2);
        Map map=new HashMap();//参数map
        map.put("query", "admin");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, JDBC.conn);
        JasperRunManager.runReportToHtmlFile(s, "E:/test2-1.html", map, getReportDataSource());




        File file3=new File("D:\\Mr-Wang-jihui\\Study2\\Jasperreports-Project\\jasperreports-one\\src\\main\\java\\com\\wang\\jasperreportsone\\reports\\report_two.jrprint");


        JasperFillManager.fillReportToFile(jasperReport,file3.getAbsolutePath(),map,JDBC.conn);

        JasperExportManager.exportReportToHtmlFile(jasperPrint,"E:/test2-2.html");
        ServletOutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);


        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "E:/first2.xls");
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        exporter.exportReport();

        return response;
    }

    private JRDataSource getReportDataSource(){
        //new JRMapArrayDataSource(getMaparray());这里也可以传自定义对象数组，对象属性字段必须对应DbReport.jrxml中显示数据字段
        JRMapArrayDataSource dataSource=new JRMapArrayDataSource(getMaparray());
        return dataSource;
    }
    private Map[] getMaparray(){
        //map key键必须对应DbReport.jrxml中显示数据字段
        Map map1=new HashMap();
        map1.put("id", 1);
        map1.put("name", "aaa");
        map1.put("age", 24);
        map1.put("sex", "男");
        map1.put("hobby", "吃鸡");
        map1.put("birthday", new Timestamp(2020,12,23,12,34,45,12));

        Map map2=new HashMap();
        map2.put("id", 2);
        map2.put("name", "bbb");
        map2.put("age", 14);
        map2.put("sex", "女");
        map2.put("hobby", "漫画");
        map2.put("birthday", new Timestamp(2020,12,23,12,34,45,12));

        Map map3=new HashMap();
        map3.put("id", 3);
        map3.put("name", "ccc");
        map3.put("age", 223);
        map3.put("sex", "男");
        map3.put("hobby", "游泳");
        map3.put("birthday", new Timestamp(2020,12,23,12,34,45,12));

        Map[] mapArray=new Map[3];
        mapArray[0]=map1;
        mapArray[1]=map2;
        mapArray[2]=map3;

        return mapArray;
    }

}
