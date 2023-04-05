package demo.shopping.controller.admin;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.admin.AdminTypeService;

import java.util.List;

@Controller
@RequestMapping("/adminType")
public class AdminTypeController extends BaseController{
	@Autowired
	private AdminTypeService adminTypeService;

	@RequestMapping("/toAddType")
	public String toAddType(Model model) {
		List list=adminTypeService.toAddType();
		model.addAttribute("allTypes", list);
		return "admin/addType";
	}


	@RequestMapping("/addType")
	public String addType(String typename,Model model,HttpSession session){
		List list=adminTypeService.addType(typename);
		session.setAttribute("goodsType", list);
		return "forward:/adminType/toAddType";
	}

	@RequestMapping("/toDeleteType")
	public String toDeleteType(Model model) {
		List list=adminTypeService.toDeleteType();
		model.addAttribute("allTypes",list);
		return "admin/deleteType";
	}

	@RequestMapping("/adminType")
	public String deleteType(Integer id,Model model) {
		System.out.println("Enter deleteType");
		System.out.println(id);

        int flag=adminTypeService.deleteType(id);
		System.out.println("Exit deleteType");
		return "forward:/adminType/toDeleteType";
	}
	
}
