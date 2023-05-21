package demo.shopping.service.before;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import demo.shopping.po.Buser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import demo.shopping.dao.CartDao;
import demo.shopping.util.MyUtil;

@Service("cartService")
@Transactional
public class CartServiceImpl implements CartService{
	@Autowired
	private CartDao cartDao;

	@Override
	public int focus(Integer id, Integer uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("gid", id);
		List<Map<String, Object>> list = cartDao.isFocus(map);
		cartDao.focus(map);
		return list.size();
		}
	@Override
	public void putCart(Integer shoppingnum, Integer id, Integer uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("gid", id);
		map.put("shoppingnum", shoppingnum);
		List<Map<String, Object>> list = cartDao.isPutCart(map);
		if(list.size() > 0)
			cartDao.updateCart(map);
		else
			cartDao.putCart(map);
	}
	@Override
	public List<Object> selectCart(Integer uid) {
		List<Map<String, Object>> list = cartDao.selectCart(uid);
		double sum = 0;
		for (Map<String, Object> map : list) {
			sum = sum + (Double)map.get("smallsum");
		}
		List<Object> list1=new ArrayList<>();
		list1.add(0,sum);
		list1.add(1,list);
		return list1;
	}
	@Override
	public void deleteAgoods(Integer id, Integer uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("gid", id);
		cartDao.deleteAgoods(map);

	}
	@Override
	public void clear(Integer uid) {
		cartDao.clear(uid);

	}
	@Override
	public List<Object> orderConfirm( Integer uid) {
		List<Map<String, Object>> list = cartDao.selectCart(uid);
		double sum = 0;
		for (Map<String, Object> map : list) {
			sum = sum + (Double)map.get("smallsum");
		}
		List<Object>list1=new ArrayList<>();
		list1.add(0,sum);
		list1.add(1,list);
	    return list1;
	}

}
