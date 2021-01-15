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
 * 圈子管理 - 评论信息实体
 * 
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
	// 评论人ID
	@Column
	private String commentUserId;
	// 评论时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;
	// 状态 - 0生效1系统禁用
	@Column(length = 1)
	private String status;

	@Column(columnDefinition = "varchar(255) default '' comment '评论人头像路径'")
	private String commentPic; //评论人头像路径
	
	public String getCommentPic() {
		return commentPic;
	}

	public void setCommentPic(String commentPic) {
		this.commentPic = commentPic;
	}

	// 外键id - 帖子信息
	@ManyToOne(optional = false)
	@JoinColumn(name = "postId", referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private PostInfoEntity postInfoEntity;
	
	@Column(columnDefinition = "int(2) default 1 comment '是否匿名'")
	private int is_anonymous; // 是否匿名，0是1否，默认1

	public int getIs_anonymous() {
		return is_anonymous;
	}

	public void setIs_anonymous(int is_anonymous) {
		this.is_anonymous = is_anonymous;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
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
