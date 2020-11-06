package com.jy.pc.VO;

import com.jy.pc.Entity.EduManualInfoEntity;

//手册列表页用VO
public class ManualListVO {
	// 主键ID
	private String id;
	// 标题
	private String title;
	// 主图路径
	private String url;
	// 学习数
	private int readNum;

	public ManualListVO() {
		super();
	}

	public ManualListVO(EduManualInfoEntity entity) {
		super();
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.url = entity.getUrl();
		this.readNum = entity.getStudyNum();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

}
