package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.QuestionEntity;



public interface QuestionService {
	//问卷调查添加
		public QuestionEntity save(QuestionEntity questionEntity);
		//问卷调查修改
		public void update(QuestionEntity questionEntity);
		//问卷调查删除
		public void delete(String id);
		//问卷调查findById
		
		public QuestionEntity findBId(String id);
		//问卷调查分页与模糊查询
		public Page<QuestionEntity> findListByName(String name,String phoneNum,Pageable pageable);
}
