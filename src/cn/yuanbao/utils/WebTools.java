package cn.yuanbao.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import sun.misc.BASE64Encoder;

//用来做一些常用的一些操作
public class WebTools {

	//获取一个新的唯一id的方法
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
