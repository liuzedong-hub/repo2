package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

/**
 * 处理收货地址数据的持久层接口
 * @author lenovo
 *
 */
public interface OrderMapper {
	/**
	 * 向订单类中插入数据
	 * @param order
	 * @return
	 */
	Integer insertOrder(Order order);
	/**
	 * 向订单商品类中插入数据
	 * @param orderItem
	 * @return
	 */
	Integer insertOrderItem(OrderItem orderItem);
	
	
}
