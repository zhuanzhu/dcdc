package com.egeo.components.utils;

import com.egeo.utils.Base64;
import com.egeo.utils.log.XLogger;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtil {
	private static final XLogger logger = XLogger.getLogger(ImageUtil.class);

	/**
	 * 根据图片链接转为base64数据
	 *
	 * @param imageUrl
	 * @return
	 * @throws Exception
	 */
	public static String getBase64ByUrl(String imageUrl) throws Exception {
		// new一个URL对象
		URL url = new URL(imageUrl);
		// 打开链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置请求方式为"GET"
		conn.setRequestMethod("GET");
		// 超时响应时间为5秒
		conn.setConnectTimeout(5 * 1000);
		// 通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = readInputStream(inStream);
		String s = "data:image/jpg;base64," + new String(Base64.getEncoder().encode(data));
		s = s.replaceAll("\\r", "").replaceAll("\\n", "");
		// System.out.println("图片base64字符串：" + s);
		int strLen = s.length();
		int fileSize = (strLen - (strLen / 8) * 2) / 1024;
		logger.info("文件大小:{}KB", fileSize);
		return s;
	}

	/**
	 * 根据图片链接压缩后转为base64数据
	 *
	 * @param imageUrl    图片URL
	 * @param desFileSize 指定图片大小，单位kb
	 * @return
	 * @throws Exception
	 */
	public static String getBase64ByCompressUrl(String imageUrl, long desFileSize) throws Exception {
		// new一个URL对象
		URL url = new URL(imageUrl);
		// 打开链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置请求方式为"GET"
		conn.setRequestMethod("GET");
		// 超时响应时间为5秒
		conn.setConnectTimeout(5 * 1000);
		// 通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = readInputStream(inStream);

		// 压缩图片
		data = PicUtils.compressPicForScale(data, desFileSize);

		String s = "data:image/jpg;base64," + new String(Base64.getEncoder().encode(data));
		s = s.replaceAll("\\r", "").replaceAll("\\n", "");
		System.out.println("图片base64字符串：" + s);
		int strLen = s.length();
		int fileSize = (strLen - (strLen / 8) * 2) / 1024;
		logger.info("文件大小:{}KB", fileSize);
		return s;
	}

	private static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}

}
