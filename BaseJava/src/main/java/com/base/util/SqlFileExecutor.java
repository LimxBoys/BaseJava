package com.base.util;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL 脚本执行类
 * 
 * @author kong
 * 
 */
public final class SqlFileExecutor {
	/**
	 * 公用的执行sql
	 * @param file
	 * @return
	 */
	public static List<String> ExcuteSql(String file) {
		List<String> sqlList = new ArrayList<String>();
		try {
			sqlList = loadSql(file);
			for (String sql : sqlList) {
				System.out.println(sql);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlList;
	}

	/**
	 * 读取 SQL 文件，获取 SQL 语句
	 * 
	 * @param sqlFile
	 *            SQL 脚本文件
	 * @return List<sql> 返回所有 SQL 语句的 List
	 * @throws Exception
	 */
	private static List<String> loadSql(String sqlFile) throws Exception {
		List<String> sqlList = new ArrayList<String>();
		try {
			InputStream sqlFileIn = new FileInputStream(sqlFile);
			StringBuffer sqlSb = new StringBuffer();
			byte[] buff = new byte[1024];
			int byteRead = 0;
			while ((byteRead = sqlFileIn.read(buff)) != -1) {
				sqlSb.append(new String(buff, 0, byteRead));
			}

			// Windows 下换行是 \r\n, Linux 下是 \n
			String[] sqlArr = sqlSb.toString()
					.split("(;\\s*\\r\\n)|(;\\s*\\n)");
			for (int i = 0; i < sqlArr.length; i++) {
				String sql = sqlArr[i].replaceAll("--.*", "").trim();
				if (!sql.equals("")) {
					sqlList.add(sql);
				}
			}
			return sqlList;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
}