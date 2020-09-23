package com.jy.pc.Service.Impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AgriculturalDao;
import com.jy.pc.DAO.AgriculturalPivtureDAO;
import com.jy.pc.DAO.PictureInfoDAO;
import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.AgriculturalPictureEntity;
import com.jy.pc.Entity.PictureInfoEntity;
import com.jy.pc.Enum.PublicationEnum;
import com.jy.pc.Service.AgriculturalService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class AgriculturalServiceImpl implements AgriculturalService {

	@Autowired
	private AgriculturalDao agriculturalDao;
	@Autowired
	private PictureInfoDAO pictureInfoDAO;
	@Autowired
	private AgriculturalPivtureDAO agriculturalPivtureDAO;
	@Autowired
	private DbLogUtil logger;

	// 农服关联Echart图
	@Override
	public List<AgriculturalEntity> findAgrSum() {

		return agriculturalDao.findAgrSum();
	}

	// 农服添加
	@Override
	public AgriculturalEntity save(AgriculturalEntity agriculturalEntity) {
		return agriculturalDao.saveAndFlush(agriculturalEntity);
	}

	// 农服查找
	@Override
	public AgriculturalEntity findBId(String id) {

		return agriculturalDao.findBId(id);
	}

	// 农服分页与模糊查询
	@Override
	public Page<AgriculturalEntity> findListByName(String type, String name, String status, Pageable pageable) {
		String argicuturalName = "%" + name + "%";
		return agriculturalDao.findListByName(argicuturalName, status, PublicationEnum.getValueByType(type), pageable);
	}

	// 农服审核
	@Override
	@Transactional
	public AgriculturalEntity audit(AgriculturalEntity agriculturalEntity, boolean result) {
		logger.initAuditLog(agriculturalEntity, result);
		return agriculturalDao.saveAndFlush(agriculturalEntity);
	}

	// 农服修改
	@Override
	public AgriculturalEntity update(AgriculturalEntity agriculturalEntity) {

		return agriculturalDao.saveAndFlush(agriculturalEntity);
	}

	// 农服查询标题名称
	@Override
	public List<AgriculturalEntity> findListByAgrName(String name) {
		String argName = "%" + name + "%";
		return agriculturalDao.findListByAgrName(argName);
	}

	// 农服查询最近三条
	@Override
	public List<AgriculturalEntity> findListByTime() {

		return agriculturalDao.findListByTime();
	}

	// 不同状态加载不同的发布
	@Override
	public List<AgriculturalEntity> findStatusPass(String status) {

		return agriculturalDao.findStatusPass(status);
	}

	// 获取农服预约信息
	@Override
	public List<AgriculturalEntity> findAppointment() {

		return agriculturalDao.findAppointment();
	}

	public List<AgriculturalEntity> findListByType(String type) {

		/*
		 * Map<String, Object> map = this.processing(type); List<String> list1 =
		 * (List<String>) map.get("list1");//交易类型 List<String> list2 = (List<String>)
		 * map.get("list2");//交易类别 Map<String, List<String>> map2 = new HashMap<String,
		 * List<String>>(); map2.put("list1", list1); map2.put("list2", list2);
		 */
		return agriculturalDao.findListByType(PublicationEnum.getValueByType(type));
	}

	@Override
	public Page<AgriculturalEntity> findListByType(String type, Pageable pageable) {
		return agriculturalDao.findListByType(PublicationEnum.getValueByType(type), pageable);
	}

	/*
	 * // 业务处理 public Map<String, Object> processing(String type) { Map<String,
	 * Object> map = new HashMap<String, Object>(); // 农服类0，粮食买卖1，农机类2 List<String>
	 * list1 = new ArrayList<String>(); List<String> list2 = new
	 * ArrayList<String>();
	 * 
	 * switch (type) { case "0": list1.add("3"); list1.add("4"); list1.add("5");
	 * list2.add("0"); list2.add("2"); list2.add("3"); list2.add("4"); break; case
	 * "1": list1.add("0"); list1.add("1"); list2.add("0"); list2.add("2");
	 * list2.add("3"); list2.add("4"); break; case "2": list1.add("0");
	 * list1.add("1"); list1.add("2"); list2.add("1"); break;
	 * 
	 * }
	 * 
	 * map.put("list1", list1); map.put("list2", list2);
	 * 
	 * return map; }
	 */

	@Override
	public List<AgriculturalEntity> findListByTime(String type) {
		return agriculturalDao.findListByTime(PublicationEnum.getValueByType(type));
	}

	@Override
	public Page<AgriculturalEntity> findAgriInfo(String name, String type, String transactionTypeCode,
			String transactionCategoryCode, String identityCode,String address, Pageable pageable) {
		String argiName = "".equals(name) ? "" : "%" + name + "%";
		String addr = "".equals(address) ? "" : "%" + address + "%";
		return agriculturalDao.findAgriInfo(argiName, transactionTypeCode, transactionCategoryCode, identityCode,addr,
				PublicationEnum.getValueByType(type), pageable);
	}

	@Override
	public Page<AgriculturalEntity> findAgriType(String transactionTypeCode, String transactionCategoryCode,
			String identityCode, String type, Pageable pageable) {
		return agriculturalDao.findAgriType(transactionTypeCode, transactionCategoryCode, identityCode,
				PublicationEnum.getValueByType(type), pageable);
	}

	// 根据id查询农服信息详情
	@Override
	public AgriculturalEntity findId(String id) {
		return agriculturalDao.findId(id);
	}

	@Override
	public Page<AgriculturalEntity> findMyPublication(String status, String type, String userId, Pageable pageable) {
		return agriculturalDao.findMyPublication(status, userId, PublicationEnum.getValueByType(type), pageable);
	}
	
	// 根据id查询我的农服，农机，粮食买卖信息详情（h5）
	@Override
	public AgriculturalEntity findMineId(String id) {
		return agriculturalDao.findMineId(id);
	}

	private static int calBetweenDate(Date begin,Date end) {
		int days = 0;
		if(begin!=null && end!=null) {
			days = (int)((end.getTime() - begin.getTime()) / (24*3600*1000));
		}
		return days;
	}
	
	@Transactional
	@Override
	public AgriculturalEntity updateAgr(AgriculturalEntity agriculturalEntity,String id,String[] addItem,String[] deleteItem,String transactionTypeCode,String transactionCategoryCode) {
		
		AgriculturalEntity agricultural = agriculturalDao.findBId(id);
		Date date = new Date();
		agriculturalEntity.setUpdateDate(date);// 设置修改时间
		agriculturalEntity.setStatus(agricultural.getStatus());
		//0923根据产品要求，只有当图片、标题、描述发生变动时才修改审核状态
		String oldName = agricultural.getName();
		String oldDes = agricultural.getDescrip();
		String newName = agriculturalEntity.getName();
		String newDes = agriculturalEntity.getDescrip();
		if(!oldName.equals(newName) || !oldDes.equals(newDes) || (addItem!=null&&addItem.length>0) || (deleteItem!=null&&deleteItem.length>0)) {
			agriculturalEntity.setStatus("0");
		}
		agriculturalEntity.setCreateDate(agricultural.getCreateDate());
		agriculturalEntity.setTransactionCategoryCode(transactionCategoryCode);
		agriculturalEntity.setTransactionTypeCode(transactionTypeCode);
		agriculturalEntity.setDays(String.valueOf(calBetweenDate(agriculturalEntity.getBeginDate(),agriculturalEntity.getEndDate())));
		agriculturalDao.save(agriculturalEntity);
		for (int i = 0; i < deleteItem.length; i++) {
			PictureInfoEntity pictureInfoEntity = pictureInfoDAO.findByAgrUrl(deleteItem[i]);
			if(pictureInfoEntity != null) {
				AgriculturalPictureEntity agriculturalPicture = agriculturalPivtureDAO.findByPicId(pictureInfoEntity.getId());
				agriculturalPivtureDAO.deleteById(agriculturalPicture.getId());
				pictureInfoDAO.deleteById(pictureInfoEntity.getId());
			}
		}
		for (int i = 0; i < addItem.length; i++) {
			PictureInfoEntity pictureInfoEntity = new PictureInfoEntity();
			AgriculturalPictureEntity agriculturalPictureEntity = new AgriculturalPictureEntity();
			pictureInfoEntity.setPicName(agriculturalEntity.getName());
			pictureInfoEntity.setPicUrl(addItem[i]);
			pictureInfoDAO.save(pictureInfoEntity);
			agriculturalPictureEntity.setAgrId(agriculturalEntity.getId());
			agriculturalPictureEntity.setPicId(pictureInfoEntity.getId());
			agriculturalPivtureDAO.save(agriculturalPictureEntity);
		}
		return agriculturalEntity;
	}
	
	@Override
	public AgriculturalEntity saveAgr(String[] addItem, AgriculturalEntity agriculturalEntity) {
		Date date = new Date();
		agriculturalEntity.setCreateDate(date);// 设置创建时间
		agriculturalEntity.setStatus("0");// 初始化值为待审核0
		agriculturalEntity.setDays(String.valueOf(calBetweenDate(agriculturalEntity.getBeginDate(),agriculturalEntity.getEndDate())));
		agriculturalDao.saveAndFlush(agriculturalEntity);
		for (int i = 0; i < addItem.length; i++) {
			PictureInfoEntity pictureInfoEntity = new PictureInfoEntity();
			AgriculturalPictureEntity agriculturalPictureEntity = new AgriculturalPictureEntity();
			pictureInfoEntity.setPicName(agriculturalEntity.getName());
			pictureInfoEntity.setPicUrl(addItem[i]);
			pictureInfoDAO.saveAndFlush(pictureInfoEntity);
			agriculturalPictureEntity.setAgrId(agriculturalEntity.getId());
			agriculturalPictureEntity.setPicId(pictureInfoEntity.getId());
			agriculturalPivtureDAO.saveAndFlush(agriculturalPictureEntity);
		}
		return agriculturalEntity;
	}

	@Override
	public void delete(String id) {
		agriculturalDao.deleteById(id);
	}

}
