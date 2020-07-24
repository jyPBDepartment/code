package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.InvitationEntity;

public interface InvitationDao extends JpaRepository<InvitationEntity,String> {

	//记录农活预约数量
	@Query(value="SELECT * FROM sas_invitation d where d.audit_status = '2'",nativeQuery = true)
	public List<InvitationEntity> findInva();
}
