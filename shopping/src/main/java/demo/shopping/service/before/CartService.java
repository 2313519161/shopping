package demo.shopping.service.before;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import java.util.List;

public interface CartService {
	public void putCart(Integer shoppingnum, Integer id, Integer uid);
	public List<Object> selectCart( Integer uid);
	public void deleteAgoods(Integer id, Integer uid);
	public void clear(Integer uid);
	public int focus(Integer id,Integer uid);
	public List<Object> orderConfirm(Integer uid);
}
