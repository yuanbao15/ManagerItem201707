package cn.yuanbao.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import cn.yuanbao.bean.Customer;
import cn.yuanbao.service.CustomerService;
import cn.yuanbao.service.impl.CustomerServiceImpl;
import cn.yuanbao.utils.WebTools;
import cn.yuanbao.utils.WebUtils;
import cn.yuanbao.web.formbean.CustomerFormBean;

/**
 * �������������ת�򣬼����̿��ƣ�ǰ�˿�������Controller extends HttpServlet
 * �߼����붼��������
 * 
 * ����UrlEnconding.encode(String,"UTF-8") ���Դ��������ַ�
 * ҳ�洫������ʱ���������Զ��������ַ����н��룻������ʽ��Ҫ�ֶ�������ķ�ʽ���н��봦�� 
 * 
 * @author yuanbao15
 *
 */
public class Controller extends HttpServlet {
	
	CustomerService cs = new CustomerServiceImpl();	//�ͻ������ʵ����������������ɾ��ʲô��yb
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("test/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		//�õ�ҳ�洫�ݵ�����
		String op = req.getParameter("op");
		
		if("all".equals(op)){
			listAll(req,resp);	//��ʾ�����������б���
		}else if("add".equals(op)){
			addCustomer(req,resp);	//�������
		}else if("toupdatepage".equals(op)){
			toUpdatePage(req,resp);	//ת���޸����ݵ�ҳ��
		}else if("update".equals(op)){
			updateCustomer(req,resp);	//�޸����ݺ���ύ
		}else if("delete".equals(op)){
			deleteCustomer(req,resp);	//ɾ��һ������
		}else if("delmore".equals(op)){
			deleteMoreCustomer(req,resp);	//ɾ����������
		}
	}
	
	//ɾ����������
	private void deleteMoreCustomer(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		//�õ�ҳ�洫�ݵ�ids
		String ids = req.getParameter("ids");
		 
		//�ж��£�����û��ѡ�������ɾ��
		if(!ids.isEmpty()){
			System.out.println("ѡ����"+ids);
			//ids���id���ö��Ÿ����ģ�Ҫȥids���һ������
			ids = ids.substring(0, ids.length()-1) ;	//substring(int1,int2)�÷��ǽ�ȡint1��int2֮����ַ���
			//����ַ���ת��Ϊ����
			String[] arrIds = ids.split(",") ;
			
			for(int i=0; i<arrIds.length; i++){
				cs.delete(arrIds[i]);		//����һ�����⣬��ʱ��û�����ɾ�������е�����ʼ��ɾ������id�����пո�
				System.out.println("ɾ����һ�����ݣ�idΪ��"+arrIds[i]);
			}
			
			listAll(req,resp);
		}else{
			System.out.println("ѡ����"+ids+"�ޣ����ǿյģ��빴ѡҪɾ��������");
		}
		
	}

	//ɾ��һ������
	private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//�õ�ҳ�洫�ݵ�id
		String id = req.getParameter("id");
		// ����service�����ҵ���߼�(ɾ���û�)
		boolean flag = cs.delete(id);
		if(flag){
//			System.out.println("test:Controller.dedate() ");
			//�����ɹ�ת����ҳ�沢���²�ѯ��ʾ
			listAll(req,resp);
		}else{
			//ɾ��ʧ�ܺ�
			req.setAttribute("error", "ɾ��ʧ��");
			req.getRequestDispatcher("/list.jsp").forward(req, resp);
		}
	}

	//�޸����ݺ��ύ,�ο�addCustomer()
	private void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��װҳ�������
		CustomerFormBean cfb = WebUtils.fillFormBean(CustomerFormBean.class, req);
		
		//������ݡ���ʡ�ԡ���
		//�������ݵ�һ��javabean��
		Customer c = new Customer();
//		//ʱ����date������Ҫ��ע��һ��ʱ��ת����
//		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		//��������
		try {
			BeanUtils.copyProperties(c, cfb);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//��������	//�Ѿ���id�˲���Ҫ������id
		//���ڰ������Ͳ�ͬ��bean������㿽�����ݣ���Ҫ�ֶ�����
		String[] hobby = cfb.getHobby();
		if(hobby != null && hobby.length > 0){
			StringBuffer sb = new StringBuffer(hobby[0]);
			for (int i = 1; i < hobby.length; i++) {
				sb.append(","+hobby[i]);
			}
			c.setHobby(sb.toString());
		}
		
		//����service�����ҵ���߼�
		boolean flag = cs.update(c);	//δ �޸� �ɹ����᷵��false
//		System.out.println("test:Controller.update() 111111");
		if(flag){
//			System.out.println("test:Controller.update() 222222");
			//��ӳɹ�ת����ҳ�沢���²�ѯ��ʾ
			listAll(req,resp);
		}else{
			//�޸�ʧ��
			req.setAttribute("error", "�޸�ʧ��");
//			toUpdatePage(req,resp);
			req.getRequestDispatcher("/update.jsp").forward(req, resp);
		}
	}

	//�޸�������ת���޸�����ҳ�棬�����Ǹ�id��Ӧ��һ�����ݵ����޸�ҳ��
	private void toUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//�õ�ҳ�洫�ݵ�id
		String id = req.getParameter("id");
		// ����service�����ҵ���߼�(�����û�)
		Customer c = cs.findCustomerById(id);
		
		//���������req�����ת�����޸�ҳ��
		req.setAttribute("customer", c);	//��update.jsp���customer�ͱ�ʾ����û�
		req.getRequestDispatcher("/update.jsp").forward(req, resp);
		
	}

	//������ݵķ���
	private void addCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��װҳ�������
		CustomerFormBean cfb = WebUtils.fillFormBean(CustomerFormBean.class, req);
		
		//������ݡ���ʡ�ԡ���
		//�������ݵ�һ��javabean��
		Customer c = new Customer();
		//ʱ����date������Ҫ��ע��һ��ʱ��ת����
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		//��������
		try {
			BeanUtils.copyProperties(c, cfb);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//��������
		//����c��û��������ID������Ҫ����һ��id
		c.setId(WebTools.createNewId());
		//���ڰ������Ͳ�ͬ��bean������㿽�����ݣ���Ҫ�ֶ�����
		String[] hobby = cfb.getHobby();
		if(hobby != null && hobby.length > 0){
			StringBuffer sb = new StringBuffer(hobby[0]);
			for (int i = 1; i < hobby.length; i++) {
				sb.append(","+hobby[i]);
			}
			c.setHobby(sb.toString());
		}
		
		//����service�����ҵ���߼�
		boolean flag = cs.add(c);	//δ��ӳɹ����᷵��false
		if(flag){
			//��ӳɹ�ת����ҳ�沢���²�ѯ��ʾ
			listAll(req,resp);
		}else{
			//���ʧ��
			req.setAttribute("error", "���ʧ��");
			req.getRequestDispatcher("/add.jsp").forward(req, resp);
		}
	}

	//��ʾ�������ݵķ��������Ѳ���ʵ��
	private void listAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//�õ���������
		List<Customer> list = cs.getAllCustomer();
		
		//�����ݴ�ŵ�session��
		//req.setAttribute("list", list);
		req.getSession().setAttribute("list", list);
		
		//ҳ�����¶�����ҳ��
		resp.sendRedirect(req.getContextPath()+"/list.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
