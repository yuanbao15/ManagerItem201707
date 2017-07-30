package cn.yuanbao.service.impl;

import java.util.List;

import cn.yuanbao.bean.Customer;
import cn.yuanbao.dao.CustomerDao;
import cn.yuanbao.dao.impl.CustomerDaoImpl;
import cn.yuanbao.service.CustomerService;

/**
 * 服务的实现类
 * @author yuanbao15
 *
 */
public class CustomerServiceImpl implements CustomerService{

	CustomerDao dao = new CustomerDaoImpl();
	
	@Override
	public boolean add(Customer customer) {
		return dao.add(customer);
	}

	@Override
	public boolean update(Customer customer) {
		return dao.update(customer);
	}

	@Override
	public boolean delete(String id) {
		return dao.delete(id);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return dao.getAllCustomer();
	}

	@Override
	public Customer findCustomerById(String id) {
		return dao.findCustomerById(id);
	}

}
