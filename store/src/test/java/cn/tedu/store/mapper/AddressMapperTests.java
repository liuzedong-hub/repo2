package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTests {
	@Autowired
	AddressMapper mapper;
	
	
	@Test
	public void insert() {
		Address address=new Address();
		address.setUid(1);
		Integer rows=mapper.insert(address);
		System.err.println(rows);
		//System.err.println(address.getAid()); 为什么会有空指针异常
		System.err.println(address.toString());
	}
	
	
	@Test
	public void countByUid() {
		Integer rows=mapper.countByUid(1);
		System.err.println(rows);
	}
	@Test
	public void findByUid() {
		List<Address> addresses=mapper.findByUid(20);
		System.err.println("BEGIN:");
		for(Address d:addresses) {
			System.err.println(d);			
		}
		
		System.err.println("END.");
	}
	
	@Test
	public void findByAid() {
		Address address=mapper.findByAid(25);
		System.err.println(address);
	}
	
	@Test
	public void updateNonDefault() {
		Integer rows=mapper.updateNonDefault(20);
		System.err.println(rows);
	}
	@Test
	public void updateDefault() {
		Integer rows=mapper.updateDefault("Tom",new Date(),25);
		System.err.println(rows);
	}
	
	
	@Test
	public void deleteByAid() {
		Integer rows=mapper.deleteByAid(33);
		System.err.println(rows);
	}
	
	@Test
	public void findLastModified() {
		Address address=mapper.findLastModified(20);
		System.err.println(address);
	}
	
}
