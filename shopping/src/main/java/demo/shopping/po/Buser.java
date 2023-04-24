package demo.shopping.po;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Validated
public class Buser {
	private Integer id;
	@Email(message = "邮箱格式错误")
	private String bemail;

	@Pattern(regexp = "^(?=.*[a-zA-Z0-9].*)(?=.*[a-zA-Z.!@#$%^&*].*)(?=.*[0-9.!@#$%^&*].*).{6,32}$", message = "密码至少包含数字，字母和符号的两种")
	private String bpwd;

	public Buser() {
	}

	public Buser(@Email(message = "邮箱格式错误") String bemail, @Pattern(regexp = "^(?=.*[a-zA-Z0-9].*)(?=.*[a-zA-Z.!@#$%^&*].*)(?=.*[0-9.!@#$%^&*].*).{6,32}$", message = "密码至少包含数字，字母和符号的两种") String bpwd) {
		this.bemail = bemail;
		this.bpwd = bpwd;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBemail() {
		return bemail;
	}
	public void setBemail(String bemail) {
		this.bemail = bemail;
	}
	public String getBpwd() {
		return bpwd;
	}
	public void setBpwd(String bpwd) {
		this.bpwd = bpwd;
	}
}
