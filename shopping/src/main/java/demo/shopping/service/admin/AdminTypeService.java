package demo.shopping.service.admin;

import javax.servlet.http.HttpSession;

import demo.shopping.po.GoodsType;
import org.springframework.ui.Model;

import java.util.List;

public interface AdminTypeService {
	public List<GoodsType> toAddType();
	public List<GoodsType> addType(String typename);
	public List<GoodsType> toDeleteType();
	public int deleteType(Integer id);
}
