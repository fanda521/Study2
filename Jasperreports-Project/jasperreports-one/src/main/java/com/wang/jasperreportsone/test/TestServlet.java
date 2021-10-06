package com.wang.jasperreportsone.test;


import com.wang.jasperreportsone.dao.JDBC;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestServlet extends HttpServlet {
	@Override

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			ServletContext context = this.getServletConfig().getServletContext();

			//JasperCompileManager 编译管理器
			//JasperFillManager 填充管理器
			//JRXmlLoader xml加载器
			//JasperPrintManager 打印管理器
			//JasperExportManager 导出管理器
			//JasperRunManager 运行管理器

			JasperCompileManager.compileReportToFile(context.getRealPath("/reports/DbReport.jrxml"));//编译jrxml文件，生成jasper文件

			Map map=new HashMap();//参数map
			map.put("userName", "admin");

			//生成jrprint文件
			//JasperFillManager.fillReportToFile(context.getRealPath("/reports/DbReport.jasper"), map, JDBC.conn);


			File jasperFile=new File(context.getRealPath("/reports/DbReport.jasper"));
			JasperReport jasperReport =(JasperReport)JRLoader.loadObject(jasperFile);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, JDBC.conn);

			//将查询的数据填充到报表中
			//String sql="select id,user_name as name,email,qq from user";
			//ResultSet rs=JDBC.getResultSet(sql);
			//JRResultSetDataSource jr=new JRResultSetDataSource(rs);
			//JasperFillManager.fillReportToFile(context.getRealPath("/reports/DbReport.jasper"), map, jr);
			//JDBC.closeAll(rs, JDBC.statement, null);//关闭数据连接

			//JasperRunManager.runReportToHtmlFile(context.getRealPath("/reports/DbReport.jasper"), map, JDBC.conn);
			//JasperRunManager.runReportToHtmlFile(context.getRealPath("/reports/DbReport.jasper"), map, jr);
			//生成html文件
			//JasperRunManager.runReportToHtmlFile(context.getRealPath("/reports/DbReport.jasper"), "E:/test.html", map, JDBC.conn);

			//InputStream  inputStream=getServletConfig().getServletContext().getResourceAsStream(context.getRealPath("/reports/DbReport.jasper"));
			//JasperRunManager.runReportToPdfStream(inputStream, response.getOutputStream(), map, jr);

			//-----以map数组为数据源,生成html文件
			JasperRunManager.runReportToHtmlFile(context.getRealPath("/reports/DbReport.jasper"), "E:/test2.html", map, getReportDataSource());



			//生成html数据
            /*
            JRHtmlExporter html = new JRHtmlExporter();
            html.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jasperPrint);
            html.setParameter(JRHtmlExporterParameter.OUTPUT_WRITER,response.getWriter());
            html.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
            html.setParameter(JRExporterParameter.CHARACTER_ENCODING, "utf-8");
            html.exportReport();
            */
			//生成excel
            /*
            JRXlsExporter xls=new JRXlsExporter();
            xls.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            xls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());
            xls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            xls.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            response.setHeader("Content-Disposition", "attachment;filename=first.xls");
            response.setContentType("application/vnd_ms-excel");
            xls.exportReport();
            */
			//生成pdf
            /*
            JRPdfExporter pdf = new JRPdfExporter();
            pdf.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            pdf.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

            response.setHeader("Content-Disposition", "attachment;filename=first.pdf");
            response.setContentType("application/pdf");
            response.setCharacterEncoding("UTF-8");
            pdf.exportReport();
			*/
		}catch(Exception e){
			e.printStackTrace();
		}


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
		map1.put("email", "111@qq.com");
		map1.put("qq", "111");
		
		Map map2=new HashMap();
		map2.put("id", 2);
		map2.put("name", "bbb");
		map2.put("email", "222@qq.com");
		map2.put("qq", "222");
		
		Map map3=new HashMap();
		map3.put("id", 3);
		map3.put("name", "ccc");
		map3.put("email", "333@qq.com");
		map3.put("qq", "333");
		
		Map[] mapArray=new Map[3];
		mapArray[0]=map1;
		mapArray[1]=map2;
		mapArray[2]=map3;
		
		return mapArray;
	}
}

