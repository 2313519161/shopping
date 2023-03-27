package demo.shopping.service.admin;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import demo.shopping.po.Goods;

import java.util.List;

public interface AdminGoodsService {
	public String selectAGoods(Model model, Integer id, String act);
	public String deleteGoods(Integer ids[], Model model);
	public String deleteAGoods(Integer id, Model model);
	public String addOrUpdateGoods(Goods goods, HttpServletRequest request, String updateAct);
	public List<Goods> selectGoods( Integer pageCur, String act);
	public int CountInfo();
	public int CountPage();

}
