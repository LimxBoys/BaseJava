package com.base.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 采用ThreadLocal维护Connection
 * @author jialin
 *
 */
public class GetConnection {
	//用于保存connection
	private static ThreadLocal<Connection> connectionHolder=new ThreadLocal<Connection>();

	/**
	 * 获取连接
	 * @return
	 */
	public static Connection GetConnection()
	{
		Connection conn=connectionHolder.get();
		
		String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/limingxing";
	    String username = "root";
	    String password = "root";
		if(conn==null)
		{
			try {
				Class.forName(driver);
				conn=DriverManager.getConnection(url,username,password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectionHolder.set(conn);
		}
		
		return conn;
	}

	
    //关闭连接
	public static void closeConnection() {
		Connection conn = connectionHolder.get();
		if (conn != null) {
			try {
				conn.close();
				//从ThreadLocal中清除Connection
				connectionHolder.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	//关闭Statement（用于执行静态 SQL 语句并返回它所生成结果的对象。）
	public static void close(Statement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//关闭结果集
	public static void close(ResultSet rs ) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//开启事务
	public static void beginTransaction(Connection conn) {
		try {
			if (conn != null) {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false); //手动提交
				}
			}
		}catch(SQLException e) {}
	}
	
	
	//提交事务
	public static void commitTransaction(Connection conn) {
		try {
			if (conn != null) {
				if (!conn.getAutoCommit()) {
					conn.commit();
				}
			}
		}catch(SQLException e) {}
	}
	
	//回滚事务
	public static void rollbackTransaction(Connection conn) {
		try {
			if (conn != null) {
				if (!conn.getAutoCommit()) {
					conn.rollback();
				}
			}
		}catch(SQLException e) {}
	}
}

