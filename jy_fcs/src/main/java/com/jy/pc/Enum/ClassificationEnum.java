package com.jy.pc.Enum;

/**
 * 定义【分类】编码，便于统一维护 建议全局使用，避免正式上线不便维护
 * 
 */
public enum ClassificationEnum {
	// 分类 -- 关键词 -- 看图识病关键词
	KEYWORD_CASEINFO("GJC-KTSB", "看图识病关键词"),
	// 分类 -- 圈子发布类型
	POSTINFO_TYPE("QZFL001", "圈子发布类型"),
	//看图识病(病虫害)分类
	DIP_CLASS("KTSBFL001","看图识病分类"),
	//农作物分类
	CASE_CLASS("NZWFL001","农作物分类"),
	// 关键词查询分类
	KEYWORD_CLASS("GJCFL001", "关键词分类");
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
