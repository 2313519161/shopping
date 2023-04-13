package demo.shopping.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Notice;
import demo.shopping.service.admin.AdminNoticeService;

import java.util.List;

@Controller
@RequestMapping("/adminNotice")
public class AdminNoticeController extends BaseController{
	@Autowired
	private AdminNoticeService adminNoticeService;
	@RequestMapping("/toAddNotice")
	public String toAddNotice(Model model) {
		model.addAttribute("notice", new Notice());
		return "admin/addNotice";
	}
	@RequestMapping("/addNotice")
	public String addNotice(@ModelAttribute Notice notice) {

		int flag= adminNoticeService.addNotice(notice);

		return "forward:/adminNotice/deleteNoticeSelect";
	}
	@RequestMapping("/deleteNoticeSelect")
	public String deleteNoticeSelect(Model model) {
	 List list= adminNoticeService.deleteNoticeSelect();
		model.addAttribute("allNotices", list);
		return "admin/deleteNoticeSelect";
	}
	@RequestMapping("/selectANotice")
	public String selectANotice(Model model, Integer id) {
		Notice notice= adminNoticeService.selectANotice(id);
		model.addAttribute("notice", notice);
		return "admin/noticeDetail";
	}

	@RequestMapping("/deleteNotice")
	public String deleteNotice(Integer id) {

        int flag=adminNoticeService.deleteNotice(id);
		return "forward:/adminNotice/deleteNoticeSelect";
	}
}
