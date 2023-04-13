package demo.shopping.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminNoticeDao;
import demo.shopping.po.Notice;

import java.util.List;

@Service("adminNoticeService")
@Transactional
public class AdminNoticeServiceImpl implements AdminNoticeService{
	@Autowired
	private AdminNoticeDao adminNoticeDao;
	@Override
	public int addNotice(Notice notice) {
		int flag=adminNoticeDao.addNotice(notice);
		return flag;
	}
	@Override
	public List<Notice> deleteNoticeSelect() {

	List list=adminNoticeDao.deleteNoticeSelect();
		return list;
	}
	@Override
	public Notice selectANotice( Integer id) {
		Notice notice=adminNoticeDao.selectANotice(id);
		return notice;

	}
	@Override
	public int deleteNotice(Integer id) {
	    int flag=adminNoticeDao.deleteNotice(id);
		return flag;
	}

}
