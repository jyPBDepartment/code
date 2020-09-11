package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.FarmworkPictureEntity;

public interface FarmworkPictureDAO extends JpaRepository<FarmworkPictureEntity,String>{
	// 通过农活id查询图片
		@Query(value = "SELECT * FROM sas_farmwork_picture f where f.farmwork_id =:farmworkId", nativeQuery = true)
		public List<FarmworkPictureEntity> findPicId(@Param("farmworkId") String farmworkId);
}
