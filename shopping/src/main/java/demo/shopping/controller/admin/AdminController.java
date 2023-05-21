package demo.shopping.controller.admin;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import demo.shopping.po.Goods;
import demo.shopping.po.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import demo.shopping.po.Auser;
import demo.shopping.service.admin.AdminService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/admin")
	public String toLogin() {
	return "admin/login";
	}

	@RequestMapping("/admin/login")
	public String login( @ModelAttribute Auser auser) {

		Auser a1=null;
		a1=adminService.getAllAdmin(auser);
		if (a1!=null){
			return "admin/main";
		}
		return "admin/login";
	}

	@RequestMapping("/exit")
	public String exit() {
		return "admin/login";
	}
}
