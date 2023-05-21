package demo.shopping.service.before;
import java.util.List;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import demo.shopping.po.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import demo.shopping.dao.AdminNoticeDao;
import demo.shopping.dao.AdminTypeDao;
import demo.shopping.dao.IndexDao;
import demo.shopping.po.Buser;
import demo.shopping.po.Goods;
import demo.shopping.po.Notice;

@Service("indexService")
@Transactional
public class IndexServiceImpl implements IndexService{
	@Autowired
	private IndexDao indexDao;
	@Autowired
	private AdminTypeDao adminTypeDao;
	@Autowired
	private AdminNoticeDao adminNoticeDao;

	@Override
	public List<Map<String, Object>> selectNotice() {
		List<Map<String, Object>> list = indexDao.selectNotice();
		return list;
	}

	@Override
	public List<Map<String, Object>> getFocusOrder() {
		List<Map<String, Object>> list=indexDao.getFocusOrder();

		return list;
	}

	@Override
	public List<Map<String, Object>> getLastedGoods(Goods goods) {
		if(goods.getId() == null)
			goods.setId(0);
		List<Map<String, Object>> list =indexDao.getLastedGoods(goods);
		return list;
	}

	@Override
	public List<Map<String, Object>> getSaleOrder() {
		List<Map<String, Object>> list=indexDao.getSaleOrder();
		return list;
	}

	@Override
	public Goods goodsDetail( Integer id) {

		return indexDao.selectGoodsById(id);
	}

	@Override
	public Notice selectANotice( Integer id) {
		Notice notice = adminNoticeDao.selectANotice(id);
	return adminNoticeDao.selectANotice(id);
	}

	@Override
	public List<Goods>  search( String mykey) {

		return indexDao.search(mykey);
	}

	@Override
	public List getAllGoodsType() {
		List list=indexDao.getGoodsType();
		return list;
	}


}
