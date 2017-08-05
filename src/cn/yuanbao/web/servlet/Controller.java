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
 * 用来控制请求的转向，即流程控制（前端控制器）Controller extends HttpServlet
 * 逻辑代码都放在这里
 * 
 * 采用UrlEnconding.encode(String,"UTF-8") 可以处理特殊字符
 * 页面传递数据时，超链会自动对特殊字符进行解码；其他方式需要手动用上面的方式进行解码处理。 
 * 
 * @author yuanbao15
 *
 */
public class Controller extends HttpServlet {
	
	CustomerService cs = new CustomerServiceImpl();	//客户服务的实例，用来操作增加删除什么的yb
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("test/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		//拿到页面传递的数据
		String op = req.getParameter("op");
		
		if("all".equals(op)){
			listAll(req,resp);	//显示所有数据在列表中
		}else if("add".equals(op)){
			addCustomer(req,resp);	//添加数据
		}else if("toupdatepage".equals(op)){
			toUpdatePage(req,resp);	//转向修改数据的页面
		}else if("update".equals(op)){
			updateCustomer(req,resp);	//修改数据后的提交
		}else if("delete".equals(op)){
			deleteCustomer(req,resp);	//删除一行数据
		}else if("delmore".equals(op)){
			deleteMoreCustomer(req,resp);	//删除多行数据
		}
	}
	
	//删除多行数据
	private void deleteMoreCustomer(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		//拿到页面传递的ids
		String ids = req.getParameter("ids");
		 
		//判断下，避免没有选框后点击了删除
		if(!ids.isEmpty()){
			System.out.println("选择了"+ids);
			//ids里的id是用逗号隔开的，要去ids最后一个逗号
			ids = ids.substring(0, ids.length()-1) ;	//substring(int1,int2)用法是截取int1到int2之间的字符串
			//拆分字符串转化为数组
			String[] arrIds = ids.split(",") ;
			
			for(int i=0; i<arrIds.length; i++){
				cs.delete(arrIds[i]);		//发现一个问题，有时候并没有真的删除掉。有的数据始终删不掉。id当中有空格？
				System.out.println("删除了一行数据，id为："+arrIds[i]);
			}
			
			listAll(req,resp);
		}else{
			System.out.println("选择了"+ids+"无，这是空的，请勾选要删除的序列");
		}
		
	}

	//删除一行数据
	private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//拿到页面传递的id
		String id = req.getParameter("id");
		// 调用service层完成业务逻辑(删除用户)
		boolean flag = cs.delete(id);
		if(flag){
//			System.out.println("test:Controller.dedate() ");
			//操作成功转向主页面并重新查询显示
			listAll(req,resp);
		}else{
			//删除失败后
			req.setAttribute("error", "删除失败");
			req.getRequestDispatcher("/list.jsp").forward(req, resp);
		}
	}

	//修改数据后提交,参考addCustomer()
	private void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//封装页面的数据
		CustomerFormBean cfb = WebUtils.fillFormBean(CustomerFormBean.class, req);
		
		//检测数据。。省略。。
		//拷贝数据到一个javabean中
		Customer c = new Customer();
//		//时间是date类型需要先注册一个时间转换器
//		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		//拷贝数据
		try {
			BeanUtils.copyProperties(c, cfb);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//处理数据	//已经有id了不需要再设置id
		//由于爱好类型不同，bean不会帮你拷贝数据，需要手动拷贝
		String[] hobby = cfb.getHobby();
		if(hobby != null && hobby.length > 0){
			StringBuffer sb = new StringBuffer(hobby[0]);
			for (int i = 1; i < hobby.length; i++) {
				sb.append(","+hobby[i]);
			}
			c.setHobby(sb.toString());
		}
		
		//调用service层完成业务逻辑
		boolean flag = cs.update(c);	//未 修改 成功，会返回false
//		System.out.println("test:Controller.update() 111111");
		if(flag){
//			System.out.println("test:Controller.update() 222222");
			//添加成功转向主页面并重新查询显示
			listAll(req,resp);
		}else{
			//修改失败
			req.setAttribute("error", "修改失败");
//			toUpdatePage(req,resp);
			req.getRequestDispatcher("/update.jsp").forward(req, resp);
		}
	}

	//修改数据跳转到修改数据页面，并把那个id对应的一行数据导入修改页面
	private void toUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//拿到页面传递的id
		String id = req.getParameter("id");
		// 调用service层完成业务逻辑(查找用户)
		Customer c = cs.findCustomerById(id);
		
		//将对象存入req对象后转发到修改页面
		req.setAttribute("customer", c);	//在update.jsp里的customer就表示这个用户
		req.getRequestDispatcher("/update.jsp").forward(req, resp);
		
	}

	//添加数据的方法
	private void addCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//封装页面的数据
		CustomerFormBean cfb = WebUtils.fillFormBean(CustomerFormBean.class, req);
		
		//检测数据。。省略。。
		//拷贝数据到一个javabean中
		Customer c = new Customer();
		//时间是date类型需要先注册一个时间转换器
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		//拷贝数据
		try {
			BeanUtils.copyProperties(c, cfb);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//处理数据
		//由于c中没有主键（ID），需要创建一个id
		c.setId(WebTools.createNewId());
		//由于爱好类型不同，bean不会帮你拷贝数据，需要手动拷贝
		String[] hobby = cfb.getHobby();
		if(hobby != null && hobby.length > 0){
			StringBuffer sb = new StringBuffer(hobby[0]);
			for (int i = 1; i < hobby.length; i++) {
				sb.append(","+hobby[i]);
			}
			c.setHobby(sb.toString());
		}
		
		//调用service层完成业务逻辑
		boolean flag = cs.add(c);	//未添加成功，会返回false
		if(flag){
			//添加成功转向主页面并重新查询显示
			listAll(req,resp);
		}else{
			//添加失败
			req.setAttribute("error", "添加失败");
			req.getRequestDispatcher("/add.jsp").forward(req, resp);
		}
	}

	//显示所有数据的方法，，已测试实现
	private void listAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//拿到所有数据
		List<Customer> list = cs.getAllCustomer();
		
		//将数据存放到session里
		//req.setAttribute("list", list);
		req.getSession().setAttribute("list", list);
		
		//页面重新定向到主页面
		resp.sendRedirect(req.getContextPath()+"/list.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
