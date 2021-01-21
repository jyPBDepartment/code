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
	@Query(value = "select * from sas_post_info t where t.is_selected=1 and t.status = 0 order by t.select_date desc", countQuery = "select count(*) from sas_post_info t where t.is_selected=1 and t.status = 0 order by t.select_date desc", nativeQuery = true)
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

	//	查询帖子列表
	@Query(value = "SELECT t.id,t.name,t.code,t.is_anonymous as isAnonymous,(select GROUP_CONCAT(a.pic_url) from sas_picture_info a,sas_post_picture a1,sas_post_info a2 where a.id = a1.photo_id and a2.id = a1.post_id AND a2.id = t.id ) as picture,t.header,t.create_user as nickName,t.update_date as updateDate,praise_num as praiseNum,pageview,collection_num as collectionNum,if((select count(0) from sas_post_collection where circle_id = t.id and user_id in :#{#map['userId']}) > 0,1,0) as isUserCollection,if((select count(0) from sas_circle_thumbs where circle_id = t.id and thumbs_user_id in :#{#map['userId']}) > 0,1,0) as isUserPraise FROM sas_post_info t where t.status='0' and if(?1 !='',t.parent_code = ?1,1=1) and if(?2 ='3',t.is_selected = 1,1=1) order by case when ?2='1' then pageview end desc,case when ?2='2' then create_date end desc,case when ?2='3' then select_date end desc,case when ?2='4' then comment_num end desc,case when ?2='5' then praise_num end desc",
			countQuery = "select count(*) FROM sas_post_info t where t.status='0' and if(?1 !='',t.parent_code = ?1,1=1) and if(?2 ='3',t.is_selected = 1,1=1) order by t.create_date desc", 
			nativeQuery = true)
	public Page<List<Map<String, Object>>> findPostInfo(String parentCode ,String sort,Map<String,Object> map,Pageable pageable);
	
	//查询帖子详情id(收藏点赞)
	@Query(value = "SELECT t.*,(select 	GROUP_CONCAT(a.pic_url) from sas_picture_info a,sas_post_picture a1,sas_post_info a2 where a.id = a1.photo_id and a2.id = a1.post_id AND a2.id = t.id ) as picture,if((select count(0) from sas_post_collection where circle_id = t.id and user_id in :#{#map['userId']}) > 0,1,0) as isUserCollection,if((select count(0) from sas_circle_thumbs where circle_id = t.id and thumbs_user_id in :#{#map['userId']}) > 0,1,0) as isUserPraise FROM sas_post_info t where t.id =:id", nativeQuery = true)
	public Map<String, Object> findByPostId(Map<String,Object> map,String id);
	
	@Query(value="SELECT t.id,t.name,t.code,t.is_anonymous as isAnonymous,t.audit_status as auditStatus,t.parent_code as parentCode,t.author,(select GROUP_CONCAT(a.pic_url) from sas_picture_info a,sas_post_picture a1,sas_post_info a2 where a.id = a1.photo_id and a2.id = a1.post_id AND a2.id = t.id ) as picture,t.header,t.create_user as nickName,date_format(t.update_date,'%Y-%m-%d %H:%i:%s') as updateDate,date_format(t.create_date,'%Y-%m-%d %H:%i:%s') as createDate,t.audit_user as auditUser,t.reason FROM sas_post_info t where t.id =?1",nativeQuery=true)
	public Map<String,Object> findInfoById(String id);
}
