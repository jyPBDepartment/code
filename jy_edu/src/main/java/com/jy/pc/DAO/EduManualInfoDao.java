package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduManualInfoEntity;

/**
 * 手册Dao
 */
public interface EduManualInfoDao extends JpaRepository<EduManualInfoEntity, String> {
	// 分页与模糊查询
	@Query(value = "select * from edu_manual_info  t  where if(?1 !='',t.title like ?1,1=1) and if(?2 !='',t.create_by like ?2,1=1) and if(?3 !='',t.vocation_id like ?3,1=1) and if(?4 !='',t.label_id = ?4,1=1) order by t.create_date desc", countQuery = "select count(*) from edu_manual_info t  where if(?1 !='',t.title like ?1,1=1) and if(?2 !='',t.create_by like ?2,1=1) and if(?3 !='',t.vocation_id like ?3,1=1) and if(?4 !='',t.label_id = ?4,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<EduManualInfoEntity> findListByName(String title, String createBy, String vocationId, String labelId,
			Pageable pageable);

	// 通过id查询
	@Query(value = "select * from edu_manual_info t where t.id =:id", nativeQuery = true)
	public EduManualInfoEntity findId(@Param("id") String id);

	// 根据用户Id、是否收藏查询手册列表信息
	@Query(value = "select t.* from edu_manual_info t,edu_user_manual t1 where t.id =t1.manual_info_id and t1.user_id=?1 and t1.is_collection=?2", nativeQuery = true)
	public List<EduManualInfoEntity> getManualListByUserId(String userId, int isCollection);

	// 移动端-首页-热门课程加载
	@Query(value = "select * from edu_manual_info t where status = 0 order by study_num desc limit 2", nativeQuery = true)
	public List<EduManualInfoEntity> getListByReading();

}
