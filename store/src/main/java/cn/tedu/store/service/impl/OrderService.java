package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.vo.CartVO;

@Service
public class OrderService implements IOrderService{
	
	@Autowired
	private OrderMapper mapper;
	
	@Autowired
	private IAddressService addressservice;
	@Autowired
	private ICartService cartService;
	
	
	
	@Override
	@Transactional
	public Order create(Integer aid, Integer[] cids,Integer uid,String username) throws InsertException {
		Long totalPrice=0L;
		
		List<CartVO> results=cartService.getByCids(cids, uid);
		for(CartVO item:results) {
//			System.err.println(item.getCid()+","+item.getPrice()+","+item.getNum());
//			System.err.println();
			totalPrice+=item.getPrice()*item.getNum();
		}
		System.err.println("总价="+totalPrice);
		
		Date now=new Date();
		Address address=addressservice.getByAid(aid);
		Order order=new Order();
		order.setUid(uid);
		order.setRecvAddress(address.getProvinceName()+
				address.getCityName()+address.getAreaName()+address.getAddress());
		order.setRecvName(address.getName());
		order.setRecvPhone(address.getPhone());
		order.setTotalPrice(totalPrice);
		order.setState(0);
		order.setOrderTime(now);
		order.setPayTime(null);
		
		order.setModifiedUser(username);
		order.setCreatedUser(username);
		order.setCreatedTime(now);
		order.setModifiedTime(now);
		//执行插入订单数据
		insertOrder(order);
		
		
		for(CartVO item:results) {
			OrderItem orderItem=new OrderItem();
			orderItem.setOid(order.getOid());
			orderItem.setGid(item.getGid());
			orderItem.setTitle(item.getTitle());
			orderItem.setImage(item.getImage());
			orderItem.setPrice(item.getPrice());
			orderItem.setNum(item.getNum());;
			orderItem.setCreatedUser(username);
			orderItem.setCreatedTime(now);
			orderItem.setModifiedTime(now);;
			orderItem.setModifiedUser(username);
			
			//执行插入订单商品信息数据
			insertOrderItem(orderItem);
		}
		//返回成功创建的订单
		return order;
	}
	

	
	private void insertOrder(Order order) throws InsertException{
		Integer rows=mapper.insertOrder(order);
		if(rows==0) {
			throw new InsertException("创建订单失败！插入订单数据时出现未知错误");
		}
		
		
	};
	
	private void insertOrderItem(OrderItem orderItem) {
		Integer rows=mapper.insertOrderItem(orderItem);
		if(rows==0) {
			throw new InsertException("创建订单失败！插入订单的商品数据时出现未知错误");
		}	
	}

	


	
}
