package demo.shopping.controller.admin;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import demo.shopping.po.Goods;
import demo.shopping.po.GoodsType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	Log logger = LogFactory.getLog(AdminController.class);
	@Autowired
	private AdminService adminService;

	@RequestMapping("/admin")
	public String toLogin() {
		return "admin/login";
	}

	@RequestMapping("/admin/login")
	public String login( @Valid @ModelAttribute Auser auser) {

		Auser a1=null;
		a1=adminService.getAllAdmin(auser);
		logger.debug("enter login()");
		logger.debug("收到登录请求，来自："+auser.getAname());
		if (a1!=null){
			return "admin/main";
		}
		logger.debug("exit loging()");
		return "admin/login";
	}

	@RequestMapping("/exit")
	public String exit() {
		logger.info("退出完成");
		return "admin/login";
	}
}
