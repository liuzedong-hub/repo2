package cn.tedu.store.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
	@Autowired
	ICartService service;
	
	@Test
	public void addToCart() {
		Cart cart=new Cart();
		cart.setGid(10000046L);
		cart.setNum(30);
		try {
			service.addToCart(cart,"liuzedong",68);
			System.err.println("ok,添加购物车成功");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	
		
		
	}
	
	@Test
	public void getByCids() {
		Integer[] cids=new Integer[] {14,15,16,17};
		Integer uid=20;
		List<CartVO> list=service.getByCids(cids, uid);
		
		for(CartVO cart:list) {
			System.err.println(cart.toString());
		}
	}
	
	@Test
	public void setNum() {
		try {
			service.setNum(14, "liuliudong",232);
			System.err.println("ok");

		} catch (ServiceException e) {
		System.err.println(e.getClass().getName());
		System.err.println(e.getMessage());
		}
	}
	
	
	@Test
	public void getByUid() {
		List<CartVO>result=service.getByUid(20);
		for(CartVO vo:result) {
			System.err.println(vo.toString());
		}
		System.err.println("输出结束");
		
	}
	
	

}
