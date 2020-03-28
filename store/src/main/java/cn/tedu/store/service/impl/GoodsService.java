package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IGoodsService;
@Service
public class GoodsService implements IGoodsService {
	@Autowired 
	GoodsMapper mapper;
	
	@Override
	public Goods getDetails(Long id) {
		return findDetails(id);
	};
	
	@Override
	public List<Goods> getHotList() {
		
		return findHotList();
	}
	
	
	
	
	private List<Goods> findHotList(){
		List<Goods>goods=mapper.findHotList();
		
		return goods;
	}
	
	
	private Goods findDetails(Long id) {
		return mapper.findDetails(id);
		
	}



	

}
