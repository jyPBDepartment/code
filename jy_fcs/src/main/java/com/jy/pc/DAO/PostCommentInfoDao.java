package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.PostCommentInfoEntity;

public interface PostCommentInfoDao extends JpaRepository<PostCommentInfoEntity, String>{
	
	@Query(value="select * from sas_post_comment_info t where t.id =:id",nativeQuery = true)
	public PostCommentInfoEntity findId(@Param("id")String id);
	
	@Query(value = "select * from sas_post_comment_info t where if(?1 !='',t.comment_content like ?1,1=1) and "
			+ "if(?2 !='',t.comment_user_name like ?2,1=1) order by t.comment_date desc ",
			countQuery="select count(*) from sas_post_comment_info t where if(?1 !='',t.comment_content like ?1,1=1) and "
					+ "if(?2 !='',t.comment_user_name like ?2,1=1) order by t.comment_date desc",nativeQuery = true)
	public Page<PostCommentInfoEntity> findListByContent(String content,String user,Pageable pageable);

}
