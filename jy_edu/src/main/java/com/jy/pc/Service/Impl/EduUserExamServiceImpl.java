package com.jy.pc.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduExamPaperInfoDao;
import com.jy.pc.DAO.EduIssueDao;
import com.jy.pc.DAO.EduUserExamDao;
import com.jy.pc.DAO.EduVocationInfoDao;
import com.jy.pc.Entity.EduExamPaperInfoEntity;
import com.jy.pc.Entity.EduIssueInfoEntity;
import com.jy.pc.Entity.EduUserExamEntity;
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduUserExamService;

@Service
@Transactional
public class EduUserExamServiceImpl implements EduUserExamService {

	@Autowired
	private EduUserExamDao eduUserExamDao;
	
	@Autowired
	private EduExamPaperInfoDao eduExamPaperInfoDao;
	
	@Autowired
	private EduVocationInfoDao eduVocationInfoDao;
	
	@Autowired
	EduIssueDao eduIssueDao;

	public void deleteByExam(String userId, String examId) {
		if (eduUserExamDao.findByExam(userId, examId) != null) {
			eduUserExamDao.deleteByExam(userId, examId);
		}
	}

	public void save(EduUserExamEntity eduUserExamEntity) throws ServiceException {
		eduUserExamDao.saveAndFlush(eduUserExamEntity);
	}

	public List<Map<String, Object>> getExamResultByUserId(String userId) throws ServiceException {
		return eduUserExamDao.getExamResultByUserId(userId);
	}
	
	public EduUserExamEntity isPass(String userId,String examId) {
		return eduUserExamDao.findByExam(userId,examId);
	}

	@Override
	public List<EduUserExamEntity> findByVocation(String vocationId, String isPass) {
		return eduUserExamDao.findByVocation(vocationId,isPass);
	}
	
	public List<Map<String, Object>> getExamIsPassByUserId(String userId) throws ServiceException {
		/**
		 * 1、我的考试查询试卷是否存在记录 2、存在：遍历记录并统计是否全部通过 3、如果全部通过，查询是否存在申请证书记录 返回结果： a,职业类别名称
		 * b,考试是否通过 c,是否申请证书
		 */
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<EduVocationInfoEntity> eduVocationInfoList = eduVocationInfoDao.findVocationIsExamId();
		if (eduVocationInfoList.size() > 0) {
			for (int i = 0; i < eduVocationInfoList.size(); i++) {
				
				String vocationId = eduVocationInfoList.get(i).getId();
				List<EduUserExamEntity> eduUserExamList = eduUserExamDao.findUserExam(userId, vocationId);
				if (eduUserExamList.size() > 0) {// 如果用户考试中存在该职业类别记录
					Map<String, Object> map = new HashMap<String, Object>();
					// 获取职业类别下的所有试卷
					List<EduExamPaperInfoEntity> examPaperInfoList = eduExamPaperInfoDao
							.getExamListByVocationId(vocationId);
					if (eduUserExamList.size() == examPaperInfoList.size()) {// 如果用户职业类别考试数等于职业类别下的试卷数量，表示都考过试，计入申请计算
						
						int count = 0;
						for (int j = 0; j < eduUserExamList.size(); j++) {
							if (eduUserExamList.get(j).getIsPass() == 1) {
								count = count + 1;
							}
						}

						if (eduUserExamList.size() == count) {// 考试通过次数等于考试试卷数，表示全部通过，可以申请证书
							map.put("vocationId", eduVocationInfoList.get(i).getId());
							map.put("vocationName", eduVocationInfoList.get(i).getName());
							map.put("isPass", "1");
							EduIssueInfoEntity eduIssueInfoEntity = eduIssueDao.findInfo(userId, vocationId);
							if (eduIssueInfoEntity != null) {
								map.put("isApply", "1");
							} else {
								map.put("isApply", "0");
							}
						}
					} else {// 否则，表示考试并未考全，不计入申请计算
						map.put("vocationId", eduVocationInfoList.get(i).getId());
						map.put("vocationName", eduVocationInfoList.get(i).getName());
						map.put("isPass", "0");
						map.put("isApply", "0");
					}
					list.add(map);
				}
			}
		}

		return list;
	}
}
