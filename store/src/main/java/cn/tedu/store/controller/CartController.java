package cn.tedu.store.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.util.JsonResult;
import cn.tedu.store.vo.CartVO;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
	@Autowired
	ICartService service;
	
	@RequestMapping("add_to_cart")
	public JsonResult<Void>addToCart(Cart cart,HttpSession session){
		Integer uid=getUidFromSession(session);
		String username=getUsernameFromSession(session);
		service.addToCart(cart, username, uid);
		return new JsonResult<>(SUCCESS);
	}
	
	@RequestMapping("/")
	public JsonResult<List<CartVO>>getByUid(HttpSession session){
		Integer uid=getUidFromSession(session);
		List<CartVO>data=service.getByUid(uid);
		return new JsonResult<>(SUCCESS,data);
	}
	
	@RequestMapping("{cid}/add")
	public JsonResult<Integer>setNum(@PathVariable("cid")Integer cid,HttpSession session){
		String username=getUsernameFromSession(session);
		Integer uid=getUidFromSession(session);
		Integer data=service.setNum(cid, username,uid);
		
		
		return new JsonResult<>(SUCCESS,data);
	}
	
	@GetMapping("get_by_cids")
	public JsonResult<List<CartVO>>getByCids(Integer[] cids,HttpSession session){
		Integer uid=getUidFromSession(session);
		List<CartVO>results=service.getByCids(cids, uid);
		
		return new JsonResult<>(SUCCESS,results);
	}
	
	
}
