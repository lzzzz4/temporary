package cn.dubidubi.model.base;

import java.io.Serializable;

/**
 * @author 16224
 * @Description: 用户对象
 * @date 2018年1月10日 下午3:27:09
 */
public class UserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String account;
	private String password;
	private String username;
	private String enabled;
	private String agency;
	private String mail;
	private String phone;

	@Override
	public String toString() {
		return "UserDO [id=" + id + ", account=" + account + ", password=" + password + ", username=" + username
				+ ", enabled=" + enabled + ", agency=" + agency + ", mail=" + mail + ", phone=" + phone + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
