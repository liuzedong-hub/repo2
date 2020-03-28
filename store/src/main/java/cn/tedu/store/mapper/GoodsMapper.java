package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Goods;

public interface GoodsMapper {
	
	List<Goods> findHotList();
	/**
	 * 根据商品ID查询商品详情
	 * @param id
	 * @return 匹配额商品详情，如果没有匹配的数据，则返回null值
	 */
	Goods findDetails(Long id);

}
