package demo.shopping.controller.admin;
import javax.servlet.http.HttpServletRequest;

import demo.shopping.po.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Goods;
import demo.shopping.service.admin.AdminGoodsService;

import java.util.List;

@Controller
@RequestMapping("/admin/adminGoods")
public class AdminGoodsController extends BaseController{
	@Autowired
	private AdminGoodsService adminGoodsService;

	@RequestMapping("/selectGoods")
	public String selectGoods(Model model, Integer pageCur, String act){
		if(pageCur==null)pageCur=1;

		System.out.println(pageCur);
		List<Goods> allGoods=null;
		int totalCount=adminGoodsService.CountInfo();
		int totalPage=adminGoodsService.CountPage();

		allGoods=adminGoodsService.selectGoods(pageCur, act);

		model.addAttribute("allGoods", allGoods);

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("pageCur", pageCur);

		if("deleteSelect".equals(act)){
			return "admin/deleteSelectGoods";
		}else if("updateSelect".equals(act)){
			return "admin/updateSelectGoods";
		}else{
			return "admin/selectGoods";
		}

	}

	@RequestMapping("/toAddGoods")
	public String toAddGoods(Model model){

		List<GoodsType> list=adminGoodsService.getGoodsType();
		model.addAttribute("GoodsType",list);
		return "admin/addGoods";
	}

	@RequestMapping("/addGoods")
	public String addGoods(@ModelAttribute Goods goods, HttpServletRequest request, String updateAct){

		System.out.println(goods.getGoprice());
		return adminGoodsService.addOrUpdateGoods(goods, request, updateAct);
	}

	@RequestMapping("/selectAGoods")
	public String selectAGoods(Model model, Integer id, String act){

		return adminGoodsService.selectAGoods(model, id, act);
	}

	@RequestMapping("/deleteGoods")
	public String deleteGoods(Integer ids[], Model model) {
		return adminGoodsService.deleteGoods(ids, model);
	}

	@RequestMapping("/deleteAGoods")
	public String deleteAGoods(Integer id, Model model) {
		return adminGoodsService.deleteAGoods(id, model);
	}
}
