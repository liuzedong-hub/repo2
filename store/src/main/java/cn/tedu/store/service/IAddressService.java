package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
/**
 * 处理收货地址的业务层接口
 * @author lenovo
 *
 */
public interface IAddressService {
	/**
	 * 收货地址数量上限
	 */
	int ADDRESS_MAX_COUNT=20;
	/**
	 * 新增加的收货地址
	 * @param address
	 * @param uid
	 * @param name
	 * @throws AddressCountLimitException
	 * @throws InsertException
	 */
	void addnew(Address address,Integer uid,String name)throws AddressCountLimitException,InsertException;

	
	List<Address> getByUid(Integer uid);
	/**
	 * 将指定的收货地址设置为默认
	 * @param aid收货地址的数据id
	 * @param uid当前登录的用户的id
	 * @param modifiedUser当前登录的用户的用户名
	 * @throws AddressNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	void setDefault(Integer aid,Integer uid,String modifiedUser)throws AddressNotFoundException,
		AccessDeniedException,UpdateException;
	
	
	void delete(Integer aid,Integer uid,String username);
	
	Address getByAid(Integer aid);
	
}
