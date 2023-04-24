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
	public String login( @ModelAttribute Auser auser, Model model, HttpSession session) {

		Auser a1=null;
		a1=adminService.login(auser);
		List<GoodsType> list=adminService.selectGoodsType();
		session.setAttribute("auser", auser);
		session.setAttribute("goodsType",list);

		if (a1!=null)return "admin/main";

		return "admin/login";
	}

	@RequestMapping("/exit")
	public String exit(@ModelAttribute Auser auser,HttpSession session) {
		session.invalidate();
		return "admin/login";
	}



	@ExceptionHandler(Exception.class)
	public ModelAndView handleLoginError(Exception ex){

		return new ModelAndView("/error");
	}
}
