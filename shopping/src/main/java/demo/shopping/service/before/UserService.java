package demo.shopping.service.before;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import demo.shopping.po.Buser;

import java.security.NoSuchAlgorithmException;

public interface UserService {
	public int register(String BEmail,String ZhenBPwd ) throws Exception;

	public Buser login(Buser buser,Model model, HttpSession session) throws Exception;
}
