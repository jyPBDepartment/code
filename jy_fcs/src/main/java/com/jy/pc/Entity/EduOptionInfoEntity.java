package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 选项信息表
 */
@Entity
@Table(name = "edu_option_info")
public class EduOptionInfoEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(columnDefinition = "varchar(36) comment '问题ID'")
	private String quId;
	@Column(columnDefinition = "varchar(5) comment '选项简写即ABCD'")
	private String title;
	@Column(columnDefinition = "varchar(255) comment '选项描述'")
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuId() {
		return quId;
	}

	public void setQuId(String quId) {
		this.quId = quId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
