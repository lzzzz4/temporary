package cn.dubidubi.service;

import cn.dubidubi.model.base.PermissionDO;
import cn.dubidubi.model.base.UserDO;

import java.util.List;

public interface UserLoginService {

	String getPasswordByAccount(String account);

	// 得到放入session中的user对象
	UserDO getUserDOToSessionByAccount(String account);

	Integer getRoleIdByUserId(int id);

	List<PermissionDO> listPermissionByRoleId(int roleId);
}