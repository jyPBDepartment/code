package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduPictureInfoEntity;

public interface EduPictureDao extends JpaRepository<EduPictureInfoEntity, String>{
	
	// 分页与模糊查询
	@Query(value = "select * from edu_picture_info  t  where if(?1 !='',t.create_by like ?1,1=1) and if(?2 !='',t.pic_type like ?2,1=1) and if(?3 !='',t.status = ?3,1=1) order by t.create_date desc",
			countQuery = "select count(*) from edu_picture_info t  where if(?1 !='',t.create_by like ?1,1=1) and if(?2 !='',t.pic_type like ?2,1=1) and if(?3 !='',t.status = ?3,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<EduPictureInfoEntity> findListByName(String createBy,String picType, String status, Pageable pageable);

	// 通过id查询
	@Query(value = "select * from edu_picture_info t where t.id =:id", nativeQuery = true)
	public EduPictureInfoEntity findId(@Param("id") String id);

	// 查询排序
	@Query(value = "select * from edu_picture_info ", nativeQuery = true)
	public List<EduPictureInfoEntity> findSort();
	
	// 查询图片类型为轮播图
	@Query(value = "SELECT * FROM edu_picture_info t where t.pic_type='0' and t.status = '0' ", nativeQuery = true)
	public List<EduPictureInfoEntity> findTypeOne();
	
	// 查询图片类型为学习手册
	@Query(value = "SELECT * FROM edu_picture_info t where t.pic_type='1' and t.status = '0' ", nativeQuery = true)
	public List<EduPictureInfoEntity> findTypeTwo();
	
	// 查询图片类型为考试
	@Query(value = "SELECT * FROM edu_picture_info t where t.pic_type='2' and t.status = '0' ", nativeQuery = true)
	public List<EduPictureInfoEntity> findTypeThree();

}
