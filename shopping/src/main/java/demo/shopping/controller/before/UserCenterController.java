package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.util.MyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.before.UserCenterService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserCenterController extends BaseBeforeController{
	Log logger = LogFactory.getLog(UserCenterController.class);
	@Autowired
	private UserCenterService userCenterService;
	@RequestMapping("/userCenter")
	public String userCenter(HttpSession session, Model model) {
		if (session.getAttribute("bruser")!=null){
			List<Object> list=new ArrayList<>();
			Integer uid= MyUtil.getUserId(session);
			list=userCenterService.userCenter(uid);
			model.addAttribute("bruser",session.getAttribute("bruser"));
			model.addAttribute("myOrder", list.get(0));
			model.addAttribute("myFocus", list.get(1));
			logger.info("前往用户中心，用户id为"+uid);
			return "before/userCenter";
		}
		logger.info("用户未登录，需先登录");
		return "before/login1";

	}
	@RequestMapping("/orderDetail")
	public String orderDetail(HttpSession session,Model model, Integer ordersn) {
		model.addAttribute("bruser",session.getAttribute("bruser"));
		model.addAttribute("myOrderDetail", userCenterService.orderDetail( ordersn));
		logger.info("前往该用户选定的商品详情页面");
		return "before/userOrderDetail";
	}
}
