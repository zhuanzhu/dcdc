package com.egeo.components.product.controller.api;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.egeo.components.product.business.PictureManage;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.vo.PictureVO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.GetToken;
import com.egeo.utils.ID_Utils;
import com.egeo.utils.Upload;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/product/picture")
public class PictureAction extends BaseSpringController {

	@Resource(name = "picture")
	private PictureManage pictureManage;
	
	@Autowired
	private Upload uploadService;
	@Autowired
	private GetToken getToken;
	@RequestMapping("savePicture")
	@ResponseBody
	public JsonResult<Long> savePicture(PictureVO pictureVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			pictureVO.setPlatformId(platformId);
		}
		logger.info("开始保存图片信息!");
		Long rows = null;
		JsonResult<Long> result = new JsonResult<Long>();
		rows = pictureManage.saveProduct(pictureVO);
		result.setData(rows);
		return result;
	}

	@RequestMapping(value = "findById")
	@ResponseBody
	public JsonResult<PictureDTO> findById(PictureVO pictureVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			pictureVO.setPlatformId(platformId);
		}
		logger.info("开始根据id查询图片信息");
		JsonResult<PictureDTO> result = new JsonResult<PictureDTO>();
		PictureDTO pictureDTO = pictureManage.findById(pictureVO);
		result.setData(pictureDTO);
		return result;
	}

	@RequestMapping(value = "findAll")
	@ResponseBody
	public JsonResult<List<PictureDTO>> findAll(PictureVO pictureVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			pictureVO.setPlatformId(platformId);
		}
		logger.info("开始根据条件查询图片信息");
		JsonResult<List<PictureDTO>> result = new JsonResult<List<PictureDTO>>();
		List<PictureDTO> list = pictureManage.findAll(pictureVO);
		result.setData(list);
		return result;
	}
	/**
	 * 根据spuid查询su轮播图信息
	 * @param standardProductUnitId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "findByStandardUnitId")
	@ResponseBody
	public JsonResult<List<String>> findByStandardUnitId(Long standardUnitId) {
		logger.info("开始根据条件查询图片信息");
		JsonResult<List<String>> result = new JsonResult<List<String>>();
		List<String> list = pictureManage.findByStandardUnitId(standardUnitId);
		result.setData(list);
		return result;
	}

	@RequestMapping("delete")
	@ResponseBody
	public JsonResult<String> delete(PictureVO pictureVO) {
		logger.info("开始根据id删除图片信息!");
		String rows = null;
		JsonResult<String> result = new JsonResult<String>();
		rows = pictureManage.delete(pictureVO);
		result.setData(rows);
		return result;
	}

	@RequestMapping("deleteByIds")
	@ResponseBody
	public JsonResult<String> deleteByIds(String ids) {
		logger.info("开始根据id批量删除图片信息!");
		String rows = null;
		JsonResult<String> result = new JsonResult<String>();
		rows = pictureManage.deleteByIds(ids);
		result.setData(rows);
		return result;
	}

	// 上传图片
	@RequestMapping(value = "upload")
	@ResponseBody
	public JsonResult<String> upload(@RequestParam(required = true) MultipartFile uploadfile) throws IOException {
		JsonResult<String> result = new JsonResult<String>();
		// 上传文件写磁盘
		String path = System.getProperty("global.config.path");
		// 生成一个新的文件名
		// 取原始文件名
		String oldName = uploadfile.getOriginalFilename();
		// 生成新文件名
		// UUID.randomUUID();
		String newName = ID_Utils.genImageName();
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		StringBuffer upload = new StringBuffer();
		upload.append(path);
		upload.append(File.separator);
		upload.append("picture");
		upload.append(File.separator);
		upload.append(newName);
		FileUtils.writeByteArrayToFile(new File(upload.toString()), uploadfile.getBytes());
		StringBuffer uploadQiNiu = new StringBuffer();
		uploadQiNiu.append(path);
		uploadQiNiu.append(File.separator);
		uploadQiNiu.append("picture");
		String load = uploadService.upload(uploadQiNiu.toString(), newName);
		File file = new File(upload.toString());
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (!file.delete()) {
				throw new BusinessException("删除单个文件" + upload.toString() + "失败！");
			}
		}
		result.setData(load);
		return result;
	}

	// 上传图片
	@RequestMapping(value = "uploadToId")
	@ResponseBody
	public JsonResult<Long> uploadToId(@RequestParam(required = true) MultipartFile uploadfile) throws IOException {
		JsonResult<Long> result = new JsonResult<Long>();
		// 上传文件写磁盘
		String path = System.getProperty("global.config.path");
		// 生成一个新的文件名
		// 取原始文件名
		String oldName = uploadfile.getOriginalFilename();
		// 生成新文件名
		// UUID.randomUUID();
		String newName = ID_Utils.genImageName();
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		StringBuffer upload = new StringBuffer();
		upload.append(path);
		upload.append(File.separator);
		upload.append("picture");
		upload.append(File.separator);
		upload.append(newName);
		FileUtils.writeByteArrayToFile(new File(upload.toString()), uploadfile.getBytes());
		String load = uploadService.upload(upload.toString(), newName);
		File file = new File(upload.toString());
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (!file.delete()) {
				throw new BusinessException("删除单个文件" + upload.toString() + "失败！");
			}
		}
		PictureVO pictureVO = new PictureVO();
		pictureVO.setUrl(load);
		Long pictureId = pictureManage.saveProduct(pictureVO);
		result.setData(pictureId);
		return result;
	}

	// 删除上传图片
	@RequestMapping(value = "deletePicture")
	@ResponseBody
	public JsonResult<String> deletePicture(String picture) {
		List<String> list = Arrays.asList(picture.split("/"));
		JsonResult<String> result = new JsonResult<String>();
		int i = getToken.deletePicture(list.get(list.size() - 1));
		if (i == 200) {
			pictureManage.deletePicture(picture);
			result.setData("删除成功");
			return result;
		}
		result.setData("删除失败");
		result.setCode(1);
		return result;
	}

	// 删除上传图片
	@RequestMapping(value = "deletePictureById")
	@ResponseBody
	public JsonResult<String> deletePictureById(PictureVO pictureVO) {
		JsonResult<String> result = new JsonResult<String>();
		// 根据id查询图片信息
		PictureDTO pictureDTO = pictureManage.findById(pictureVO);
		int i = getToken.deletePicture(pictureDTO.getUrl());
		if (i == 200) {
			pictureManage.deletePicture(pictureDTO.getUrl());
			result.setData("删除成功");
			return result;
		}
		result.setData("删除失败");
		result.setCode(1);
		return result;
	}

	// 获取
	/*@RequestMapping(value = "base642ImageFile")
	@ResponseBody
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null)
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// 解密
			byte[] b = decoder.decodeBuffer(imgStr);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}*/
}
