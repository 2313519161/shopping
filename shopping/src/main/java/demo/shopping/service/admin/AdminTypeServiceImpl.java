package demo.shopping.service.admin;

import javax.servlet.http.HttpSession;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import demo.shopping.po.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminTypeDao;

import java.util.List;

@Service("adminTypeService")
@Transactional
public class AdminTypeServiceImpl implements AdminTypeService{
	@Autowired
	private AdminTypeDao adminTypeDao;

	@Override
	public List<GoodsType> toAddType(){
       //查找所有的商品类型
       List list=adminTypeDao.selectGoodsType();
		return list;
	}

	@Override
	public List<GoodsType> addType(String typename) {
		adminTypeDao.addType(typename);
		List list=adminTypeDao.selectGoodsType();
		return list;
	}

	@Override
	public List<GoodsType> toDeleteType() {
		List list=adminTypeDao.selectGoodsType();
		return list;
	}

	@Override
	public int deleteType(Integer id) {
//		if(adminTypeDao.selectGoodsByType(id).size() > 0) {
//			return "forward:/adminType/toDeleteType";
//		}
		int flag= adminTypeDao.deleteType(id);

		return flag;
	}
	
}
