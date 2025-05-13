package com.egeo.components.finance.common;

import java.math.BigDecimal;

import com.egeo.components.config.dto.SaltDTO;
import com.egeo.util.security.MD5Util;

/**
 * 对Secret库加密的信息进行校验
 * @author GRACIA
 *
 */
public class CiphertextUtil {
	
	/**
	 * 金额余额校验
	 * @param balance 余额
	 * @param salt 盐DTO
	 * @param curCipher 当前密文
	 * @return
	 */
	public static boolean balanceValidate(BigDecimal balance,SaltDTO salt,String curCipher){
		return MD5Util.md5Valid(balance.toString(), salt.getSaltValue(), curCipher);
	}
	
//	/**
//	 * 生成uuid,加密值,sql
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		String salt=SaltUtils.getRandomSalt();
//		String uuid=UUID.uuid();
//		String cipher=MD5Util.MD5Salt("123456", "wj&2p$4$tw44y#31");
//		String insertSql="insert into salt (uuid,salt_value) values('"+uuid+"','"+salt+"')";
//		System.out.println("uuid="+uuid);
//		System.out.println("cipher="+cipher);
//		System.out.println("insertSql="+insertSql);
//		
//		System.out.println(MD5Util.MD5Salt(String.valueOf(300.0), "k@hg&mq6zlu4ioto"));
//		//213781683256d837d130eb3e0118bed9
//	}
}
