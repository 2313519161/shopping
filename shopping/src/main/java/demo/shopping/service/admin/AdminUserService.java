package demo.shopping.service.admin;

import demo.shopping.po.Buser;
import org.springframework.ui.Model;

import java.util.List;

public interface AdminUserService {
	public List<Buser> userInfo();
	public int deleteuserManager(Integer id);
}
