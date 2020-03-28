package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

public interface CartMapper {
	

	Integer insert(Cart cart);
	
	Integer updateNum(@Param("cid")Integer cid,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime,
			@Param("num")Integer num);
	/**
	 * 获取某用户的购物车数据列表
	 * @param uid
	 * @return
	 */
	
	List<CartVO> findByUid(Integer uid);
	
	/**
	 * 根据购物车中商品的id查询商品详情
	 * @param cid
	 * @return
	 */
	List<CartVO> findByCids(Integer[]cids);

	/**
	 * 根据购物车商品id查找该商品的数量
	 * @param cid
	 * @return
	 */
	Cart findByCid(Integer cid);
	/**
	 * 根据前端提交的商品id和用户id查找该用户购物车中商品的数量
	 * @param uid
	 * @param gid
	 * @return
	 */
	Cart findNum(@Param("uid")Integer uid,@Param("gid")Long gid);

}
