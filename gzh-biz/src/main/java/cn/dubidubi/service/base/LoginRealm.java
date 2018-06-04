package cn.dubidubi.service.base;

import cn.dubidubi.model.base.PermissionDO;
import cn.dubidubi.model.base.UserDO;
import cn.dubidubi.service.UserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LoginRealm extends AuthorizingRealm {
	@Autowired
	UserLoginService userLoginService;

	// 清除缓存
	public void clearCache() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principalCollection);
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String account = (String) token.getPrincipal();
		String dbpassword = userLoginService.getPasswordByAccount(account);
		if (dbpassword == null) {
			return null;
		}
		UserDO userDO = userLoginService.getUserDOToSessionByAccount(account);
		if (!userDO.getEnabled().equals("Y")) {
			throw new LockedAccountException();
		}
		userDO.setAccount(account);
		return new SimpleAuthenticationInfo(userDO, dbpassword, this.getName());
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserDO userDO = (UserDO) principals.getPrimaryPrincipal();
		Integer roleId = userLoginService.getRoleIdByUserId(userDO.getId());
		if (roleId == null) {
			return null;
		}
		List<PermissionDO> list = userLoginService.listPermissionByRoleId(roleId);
		int length = list.size();
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		for (int i = 0; i < length; i++) {
			String temp = list.get(i).getEnabled();
			if (temp.equals("Y")) {
				simpleAuthorizationInfo.addStringPermission(list.get(i).getRemark());
			}
		}
		return simpleAuthorizationInfo;
	}

}
