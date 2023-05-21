package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.before.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseBeforeController{
	@Autowired
	private OrderService orderService;

	@RequestMapping("/orderSubmit")
	public String orderSubmit(Model model, HttpSession session,Double amount) {
		Integer uid= MyUtil.getUserId(session);
	    Integer id=orderService.orderSubmit( amount,uid);
	    model.addAttribute("bruser",session.getAttribute("bruser"));
		model.addAttribute("ordersn",id);
		return "before/orderdone";
	}

	@RequestMapping("/pay")
	public String pay(Model model,HttpSession session,Integer ordersn) {
		 orderService.pay(ordersn);
		model.addAttribute("bruser",session.getAttribute("bruser"));
		return "before/paydone";
	}
}
