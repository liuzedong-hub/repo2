package cn.tedu.store.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.service.impl.GoodsService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTests {
	@Autowired
	GoodsService service;
	
	@Test
	public void getHotList() {
		List<Goods>hots=service.getHotList();
		System.err.println("BEGIN:");
		for(Goods g:hots) {
			System.err.println(g);
		}
		System.err.println("END.");
	}
	
	@Test
	public void getDetail() {
		System.err.println(service.getDetails(10000017L));
	}
}
