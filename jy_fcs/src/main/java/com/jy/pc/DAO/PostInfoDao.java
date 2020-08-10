package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.PostInfoEntity;

public interface PostInfoDao extends JpaRepository<PostInfoEntity,String> {

	//记录帖子数量
	@Query(value="SELECT * FROM sas_post_info d where d.audit_status = '2'",nativeQuery = true)
	public List<PostInfoEntity> findInva();
	@Query(value="select * from sas_post_info t where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.create_user like ?2,1=1)  order by t.create_date desc",
			countQuery="select count(*) from sas_post_info t where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.create_user like ?2,1=1) order by t.create_date desc",nativeQuery = true)
	public Page<PostInfoEntity> findListByName(String name,String createUser,Pageable pageable);
	@Query(value="select * from sas_post_info t where t.id =:id",nativeQuery = true)
	public PostInfoEntity findId(@Param("id")String id);
	
	@Query(value="select * from sas_post_info t where if(?1 !='',t.parent_code = ?1,1=1) order by t.create_date desc",nativeQuery = true)
	public List<PostInfoEntity> findListByType(String type);
}