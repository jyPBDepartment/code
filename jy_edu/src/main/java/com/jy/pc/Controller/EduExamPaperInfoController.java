package com.jy.pc.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.EduExamPaperInfoEntity;
import com.jy.pc.Entity.EduOptionInfoEntity;
import com.jy.pc.Entity.EduQuestionExamLinkEntity;
import com.jy.pc.Entity.EduQuestionInfoEntity;
import com.jy.pc.Entity.EduUserExamEntity;
import com.jy.pc.Service.EduExamPaperInfoService;
import com.jy.pc.Service.EduOptionInfoService;
import com.jy.pc.Service.EduQuestionExamService;
import com.jy.pc.Service.EduQuestionInfoService;
import com.jy.pc.Service.EduUserExamService;
import com.jy.pc.VO.AnswerVO;
import com.jy.pc.VO.ExamResultVO;
import com.jy.pc.VO.ExamReturnAnsVO;
import com.jy.pc.VO.ExamReturnQueVO;
import com.jy.pc.VO.ExamReturnTableVO;
import com.jy.pc.VO.ExamReturnTotal;

/**
 * 试卷信息表Controller
 */
@Controller
@RequestMapping(value = "examPaperInfo")
public class EduExamPaperInfoController {
	@Autowired
	private EduExamPaperInfoService eduExamPaperInfoService;
	@Autowired
	private EduQuestionInfoService eduQuestionInfoService;
	@Autowired
	private EduOptionInfoService eduOptionInfoService;
	@Autowired
	private EduUserExamService eduUserExamService;
	@Autowired
	private EduQuestionExamService eduQuestionExamService;

	// 批卷并返回考试结果
	@RequestMapping(value = "/submitExam")
	@ResponseBody
	public Map<String, Object> submitExam(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		String s = res.getParameter("entity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		ExamResultVO entity = jsonObject.toJavaObject(ExamResultVO.class);
		EduExamPaperInfoEntity exam = eduExamPaperInfoService.findId(entity.getExamId());
		List<AnswerVO> answerList = entity.getAnswerList();
		// 考试结果包装类
		ExamReturnTotal total = new ExamReturnTotal();
		// 选择题统计项
		ExamReturnTableVO table1 = new ExamReturnTableVO(0, 0, 0, 0);
		// 判断题统计项
		ExamReturnTableVO table2 = new ExamReturnTableVO(1, 0, 0, 0);
		List<ExamReturnTableVO> examTableInfo = new ArrayList<ExamReturnTableVO>();
		List<ExamReturnQueVO> examTableList = new ArrayList<ExamReturnQueVO>();
		List<EduOptionInfoEntity> options = null;
		EduQuestionInfoEntity que = null;
		for (AnswerVO answer : answerList) {
			que = eduQuestionInfoService.findId(answer.getAnswerId());
			if (answer.getAnswerCode().equals(que.getAnswer())) {
				// 这道题答对了
				if (que.getQuType() == 0) {
					table1.addQueNum(1);
					table1.addGetGrade(que.getScore());
				}
				if (que.getQuType() == 1) {
					table2.addQueNum(1);
					table2.addGetGrade(que.getScore());
				}
				ExamReturnQueVO item = new ExamReturnQueVO(que.getQuType(), answer.getSerialNumber(), que.getScore(),
						que.getAnswer(), que.getQuContent(), 1);
				examTableList.add(item);
				item.setQueChoose(answer.getAnswerCode());
			} else {
				// 这道题打错了
				if (que.getQuType() == 0) {
					table1.addQueNum(1);
					table1.addWrongNum(1);
				}
				if (que.getQuType() == 1) {
					table2.addQueNum(1);
					table2.addWrongNum(1);
				}
				ExamReturnQueVO item = new ExamReturnQueVO(que.getQuType(), answer.getSerialNumber(), que.getScore(),
						que.getAnswer(), que.getQuContent(), 2);
				// 查询出该题目所有答案
				options = eduOptionInfoService.findquestionId(que.getId());
				item.setQueAnalysis(castP2V(options));
				item.setQueChoose(answer.getAnswerCode());
				examTableList.add(item);
			}
		}
		examTableInfo.add(table1);
		examTableInfo.add(table2);
		total.setExamTableList(examTableList);
		total.setExamTableInfo(examTableInfo);
		EduUserExamEntity eduUserExamEntity = new EduUserExamEntity();
		eduUserExamEntity.setExamId(entity.getExamId());
		eduUserExamEntity.setExamDate(new Date());
		eduUserExamEntity.setIsPass(table1.getGetGrade()+table2.getGetGrade()>=exam.getPassScore()?1:0);
		eduUserExamEntity.setScore(table1.getGetGrade()+table2.getGetGrade());
		eduUserExamEntity.setUserId(entity.getUserId());
		//清空旧记录
		eduUserExamService.deleteByExam(entity.getUserId(), entity.getExamId());
		eduUserExamService.save(eduUserExamEntity);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", total);
		return map;
	}

	private List<ExamReturnAnsVO> castP2V(List<EduOptionInfoEntity> options) {
		List<ExamReturnAnsVO> list = new ArrayList<ExamReturnAnsVO>();
		for (EduOptionInfoEntity option : options) {
			ExamReturnAnsVO vo = new ExamReturnAnsVO();
			vo.setQueIndex(option.getTitle());
			vo.setQueSelectAnswer(option.getContent());
			list.add(vo);
		}
		return list;
	}

	// 查询 分页
	@RequestMapping(value = "/findByName")
	@ResponseBody
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "createBy") String createBy, @RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduExamPaperInfoEntity> questionList = eduExamPaperInfoService.findListByName(createBy, status, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", questionList);
		return map;
	}

	// 添加
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req,
			EduExamPaperInfoEntity eduExamPaperInfoEntity, @RequestParam(name = "questionId") String questionId) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			eduExamPaperInfoService.saveQuest(res, req, eduExamPaperInfoEntity, questionId);
			map.put("state", "0");
			map.put("message", "添加成功");

		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "添加失败");

		}
		return map;
	}

	// 查询试卷预览结果
	@RequestMapping(value = "/preview")
	@ResponseBody
	public Map<String, Object> findQuestion(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "idArray") String ids) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String[] array = ids.split(",");
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}

		List<EduQuestionInfoEntity> eduQuestionInfo = eduQuestionInfoService.findListByIds(list);
		Map<String, Object> item = new HashMap<String, Object>();
		for (int i = 0; i < eduQuestionInfo.size(); i++) {
			List<EduOptionInfoEntity> optionList = eduOptionInfoService.findquestionId(eduQuestionInfo.get(i).getId());
			eduQuestionInfo.get(i).setOptionList(optionList);
		}

		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", eduQuestionInfo);
		return map;
	}

	// 启用/禁用
	@RequestMapping(value = "/enable")
	@ResponseBody
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") Integer status, @RequestParam(name = "id") String id,
			@RequestParam(name = "updateUser") String updateUser) {
		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		EduExamPaperInfoEntity eduExamPaperInfoEntity = eduExamPaperInfoService.findId(id);
		eduExamPaperInfoEntity.setStatus(status);
		eduExamPaperInfoEntity.setUpdateDate(date);
		eduExamPaperInfoEntity.setUpdateBy(updateUser);
		boolean result = true;
		// 启用
		if (status.equals(0)) {
			map.put("state", "0");
			map.put("message", "启用成功");
		}
		// 禁用
		if (status.equals(1)) {
			map.put("state", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		eduExamPaperInfoService.enable(eduExamPaperInfoEntity, result);
		return map;
	}

	// 通过id查询
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		EduExamPaperInfoEntity eduExamPaperInfoEntity = eduExamPaperInfoService.findId(id);
		List<EduQuestionExamLinkEntity> questionExamLink = eduQuestionExamService.findExamId(id);
		List<EduQuestionInfoEntity> questList = new ArrayList<EduQuestionInfoEntity>();
		for (int i = 0; i < questionExamLink.size(); i++) {
			EduQuestionInfoEntity question = eduQuestionInfoService.findId(questionExamLink.get(i).getQuestionId());
			questList.add(question);
		}
		map.put("state", "0");
		map.put("data", eduExamPaperInfoEntity);
		map.put("questData", questList);
		return map;
	}

	// 修改
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req,
			EduExamPaperInfoEntity eduExamPaperInfoEntity, @RequestParam(name = "questionId") String questionId) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			eduExamPaperInfoService.updateQuest(res, req, eduExamPaperInfoEntity, questionId);
			map.put("state", "0");
			map.put("message", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "1");
			map.put("message", "修改失败");
		}
		return map;
	}

	// findById
	@RequestMapping(value = "/findByExamId")
	@ResponseBody
	public Map<String, Object> findByExamId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		EduExamPaperInfoEntity eduExamPaperInfoEntity = eduExamPaperInfoService.findId(id);
		List<EduQuestionExamLinkEntity> questionExamList = eduQuestionExamService.findExamId(id);
		List<EduQuestionInfoEntity> questList = new ArrayList<EduQuestionInfoEntity>();
		for (int i = 0; i < questionExamList.size(); i++) {
			EduQuestionInfoEntity question = eduQuestionInfoService.findId(questionExamList.get(i).getQuestionId());
			List<EduOptionInfoEntity> option = eduOptionInfoService.findquestionId(question.getId());
			questList.add(question);
			questList.get(i).setOptionList(option);
			;
		}
		map.put("state", "0");
		map.put("data", eduExamPaperInfoEntity);
		map.put("dataQuest", questList);
		return map;
	}

	/**
	 * 新增用户考试结果记录
	 * 
	 * @param userId 用户Id
	 * 
	 */
	@RequestMapping(value = "/saveUserExam")
	@ResponseBody
	public Map<String, String> saveUserExam(HttpServletRequest res, HttpServletResponse req,
			EduUserExamEntity eduUserExamEntity) {
		Map<String, String> map = new HashMap<String, String>();
		Date date = new Date();
		eduUserExamEntity.setExamDate(date);
		try {
			eduUserExamService.save(eduUserExamEntity);
			map.put("code", "200");
			map.put("msg", "收藏成功");
		} catch (Exception e) {
			map.put("code", "201");
			map.put("msg", "收藏失败");
		}
		return map;
	}

	/**
	 * 	根据用户Id查询试卷列表
	 * 
	 */
	@RequestMapping(value = "/getExamListByVocationId")
	@ResponseBody
	public Map<String, Object> getExamListByVocationId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "vocationId") String vocationId,@RequestParam(name = "userId") String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EduExamPaperInfoEntity> examPaperList = eduExamPaperInfoService.getExamListByVocationId(vocationId);
		for(int i=0;i<examPaperList.size();i++) {
			EduUserExamEntity eduUserExamEntity = eduUserExamService.isPass(userId,examPaperList.get(i).getId());
			if(eduUserExamEntity!=null) {
				if(eduUserExamEntity.getIsPass()==1) {//考试记录通过
					examPaperList.get(i).setIsPass("1");//已通过考试
				}else {
					examPaperList.get(i).setIsPass("2");//考过试未通过
				}
			}else {
				examPaperList.get(i).setIsPass("0");//0未考试
			}
		}
		map.put("code", "200");
		map.put("data", examPaperList);
		return map;
	}
}
