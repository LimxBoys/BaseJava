package com.base.util;
import java.io.OutputStream;
import java.util.HashMap;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.Region;

public class ExcelUtil {
	/**
	 * 
	 * @param response
	 * @param list里面的map含有三个属性:1:sheetTitle(excel里面的sheet的名称)
	 * 							   2:head-->字符串数组格式的列名 
	 * 							   3:List<Map<String, Object>> list(真实数据)
	 * @param title 标题
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void exportExcel(HttpServletResponse response,List<Map<String, Object>> map,String title) throws Exception {

		HSSFWorkbook workbook = new HSSFWorkbook();
		//设置
		CellStyle cellStyle=workbook.createCellStyle();
		cellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("m/d/yy h:mm"));

		//int sheetNum = map.size();
		//for (int j = 0; j < map.size(); j++) {
		//	int sheetNum = j;
		int iii = 0;
			//遍历list集合获取对应的sheet
			for (Map<String, Object> listMap : map) {
				//获取每对应的title head,以及列信息,一个map中的元素对应一个sheet
				//1.先创建HSSFSheet
				Map<String,Object> maps = new HashMap<String,Object>();
			
				String sheetTitle =(String)listMap.get("title");
				String[] headers =(String[])listMap.get("head");
				List<Map<String,Object>> result = (List<Map<String,Object>>)listMap.get("list");
				int sheetNum = iii;

				// 生成一个表格
				HSSFSheet sheet = workbook.createSheet();
				//2.设置头信息
				if(maps.containsKey(sheetTitle)){
					workbook.setSheetName(sheetNum, sheetTitle + "(" + Integer.valueOf(maps.get(sheetTitle).toString()) + ")");
					maps.put(sheetTitle, Integer.valueOf(maps.get(sheetTitle).toString()) + 1);
				}else{
					maps.put(sheetTitle, 1);
					workbook.setSheetName(sheetNum, sheetTitle);
				}
				// 设置表格默认列宽度为20个字节
				sheet.setDefaultColumnWidth((short) 10);
				sheet.setDefaultRowHeight((short) 7);
				
		////////////////////////////////////////////////////////////////////////////////////////////////
				HSSFRow row = sheet.createRow(0);  
			    // 设置第一行  
			    HSSFCell cell = row.createCell(0);  
			    row.setHeight((short) 1000);  
		    // 定义单元格为字符串类型  
			    cell.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理  
			    System.out.println(sheetTitle);
			    cell.setCellValue(sheetTitle);  
			    // 指定合并区域  
			    sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 21));
			    sheet.createFreezePane(0, 2);
			    //sheet.createSplitPane(xSplitPos, ySplitPos, leftmostColumn, topRow, activePane)
			    //定义单元格格式，添加单元格表样式，并添加到工作簿  
			    HSSFCellStyle cellStyle1 = workbook.createCellStyle();  
			    //设置单元格水平对齐类型  
			   // cellStyle1.setFillBackgroundColor(HSSFColor.DARK_TEAL.index);
			    cellStyle1.setTopBorderColor(HSSFColor.DARK_TEAL.index);
			    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐  
			    cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐  
			    cellStyle1.setWrapText(true);// 指定单元格自动换行  
			    // 设置单元格字体  
			    HSSFFont font3 = workbook.createFont();  
			    font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
			    font3.setFontName("宋体");
			    font3.setColor(HSSFColor.BLACK.index);
			    font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//标题加粗
			    font3.setFontHeight((short) 600);  
			    cellStyle1.setFont(font3);  
			    cell.setCellStyle(cellStyle1);
				// 生成一个表头样式
				HSSFCellStyle headStyle = workbook.createCellStyle();
		        //headStyle.cloneStyleFrom(titleStyle);
				headStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        headStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		        
				//生成内容样式
		        HSSFCellStyle bodyStyle=workbook.createCellStyle();
		        bodyStyle.cloneStyleFrom(headStyle);
		        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		      
				// 生成一个字体
				HSSFFont headfont = workbook.createFont();
				HSSFFont bodyfont = workbook.createFont();
				headfont.setColor(HSSFColor.BLUE.index);
				headfont.setFontHeightInPoints((short) 11);
				headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				bodyfont.setColor(HSSFColor.BLACK.index);
				bodyfont.setFontHeightInPoints((short) 11);
				bodyfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				// 把字体应用到当前的样式
				headStyle.setFont(headfont);
				bodyStyle.setFont(bodyfont);
				// 指定当单元格内容显示不下时自动换行
				headStyle.setWrapText(true);
				bodyStyle.setWrapText(false);
				// 产生表格标题行
				row = sheet.createRow(1);
				for (int i = 0; i < headers.length; i++) {
					HSSFCell cell2 = row.createCell((short) i);
					cell2.setCellType(HSSFCell.ENCODING_UTF_16);
					cell2.setCellStyle(headStyle);
					HSSFRichTextString text = new HSSFRichTextString(headers[i]);
					cell2.setCellValue(text.toString());
				}
				// 遍历集合数据，产生数据行
				if (result != null) {
					int index = 2;
					for (Map<String,Object> m : result) {
						row = sheet.createRow(index);
						int cellIndex = 0;
						for (Object obj : m.values()) {
							HSSFCell cell3 = row.createCell((short) cellIndex);
							cell3.setCellType(HSSFCell.ENCODING_UTF_16);
							cell3.setCellStyle(bodyStyle);
							cell3.setCellValue(obj == null ? "" : obj.toString());
							cellIndex++;
						}
						index++;
					}
				}
				iii++;
			} 
			//continue;
		//}
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

}
