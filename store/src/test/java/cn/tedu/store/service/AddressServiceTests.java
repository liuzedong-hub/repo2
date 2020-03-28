package cn.tedu.store.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.ServiceException;

@SpringBootTest
public class AddressServiceTests {
	
	@Autowired
	IAddressService service;
	
	@Test
	public void addNewTest() {
		Address address=new Address();
		
		try {
			service.addnew(address, 2, "小红");
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		} 
	}
	
	@Test
	public void getByUid() {
		List<Address>addresses=service.getByUid(20);
		
		System.err.println("BEGIN:");
		for(Address d:addresses) {
			System.err.println(d);			
		}
		
		System.err.println("END.");
	}
	@Test
	public void getByAid() {
		Address address=service.getByAid(30);
		System.err.println("BEGIN:");
		System.err.println(address);
	}
	
	@Test
	public void setDefault() {
		Integer aid=26;
		Integer uid=21;
		String modifiedUser="Tom";
		
		try {
			service.setDefault(aid, uid, modifiedUser);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
			
		}
		
		
	}
	
	@Test
	public void delete() {
		try {
			Integer aid=36;
			Integer uid=20;
			String username="ahuang";
			service.delete(aid, uid, username);
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
}
