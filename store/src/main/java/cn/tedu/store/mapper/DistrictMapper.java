package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.District;
/**
 * 处理省/市/区数据的持久层接口
 * @author lenovo
 *
 */
public interface DistrictMapper {
	/**
	 * 根据父级代号获取全国所有省中/所有市中/所有区的列表
	 * @param parent父级代号，如果尝试获取全国所有省，则代号应该使用86
	 * @return 全国所有省中/所有市中/所有区的列表
	 */
	List<District>findByParent(String parent);

	District findByCode(String code);
}
