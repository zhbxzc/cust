package com.bincai.cloud.cust.domain.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.bincai.cloud.cust.domain.common.MongoDB;
import com.bincai.cloud.cust.domain.common.PhotosVO;



@Service
public class PhotoService {
	@Resource
	private MongoDB mongoDB;
	public static final Logger logger=LoggerFactory.getLogger(PhotoService.class);
	public String savephoto(PhotosVO photosVO){
		logger.info("开始保存照片");
		MongoTemplate mongoTemplate = mongoDB.getMongoTemplate();
		try {
			photosVO.set_id(UUID.randomUUID().toString().replace("-", ""));
			mongoTemplate.insert(photosVO, "photos");
		} catch (Exception e) {
			logger.warn("mongo存储照片时发生异常"+e);
			logger.info("保存照片结束");
			return "error";
		}
		logger.info("保存照片结束");
		return "success";
	}
}
