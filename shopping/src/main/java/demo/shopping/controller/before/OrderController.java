package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.util.MyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.before.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseBeforeController{
	Log logger = LogFactory.getLog(OrderController.class);
	@Autowired
	private OrderService orderService;

	@RequestMapping("/orderSubmit")
	public String orderSubmit(Model model, HttpSession session,Double amount) {
		Integer uid= MyUtil.getUserId(session);
		Integer id=orderService.orderSubmit( amount,uid);
		model.addAttribute("bruser",session.getAttribute("bruser"));
		model.addAttribute("ordersn",id);
		logger.info("提交商品去支付");
		logger.info("用户"+uid+"提交商品以支付，商品id为"+id);
		return "before/orderdone";
	}

	@RequestMapping("/pay")
	public String pay(Model model,HttpSession session,Integer ordersn) {
		orderService.pay(ordersn);
		model.addAttribute("bruser",session.getAttribute("bruser"));
		logger.info("支付订单");
		return "before/paydone";
	}
}
