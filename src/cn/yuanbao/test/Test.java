package cn.yuanbao.test;

import java.util.Date;
import java.util.List;

import cn.yuanbao.bean.Customer;
import cn.yuanbao.dao.CustomerDao;
import cn.yuanbao.dao.impl.CustomerDaoImpl;
import cn.yuanbao.service.CustomerService;
import cn.yuanbao.service.impl.CustomerServiceImpl;
import cn.yuanbao.utils.WebTools;

/**
 * yuanbao�����ã����ܷ�������ݿ��е����ݡ����Զ��������Ǻ�����Ҫ������������Ȼ���Զ�����˵���Ƕ����صķ�ʽ��
 * @author yuanbao15
 *
 */
public class Test {
	
	
	public static void main(String[] args){
		
		//	static CustomerService cs = new CustomerServiceImpl();
		/*
		 * �����Ƿ��ܶ�ȡ���ݿ���Ϣ ���ɹ�
		 */
//		List<Customer> list = cs.getAllCustomer();
//		for (Customer c : list) {
//			System.out.println(c.getId()+","+c.getName()+","+c.getGender()+","+c.getTelephone()+","+c.getHobby());
//		}
		
		/*
		 * �����ܷ������ݿ���������� ��ʧ�ܣ����ݻ�����Ӳ���ȥ
		 * add()����������
		 */
/*		Customer customer = new Customer();
		customer.setId("2");
		customer.setName("yuanbao");
		customer.setGender("��");
		customer.setBirthday(new Date(100000));
		customer.setEmail("1344");
		customer.setTelephone("1344");
		customer.setType("vip");
		customer.setHobby("˯��");
		customer.setDescription("�����Ķ�");
		boolean flag = cs.add(customer);
		
		if(flag){
			System.out.println("��ӳɹ�");
		}
		List<Customer> list = cs.getAllCustomer();
		for (Customer c : list) {
			System.out.println(c.getId()+","+c.getName()+","+c.getGender()+","+c.getTelephone()+","+c.getHobby());
		}*/
		
		
		
		
		/*
		 * �����ݿ�����һЩ����
		 */
		CustomerDao dao = new CustomerDaoImpl() ;
		
		for (int i = 0; i < 5; i++) {
			Customer c = new Customer() ;
			c.setId(WebTools.createNewId());
			c.setName("Ԫ��ɽ" + (i+1)) ;
			c.setTelephone(i + "1") ;
//			c.setBirthday(new java.sql.Date(new Date().getTime())) ;
			c.setBirthday("2007-07-07") ;
			c.setEmail("@qq.com") ;
			c.setGender("��") ;
			c.setHobby("�Է�,˯��") ;
			c.setType("vip") ;
			c.setDescription("��������") ;
			
			dao.add(c) ;
			
			System.out.println("��Ӷ����������");		//�����ݵ�����localhostû���⣬�������Ʒ���������ʧ�ܵģ���֪�����Ķ������⡣
		}
		
	}
	
}
