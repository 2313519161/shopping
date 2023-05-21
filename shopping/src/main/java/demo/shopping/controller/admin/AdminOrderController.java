package demo.shopping.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.admin.AdminOrderService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminOrder")
public class AdminOrderController extends BaseController{
	Log logger = LogFactory.getLog(AdminOrderController.class);
	@Autowired
	private AdminOrderService adminOrderService;

	@RequestMapping("/orderInfo")
	public String orderInfo(Model model) {

		List<Map<String,Object>> list = adminOrderService.orderInfo();
		model.addAttribute("orderList", list);
		logger.info("前往查看订单信息");
		return "admin/orderManager";
	}
	@RequestMapping("/deleteorderManager")
	public String deleteorderManager(Integer id) {
		int flag= adminOrderService.deleteorderManager(id);
		logger.info("删除订单，其id为"+id);
		return "forward:/adminOrder/orderInfo";
	}
}

