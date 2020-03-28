package cn.tedu.store.mapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTests {
	
	@Autowired
	OrderMapper mapper;
	
	@Test
	public void insertOrder() {
		Order order=new Order();
		order.setRecvAddress("山东省聊城市");
		order.setUid(2);
		Integer rows=mapper.insertOrder(order);
		System.err.println(rows);
	}

	@Test
	public void insertOrderItem() {
		OrderItem order=new OrderItem();
		order.setTitle("非你莫属");
		order.setOid(2);
		Integer rows=mapper.insertOrderItem(order);
		System.err.println(rows);
	}
}
