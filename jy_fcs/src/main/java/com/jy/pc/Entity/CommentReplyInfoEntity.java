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
@Table(name="sas_comment_reply_info")
public class CommentReplyInfoEntity {
	@Id
	@GeneratedValue(generator="uuid")    
	@GenericGenerator(strategy = "uuid", name = "uuid")  
	//主键id
	private String id;
	//外键id - 评论信息
	@Column
	private String commentId;
	//回复内容
	@Column
	private String replyContent;
	//回复人
	@Column
	private String replyUserName;
	//回复时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyDate;
	//状态 - 0生效1系统禁用
	@Column(length=1)
	private String status;
}
