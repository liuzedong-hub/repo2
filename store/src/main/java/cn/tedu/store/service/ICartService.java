package cn.tedu.store.service;

import java.util.List;
import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;

public interface ICartService {
	/**
	 * 将商品添加到购物车
	 * @param cart购物车数据
	 * @param username当前登录的用户名
	 * @param uid当前登录的用户id
	 * @throws UpdateException
	 * @throws InsertException
	 */
	void addToCart(Cart cart,String username,Integer uid)throws UpdateException,InsertException;
	
	Integer setNum(Integer cid,String username,Integer uid)throws UpdateException;
	
	List<CartVO> getByUid(Integer uid);
	
	List<CartVO> getByCids(Integer[]cids,Integer uid);
}
