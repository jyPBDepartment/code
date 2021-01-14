package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.PostInfoEntity;

public interface PostInfoDao extends JpaRepository<PostInfoEntity, String> {

	// 记录帖子数量
	@Query(value = "SELECT * FROM sas_post_info d where d.audit_status = '2'", nativeQuery = true)
	public List<PostInfoEntity> findInva();

	// 查询
	@Query(value = "select * from sas_post_info t where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.create_user like ?2,1=1)  order by t.create_date desc", countQuery = "select count(*) from sas_post_info t where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.create_user like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<PostInfoEntity> findListByName(String name, String createUser, Pageable pageable);

	// findById
	@Query(value = "select * from sas_post_info t where t.id =:id", nativeQuery = true)
	public PostInfoEntity findId(@Param("id") String id);

	// 查询
	@Query(value = "select * from sas_post_info t where t.audit_status = 1 and t.status = 0 and if(?1 !='',t.parent_code = ?1,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_post_info t where t.audit_status = 2 and t.status = 0 and if(?1 !='',t.parent_code = ?1,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<PostInfoEntity> findListWithSub(String postType, Pageable pageable);

	// 查询最火
	@Query(value = "select * from sas_post_info t where t.status=0 order by t.Pageview desc", countQuery = "select count(*) from sas_post_info t where t.status=0 order by t.Pageview desc", nativeQuery = true)
	public Page<PostInfoEntity> findListByHotLabel(Pageable pageable);

	// 查询最新
	@Query(value = "select * from sas_post_info t where t.status=0 order by t.create_date desc", countQuery = "select count(*) from sas_post_info t where t.status=0  order by t.create_date desc", nativeQuery = true)
	public Page<PostInfoEntity> findListByNewLabel(Pageable pageable);

	// 查询精品
	@Query(value = "select * from sas_post_info t where t.is_selected=0 and t.status = 0 order by t.create_date desc", countQuery = "select count(*) from sas_post_info t where t.is_selected=0 and t.status = 0 order by t.create_date desc", nativeQuery = true)
	public Page<PostInfoEntity> findListByBoutiqueLabel(Pageable pageable);

	// 查询好评
	@Query(value = "select * from sas_post_info t where t.status=0 order by t.praise_num desc", countQuery = "select count(*) from sas_post_info t where t.status=0 order by t.praise_num desc", nativeQuery = true)
	public Page<PostInfoEntity> findListByThumbsLabel(Pageable pageable);

	// 查询热议
	@Query(value = "select * from sas_post_info t where t.status=0 order by t.comment_num desc", countQuery = "select count(*) from sas_post_info t where t.status=0 order by t.comment_num desc", nativeQuery = true)
	public Page<PostInfoEntity> findListByCommentLabel(Pageable pageable);
	
	// 通过id,userId查询帖子信息
	@Query(value = "select *,(select t1.id from sas_post_collection t1 where t.id=t1.circle_id and t1.user_id=:userId) as is_collection,(select t2.id from sas_circle_thumbs t2 where t.id=t2.circle_id and t2.thumbs_user_id=:userId) as is_praise from sas_post_info t where t.id =:id", nativeQuery = true)
	public Map<String,Object> findInfoByPostUserId(@Param("id") String id,@Param("userId") String userId);

}
