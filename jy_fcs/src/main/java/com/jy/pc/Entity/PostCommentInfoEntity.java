package com.jy.pc.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 圈子管理 - 评论信息实体
 * @author admin
 *
 */
@Entity
@Table(name = "sas_post_comment_info")
public class PostCommentInfoEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	// 主键id
	private String id;

	// 评论内容
	@Column
	private String commentContent;
	// 评论人
	@Column
	private String commentUserName;
	// 评论时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;
	// 状态 - 0生效1系统禁用
	@Column(length = 1)
	private String status;

	// 外键id - 帖子信息
	@ManyToOne(optional = false)
	@NotFound(action=NotFoundAction.IGNORE) 
	@JoinColumn(name = "postId", referencedColumnName = "id")
	private PostInfoEntity postInfoEntity;
	
	//评论下的回复
	@ElementCollection(targetClass=PostCommentInfoEntity.class)
	private List<CommentReplyInfoEntity> replyList;
	
	public List<CommentReplyInfoEntity> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<CommentReplyInfoEntity> replyList) {
		this.replyList = replyList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PostInfoEntity getPostInfoEntity() {
		return postInfoEntity;
	}

	public void setPostInfoEntity(PostInfoEntity postInfoEntity) {
		this.postInfoEntity = postInfoEntity;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentUserName() {
		return commentUserName;
	}

	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
