package com.bincai.cloud.cust.domain.controller;


import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.alibaba.fastjson.JSON;
import com.bincai.cloud.cust.domain.common.PhotosVO;
import com.bincai.cloud.cust.domain.service.PhotoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cust")
@Api(value="photo",description="客户相片领域服务接口")
public class PhotoController {
	@Resource
	private PhotoService mongoService;
	@ApiOperation(value="图片保存",notes="图片保存接口")
	@ApiImplicitParam(name="photosVO",value="图片对象",required=true,paramType="body")
	@RequestMapping(value="/photos",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String savephoto(@RequestBody PhotosVO photosVO){
		String result = mongoService.savephoto(photosVO);
		return JSON.toJSONString(result);
	}
}
