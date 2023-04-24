package demo.shopping.service.admin;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public interface AdminOrderService {
	public List<Map<String,Object>> orderInfo();
	public int deleteorderManager(Integer id);
}
