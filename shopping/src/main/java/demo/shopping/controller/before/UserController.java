package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Buser;
import demo.shopping.service.before.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/register")
	public String register(@RequestBody @ModelAttribute @Validated Buser buser, BindingResult bindingResult, Model model, HttpSession session, String code) throws Exception {
		if (bindingResult.hasErrors()){
			model.addAttribute("codeError", "检查输入");
			return "before/register";
		}
		else {
			if(!code.equalsIgnoreCase(session.getAttribute("code").toString())) {
				model.addAttribute("codeError", "检查输入");
				return "before/register";
			}

			String CunBPwd = userService.Bpwd(buser.getBpwd());
			//密码加密，通过userService.Bpwd（）函数加密，加密方法用的md5，未加盐

			int n=userService.register(buser.getBemail(),CunBPwd);
			if(n > 0) {
				return "before/login";
			}else {
				model.addAttribute("msg", "码");
				return "before/register";
			}
		}

	}




	@RequestMapping("/login")
	public String login(@ModelAttribute Buser buser,Model model, HttpSession session, String code) {
		return userService.login(buser, model, session, code);





	}





	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		session.invalidate();
		return "forward:/before";
	}
}
