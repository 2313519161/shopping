package demo.shopping.service.before;

import java.security.MessageDigest;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.UserDao;
import demo.shopping.po.Buser;

@Service("userService")
@Transactional

public class UserServiceImpl implements UserService{
	@Qualifier("userDao")
	@Autowired
	private UserDao userDao;
	@Override
	public int register(String BEmail,String  CunBPwd) {

		int n = userDao.register(BEmail,CunBPwd);
		return n;
	}

	@Override
	public String Bpwd(String pwd) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = md5.digest(pwd.getBytes());
		//md5加密
		String result = "";
			for(byte b : bytes)
		{
			String temp = Integer.toHexString(b & 0xff);

			if(temp.length() == 1)
			{
				temp = "0" + temp;
			}
			result = result + temp;
		}
		//将byte【】转为16进制存储
		System.out.println(result);
		return result;

	}


	@Override
	public Buser login(Buser buser, Model model, HttpSession session) {

		Buser ruser = null;
		List<Buser> list = userDao.login(buser);
		if(list.size() > 0) {
			ruser = list.get(0);
		}

		return ruser;

	}

}
