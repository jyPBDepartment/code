package com.jy.pc.Service;

import com.jy.pc.Entity.RelationEntity;

public interface RelationService {
	// 添加
	public void save(RelationEntity relation);
	
	// 修改
	public void update(RelationEntity relation);

	// 删除
	public void delete(String id);

	public RelationEntity findRelationId(String relationId);
}
