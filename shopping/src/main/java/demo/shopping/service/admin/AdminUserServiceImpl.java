package demo.shopping.service.admin;
import demo.shopping.po.Buser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminUserDao;
import demo.shopping.dao.CartDao;
import demo.shopping.dao.UserCenterDao;

import java.util.List;

@Service("adminUserService")
@Transactional
public class AdminUserServiceImpl implements AdminUserService{
	@Autowired
	private AdminUserDao adminUserDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private UserCenterDao userCenterDao;
	@Override
	public List<Buser> userInfo() {
		List list=adminUserDao.userInfo();
		return list;
	}
	@Override
	public int deleteuserManager(Integer id) {
		int flag=adminUserDao.deleteuserManager(id);
		return flag;
	}

}
