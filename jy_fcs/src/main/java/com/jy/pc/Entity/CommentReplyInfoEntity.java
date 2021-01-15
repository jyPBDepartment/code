package com.jy.pc.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 *	回复管理
 */
@Entity
@Table(name="sas_comment_reply_info")
public class CommentReplyInfoEntity {
	@Id
	@GeneratedValue(generator="uuid")    
	@GenericGenerator(strategy = "uuid", name = "uuid")  
	//主键id
	private String id;
	//外键id - 评论信息
	// 外键id - 帖子信息
	@ManyToOne(optional=true )//可选属性optional=false,表示author不能为空。删除文章，不影响用户
	@NotFound(action=NotFoundAction.IGNORE) 
	@JoinColumn(name="commentId", referencedColumnName = "id")
	private PostCommentInfoEntity postCommentInfoEntity;
	//回复内容
	@Column
	private String replyContent;
	//回复人
	@Column
	private String replyUserName;
	//回复人ID
	@Column
	private String replyUserId;
	//回复时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyDate;
	//状态 - 0生效1系统禁用
	@Column(length=1)
	private String status;
	
	@Column(columnDefinition = "varchar(255) default '' comment '回复人头像路径'")
	private String replyPic; //回复人头像路径
	
	public String getReplyPic() {
		return replyPic;
	}
	public void setReplyPic(String replyPic) {
		this.replyPic = replyPic;
	}
	@Column(columnDefinition = "int(2) default 0 comment '是否匿名 '")
	private int isAnonymous; // 是否匿名，0否1是，默认0
	
	public int getIsAnonymous() {
		return isAnonymous;
	}
	public void setIsAnonymous(int isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public PostCommentInfoEntity getPostCommentInfoEntity() {
		return postCommentInfoEntity;
	}
	public void setPostCommentInfoEntity(PostCommentInfoEntity postCommentInfoEntity) {
		this.postCommentInfoEntity = postCommentInfoEntity;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public String getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyUserName() {
		return replyUserName;
	}
	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
