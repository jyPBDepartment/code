package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduManualLabelInfoEntity;
/**
 * 标签Dao
 * */
public interface EduManualLabelDao extends JpaRepository<EduManualLabelInfoEntity, String>{
	// 分页与模糊查询
	@Query(value = "select * from edu_manual_label_info  t  where if(?1 !='',t.create_by like ?1,1=1) and if(?2 !='',t.name like ?2,1=1) and if(?3 !='',t.status = ?3,1=1) order by t.create_date desc",
			countQuery = "select count(*) from edu_manual_label_info t  where if(?1 !='',t.create_by like ?1,1=1) and if(?2 !='',t.name like ?2,1=1) and if(?3 !='',t.status = ?3,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<EduManualLabelInfoEntity> findListByName(String createBy,String name, String status, Pageable pageable);

	// 通过id查询
	@Query(value = "select * from edu_manual_label_info t where t.id =:id", nativeQuery = true)
	public EduManualLabelInfoEntity findId(@Param("id") String id);
	
	//查询启用标签
	@Query(value = "SELECT * FROM edu_manual_label_info t where t.status = '0'", nativeQuery = true)
	public List<EduManualLabelInfoEntity> findManualLabelId();
	
	// 查询未关联标签
	@Query(value = "select distinct t.id,t.name,t.status,t.create_by,t.create_date,t.update_by,t.update_date from  edu_manual_label_info t where t.id not in (select m.label_id from edu_manual_info m)", nativeQuery = true)
	public List<EduManualLabelInfoEntity> findManualLink();

}
