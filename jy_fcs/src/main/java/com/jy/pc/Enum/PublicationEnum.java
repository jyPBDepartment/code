package com.jy.pc.Enum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * type -- 交易类型，0.收购1.出售2.出租3.播种4.植保5.收割
 * category -- 交易类别，0.玉米1.农机2.水稻3高粱4黄豆
 * @author admin
 *
 */
public enum PublicationEnum {
	SERVICE("0", new HashMap<String, List<String>>() {
		private static final long serialVersionUID = 1L;
		{
			put("type", Arrays.asList("3", "4", "5"));
			put("category", Arrays.asList("0", "2", "3", "4"));
		}
	}),
	GRAIN("1", new HashMap<String, List<String>>() {
		private static final long serialVersionUID = 1L;
		{
			put("type", Arrays.asList("0", "1"));
			put("category", Arrays.asList("0", "2", "3", "4"));
		}
	}),
	MACHINE("2", new HashMap<String, List<String>>() {
		private static final long serialVersionUID = 1L;
		{
			put("type", Arrays.asList("0", "1","2"));
			put("category", Arrays.asList("1"));
		}
	});

	private String type;
	private Map<String, List<String>> map;

	PublicationEnum(String type, Map<String, List<String>> map) {
		this.type = type;
		this.map = map;
	}

	public static  Map<String, List<String>> getValueByType(String type) {
		for (PublicationEnum item : PublicationEnum.values()) {
			if (type.equals(item.getType())) {
				return item.map;
			}
		}
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, List<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<String>> map) {
		this.map = map;
	}
}
