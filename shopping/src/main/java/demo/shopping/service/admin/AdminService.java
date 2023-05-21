package demo.shopping.service.admin;

import javax.servlet.http.HttpSession;

import demo.shopping.po.Goods;
import demo.shopping.po.GoodsType;
import org.springframework.ui.Model;

import demo.shopping.po.Auser;

import java.util.List;

public interface AdminService {
	public Auser getAllAdmin(Auser auser);

	public List<GoodsType> selectGoodsType();
}
