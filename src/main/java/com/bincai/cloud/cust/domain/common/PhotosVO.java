package com.bincai.cloud.cust.domain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PhotosVO {
	@ApiModelProperty(value="图片id")
	private String _id;
	private String _class=null;
	@ApiModelProperty(value="图片所有者")
	private String whose;
	@ApiModelProperty(value="图片文本")
	private String photo;
	@ApiModelProperty(value="图片文件")
	public String get_class() {
		return _class;
	}
	public void set_class(String _class) {
		this._class = _class;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getWhose() {
		return whose;
	}
	public void setWhose(String whose) {
		this.whose = whose;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
