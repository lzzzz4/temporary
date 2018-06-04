package cn.dubidubi.dao.base;

import cn.dubidubi.model.base.PermissionDO;

import java.util.List;

public interface PermissionMapper {
	List<PermissionDO> listPermissionByRole(int roleid);
}
