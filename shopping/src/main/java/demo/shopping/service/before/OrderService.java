package demo.shopping.service.before;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.ui.Model;
public interface OrderService {
	public Integer orderSubmit(Double amount,Integer uid);
	public void pay(Integer ordersn);
}
