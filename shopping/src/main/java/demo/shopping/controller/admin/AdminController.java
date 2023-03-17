package demo.shopping.controller.admin;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Auser;
import demo.shopping.service.admin.AdminService;
@Controller
public class AdminController {
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private AdminService adminService;

	@RequestMapping("/admin")
	public String toLogin(@ModelAttribute Auser auser,Model model) {
	String s=messageSource.getMessage("login.page.title",new String[]{"参数0","参数1"},LocaleContextHolder.getLocale());
		model.addAttribute("title",s);

	return "admin/login";
	}

	@RequestMapping("/admin/login")
	public String login(@ModelAttribute Auser auser, Model model, HttpSession session) {
		return adminService.login(auser, model, session);
	}

	@RequestMapping("/exit")
	public String exit(@ModelAttribute Auser auser,HttpSession session) {
		session.invalidate();
		return "admin/login";
	}
}
