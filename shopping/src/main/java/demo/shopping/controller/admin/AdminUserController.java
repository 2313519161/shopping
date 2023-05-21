package demo.shopping.controller.admin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.admin.AdminUserService;

import java.util.List;

@Controller
@RequestMapping("/adminUser")
public class AdminUserController extends BaseController{
	Log logger = LogFactory.getLog(AdminUserController.class);
	@Autowired
	private AdminUserService adminUserService;
	@RequestMapping("/userInfo")
	public String userInfo(Model model){
		List list=adminUserService.userInfo();
		model.addAttribute("userList", list);
		logger.info("前往管理用户信息");
		logger.trace("用户列表"+list);
		return "admin/userManager";
	}
	@RequestMapping("/deleteuserManager")
	public String deleteuserManager(Integer id) {
		int flag=adminUserService.deleteuserManager(id);
		logger.info("删除用户信息成功，删除的id为"+id);
		return "forward:/adminUser/userInfo";
	}

}
