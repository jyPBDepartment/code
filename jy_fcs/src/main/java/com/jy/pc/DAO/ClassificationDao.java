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
	@Query(value = "select * from sas_classification_info t where t.code like ?1 and t.name like ?2 and t.parent_code = ''", countQuery = "select count(*) from sas_classification_info t  where if(?1 !='',t.code like ?1,1=1) and if(?2 !='',t.name like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<ClassificationEntity> findListByName(String code, String name, Pageable pageable);

	// 查询子菜单
	@Query(value = "select * from sas_classification_info t where t.parent_code=:id", nativeQuery = true)
	public List<ClassificationEntity> findListById(@Param("id") String id);

	// 查询分类编码
	@Query(value = "select * from sas_classification_info t where t.parent_code ='' AND t.status ='1'", nativeQuery = true)
	public List<ClassificationEntity> findSubClassiList();

	// 查询农作物种类分类编码
	@Query(value = "select * from sas_classification_info t where t.parent_code = ?1 AND t.status ='1'", nativeQuery = true)
	public List<ClassificationEntity> findCaseList(String classCode);

	// 查询病虫害分类编码
	@Query(value = "select * from sas_classification_info t where t.parent_code = ?1 AND t.status ='1'", nativeQuery = true)
	public List<ClassificationEntity> findDipList(String classCode);

	// 查询关键词分类编码
	@Query(value = "select * from sas_classification_info a where a.parent_code = ?1 AND a.status ='1'", nativeQuery = true)
	public List<ClassificationEntity> findKeyWordList(String classCode);

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

	// 根据传入的分类编码(顶层分类), 获取分类(下层分类)集合
	@Query(value = "select a.* from sas_classification_info a where find_in_set(a.parent_code,(select t.id from sas_classification_info t where t.code =:classCode)) and a.status = 1", nativeQuery = true)
	public List<ClassificationEntity> findClassByCode(@Param("classCode") String classCode);
}
