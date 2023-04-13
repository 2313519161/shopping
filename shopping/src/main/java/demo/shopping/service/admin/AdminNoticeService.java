package demo.shopping.service.admin;

import org.springframework.ui.Model;

import demo.shopping.po.Notice;

import java.util.List;

public interface AdminNoticeService {
	public int addNotice(Notice notice);
	public List<Notice> deleteNoticeSelect();
	public Notice selectANotice( Integer id);
	public int deleteNotice(Integer id);
}
