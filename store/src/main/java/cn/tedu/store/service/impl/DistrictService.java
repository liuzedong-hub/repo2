package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;

@Service
public class DistrictService implements IDistrictService {
	@Autowired
	private DistrictMapper mapper;
	
	
	@Override
	public List<District> getByParent(String parent) {
		List list=findByParent(parent);
		return list;
	}
	
	

	@Override
	public District getByCode(String code) {
		District name=findByCode(code);
		return name;
	}

	
	private List<District>findByParent(String parent){
		List list=mapper.findByParent(parent);
		return list;
	}
	private District findByCode(String code) {
		District name=mapper.findByCode(code);
		return name;
	}


}
