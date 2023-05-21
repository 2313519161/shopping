package demo.shopping.service.before;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.OrderDao;
import demo.shopping.po.Order;
import demo.shopping.util.MyUtil;
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDao orderDao;

	@Override
	public Integer orderSubmit( Double amount,Integer uid) {
		Order order = new Order();
		order.setAmount(amount);
		order.setBusertable_id(uid);
		orderDao.addOrder(order);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ordersn", order.getId());
		map.put("uid", uid);
		orderDao.addOrderDetail(map);
		List<Map<String, Object>> list = orderDao.selectGoodsShop(uid);
		for (Map<String, Object> map2 : list) { orderDao.updateStore(map2); }
		orderDao.clear(uid);
		return order.getId();
	}

	@Override
	public void pay(Integer ordersn) {
		orderDao.pay(ordersn);

	}

}
