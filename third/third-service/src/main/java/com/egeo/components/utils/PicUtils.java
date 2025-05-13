package com.egeo.components.utils;

import com.egeo.utils.log.XLogger;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PicUtils {
	private static final XLogger logger = XLogger.getLogger(PicUtils.class);

	/**
	 * 根据指定大小压缩图片
	 *
	 * @param imageBytes  源图片字节数组
	 * @param desFileSize 指定图片大小，单位kb
	 * @param imageId     影像编号
	 * @return 压缩质量后的图片字节数组
	 */
	public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize) {
		if (imageBytes == null || imageBytes.length <= 0 || imageBytes.length < desFileSize * 1024) {
			return imageBytes;
		}
		long srcSize = imageBytes.length;
		double accuracy = 0.8f;
		// double accuracy = getAccuracy(srcSize / 1024);
		try {
			while (imageBytes.length > desFileSize * 1024) {
				ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
				Thumbnails.of(inputStream).scale(accuracy).outputQuality(1).toOutputStream(outputStream);
				imageBytes = outputStream.toByteArray();
			}
			logger.info("【图片压缩】| 图片原大小={}kb | 压缩后大小={}kb", srcSize / 1024, imageBytes.length / 1024);
		} catch (Exception e) {
			logger.error("【图片压缩】msg=图片压缩失败!", e);
		}
		return imageBytes;
	}

	/**
	 * 自动调节精度(经验数值)
	 *
	 * @param size 源图片大小
	 * @return 图片压缩质量比
	 */

//	private static double getAccuracy(long size) {
//		double accuracy;
//		if (size < 900) {
//			accuracy = 0.85;
//		} else if (size < 2047) {
//			accuracy = 0.8;
//		} else if (size < 3275) {
//			accuracy = 0.44;
//		} else {
//			accuracy = 0.4;
//		}
//		return accuracy;
//	}

}
