package com.egeo.components.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


public class FileDownUtils {
	private static Logger logger = LoggerFactory.getLogger(FileDownUtils.class);
	/**
	 * 从网络Url中下载文件
	 * @param urlStr
	 * @param fileName
	 * @param savePath
	 * @throws IOException
	 */
	public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                //设置超时间为3秒
		conn.setConnectTimeout(3*1000);
		//防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		//得到输入流
		InputStream inputStream = conn.getInputStream();
		//获取自己数组
		byte[] getData = readInputStream(inputStream);

		//文件保存位置
		File saveDir = new File(savePath);
		if(!saveDir.exists()){
			saveDir.mkdir();
		}
		File file = new File(saveDir+File.separator+fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(getData);
		if(fos!=null){
			fos.close();
		}
		if(inputStream!=null){
			inputStream.close();
		}


		System.out.println("info:"+url+" download success");

	}

	/**
	 * 从网络Url中下载文件
	 * @param urlStr
	 * @throws IOException
	 */
	public static InputStream  downLoadInputStreamFromUrl(String urlStr) throws IOException{
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                //设置超时间为3秒
		conn.setConnectTimeout(5*1000);
		//防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		//得到输入流
		return conn.getInputStream();
	}

	public static String downLoadInputStreamFromUrlBase64Str(String urlStr) {
		String result = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		try {
			if(StringUtils.isEmpty(urlStr)){
				logger.warn("下载url[{}]为空",urlStr);
				return result;
			 }
			 conn = getHttpURLConnection(urlStr);
			 inputStream = getInputStream(conn);
			 if(inputStream != null){
					return Base64.getEncoder().encodeToString(StreamUtils.input2byte(inputStream));
				}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			logger.error("下载url[{}]异常{}",urlStr,e);
		}finally {
			closeHttpURLConnection(conn);
		}
		return result;
	}

	private static HttpURLConnection getHttpURLConnection(String urlStr) throws IOException{
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                //设置超时间为3秒
		conn.setConnectTimeout(5*1000);
		//防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		return conn;
	}

	private static void closeHttpURLConnection(HttpURLConnection conn){
		if(null !=conn){
			conn.disconnect();
		}
	}

	private static InputStream getInputStream(HttpURLConnection conn) throws IOException{
		return conn.getInputStream();
	}




	/**
	 * 从输入流中获取字节数组
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static  byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}




	public static void main(String[] args) {
		try{
			//System.out.println(downLoadInputStreamFromUrlBase64Str("http://192.168.26.92:8080/rzj/signature/contract/download?account=18129836816&contractId=154227118301000001"));

			//System.out.println(downLoadInputStreamFromUrlBase64Str("http://bktest-10010.oss-cn-hangzhou.aliyuncs.com/1711279571/f261a37cf26a4a16bd2951db119216b0.png"));
			downLoadFromUrl("http://image.tongrong001.com/bill/TR88/JCATbankcardzcm/out/20180425/201804250104073_2018-04-25.xlsx", "test.xlsx", "d://");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
