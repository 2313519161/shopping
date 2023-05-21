package demo.shopping.service.before;

import javax.servlet.http.HttpSession;

import demo.shopping.po.GoodsType;
import demo.shopping.po.Notice;
import org.springframework.ui.Model;

import demo.shopping.po.Goods;

import java.util.List;
import java.util.Map;

public interface IndexService {

	public Goods goodsDetail(Integer id);
	public Notice selectANotice(Integer id);
	public List<Goods> search(String mykey);
    public List getAllGoodsType();
    public List<Map<String,Object>> getFocusOrder();
    public List<Map<String,Object>> selectNotice();
    public  List<Map<String,Object>> getLastedGoods(Goods goods);
    public  List<Map<String,Object>> getSaleOrder();

}
