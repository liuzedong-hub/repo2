package cn.tedu.store.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;

/**
 * 
 * @author lenovo
 *
 */
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserMapper userMapper;
	
	
	/**
	 * 用户注册
	 */
	@Override
	public void reg(User user) throws UsernameDuplicateException, InsertException {
		String userName=user.getUsername();
		User user2=userMapper.findByUsername(userName);
		if(user2!=null) {
			throw new UsernameDuplicateException("注册失败！尝试注册的用户名("+userName+")已经被占用");
		}
		
		//得到盐值
		System.err.println("reg()>password="+user.getPassword());
		String salt=UUID.randomUUID().toString().toUpperCase();
		//基于参数user对象中的password进行加密，得到加密后的密码
		String md5Password=getMd5Password(user.getPassword(),salt);
		//将加密后的密码和盐值封装到user中
		user.setSalt(salt);
		user.setPassword(md5Password);
		System.err.println("reg()>salt="+salt);
		System.err.println("reg()>md5Password="+md5Password);
		user.setIsDelete(0);
		
		user.setCreatedTime(new Date());
		user.setModifiedUser("liuzedong");
		user.setCreatedUser("liuzedong");
		user.setModifiedTime(new Date());
		int rows=userMapper.insert(user);
		if(rows!=1) {
			throw new InsertException("注册失败！写入数据时出现未知错误！请联系系统管理员！");
		}
	
		
	}
	
	/**
	 * 用户登录
	 */
	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		User user=userMapper.findByUsername(username);
		if(user==null) {
			throw new UserNotFoundException("登陆失败，用户名不存在");
		}
		
		if(user.getIsDelete()==1) {
			throw new UserNotFoundException("登陆失败，已经标记为删除");
		}
		
		String salt=user.getSalt();
		String Md5Password=getMd5Password(password, salt);
		if(!Md5Password.equals(user.getPassword())) {
			
			throw new PasswordNotMatchException("登陆失败，密码不正确");
		}
		user.setPassword(null);//把不希望给用户的值设置为null
		user.setSalt(null);
		user.setIsDelete(null);
		return user;
	}
	/**
	 *  对密码进行加密
	 * @param password原始密码
	 * @param salt盐值
	 * @return 加密后的密码
	 */
	private String getMd5Password(String password,String salt) {
		//规则：对password+salt进行3重加密
		String str=password+salt;
		for(int i=0;i<3;i++) {
			str=DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();			
		}
		return str;
		
	}
	
	
	
	

	//根据用户id查询用户基本信息
	@Override
	public User getByUid(Integer uid) {
		
		User user=userMapper.findByUid(uid);
		if (user!=null) {
			user.setPassword(null);
			user.setSalt(null);
			user.setIsDelete(null);
		}
		return user;
	}
	
	
	//修改用户基本信息
	@Override
	public void changeInfo(User user) throws UserNotFoundException, UpdateException {
		User result=userMapper.findByUid(user.getUid());
		if(result==null) {
			throw new UserNotFoundException("修改个人资料失败！用户数据不存在");
		}
		
		if(result.getIsDelete()==1) {
			throw new UserNotFoundException("修改个人资料失败！用户数据不存在");
		}
	
		//创建当前时间对象
		Date now=new Date();
		user.setModifiedUser(user.getUsername());
		user.setModifiedTime(now);
		
		//执行修改个人资料
		Integer rows=userMapper.updateInfo(user);
		if(rows!=1) {
			throw new UpdateException("修改个人资料失败！更新用户数据时出现未知错误");
		}
	}

	
	//更新密码
	@Override
	public void changePassword(Integer uid, String newPassword, String oldPassword, String username)
			throws UserNotFoundException, PasswordNotMatchException {
		
		User user=userMapper.findByUid(uid);
		if(user==null) {
			throw new UserNotFoundException("需要修改密码的用户名不存在");
		}
		if(user.getIsDelete()==1) {
			throw new UserNotFoundException("需要修改密码的用户已经标记为删除");
		}
		
		//对老密码进行加密，方便后续比较
		String salt=user.getSalt();
		String md5Password=getMd5Password(oldPassword, salt);
		
		if(!md5Password.equals(user.getPassword())) {
			throw new PasswordNotMatchException("原密码输入错误");
		}
		//将新密码进行加密，覆盖原密码
		String md5Password2=getMd5Password(newPassword, salt);
		
		
		Integer rows=userMapper.updatePassword(uid, md5Password2, username, new Date());
		if(rows!=1) {
			throw new UpdateException("更新失败，出现未知错误，请联系管理员");
		}	
	}
	//更新头像
	@Override
	public void changeAvatar(Integer uid, String username, String avatar)
			throws UserNotFoundException, UpdateException {
		User result=userMapper.findByUid(uid);
		if(result==null) {
			throw new UserNotFoundException("修改头像失败，用户数据不存在");
		}
		if(result.getIsDelete()==null) {
			throw new UserNotFoundException("修改头像失败，用户数据不存在");
		}
		//创建当前时间对象
		Date now=new Date();
		Integer rows=userMapper.updateAvatar(uid, avatar, username, now);
		if(rows!=1) {
			throw new UpdateException("修改头像！更新用户数据时出现未知错误");
		}
		
	}


	

	
	
}
