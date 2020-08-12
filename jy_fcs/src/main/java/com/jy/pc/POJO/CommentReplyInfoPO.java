package com.jy.pc.POJO;

import java.util.Date;

public class CommentReplyInfoPO {
		//主键id
		private String id;
		//回复内容
		private String replyContent;
		//回复人
		private String replyUserName;
		//回复时间
		private Date replyDate;
		//状态 - 0生效1系统禁用
		private String status;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getReplyContent() {
			return replyContent;
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
		public CommentReplyInfoPO(String id, String replyContent, String replyUserName, Date replyDate, String status) {
			super();
			this.id = id;
			this.replyContent = replyContent;
			this.replyUserName = replyUserName;
			this.replyDate = replyDate;
			this.status = status;
		}
		public CommentReplyInfoPO() {
			super();
		}
		
		
}
