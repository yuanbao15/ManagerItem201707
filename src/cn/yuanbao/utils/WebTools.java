package cn.yuanbao.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import sun.misc.BASE64Encoder;

//������һЩ���õ�һЩ����
public class WebTools {

	//��ȡһ���µ�Ψһid�ķ���
	public static String createNewId(){
		String id = UUID.randomUUID().toString() ;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("md5");
			byte[] bs = md.digest(id.getBytes()) ;
			BASE64Encoder base = new BASE64Encoder() ;
			id = base.encode(bs) ;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return id ;
	}
}
