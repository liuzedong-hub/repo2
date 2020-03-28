package cn.tedu.store.service;

import cn.tedu.store.entity.Order;
import cn.tedu.store.service.ex.InsertException;

public interface IOrderService {
	/**
	 * 创建订单
	 * @param aid用户选择的收货地址数据的id
	 * @param cids用户将购买的购物车数据的id
	 * @param uid当前登录的用户的id
	 * @param username当前登录的用户名
	 * @return 成功创建的订单数据
	 * @throws InsertException
	 * 
	 */
	Order create(Integer aid,Integer[] cids,Integer uid,String username)throws InsertException;


}
