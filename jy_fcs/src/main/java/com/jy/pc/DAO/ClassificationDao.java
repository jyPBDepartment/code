package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.ClassificationEntity;

public interface ClassificationDao extends JpaRepository<ClassificationEntity, String> {
	// fingById方法
	@Query(value = "select * from sas_classification_info  where id =:id", nativeQuery = true)
	public ClassificationEntity findBId(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from sas_classification_info  t  where if(?1 !='',t.code like ?1,1=1)  order by t.create_date desc", countQuery = "select count(*) from sas_classification_info t  where if(?1 !='',t.code like ?1,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<ClassificationEntity> findListByName(String code, Pageable pageable);

	// 查询分类编码
	@Query(value = "select * from sas_classification_info t where t.parent_code ='' AND t.status ='1'", nativeQuery = true)
	public List<ClassificationEntity> findSubClassiList();

	// 查询农作物种类分类编码
	@Query(value = "select * from sas_classification_info t where t.parent_code ='4028f8b1739931eb01739933fe370000' AND t.status ='1'", nativeQuery = true)
	public List<ClassificationEntity> findCaseList();

	// 查询病虫害分类编码
	@Query(value = "select * from sas_classification_info t where t.parent_code ='4028f8b173993c34017399439aef0008' AND t.status ='1'", nativeQuery = true)
	public List<ClassificationEntity> findDipList();

	// 查询关键词分类编码
	@Query(value = "select * from sas_classification_info t where t.parent_code ='402881e5738f91ee01738fad7b800001' AND t.status ='1'", nativeQuery = true)
	public List<ClassificationEntity> findKeyWordList();

	// 分类看图识病农作物删除
	@Query(value = "select distinct t.id,t.code,t.create_date,t.create_user,t.name,t.parent_code,t.status,t.update_date,t.update_user from  sas_classification_info t where t.id not in (select w.classi_code from sas_case_info w)", nativeQuery = true)
	public List<ClassificationEntity> findCropLink();

	// 分类看图识病病虫害删除
	@Query(value = "select distinct t.id,t.code,t.create_date,t.create_user,t.name,t.parent_code,t.status,t.update_date,t.update_user from  sas_classification_info t where t.id not in (select w.classi_dip_code from sas_case_info w)", nativeQuery = true)
	public List<ClassificationEntity> findDipLink();

	// 分类关键字删除
	@Query(value = "select distinct t.id,t.code,t.create_date,t.create_user,t.name,t.parent_code,t.status,t.update_date,t.update_user from  sas_classification_info t where t.id not in (select w.parent_code from sas_key_word w)", nativeQuery = true)
	public List<ClassificationEntity> findKeywordLink();
	
	@Query(value = "select count(0) from sas_classification_info t where t.parent_code =:parentCode", nativeQuery = true)
	public int findParentCode(@Param("parentCode") String parentCode);
}
