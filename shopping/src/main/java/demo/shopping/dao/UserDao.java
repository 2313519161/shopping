package demo.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import demo.shopping.po.Buser;

@Repository("userDao")
@Mapper
public interface UserDao {
//	String bemail ,String bpwd
	public int addUser(Buser buser);
	public List<Buser> searchAllUserInfo();
}
