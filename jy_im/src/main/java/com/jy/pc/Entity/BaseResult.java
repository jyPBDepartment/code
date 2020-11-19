package com.jy.pc.Entity;

public class BaseResult {
	private int code;
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		switch (this.code) {
		case 200:
			msg = "操作成功";
			break;
		case 201:
			msg = "客户端版本不对，需升级sdk";
			break;
		case 301:
			msg = "被封禁";
			break;
		case 302:
			msg = "用户名或密码错误";
			break;
		case 315:
			msg = "IP限制";
			break;
		case 403:
			msg = "非法操作或没有权限";
			break;
		case 404:
			msg = "对象不存在";
			break;
		case 405:
			msg = "参数长度过长";
			break;
		case 406:
			msg = "对象只读";
			break;
		case 408:
			msg = "客户端请求超时";
			break;
		case 413:
			msg = "验证失败(短信服务)";
			break;
		case 414:
			msg = "参数错误";
			break;
		case 415:
			msg = "客户端网络问题";
			break;
		case 416:
			msg = "频率控制";
			break;
		case 417:
			msg = "重复操作";
			break;
		case 418:
			msg = "通道不可用(短信服务)";
			break;
		case 419:
			msg = "帐号被禁言";
			break;
		case 431:
			msg = "HTTP重复请求";
			break;
		case 500:
			msg = "服务器内部错误";
			break;
		case 503:
			msg = "服务器繁忙";
			break;
		case 508:
			msg = "消息撤回时间超限";
			break;
		case 509:
			msg = "无效协议";
			break;
		case 998:
			msg = "解包错误";
			break;
		case 999:
			msg = "打包错误";
			break;
		case 801:
			msg = "群人数达到上限";
			break;
		case 802:
			msg = "没有权限";
			break;
		case 803:
			msg = "群不存在";
			break;
		case 804:
			msg = "用户不在群";
			break;
		case 805:
			msg = "群类型不匹配";
			break;
		case 806:
			msg = "创建群数量达到限制";
			break;
		case 807:
			msg = "群成员状态错误";
			break;
		case 808:
			msg = "申请成功";
			break;
		case 809:
			msg = "已经在群内";
			break;
		case 810:
			msg = "邀请成功";
			break;
		case 811:
			msg = "@账号数量超过限制";
			break;
		case 812:
			msg = "群禁言，普通成员不能发送消息";
			break;
		case 813:
			msg = "群拉人部分成功";
			break;
		case 814:
			msg = "禁止使用群组已读服务";
			break;
		case 815:
			msg = "群管理员人数超过上限";
			break;
		default:
			msg = "未知状态码";
			break;
		}
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
