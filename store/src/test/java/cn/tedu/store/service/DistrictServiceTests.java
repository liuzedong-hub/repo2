package cn.tedu.store.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTests {
	@Autowired
	IDistrictService service;
	
	@Test
	public void findByParent() {
		String parent="86";
		List<District> list=service.getByParent(parent);
		System.err.println("BEGIN:");
		for(District d:list) {
			System.err.println(d);
		}
		System.err.println("END.");
		
	}
	@Test
	public void findByCode() {
		String code="110102";
		District name=service.getByCode(code);
		System.err.println(name);
	}
	

}
