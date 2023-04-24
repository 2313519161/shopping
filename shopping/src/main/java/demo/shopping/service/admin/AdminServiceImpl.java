package demo.shopping.service.admin;
import javax.servlet.http.HttpSession;

import demo.shopping.po.Goods;
import demo.shopping.po.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminDao;
import demo.shopping.dao.AdminTypeDao;
import demo.shopping.po.Auser;

import java.util.List;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private AdminTypeDao adminTypeDao;

	@Override
	public List<GoodsType> selectGoodsType() {
		List<GoodsType> list=adminTypeDao.selectGoodsType();
		return list;
	}

	@Override
	public Auser login(Auser auser) {

List<Auser> ausers=adminDao.login();

		for (int i = 0; i <ausers.size() ; i++) {
			if (auser.getAname().equals(ausers.get(i).getAname()) && auser.getApwd().equals(ausers.get(i).getApwd())){

				Auser auser1=ausers.get(i);
				return auser1 ;
			}
		}
			return null;



	}

}
