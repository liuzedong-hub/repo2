package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;

/**
 * 处理用户数据的业务层接口
 * @author lenovo
 *
 */
public interface IUserService {
	/**
	 * 用户注册
	 * @param user 用户数据对象
	 * @throws UsernameDuplicateException 用户名已经被占用的异常
	 * @throws InsertException 插入用户数据失败的
	 */
	void reg(User user)throws UsernameDuplicateException,InsertException;
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return 登录用户的信息
	 * @throws UserNotFoundException 用户数据不存在，例如用户名尚未注册，或用户数据被标记为删除
	 * @throws PasswordNotMatchException 密码错误
	 */
	User login(String username,String password)throws UserNotFoundException,PasswordNotMatchException;
	
	/**
	 * 修改密码
	 * @param uid
	 * @param newPassword
	 * @param oldPassword
	 * @param username
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 */
	void changePassword(Integer uid,String newPassword,String oldPassword,String username)
			throws UserNotFoundException,PasswordNotMatchException;
	
	
	/**
	 * 修改密码
	 * @param uid	 
	 * @param username
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeAvatar(Integer uid,String username,String avatar)
			throws UserNotFoundException,UpdateException;
	
	
	/**
	 * 根据用户ID查询用户相关数据
	 * @param uid
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
		User getByUid(Integer uid);
	
	/**
	 * 修改用户基本信息
	 * @param user 封装了用户基本资料的对象，至少需要封装用户的id和用户名
	 * ，可选择性的封装用户的手机号码、电子邮箱、年龄等
	 * @throws UserNotFoundException 
	 * @throws UpdateException
	 */
	 
		void changeInfo(User user)throws UserNotFoundException,UpdateException;
}
