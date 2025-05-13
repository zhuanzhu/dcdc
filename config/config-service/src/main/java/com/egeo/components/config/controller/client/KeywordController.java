package com.egeo.components.config.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.client.KeywordClient;
import com.egeo.components.config.dto.KeywordDTO;
import com.egeo.components.config.dto.KeywordPageDTO;
import com.egeo.components.config.service.read.KeywordReadService;
import com.egeo.components.config.service.write.KeywordWriteService;
import com.egeo.orm.PageResult;

@Controller
@RequestMapping("/client/config/keyword") 
public class KeywordController implements KeywordClient{ 

	@Autowired
	private KeywordReadService keywordReadService;
	@Autowired
	private KeywordWriteService keywordWriteService;


	@Override
	@RequestMapping(value = "/findKeywordById", method = { RequestMethod.POST })
	@ResponseBody
	public KeywordDTO findKeywordById(@RequestBody KeywordDTO dto) {
		return keywordReadService.findKeywordById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findKeywordOfPage", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<KeywordDTO> findKeywordOfPage(@RequestBody KeywordPageDTO dto) {
		return keywordReadService.findKeywordOfPage(dto.getDto(),dto.getPage());
	} 
 
	@Override
	@RequestMapping(value = "/findKeywordAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<KeywordDTO> findKeywordAll(@RequestBody KeywordDTO dto) {
		return keywordReadService.findKeywordAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/insertKeywordWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertKeywordWithTx(@RequestBody KeywordDTO dto) {
		return keywordWriteService.insertKeywordWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/updateKeywordWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateKeywordWithTx(@RequestBody KeywordDTO dto) {
		return keywordWriteService.updateKeywordWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/deleteKeywordWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteKeywordWithTx(@RequestBody KeywordDTO dto) {
		return keywordWriteService.deleteKeywordWithTx(dto);
	} 
 
}