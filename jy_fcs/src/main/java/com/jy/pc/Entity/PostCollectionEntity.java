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

//帖子收藏表
@Entity
@Table(name = "sas_post_collection")
public class PostCollectionEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	private String id;// 主键Id

//	// 外键id - 帖子信息
//	@ManyToOne(optional = false)
//	@JoinColumn(name = "circleId", referencedColumnName = "id")
//	@NotFound(action = NotFoundAction.IGNORE)
//	private PostInfoEntity postInfoEntity;

	@Column(columnDefinition = "varchar(36) default '' comment '帖子Id'")
	private String circleId;// 用户Id
	
	@Column(columnDefinition = "varchar(36) default '' comment '用户Id'")
	private String userId;// 用户Id

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
