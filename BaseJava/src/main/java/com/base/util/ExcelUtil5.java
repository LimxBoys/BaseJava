package com.base.util;


import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.StringUtils;
/**
 * 
 * @param response
 * @param list里面的map含有三个属性:1:sheetTitle(excel里面的sheet的名称)
 * 							   2:head-->字符串数组格式的列名 
 * 							   3:List<Map<String, Object>> list(真实数据)
 * @param title 标题
 * @throws Exception
 */
public class ExcelUtil5 {
	
	@SuppressWarnings("deprecation")
	public static void exportExcel(HttpServletResponse response,List<Map<String, Object>> map, String title) throws Exception {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle commonStyle = getCellStyle(workbook, "common");
		HSSFRow row = null;
		HSSFCell cell = null;
		if(map == null || map.size() == 0){
			HSSFSheet sheet = workbook.createSheet("无记录");
			createSheet(sheet, "无记录", new String[]{"无记录"}, "header");
		}else{
			for(int i = 0; i < map.size(); i++){
				String sheetTitle = (String)map.get(i).get("title");
				String[] headers = (String[])map.get(i).get("head");
				List<Map<String,Object>> cellList = (List<Map<String,Object>>)map.get(i).get("list");
				//如果sheetTitle为空，自动生成
				if(StringUtils.isEmpty(sheetTitle)){
					sheetTitle = "sheet" + new SimpleDateFormat("HH小时mm分ss秒").format(new Date());  
				}
				
				HSSFSheet sheet = workbook.createSheet(sheetTitle);
				
				//创建sheet页的标题
				createSheet(sheet, sheetTitle, headers, "header");
				//创建sheet页的列标题
				createSheet(sheet, sheetTitle, headers, "title");
				if(cellList != null && cellList.size() > 0){
					for(int j = 0; j < cellList.size(); j++){
						row = sheet.createRow(j + 2);
						row.setHeightInPoints(20);
						int num = 0;
						for (Map.Entry<String, Object> entry : cellList.get(j).entrySet()) {
							cell = row.createCell(num);
							cell.setCellStyle(commonStyle);
							Object obj = entry.getValue();
							HSSFRichTextString text = new HSSFRichTextString(obj == null ? "" : obj.toString());
							cell.setCellValue(text);
							num++;
						}
					}
				}
			}
		}
		
		String inputPath = title+".xls";  
		response.setCharacterEncoding("ISO8859-1");   
		response.setHeader("Content-Disposition", "attachment; filename=" + new String(inputPath.getBytes("GBK"), "ISO8859-1"));
		response.setHeader("Connection", "close");  
        response.setHeader("Content-Type", "application/octet-stream");
		OutputStream out=response.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
	}
	
	private static void createSheet(HSSFSheet sheet, String sheetTitle, String[] headers, String type) {
		HSSFRow row = null;
		HSSFCell cell = null;
		HSSFCellStyle headerCellStyle = getCellStyle(sheet.getWorkbook(), "header");
		HSSFCellStyle titleCellStyle = getCellStyle(sheet.getWorkbook(), "title");
		
		if("header".equals(type)){
			row = sheet.createRow(0);
			row.setHeightInPoints(50);
			
			for(int i = 0; i < headers.length; i++){
				cell = row.createCell(i);
				sheet.setColumnWidth(i, 18 * 256);
				cell.setCellStyle(headerCellStyle);
				//将sheetTitle放在第0个单元格中
				if(i == 0){
					cell.setCellValue(sheetTitle);
				}
			}
			//合并单元格形成标题行
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,headers.length - 1));
		}else{
			row = sheet.createRow(1);
			row.setHeightInPoints(30);
			for(int i = 0; i < headers.length; i++){
				
				cell = row.createCell(i);
				cell.setCellStyle(titleCellStyle);
				cell.setCellValue(headers[i]);
				
				
			}
		}	
	}

	private  static HSSFCellStyle getCellStyle(HSSFWorkbook workbook, String type){
		//生成标题样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        if("header".equals(type)){
        	font.setFontHeightInPoints((short) 24);
        	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        }else if("title".equals(type)){
        	font.setFontHeightInPoints((short) 14);
        	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        	cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        	cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        }else{
        	font.setFontHeightInPoints((short) 12);
        }
        
        // 把字体应用到当前的样式
        cellStyle.setFont(font); 
        return cellStyle;
	}
}
