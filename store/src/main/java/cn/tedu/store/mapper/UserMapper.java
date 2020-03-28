package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

/**
 * 处理用户数据的持久层接口
 * @author lenovo
 *
 */
public interface UserMapper {
	/**
	 * 插入用户数据
	 * @param user用户数据对象
	 * @return 受影响的行数
	 */
	Integer insert(User user);
	
	/**
	 * 根据用户名查询用户数据
	 * @param username用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findByUsername(String username);
	
	/**
	 * 更新用户基本资料
	 * @param user用户资料
	 * @return 受影响的行数
	 */
	Integer updateInfo(User user);
	/**
	 * 更新用户密码
	 * @param uid
	 * @param password
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updatePassword(@Param("uid")Integer uid,@Param("password") String password,
			 @Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);
	/**
	 * 根据用户ID查找用户信息
	 */	
	User findByUid(Integer uid);
	
	/**
	 * 上传用户头像
	 * @param uid
	 * @param avatar
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updateAvatar(@Param("uid")Integer uid,@Param("avatar") String avatar,
			 @Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);
	
	
	
}
