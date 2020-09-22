package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.PictureInfoEntity;

public interface PictureInfoDAO extends JpaRepository<PictureInfoEntity, String> {

	// fingById方法(agri农服图片)
	@Query(value = "select t.* from sas_picture_info t,sas_agricultural_picture d  where t.id = d.pic_id  and d.agr_id = :id", nativeQuery = true)
	public List<PictureInfoEntity> findByAgrId(@Param("id") String id);

	// fingById方法(农活图片)
	@Query(value = "select t.* from sas_picture_info t,sas_farmwork_picture d  where t.id = d.pic_id  and d.farmwork_id =:id", nativeQuery = true)
	public List<PictureInfoEntity> findByFarmId(@Param("id") String id);
	// fingById方法
	@Query(value = "SELECT * FROM sas_picture_info p where p.pic_url=:url", nativeQuery = true)
	public PictureInfoEntity findByAgrUrl(@Param("url") String url);
}
