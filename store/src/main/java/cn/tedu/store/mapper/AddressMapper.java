package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

/**
 * 处理收货地址数据的持久层接口
 * @author lenovo
 *
 */
public interface AddressMapper {
	/**
	 * 插入收货地址数据
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	Integer insert(Address address);
	
	
	
	
	
	Integer deleteByAid(Integer aid);
	
	
	
	
	
	
	
	/**
	 * 统计某个用户的收货地址数据的数量
	 * @param uid 用户的id
	 * @return 该用户的收货地址数据的数量
	 */
	Integer countByUid(Integer uid);
	/**
	 * 根据用户ID查询该用户的收货地址列表
	 * @param uid 用户id
	 * @return 该用户的收货地址列表
	 */
	List<Address> findByUid(Integer uid);
	
	
	/**
	 * 将所有用户的收货地址都设置为非默认
	 * @param uid
	 * @return
	 */
	Integer updateNonDefault(Integer uid);
	/**
	 * 将指定用户的收货地址设置为默认
	 * @param modifiedUser
	 * @param modifiedTime
	 * @param aid
	 * @return
	 */
	Integer updateDefault(@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime,@Param("aid")Integer aid);
	/**
	 * 根据收货地址的数据id查询详情
	 * @param aid
	 * @return
	 */
	Address findByAid(Integer aid);
	
	
	Address findLastModified(Integer uid);
}
