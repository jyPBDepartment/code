package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.AgriculturalPictureEntity;

public interface AgriculturalPivtureDAO extends JpaRepository<AgriculturalPictureEntity,String>{

	// fingById方法
		@Query(value = "select * from sas_agricultural_picture where agr_id =:id", nativeQuery = true)
		public List<AgriculturalPictureEntity> findByAgrId(@Param("id") String id);
		// fingById方法
		@Query(value = "select * from sas_agricultural_picture t where t.pic_id=:picId", nativeQuery = true)
		public AgriculturalPictureEntity findByPicId(@Param("picId") String picId);
		
}
