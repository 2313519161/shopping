package demo.shopping.controller.admin;
import javax.servlet.http.HttpServletRequest;

import demo.shopping.po.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
	public String selectGoods(Model model, Integer pageCur){
		System.out.println("进入selectGoods");
		if(pageCur==null)pageCur=1;
		List<Goods> allGoods=null;
		int totalCount=adminGoodsService.CountInfo();
		int totalPage=adminGoodsService.CountPage();
		allGoods=adminGoodsService.selectGoods(pageCur);
		model.addAttribute("allGoods", allGoods);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("pageCur", pageCur);
			return "admin/selectGoods";

	}
	@RequestMapping("/toDeleteGoods")
	public String toDeleteGoods(Model model,Integer pageCur){
		if(pageCur==null)pageCur=1;
		List<Goods> allGoods=null;
		int totalCount=adminGoodsService.CountInfo();
		int totalPage=adminGoodsService.CountPage();
		allGoods=adminGoodsService.selectGoods(pageCur);
		model.addAttribute("allGoods", allGoods);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("pageCur", pageCur);
		return "admin/deleteSelectGoods";
	}

	@RequestMapping("/toUpadateGoods")
	public String toUpadateGoods(Model model,Integer pageCur){
		if(pageCur==null)pageCur=1;
		List<Goods> allGoods=null;
		int totalCount=adminGoodsService.CountInfo();
		int totalPage=adminGoodsService.CountPage();
		allGoods=adminGoodsService.selectGoods(pageCur);
		model.addAttribute("allGoods", allGoods);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("pageCur", pageCur);
		return "admin/updateSelectGoods";

	}
	@RequestMapping("/toAddGoods")
	public String toAddGoods(Model model){
		List<GoodsType> list=adminGoodsService.getGoodsType();
		model.addAttribute("GoodsType",list);
		return "admin/addGoods";
	}

	@RequestMapping("/addGoods")
	public String addGoods(@ModelAttribute Goods goods ){
		int flag=adminGoodsService.addGoods(goods);
		if (flag>0){
			return "forward:/admin/adminGoods/selectGoods";
		}
		return "error/error";
	}

	//进行更新操作
	@RequestMapping("/updateGoods")
	public String updateGoods(@ModelAttribute Goods goods){

		int flag=adminGoodsService.updateGoods(goods);
		if (flag>0){
			return "forward:/admin/adminGoods/selectGoods";
		}
		return "error/error";
	}

	//在更新页面查找一个商品,以及商品类型
	@RequestMapping("/selectAGoods")
	public String selectAGoods( Model model,Integer id){

	     Goods goods=null;
		 goods=adminGoodsService.selectAGoods(id);
		 List list=adminGoodsService.getGoodsType();

		if(goods!=null){
			model.addAttribute("goods", goods);
			model.addAttribute("GoodsType",list);
			return "admin/updateAgoods";
		}
		return "error/error";
	}

	@RequestMapping("showGoodsDetil")
	public String showGoodsDetil(Model model,Integer id){
		Goods goods=null;
		goods=adminGoodsService.selectAGoods(id);

		List list=adminGoodsService.getGoodsType();

		if(goods!=null){
			model.addAttribute("goods", goods);
			model.addAttribute("GoodsType",list);
			return "admin/goodsDetail";
		}
		return "error/error";

	}

	@RequestMapping("/deleteGoods")
	public String deleteGoods(Integer ids[], Model model){
		//删除一堆商品
		int flag;
		flag= adminGoodsService.deleteGoods(ids);
		if (flag>0){
			return "forward:/admin/adminGoods/toDeleteGoods"; }
		    return "error/error";
	}

	@RequestMapping("/deleteAGoods")
	public String deleteAGoods(Integer id) {
		//删除一个商品
		int flag;
		flag= adminGoodsService.deleteAGoods(id);
		if (flag>0){
			return "forward:/admin/adminGoods/toDeleteGoods";
		}
	return "error/error";
	}

}
