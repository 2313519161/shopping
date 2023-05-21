package demo.shopping.service.before;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.UserCenterDao;
import demo.shopping.util.MyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("userCenterService")
@Transactional
public class UserCenterServiceImpl implements UserCenterService{
	@Autowired
	private UserCenterDao userCenterDao;

	@Override
	public List<Object> userCenter(Integer uid) {
		List<Object>list=new ArrayList<>();
		list.add(0,userCenterDao.myOrder(uid));
		list.add(1,userCenterDao.myFocus(uid));
		return list;
	}

	@Override
	public List<Map<String,Object>> orderDetail(Integer ordersn) {
		List<Map<String,Object>> list=userCenterDao.orderDetail(ordersn);
		return list;
	}

}
