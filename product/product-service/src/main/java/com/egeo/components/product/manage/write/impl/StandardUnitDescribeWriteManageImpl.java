package com.egeo.components.product.manage.write.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.egeo.components.product.dao.write.StandardUnitDescribeWriteDAO;
import com.egeo.components.product.manage.write.StandardUnitDescribeWriteManage;
import com.egeo.components.product.po.StandardUnitDescribePO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.GetToken;
import com.egeo.utils.UUIDBuild;
import com.egeo.utils.Upload;

@Service
public class StandardUnitDescribeWriteManageImpl implements StandardUnitDescribeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitDescribeWriteDAO standardUnitDescribeWriteDAO;
	
	private static String contentFront = "<html><head><title>Info" 
			+"</title><meta http-equiv='Content-Type' content='text/html;charset=utf-8'>" 
			+ "<meta name='viewport' content='width=device-width, initial-scale=1, user-scalable=no'>"
			+ "<style type='text/css'>"
			+ "body{margin:0;padding:0;}img{width:100%;}"
			+ "</style></head><body>";
	private static String contentBack = "</body></html>";
	
	@Value(value="${qiniu.picture}")
	private String qiniuPicture;
	
	@Autowired
	private GetToken qiNiu;

	@Autowired
	private Upload qiNiuUpload;
	@Override
	public Long insertStandardUnitDescribeWithTx(StandardUnitDescribePO po) {
		
		int i ;
		try {
			//上传html生成url
			String contentUrl = uploadContenUrl(po);
			po.setContentUrl(contentUrl);
				i = standardUnitDescribeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (Exception e) {
				logger.error("", e);
				throw new BusinessException("添加su商品详情异常!");
			}
		return po.getId();
	}
	
	private String uploadContenUrl(StandardUnitDescribePO po){
		// 删除七牛图片
		if(EmptyUtil.isNotEmpty(po.getContentUrl())){
			String[] split = po.getContentUrl().split("/");
			int length = split.length;
			String string = split[length - 1];
			int i = qiNiu.deletePicture(string);
			if (i != 200) {
				throw new BusinessException("删除七牛su商品详情url失败");
			}
		}
		
		// 上传七牛图片
		try {
			// 上传文件写磁盘
			String path = System.getProperty("global.config.path");
			// 生成一个新的文件名
			String newName = UUIDBuild.getUUID() + "StandardUnit" + po.getStandardUnitId();
			StringBuffer upload = new StringBuffer();
			upload.append(path);
			upload.append(File.separator);
			upload.append("html");
			upload.append(File.separator);
			upload.append(newName  + ".html");
			String valueOf = String.valueOf(contentFront + po.getContent() + contentBack);
			FileUtils.writeByteArrayToFile(new File(upload.toString()), valueOf.getBytes());
			StringBuffer uploadQiNiu = new StringBuffer();
			uploadQiNiu.append(path);
			uploadQiNiu.append(File.separator);
			uploadQiNiu.append("html");
			uploadQiNiu.append(File.separator);
			uploadQiNiu.append(newName  + ".html");
			String contentUrl = qiNiuUpload.upload(uploadQiNiu.toString(), newName);
			File file = new File(upload.toString());
			// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
			if (file.exists() && file.isFile()) {
				if (file.delete()) {
					logger.info("删除单个文件" + upload.toString() + "成功！");
				} else {
					logger.info("删除单个文件" + upload.toString() + "失败！");
				}
			} else {
				logger.info("删除单个文件" + upload.toString() + "不存在！");
			}
			return qiniuPicture + "/" + contentUrl;
		} catch (IOException e) {
			logger.info("上传html文件异常");
			throw new BusinessException("上传html文件异常");
		}
	}

	@Override
	public int updateStandardUnitDescribeWithTx(StandardUnitDescribePO po) {
		int i;
		//上传html生成url
		String contentUrl = uploadContenUrl(po);
		po.setContentUrl(contentUrl);
		i = standardUnitDescribeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitDescribeWithTx(StandardUnitDescribePO po) {
		int i;
		i = standardUnitDescribeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据suid同步su商品详情信息
	 * @param standardUnitDescribeDTO
	 * @return
	 */
	@Override
	public int updateByStandardUnitIdWithTx(StandardUnitDescribePO po) {
		//上传html生成url
		String contentUrl = uploadContenUrl(po);
		po.setContentUrl(contentUrl);
		return standardUnitDescribeWriteDAO.updateByStandardUnitIdWithTx(po);
	}

	@Override
	public void saveStandardUnitDescribe(List<StandardUnitDescribePO> standardUnitDescribePOList) {
		try{
		standardUnitDescribeWriteDAO.saveStandardUnitDescribe( standardUnitDescribePOList);
	}catch (Exception e){
		logger.error("standardUnitDescribe保存失败"+e.getMessage());

	}
	}

	@Override
	public void updateStandardUnitDescribeSWithTx(StandardUnitDescribePO standardUnitDescribePO) {
		standardUnitDescribeWriteDAO.updateStandardUnitDescribeSWithTx( standardUnitDescribePO);
	}
}
	