package cn.tedu.store.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTests {
	@Autowired
	DistrictMapper mapper;
	
	@Test
	public void find() {
		String parent="86";
		List<District>list=mapper.findByParent(parent);
		System.err.println("BEGIN:");
		for(District d:list) {
			System.err.println(d);
		}
		System.err.println("END.");
	}
	
	@Test
	public void findByCode() {
		String code="110102";
		District name=mapper.findByCode(code);
		System.err.println(name);
	}
}
