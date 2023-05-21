package demo.shopping.controller.before;
import javax.servlet.http.HttpSession;

import demo.shopping.po.Buser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Goods;
import demo.shopping.service.before.IndexService;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
@Controller
public class IndexController {
	@Autowired
	private IndexService indexService;

	@RequestMapping("/before")
	public String before(Model model, Goods goods,HttpSession session) {
		model.addAttribute("goodsType",indexService.getAllGoodsType());
		model.addAttribute("salelist", indexService.getSaleOrder());
		model.addAttribute("focuslist", indexService.getFocusOrder());
		model.addAttribute("noticelist",indexService.selectNotice());
		model.addAttribute("lastedlist", indexService.getLastedGoods(goods));
		model.addAttribute("bruser",session.getAttribute("bruser"));
		return "before/index";
	}

	@RequestMapping("/toRegister")
	public String toRegister(Model model) {
		model.addAttribute("rbuser", new Buser());
		return "before/register";
	}

	@RequestMapping("/toLogin")
	public String toLogin(Model model) {
		model.addAttribute("lbuser", new Buser());
		return "before/login1";
	}

	@RequestMapping("/goodsDetail")
	public String goodsDetail(Model model,Integer id ,HttpSession session) {
		model.addAttribute("bruser",session.getAttribute("bruser"));
		model.addAttribute("goods", indexService.goodsDetail(id));
		return "before/goodsdetail";
	}

	@RequestMapping("/selectANotice")
	public String selectANotice(Model model,Integer id) {
		model.addAttribute("notice", indexService.selectANotice( id));
		return "admin/noticeDetail";
	}

	@RequestMapping("/search")
	public String search(Model model,HttpSession session,String mykey) {
		model.addAttribute("bruser",session.getAttribute("bruser"));
		model.addAttribute("searchlist", indexService.search(mykey));
		return "before/searchResult";
	}
}
