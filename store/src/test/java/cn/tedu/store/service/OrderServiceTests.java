package cn.tedu.store.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.impl.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {
	@Autowired
	IOrderService service;
	
	
	@Test
	public void insertOrder() {
		
		int aid=30;
		Integer[]cids=new Integer[] {14,15,16,17};
		int uid=20;
		String username="hahahhhh";
		try {
			service.create(aid, cids, uid, username);
		} catch (InsertException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	

}
