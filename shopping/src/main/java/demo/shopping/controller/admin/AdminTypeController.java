package demo.shopping.controller.admin;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.admin.AdminTypeService;

import java.util.List;

@Controller
@RequestMapping("/adminType")
public class AdminTypeController extends BaseController{
	Log logger = LogFactory.getLog(AdminTypeController.class);
	@Autowired
	private AdminTypeService adminTypeService;

	@RequestMapping("/toAddType")
	public String toAddType(Model model) {
		List list=adminTypeService.toAddType();
		logger.info("前往新增管理员页面");
		model.addAttribute("allTypes", list);
		logger.trace("管理员列表"+list);
		return "admin/addType";
	}


	@RequestMapping("/addType")
	public String addType(String typename,Model model,HttpSession session){
		List list=adminTypeService.addType(typename);
		session.setAttribute("goodsType", list);
		logger.info("成功新增管理员，管理员为"+typename);
		logger.trace("全部管理员(新增后)"+list);
		return "forward:/adminType/toAddType";
	}

	@RequestMapping("/toDeleteType")
	public String toDeleteType(Model model) {
		List list=adminTypeService.toDeleteType();
		model.addAttribute("allTypes",list);
		logger.trace("全部管理员(删除后)"+list);
		logger.info("前往删除管理员");
		return "admin/deleteType";
	}

	@RequestMapping("/adminType")
	public String deleteType(Integer id,Model model) {

		int flag=adminTypeService.deleteType(id);
		logger.info("删除管理员,id为"+id);
		return "forward:/adminType/toDeleteType";
	}

}
