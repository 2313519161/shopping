package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.po.Buser;
import demo.shopping.util.MyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.before.CartService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseBeforeController{
	Log logger = LogFactory.getLog(CartController.class);
	@Autowired
	private CartService cartService;

	@RequestMapping("/focus")
	public String focus(Model model,Integer id, HttpSession session) {

		if (session.getAttribute("bruser")!=null){

			Integer uid= MyUtil.getUserId(session);
			int n= cartService.focus(id,uid );
			if (n>0){
				logger.info("用户:"+uid+"已经关注商品id为"+id);
				model.addAttribute("msg", "已经关注过了");
			}
			return "forward:/goodsDetail?id="+id;

		}
		logger.info("用户未登录，需先登录");
		return "before/login1";


	}





	@RequestMapping("/putCart")
	public String putCart(Model model,Integer shoppingnum, Integer id, HttpSession session) {
		if (session.getAttribute("bruser")!=null){
			Integer uid= MyUtil.getUserId(session);
			cartService.putCart(shoppingnum, id,uid);
			logger.info("放置购物车");
			logger.info("用户:"+uid+"放置的商品id为"+id);
			return "forward:/cart/selectCart";
		}
		logger.info("用户未登录，需先登录");
		return "before/login1";

	}

	@RequestMapping("/selectCart")
	public String selectCart(Model model, HttpSession session) {
		if (session.getAttribute("bruser")!=null){
			Integer uid= MyUtil.getUserId(session);
			List<Object> list1=new ArrayList<>();
			list1= cartService.selectCart(uid);
			model.addAttribute("bruser",session.getAttribute("bruser"));
			model.addAttribute("total", list1.get(0));
			model.addAttribute("cartlist", list1.get(1));
			logger.info("用户:"+uid+"筛选购物车");
			return "before/cart";
		}
		logger.info("用户未登录，需先登录");
		return "before/login1";
	}

	@RequestMapping("/deleteAgoods")
	public String deleteAgoods(Integer id,HttpSession session) {
		Integer uid= MyUtil.getUserId(session);
		cartService.deleteAgoods(id,uid);
		logger.info("用户:"+uid+"删除一件商品,商品id为"+id);
		return "forward:/cart/selectCart";
	}

	@RequestMapping("/clear")
	public String clear(HttpSession session) {
		Integer uid= MyUtil.getUserId(session);
		cartService.clear(uid);
		logger.info("用户:"+uid+"清空购物车");
		return "forward:/cart/selectCart";
	}

	@RequestMapping("/orderConfirm")
	public String orderConfirm(Model model, HttpSession session) {
		Integer uid= MyUtil.getUserId(session);
		List<Object>list=new ArrayList<>();
		list=cartService.orderConfirm(uid);
		model.addAttribute("bruser",session.getAttribute("bruser"));
		model.addAttribute("total", list.get(0));
		model.addAttribute("cartlist", list.get(1));
		logger.info("用户:"+uid+"确认订单");
		return "before/orderconfirm";
	}
}
