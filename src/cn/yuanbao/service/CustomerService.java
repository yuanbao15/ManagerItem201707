package cn.yuanbao.service;

import java.util.List;

import cn.yuanbao.bean.Customer;

/**
 * Ϊҳ�����ģ�����Ҫ����ɾ��ĵķ�����
 * @author yuanbao15
 *
 */
public interface CustomerService {
	
	/**
	 * ���һ���ͻ�
	 * @param customer
	 * @return ��ӳɹ�����true�����򷵻�false
	 */
	public boolean add(Customer customer);
	
	/**
	 * �޸�һ���ͻ�
	 * @param customer
	 * @return ��ӳɹ�����true�����򷵻�false
	 */
	public boolean update(Customer customer);
	
	/**
	 * ɾ��һ���ͻ�(����id)
	 * @param customer
	 * @return ��ӳɹ�����true�����򷵻�false
	 */
	public boolean delete(String id);
	
	/**
	 * ��ȡ���пͻ�
	 * @param customer
	 * @return ��ӳɹ�����true�����򷵻�false
	 */
	public List<Customer> getAllCustomer();
	
	/**
	 * ����id���ҿͻ�
	 * @param customer
	 * @return ������ͷ��ش˿ͻ������򷵻�null
	 */
	public Customer findCustomerById(String id);
	
}
