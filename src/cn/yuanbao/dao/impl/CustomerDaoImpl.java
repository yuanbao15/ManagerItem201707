package cn.yuanbao.dao.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.yuanbao.bean.Customer;
import cn.yuanbao.dao.CustomerDao;
import cn.yuanbao.utils.JdbcUtils;

/**
 * 实现CustomerDao的接口的类CustomerDaoImpl
 * 很重要，具体增删查改，操作数据库的方法（jdbc）都写在这儿。
 * getAllCustomer()方法成功
 * add()方法 成功
 * update()方法 成功
 * delete()方法 成功
 * @author yuanbao15
 *
 */
public class CustomerDaoImpl implements CustomerDao {

	@Override
	public boolean add(Customer customer) {
		//拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstmt = null;
		//创建预处理命令对象
		int n = 0;
		try {
			pstmt = conn.prepareStatement("insert into customer(id,name,gender,"
					+ "birthday,telephone,email,hobby,type,description) values(?,?,?,?,?,?,?,?,?)");
			//这里的sql代码马虎犯错害死人，value写错直接就跪了。。。
			//指定？的值
			pstmt.setString(1, customer.getId());
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getGender()); 
			pstmt.setString(4, customer.getBirthday());	
			pstmt.setString(5, customer.getTelephone());
			pstmt.setString(6, customer.getEmail());
			pstmt.setString(7, customer.getHobby());
			pstmt.setString(8, customer.getType());
			pstmt.setString(9, customer.getDescription());
			
			//执行sql语句
//			System.out.println("test：执行到这了。。。。CustomerDaoImpl.add() 1");	//找到问题并解决了
			n = pstmt.executeUpdate();	//返回的数字就是更新了第几行数据，返回0表示未更新
//			System.out.println("test：执行到这了。。。。CustomerDaoImpl.add() 2");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(null, pstmt, conn);
		}
		return n>0? true:false;
	}

	@Override
	public boolean update(Customer customer) {
		
		//拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstmt = null;
		//创建预处理命令对象
		int n = 0;
		try {
			//以id进行查询更新
			pstmt = conn.prepareStatement("update customer set name=?,gender=?,birthday=?,"
					+ "telephone=?,email=?,hobby=?,type=?,description=? where id=?");
			
			//指定？的值
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getGender()); 
			pstmt.setString(3, customer.getBirthday());
			pstmt.setString(4, customer.getTelephone());
			pstmt.setString(5, customer.getEmail());
			pstmt.setString(6, customer.getHobby());
			pstmt.setString(7, customer.getType());
			pstmt.setString(8, customer.getDescription());
			pstmt.setString(9, customer.getId());
			
			//执行sql语句
			n = pstmt.executeUpdate();	//返回的数字就是更新了第几行数据，返回0表示未更新
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(null, pstmt, conn);
		}
		return n>0? true:false;
	}

	@Override
	public boolean delete(String id) {
		int n = 0;
		//拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstmt = null;
		//创建预处理命令对象
		try {
			pstmt = conn.prepareStatement("delete from customer where id = ?");	//yb
			//指定？的值
			pstmt.setString(1, id);	//如果这里的一个id当中有空格。。。就无法操作了，找到问题。解决方法：对id编码
			
			//执行sql语句
			n = pstmt.executeUpdate();	//返回的数字就是更新了第几行数据，返回0表示未更新
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(null, pstmt, conn);
		}
		return n>0? true:false;
	}

	//注意先在列表里读取时就把id进行一个编码
	@Override
	public List<Customer> getAllCustomer() {
		//拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //结果集
		List<Customer> list = new ArrayList<Customer>();	//用来接收返回customer的集合
		//创建预处理命令对象
		try {
			pstmt = conn.prepareStatement("select id,name,gender,birthday,telephone,email,hobby,type,description from customer");
			//执行sql语句
			rs = pstmt.executeQuery();
			while(rs.next()){
				//封装数据
				Customer c = new Customer();
				
				//担心id里有特殊字符产生空格会影响sql语句，所以先对id编码
//				c.setId(rs.getString("id"));
				try {
					String id = URLEncoder.encode(rs.getString("id"), "UTF-8");
					c.setId(id);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				c.setName(rs.getString("name"));
				c.setGender(rs.getString("gender"));
				c.setBirthday(rs.getString("birthday"));
				c.setTelephone(rs.getString("telephone"));
				c.setEmail(rs.getString("email"));
				c.setHobby(rs.getString("hobby"));
				c.setType(rs.getString("type"));
				c.setDescription(rs.getString("description"));
				
				list.add(c);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(null, pstmt, conn);
		}
		return list;
	}

	@Override
	public Customer findCustomerById(String id) {
		//拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //结果集
		//创建预处理命令对象
		try {
			pstmt = conn.prepareStatement("select id,name,gender,birthday,telephone,email,hobby,type,description from customer where id = '"+id+"'");
			//执行sql语句
			rs = pstmt.executeQuery();
			if(rs.next()){
				//封装数据
				Customer c = new Customer();
				
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setGender(rs.getString("gender"));
				c.setBirthday(rs.getString("birthday"));
				c.setTelephone(rs.getString("telephone"));
				c.setEmail(rs.getString("email"));
				c.setHobby(rs.getString("hobby"));
				c.setType(rs.getString("type"));
				c.setDescription(rs.getString("description"));
				
				return c;	//封装完成后return c 否则就return null
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(null, pstmt, conn);
		}
		return null;
	}

}
