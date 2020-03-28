package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;
@Service
public class CartService implements ICartService {
	@Autowired
	CartMapper mapper;
	
	
	@Override
	public void addToCart(Cart cart,String username,Integer uid) throws UpdateException, InsertException {
		Cart result=findNum(uid,cart.getGid());
		if(result==null) {
			cart.setUid(uid);
			cart.setCreatedTime(new Date());
			cart.setCreatedUser(username);
			cart.setModifiedTime(new Date());
			cart.setModifiedUser(username);
			insert(cart);//执行添加购物车操作
		}else {
			updateNum(result.getCid(), username,
					new Date(),result.getNum()+cart.getNum());//执行更新购物车数据操作
		}
	}
	
	@Override
	public Integer setNum(Integer cid, String username,Integer uid){
		Cart result=findByCid(cid);
		if(result==null) {
			throw new CartNotFoundException("增加失败，购物车数据不存在");
		}
		if(result.getUid()!=uid) {
			throw new AccessDeniedException("增加失败，非法访问用户数据");
		}
		updateNum(cid, username, new Date(), result.getNum()+1);
		return result.getNum()+1;
	};
	
	@Override
	public List<CartVO> getByUid(Integer uid) {
		return findByUid(uid);
	};
	
	
	@Override
	public List<CartVO> getByCids(Integer[] cids,Integer uid) { 
		//获取数据
		List<CartVO>result=findByCids(cids);
		//检查数据归属，将去除归属错误的数据
		Iterator<CartVO> it=result.iterator();
		while(it.hasNext()) {
			CartVO cart=it.next();
			if(cart.getUid()!=uid) {
				it.remove();
			}
		}
		return result;
	};
	
	
	private Cart findNum(Integer uid,Long gid) {
		return mapper.findNum(uid, gid);
	};

	private void insert(Cart cart) {
		
		Integer rows=mapper.insert(cart);
		if(rows==0) {
			throw new InsertException("添加购物车失败，添加时出现未知错误");
		}
	};
	
	private void updateNum(Integer cid,String modifiedUser,Date modifiedTime,Integer num) {
		Integer rows=mapper.updateNum(cid, modifiedUser, modifiedTime, num);
		if(rows==0) {
			throw new UpdateException("添加购物车失败，执行更新时出现未知错误");
		}
	};
	
	
	private List<CartVO> findByUid(Integer uid){
		return mapper.findByUid(uid);
	}
	
	private Cart findByCid(Integer cid) {
		return mapper.findByCid(cid);
		
	}
	
	private List<CartVO> findByCids(Integer[]cids){
		List<CartVO>list=mapper.findByCids(cids);
		return list;
	}

	



	


	
	
	

}
