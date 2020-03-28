package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTest {
	@Autowired
	CartMapper mapper;
	
	@Test
	public void findByUid() {
		List<CartVO>result=mapper.findByUid(20);
		for(CartVO vo:result) { 
			System.err.println(vo.toString());
		}
		System.err.println("输出结束");
	}
	
	@Test
	public void findByCid() {
		Cart result=mapper.findByCid(15);
		System.err.println(result.getNum());
	}
	
	@Test
	public void insertTest() {
		Cart cart=new Cart();
		cart.setUid(66);
		cart.setGid(10000034L);
		cart.setNum(3);
		cart.setCreatedUser("liuzedong");
		cart.setCreatedTime(new Date());
		cart.setModifiedTime(new Date());
		cart.setModifiedUser("liuzedong");
		Integer rows=mapper.insert(cart);
		System.err.println(rows);
		System.err.println(cart.getCid());
	}
	
	
	@Test
	public void findByCids() {
		Integer[]cids=new Integer[] {14,15,16,17};
		
		List<CartVO>list=mapper.findByCids(cids);
		for(CartVO item:list) {
			System.err.println(item.toString());
		}
	}
	
	
	@Test
	public void findNum() {
		Cart cart=mapper.findNum(66,10000034L);
		System.err.println(cart.toString());
		
	}
	
	@Test
	public void updateNum() {
		
		Integer rows=mapper.updateNum(1,"liuzedong",new Date(),2342342);
		System.err.println(rows);
	}
	

}
