package demo.shopping.service.admin;
import javax.servlet.http.HttpServletRequest;

import demo.shopping.po.GoodsType;
import org.springframework.ui.Model;

import demo.shopping.po.Goods;

import java.util.List;

public interface AdminGoodsService {
	public Goods selectAGoods( Integer id);
	public int deleteGoods(Integer ids[]);
	public int deleteAGoods(Integer id);
	public int addGoods(Goods goods);
	public List<Goods> selectGoods( Integer pageCur);
	public int CountInfo();
	public int CountPage();
	public List<GoodsType> getGoodsType();
    public int updateGoods(Goods goods);

}
