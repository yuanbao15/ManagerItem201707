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
 * yuanbao测试用，看能否读到数据库中的数据。可以读到，但是好像不需要启动服务器仍然可以读到，说明是读本地的方式。
 * @author yuanbao15
 *
 */
public class Test {
	
	
	public static void main(String[] args){
		
		//	static CustomerService cs = new CustomerServiceImpl();
		/*
		 * 测试是否能读取数据库信息 ，成功
		 */
//		List<Customer> list = cs.getAllCustomer();
//		for (Customer c : list) {
//			System.out.println(c.getId()+","+c.getName()+","+c.getGender()+","+c.getTelephone()+","+c.getHobby());
//		}
		
		/*
		 * 测试能否向数据库中添加数据 ，失败，数据还是添加不进去
		 * add()方法有问题
		 */
/*		Customer customer = new Customer();
		customer.setId("2");
		customer.setName("yuanbao");
		customer.setGender("男");
		customer.setBirthday(new Date(100000));
		customer.setEmail("1344");
		customer.setTelephone("1344");
		customer.setType("vip");
		customer.setHobby("睡觉");
		customer.setDescription("我在哪儿");
		boolean flag = cs.add(customer);
		
		if(flag){
			System.out.println("添加成功");
		}
		List<Customer> list = cs.getAllCustomer();
		for (Customer c : list) {
			System.out.println(c.getId()+","+c.getName()+","+c.getGender()+","+c.getTelephone()+","+c.getHobby());
		}*/
		
		
		
		
		/*
		 * 向数据库增加一些数据
		 */
		CustomerDao dao = new CustomerDaoImpl() ;
		
		for (int i = 0; i < 5; i++) {
			Customer c = new Customer() ;
			c.setId(WebTools.createNewId());
			c.setName("元宝山" + (i+1)) ;
			c.setTelephone(i + "1") ;
//			c.setBirthday(new java.sql.Date(new Date().getTime())) ;
			c.setBirthday("2007-07-07") ;
			c.setEmail("@qq.com") ;
			c.setGender("男") ;
			c.setHobby("吃放,睡觉") ;
			c.setType("vip") ;
			c.setDescription("哈哈哈哈") ;
			
			dao.add(c) ;
			
			System.out.println("添加多行数据完成");		//传数据到本地localhost没问题，但传到云服务器上是失败的，不知道是哪儿的问题。
		}
		
	}
	
}
