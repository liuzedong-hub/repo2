package cn.tedu.store.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Goods;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsMapperTests {
	
	@Autowired
	GoodsMapper mapper;
	@Test
	public void findHotList() {
		List<Goods> hots=mapper.findHotList();
		System.err.println("BEGIN:");
		for(Goods g:hots) {
			System.err.println(g);
		}
		System.err.println("END.");
	}

	@Test
	public void finddetile() {
		Goods goods=mapper.findDetails(10000017L);
		System.err.println(goods);
	}
}
