package cn.yuanbao.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

//ר��Ϊҳ�����: ��װ��ҳ�����Ϣ
public class WebUtils {

	public static <T> T fillFormBean(Class<T> clazz,HttpServletRequest request){
		T t = null ;
		try {
			t = clazz.newInstance() ;
			BeanUtils.populate(t, request.getParameterMap()) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t ;
	}
}
