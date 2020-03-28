package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.JsonResult;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
	@Autowired
	IAddressService service;
	
	@RequestMapping("addnew")
	public JsonResult<Void> addnew(Address address,HttpSession session){
		Integer uid=Integer.valueOf(session.getAttribute("uid").toString());
		String username=session.getAttribute("username").toString();
		service.addnew(address, uid, username);
		return new JsonResult<Void>(SUCCESS);
	}
	
	@GetMapping("/")
	public JsonResult<List<Address>>getByUid(HttpSession session){
		Integer uid=getUidFromSession(session);
		
		List<Address>addresses=service.getByUid(uid);
		
//		System.err.println("BEGIN:");将查到的地址信息在控制台输出一下
//		for(Address d:addresses) {
//			System.err.println(d);			
//		}
//		
//		System.err.println("END.");
		
		return new JsonResult<List<Address>>(SUCCESS,addresses);
	}
	
	@RequestMapping("{aid}/set_default")
	public JsonResult<Void>setDefault(@PathVariable("aid") Integer aid,
			HttpSession session){
		Integer uid=Integer.valueOf(session.getAttribute("uid").toString());
		String username=session.getAttribute("username").toString();
		service.setDefault(aid, uid, username);
		return new JsonResult<Void>(SUCCESS);
	}
	
	
	
	
	@RequestMapping("{aid}/delete")
	public JsonResult<Void>delete(@PathVariable("aid") Integer aid,
			HttpSession session){
		
		
		String username=getUsernameFromSession(session);
		Integer uid=getUidFromSession(session);
		
		service.delete(aid, uid, username);
		return new JsonResult<Void>(SUCCESS);
	}

	
	
	
}
