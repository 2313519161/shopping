package demo.shopping.dao;

import java.util.List;
import java.util.Map;

import demo.shopping.po.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import demo.shopping.po.Goods;

@Repository("indexDao")
@Mapper
public interface IndexDao {
	public List<Map<String, Object>> getSaleOrder();
	public List<Map<String, Object>> getFocusOrder();
	public List<Map<String, Object>> selectNotice();
	public List<Map<String, Object>> getLastedGoods(Goods goods);
	public Goods selectGoodsById(Integer id);
	public List<Goods> search(String mykey);
	public List<GoodsType> getGoodsType();
}
