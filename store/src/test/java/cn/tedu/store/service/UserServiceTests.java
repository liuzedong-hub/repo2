package cn.tedu.store.service;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;

@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	IUserService service;
	
	@Test
	public void reg() {
		try {
			User user=new User();
			user.setUsername("root");
			user.setPassword("12345");
			service.reg(user);
			System.err.println("ok");
		}catch(ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void login() {
		try {
			String username="root";
			String password="1234";
			User user=service.login(username, password);
			System.out.println(user);
			
			
			
		}catch(ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changeInfo() {
		User user=new User();
		user.setUid(20);
		user.setPhone("1777777");
		user.setEmail("hhhdddd@qq.com");
		user.setGender(1);
		user.setModifiedTime(new Date());
		user.setModifiedUser("liuliuliuliuliuliu");
		service.changeInfo(user);
		
		System.err.println("修改成功");
	}
	
	@Test
	public void updateAvatar() {
		
		try {
			service.changeAvatar(19, "liuzedongddd", "c://sdfasfdasdf");
			System.err.println("更新成功");
		} catch (UserNotFoundException e) {
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		} catch (UpdateException e) {
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
	@Test
	public void changPassword() {
		try{
			service.changePassword(20, "1234","8888","Tom");
			System.err.println("更新成功");
			
		}catch(ServiceException e){
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		}
	}
	
	
	@Test
	public void findById() {
		User user=service.getByUid(20);
		System.err.println(user);
	}
	
	
	
	
	
	
	
}
