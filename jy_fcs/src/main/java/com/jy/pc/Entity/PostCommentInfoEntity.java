package com.jy.pc.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="sas_post_comment_info")
public class PostCommentInfoEntity {
	@Id
	@GeneratedValue(generator="uuid")    
	@GenericGenerator(strategy = "uuid", name = "uuid")  
	//主键id
	private String id;
	//外键id - 帖子信息
	@Column
	private String postId;
	//评论内容
	@Column
	private String commentContent;
	//评论人
	@Column
	private String commentUserName;
	//评论时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;
	//状态 - 0生效1系统禁用
	@Column(length=1)
	private String status;
}
