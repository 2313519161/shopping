package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.util.MailUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	Log logger = LogFactory.getLog(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping("/register")
	public String register(@RequestBody @ModelAttribute @Validated Buser buser, BindingResult bindingResult, Model model, HttpSession session, String code) throws Exception {
		logger.info("显示注册");
		if (bindingResult.hasErrors()){
			model.addAttribute("codeError", "邮箱或密码格式不符合要求！");
			logger.debug("邮箱或密码格式不符合要求");
			return "before/register";
		}
		else {
			if(!code.equalsIgnoreCase(session.getAttribute("code").toString())) {
				model.addAttribute("codeError", "验证码错误！");
				logger.debug("验证码错误");
				return "before/register";
			}
			int n=userService.register(buser);

			if(n > 0) {
				logger.info("注册成功，登录！");
				return "before/login1";
			}else {
				model.addAttribute("msg", "注册失败！");
				logger.debug("注册失败");
				return "before/register";
			}
		}
	}
	@RequestMapping("/login")
	public String login(@ModelAttribute Buser buser,Model model, HttpSession session, String code) throws Exception {
		if(!code.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("msg", "验证码错误");
			logger.debug("验证码错误");
			return "before/login1";
		}
		Buser buser1=null;
		buser1=userService.login(buser);
		if(buser1 != null) {
			model.addAttribute("bruser",buser1);

			session.setAttribute("bruser",buser1);
			logger.info("登录成功，前往首页");
			return "forward:/before";
		}else {
			model.addAttribute("msg", "用户不存在");
			logger.debug("用户不存在，请重新登录");
			return "before/login1";
		}
	}
	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		session.invalidate();
		logger.info("用户退出");
		return "forward:/before";
	}
}
