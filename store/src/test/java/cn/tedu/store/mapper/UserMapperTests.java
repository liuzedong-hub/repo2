package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.tedu.store.entity.User;

@SpringBootTest
public class UserMapperTests {
	@Autowired
	UserMapper userMapper;
	
	
	@Test
	public void insert() {
		String userName="liuzd1";
		User user=new User();
		User user2=userMapper.findByUsername(userName);
		if(user2==null) {
			user.setUsername(userName);
			user.setPassword("12121");
			Integer i=userMapper.insert(user);
			System.err.println(i);
		}else {
			System.err.println("用户名已存在");
		}
	}
	
	@Test
	public void findByUsername() {
		String username="root";
		User user=userMapper.findByUsername(username);
		System.out.println(user);
		
	}
	
	@Test
	public void updateInfo() {
		
		User user=new User();
		user.setUid(20);
		user.setPhone("13333333333333");
		user.setEmail("hhh@qq.com");
		user.setGender(1);
		
		Integer rows=userMapper.updateInfo(user);
		System.err.println("rows="+rows);
		
		
	}
	
	@Test
	public void updateAvatar() {
	
		Integer rows=userMapper.updateAvatar(19, "c:/","liuzzz", new Date());
		System.err.println(rows);
	}
	
	
	@Test
	public void updataPassword() {
		userMapper.updatePassword(15, "12345", "刘泽东", new Date());
		System.err.println("ok");
	}
	
	@Test
	public void findById() {
		
		User user=userMapper.findByUid(19);
		System.err.println(user);
	}
	
}
