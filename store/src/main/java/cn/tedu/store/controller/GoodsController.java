package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.service.IGoodsService;
import cn.tedu.store.util.JsonResult;

@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController {
	@Autowired
	IGoodsService service;
	
	@RequestMapping("hot")
	public JsonResult<List<Goods>>getHotList(){
		List<Goods>list=service.getHotList();
		
		return new JsonResult<>(SUCCESS,list);
	}
	@RequestMapping("{id}/details")
	public JsonResult<Goods>getDetails(@PathVariable("id") Long id){
		
		Goods data=service.getDetails(id);
		return new JsonResult<>(SUCCESS,data);
	}
}
