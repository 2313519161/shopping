package demo.shopping.dao;

import java.util.List;
import java.util.Map;

import demo.shopping.po.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import demo.shopping.po.Goods;
@Repository("adminGoodsDao")
@Mapper
public interface AdminGoodsDao {
	public List<Goods> selectGoods();
	public List<Goods> selectAllGoodsByPage(int startIndex, int perPageSize);
	public int addGoods(Goods goods);
	public Goods selectGoodsById(Integer id);
	public int deleteGoods(List<Integer> ids);
	public int deleteAGoods(Integer id);
	public int updateGoodsById(Goods goods);
	public List<Map<String, Object>> selectCartGoods(Integer id);
	public List<Map<String, Object>> selectFocusGoods(Integer id);
	public List<Map<String, Object>> selectOrderdetailGoods(Integer id);
	public List<GoodsType> getGoodsType();
	public List<GoodsType> getAllGoodsType();
}
