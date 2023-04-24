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
	public int register(String BEmail,String  CunBPwd) throws Exception {

	    String pwd=Bpwd(CunBPwd);

		int n = userDao.addUser(new Buser(BEmail,pwd));
		return n;
	}

	public static String Bpwd(String pwd) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = md5.digest(pwd.getBytes());

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
		return result;
	}

	@Override
	public Buser login(Buser buser) throws Exception {

		Buser ruser = null;

		buser.setBpwd(Bpwd(buser.getBpwd()));
		List<Buser> list = userDao.searchAllUserInfo();
		//通过dao层将所有user取出比较

		for (int i = 0; i <list.size() ; i++) {

			if (ruser.getBemail().equals(list.get(i).getBemail()) && ruser.getBpwd().equals(list.get(i).getBpwd())
			){
				ruser = list.get(i);
				return ruser;

			}
		}

return null;
	}

}
