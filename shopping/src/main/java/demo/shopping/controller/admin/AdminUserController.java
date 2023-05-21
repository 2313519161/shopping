package demo.shopping.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.admin.AdminUserService;

import java.util.List;

@Controller
@RequestMapping("/adminUser")
public class AdminUserController extends BaseController{
	@Autowired
	private AdminUserService adminUserService;
	@RequestMapping("/userInfo")
	public String userInfo(Model model){
		List list=adminUserService.userInfo();
		model.addAttribute("userList", list);
		return "admin/userManager";
	}
	@RequestMapping("/deleteuserManager")
	public String deleteuserManager(Integer id) {
		int flag=adminUserService.deleteuserManager(id);
		return "forward:/adminUser/userInfo";

	}

}
