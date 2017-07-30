package cn.yuanbao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 通用的连接数据库的工具类
 * @author yuanbao15
 *
 */
public class JdbcUtils {

	private static String driverClass = "" ;
	private static String url = "" ;
	private static String user = "" ;
	private static String password  = "";
	
	static{
		ResourceBundle rb = ResourceBundle.getBundle("dbcfg") ;
		driverClass = rb.getString("driverClass") ;
		url = rb.getString("url") ;
		user = rb.getString("user") ;
		password = rb.getString("password") ;
		
		try {
			Class.forName(driverClass) ;	//注册驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url, user, password) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	//释放所有连接对象的方法，都写到这儿了
	public static void release(ResultSet rs ,Statement stmt,Connection conn){
		if(rs != null){
			try {
				rs.close() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(stmt != null){
			try {
				stmt.close() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null){
			try {
				conn.close() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
