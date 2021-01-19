package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

//帖子点赞表
@Entity
@Table(name = "sas_circle_thumbs")
public class CircleThumbsEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id

	@Column(columnDefinition = "varchar(36) default '' comment '帖子Id'")
	private String circleId;// 用户Id

	@Column(columnDefinition = "varchar(36) default '' comment '点赞用户id'")
	private String thumbsUserId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCircleId() {
		return circleId;
	}

	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}

	public String getThumbsUserId() {
		return thumbsUserId;
	}

	public void setThumbsUserId(String thumbsUserId) {
		this.thumbsUserId = thumbsUserId;
	}

}
