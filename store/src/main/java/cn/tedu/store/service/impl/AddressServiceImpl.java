package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;

@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	AddressMapper mapper;
	@Autowired
	IDistrictService service;
//	@Autowired
//	RedisTemplate template;   //这里想要引入redis实现对数据库的缓存访问功能,但是添加jar包的时候出现了问题,他要说我spring容器中有两个Redistemplate类
//	
	
	@Override
	public List<Address> getByUid(Integer uid) {
		
//		List<Address>list=(List<Address>)template.opsForValue().get("province");
//		if(list==null){
//			System.out.println("查询address"); 
//			list=findByUid(uid);
//			template.opsForValue()
//			.set("province",list);
//		}
//		
//		return list;
		
		
		return findByUid(uid);
		
	}
	

	@Override
	public void addnew(Address address, Integer uid, String username) throws AddressCountLimitException, InsertException {
		
		
		
		
		Integer count=countByUid(uid);
		
		//判断收货地址数量是否达到上限值ADDRESS_MAX_COUNT
		if(count>=ADDRESS_MAX_COUNT) {
			throw new AddressCountLimitException("添加收货地址失败！收货地址数量超出最大值"+",最多允许添加"+ADDRESS_MAX_COUNT+"条");
		}
		
		
		address.setUid(uid);
		
		//确定属性is_default的值	
		Integer isDefalut=count==0?1:0;
		address.setIsDefault(isDefalut);
		
		Date now =new Date();
		District provinceName=service.getByCode(address.getProvinceCode());
		District cityName=service.getByCode(address.getCityCode());
		District areaName=service.getByCode(address.getAreaCode());
		
		if(provinceName==null) {
			address.setProvinceCode(null);
		}else {
			address.setProvinceName(provinceName.getName());
		}
		
		if(cityName==null) {
			address.setCityCode(null);
		}else {
			address.setCityName(cityName.getName());
		}
		
		if(areaName==null) {
			address.setAreaCode(null);
		}else {
			address.setAreaName(areaName.getName());
		}
		
		
		address.setModifiedTime(now);
		address.setModifiedUser(username);
		address.setCreatedTime(now);
		address.setCreatedUser(username);
		
		insert(address);
		
	}
	
	@Override
	@Transactional
	public void delete(Integer aid, Integer uid, String username) {
		Address address=findByAid(aid);
		if(address==null) {
			throw new AddressNotFoundException("要删除的用户地址数据不存在");
		}
		
		
		if(uid!=address.getUid()) {
			throw new AccessDeniedException("非法访问，删除用户地址数据失败");
		}
		deleteByAid(aid);//执行删除操作
		if(address.getIsDefault()==1) {//如果刚才删除的是默认的收货地址
			//在判断是否有其他收货地址，即刚才删除的不是最后一条数据
			Integer count=countByUid(uid);
			if(count!=0) {
				Address result=findLastModified(uid);//找到最新的一条数据
				//将最新的数据设置为默认数据
				updateDefault(username, new Date(), result.getAid());
			}	
		}
	}

	
	
	@Override
	@Transactional
	public void setDefault(Integer aid,Integer uid,String modifiedUser) {
		Address address=findByAid(aid);
		if(address==null) {
			throw new AddressNotFoundException("用户地址数据不存在");
		}
		
		if(address.getUid()!=uid) {
			throw new AccessDeniedException("设置失败，非法访问用户数据");
		}
		
		updateNonDefault(uid);
		Date modifiedTime=new Date();
		updateDefault(modifiedUser, modifiedTime, aid);
		
		
	}
	

	@Override
	public Address getByAid(Integer aid) {
		return findByAid(aid);
	};
	
	private Address findByAid(Integer aid) {
		Address result=mapper.findByAid(aid);
			return result;
		
	}
	
	
	private void updateNonDefault(Integer uid) throws UpdateException{
		Integer rows=mapper.updateNonDefault(uid);
		if(rows==0) {
			throw new UpdateException("设置默认失败，更新全部默认时出现未知错误");
		}
		
	}
	
	
	private void updateDefault(String modifiedUser, Date modifiedTime,Integer aid) {
		Integer rows=mapper.updateDefault(modifiedUser, modifiedTime, aid);
		if(rows==0) {
			throw new UpdateException("设置默认失败，系统出现未知错误");
		}
	}
	
	
	
	
	/**
	 * 封装插入数据的功能
	 * @param address
	 */
	private void insert(Address address) {		
		Integer rows=mapper.insert(address);
		if(rows!=1) {
			throw new InsertException("插入失败！插入地址数据时出现未知错误");
		}
	}
	
	/**
	 * 封装查询地址数据数量的功能
	 */
	private Integer countByUid(Integer uid) {
		Integer count=mapper.countByUid(uid);
		return count;	
	}

	
	private List<Address>findByUid(Integer uid){
		return mapper.findByUid(uid);
		
	}

	
	private void deleteByAid(Integer aid) {
		Integer rows=mapper.deleteByAid(aid);
		if(rows!=1) {
			throw new DeleteException("出现未知错误，删除用户地址失败");
		}
	};
	
	private Address findLastModified(Integer uid) {
		Address address=mapper.findLastModified(uid);
		return address;
	}





	
	
	
	
}
