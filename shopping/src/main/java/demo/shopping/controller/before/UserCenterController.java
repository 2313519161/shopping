package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.before.UserCenterService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserCenterController extends BaseBeforeController{
	@Autowired
	private UserCenterService userCenterService;
	@RequestMapping("/userCenter")
	public String userCenter(HttpSession session, Model model) {
		List<Object> list=new ArrayList<>();
		Integer uid= MyUtil.getUserId(session);
		list=userCenterService.userCenter(uid);
		model.addAttribute("bruser",session.getAttribute("bruser"));
		model.addAttribute("myOrder", list.get(0));
		model.addAttribute("myFocus", list.get(1));
		return "before/userCenter";
	}
	@RequestMapping("/orderDetail")
	public String orderDetail(HttpSession session,Model model, Integer ordersn) {
		model.addAttribute("bruser",session.getAttribute("bruser"));
		model.addAttribute("myOrderDetail", userCenterService.orderDetail( ordersn));
		return "before/userOrderDetail";
	}
}
