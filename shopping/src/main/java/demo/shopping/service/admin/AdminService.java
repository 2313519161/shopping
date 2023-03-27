package demo.shopping.service.admin;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import demo.shopping.po.Auser;

public interface AdminService {
	public Auser login(Auser auser, Model model, HttpSession session);
}
