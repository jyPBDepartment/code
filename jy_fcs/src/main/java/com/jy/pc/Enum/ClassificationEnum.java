package com.jy.pc.Enum;

/**
 * 定义【分类】编码，便于统一维护 建议全局使用，避免正式上线不便维护
 * 
 */
public enum ClassificationEnum {
	// 分类 -- 关键词 -- 看图识病关键词
	KEYWORD_CASEINFO("GJC0101", "看图识病关键词"),
	// 分类 -- 圈子发布类型
	POSTINFO_TYPE("QZFL001","圈子发布类型");
	private String code;
	private String name;

	ClassificationEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
