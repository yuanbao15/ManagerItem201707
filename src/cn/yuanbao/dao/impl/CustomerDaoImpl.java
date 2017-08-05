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
 * ʵ��CustomerDao�Ľӿڵ���CustomerDaoImpl
 * ����Ҫ��������ɾ��ģ��������ݿ�ķ�����jdbc����д�������
 * getAllCustomer()�����ɹ�
 * add()���� �ɹ�
 * update()���� �ɹ�
 * delete()���� �ɹ�
 * @author yuanbao15
 *
 */
public class CustomerDaoImpl implements CustomerDao {

	@Override
	public boolean add(Customer customer) {
		//�õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstmt = null;
		//����Ԥ�����������
		int n = 0;
		try {
			pstmt = conn.prepareStatement("insert into customer(id,name,gender,"
					+ "birthday,telephone,email,hobby,type,description) values(?,?,?,?,?,?,?,?,?)");
			//�����sql�������������ˣ�valueд��ֱ�Ӿ͹��ˡ�����
			//ָ������ֵ
			pstmt.setString(1, customer.getId());
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getGender()); 
			pstmt.setString(4, customer.getBirthday());	
			pstmt.setString(5, customer.getTelephone());
			pstmt.setString(6, customer.getEmail());
			pstmt.setString(7, customer.getHobby());
			pstmt.setString(8, customer.getType());
			pstmt.setString(9, customer.getDescription());
			
			//ִ��sql���
//			System.out.println("test��ִ�е����ˡ�������CustomerDaoImpl.add() 1");	//�ҵ����Ⲣ�����
			n = pstmt.executeUpdate();	//���ص����־��Ǹ����˵ڼ������ݣ�����0��ʾδ����
//			System.out.println("test��ִ�е����ˡ�������CustomerDaoImpl.add() 2");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(null, pstmt, conn);
		}
		return n>0? true:false;
	}

	@Override
	public boolean update(Customer customer) {
		
		//�õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstmt = null;
		//����Ԥ�����������
		int n = 0;
		try {
			//��id���в�ѯ����
			pstmt = conn.prepareStatement("update customer set name=?,gender=?,birthday=?,"
					+ "telephone=?,email=?,hobby=?,type=?,description=? where id=?");
			
			//ָ������ֵ
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getGender()); 
			pstmt.setString(3, customer.getBirthday());
			pstmt.setString(4, customer.getTelephone());
			pstmt.setString(5, customer.getEmail());
			pstmt.setString(6, customer.getHobby());
			pstmt.setString(7, customer.getType());
			pstmt.setString(8, customer.getDescription());
			pstmt.setString(9, customer.getId());
			
			//ִ��sql���
			n = pstmt.executeUpdate();	//���ص����־��Ǹ����˵ڼ������ݣ�����0��ʾδ����
		
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
		//�õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstmt = null;
		//����Ԥ�����������
		try {
			pstmt = conn.prepareStatement("delete from customer where id = ?");	//yb
			//ָ������ֵ
			pstmt.setString(1, id);	//��������һ��id�����пո񡣡������޷������ˣ��ҵ����⡣�����������id����
			
			//ִ��sql���
			n = pstmt.executeUpdate();	//���ص����־��Ǹ����˵ڼ������ݣ�����0��ʾδ����
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(null, pstmt, conn);
		}
		return n>0? true:false;
	}

	//ע�������б����ȡʱ�Ͱ�id����һ������
	@Override
	public List<Customer> getAllCustomer() {
		//�õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //�����
		List<Customer> list = new ArrayList<Customer>();	//�������շ���customer�ļ���
		//����Ԥ�����������
		try {
			pstmt = conn.prepareStatement("select id,name,gender,birthday,telephone,email,hobby,type,description from customer");
			//ִ��sql���
			rs = pstmt.executeQuery();
			while(rs.next()){
				//��װ����
				Customer c = new Customer();
				
				//����id���������ַ������ո��Ӱ��sql��䣬�����ȶ�id����
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
		//�õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //�����
		//����Ԥ�����������
		try {
			pstmt = conn.prepareStatement("select id,name,gender,birthday,telephone,email,hobby,type,description from customer where id = '"+id+"'");
			//ִ��sql���
			rs = pstmt.executeQuery();
			if(rs.next()){
				//��װ����
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
				
				return c;	//��װ��ɺ�return c �����return null
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(null, pstmt, conn);
		}
		return null;
	}

}
