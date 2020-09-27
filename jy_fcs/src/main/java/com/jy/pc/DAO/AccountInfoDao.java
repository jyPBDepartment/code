package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.AccountInfoEntity;

public interface AccountInfoDao extends JpaRepository<AccountInfoEntity, String>{
	//判断用户名密码是否正确
	@Query(value = "select * from sas_account_info t where t.name=:name and t.pass_word=:password", nativeQuery = true)
	public AccountInfoEntity findUserInfo(@Param("name") String name,@Param("password") String password);
	//查询分页
	@Query(value="select * from sas_account_info t where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.phone like ?2,1=1) and if(?3 !='',t.audit_status like ?3,1=1) and t.name !='admin' order by t.create_date desc",
			countQuery="select count(*) from sas_account_info t where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.phone like ?2,1=1) and if(?3 !='',t.audit_status like ?3,1=1) and t.name !='admin' order by t.create_date desc",nativeQuery = true)
	public Page<AccountInfoEntity> findListByName(String name,String phone,String auditStatus,Pageable pageable);
	//通过id查询
	@Query(value="select * from sas_account_info t where t.id =:id",nativeQuery = true)
	public AccountInfoEntity findId(@Param("id")String id);

}
