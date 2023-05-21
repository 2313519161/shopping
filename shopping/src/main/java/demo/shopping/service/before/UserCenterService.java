package demo.shopping.service.before;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public interface UserCenterService {
	public List<Object> userCenter(Integer uid);
	public List<Map<String,Object>> orderDetail(Integer ordersn);
}
