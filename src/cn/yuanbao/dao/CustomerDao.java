package cn.yuanbao.dao;

import java.util.List;

import cn.yuanbao.bean.Customer;

/**
 * Customer类的接口
 * @author yuanbao15
 *
 */

public interface CustomerDao {
	/**
	 * 添加一个客户
	 * @param customer
	 * @return 添加成功返回true，否则返回false
	 */
	public boolean add(Customer customer);
	
	/**
	 * 修改一个客户
	 * @param customer
	 * @return 添加成功返回true，否则返回false
	 */
	public boolean update(Customer customer);
	
	/**
	 * 删除一个客户(根据id)
	 * @param customer
	 * @return 添加成功返回true，否则返回false
	 */
	public boolean delete(String id);
	
	/**
	 * 获取所有客户
	 * @param customer
	 * @return 添加成功返回true，否则返回false
	 */
	public List<Customer> getAllCustomer();
	
	/**
	 * 根据id查找客户
	 * @param customer
	 * @return 查出来就返回此客户，否则返回null
	 */
	public Customer findCustomerById(String id);
	
}
