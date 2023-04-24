package demo.shopping.controller.before;
import javax.servlet.http.HttpSession;

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
	public String before(Model model,HttpSession session, Goods goods) {
		return indexService.before(model, session, goods);
	}

	@RequestMapping("/showHeadPage")
	public String showHeadPage(Model model){
     //发送全部的商品类型

		List list=indexService.getAllGoodsType();
        model.addAttribute("goodsType",list);
         return "before/head";
	}

	@RequestMapping("/toRegister")
	public String toRegister(Model model) {
		return indexService.toRegister(model);
	}

	@RequestMapping("/toLogin")
	public String toLogin(Model model) {
		System.out.println("enter toLogin");
		return indexService.toLogin(model);
	}

	@RequestMapping("/goodsDetail")
	public String goodsDetail(Model model,Integer id) {
		return indexService.goodsDetail(model, id);
	}

	@RequestMapping("/selectANotice")
	public String selectANotice(Model model,Integer id) {
		return indexService.selectANotice(model, id);
	}

	@RequestMapping("/search")
	public String search(Model model,String mykey) {
		return indexService.search(model, mykey);
	}

	@GetMapping("/reset_password")
	public String ToReset_Password(){

		return "before/reset_password";
	}

	@PostMapping("/reset_password")
	public DeferredResult<String> DoReset_Password(){
		DeferredResult<String> result=new DeferredResult<>();
		result.setResult("before/email_sent");
		return result;
	}
}
