package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduCertificateInfoEntity;

public interface EduCertificateInfoService {
	// 搜索
	public Page<EduCertificateInfoEntity> findListByParam(String name, String status, String createBy,
			Pageable pageable);

	// 添加
	public EduCertificateInfoEntity save(EduCertificateInfoEntity entity);

	// 修改
	public void update(EduCertificateInfoEntity entity);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public EduCertificateInfoEntity findInfobyId(String id);

	// 切换生效状态
	public void enable(EduCertificateInfoEntity entity, boolean result);
}
