package com.jy.pc.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jy.pc.DAO.QuestionDao;
import com.jy.pc.Entity.QuestionEntity;
import com.jy.pc.Service.QuestionService;
import com.jy.pc.Utils.Aes;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionDao questionDao;

	@Override
	public QuestionEntity save(QuestionEntity questionEntity) {

		String qa = questionEntity.getQuestionAnswer();
		Aes aes = new Aes();
		String qus = "";
		try {
			qus = aes.desEncrypt(qa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = JSON.parseArray(qus);
		int score = 0;
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jo = JSON.parseObject(jsonArray.get(i).toString());
			if (i == 0) {
				if (jo.get("value").toString().indexOf("A") > 0) {
					score = score + 5;
				}
				if (jo.get("value").toString().indexOf("B") > 0) {
					score = score + 3;
				}
				if (jo.get("value").toString().indexOf("C") > 0) {
					score = score + 1;
				}
			}
			if (i == 1) {
				if (jo.get("value").toString().indexOf("A") > 0) {
					score = score + 5;
				}
				if (jo.get("value").toString().indexOf("B") > 0 || jo.get("value").toString().indexOf("C") > 0
						|| jo.get("value").toString().indexOf("D") > 0 || jo.get("value").toString().indexOf("E") > 0
						|| jo.get("value").toString().indexOf("F") > 0) {
					score = score + 3;
				}
			}
			if (i == 2) {
				if (jo.get("value").toString().indexOf("A") > 0) {
					score = score + 5;
				}
				if (jo.get("value").toString().indexOf("B") > 0) {
					score = score + 3;
				}
				if (jo.get("value").toString().indexOf("C") > 0) {
					score = score + 1;
				}
			}
			if (i == 3) {
				if (jo.get("value").toString().indexOf("A") > 0) {
					score = score + 5;
				}
				if (jo.get("value").toString().indexOf("B") > 0) {
					score = score + 3;
				}

			}
			if (i == 4) {
				if (jo.get("value").toString().indexOf("A") > 0 || jo.get("value").toString().indexOf("B") > 0
						|| jo.get("value").toString().indexOf("C") > 0 || jo.get("value").toString().indexOf("D") > 0
						|| jo.get("value").toString().indexOf("E") > 0 || jo.get("value").toString().indexOf("F") > 0
						|| jo.get("value").toString().indexOf("G") > 0 || jo.get("value").toString().indexOf("H") > 0
						|| jo.get("value").toString().indexOf("I") > 0) {
					score = score + 5;
				}
				if (jo.get("value").toString().indexOf("J") > 0) {
					score = score + 3;
				}

			}
			if (i == 5) {
				if (jo.get("value").toString().indexOf("A") > 0 || jo.get("value").toString().indexOf("B") > 0) {
					score = score + 5;
				}
				if (jo.get("value").toString().indexOf("C") > 0 || jo.get("value").toString().indexOf("D") > 0) {
					score = score + 3;
				}

			}

		}

		questionEntity.setQuestionAnswer("");
		questionEntity.setQuestionScore(score);

		return questionDao.saveAndFlush(questionEntity);

	}

	@Override
	public void update(QuestionEntity questionEntity) {
		questionDao.saveAndFlush(questionEntity);

	}

	@Override
	public void delete(String id) {
		questionDao.deleteById(id);

	}

	@Override
	public QuestionEntity findBId(String id) {

		return questionDao.findBId(id);
	}

	@Override
	public Page<QuestionEntity> findListByName(String name, String phoneNum, Pageable pageable) {
		String questionName = "%" + name + "%";
		String questionTel = "%" + phoneNum + "%";
		return questionDao.findListByName(questionName, questionTel, pageable);
	}

}
