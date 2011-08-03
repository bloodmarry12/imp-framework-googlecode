package com.huawei.imp.framework.model.privilege.service;

import java.util.List;
import java.util.Map;

import com.huawei.imp.framework.exception.FrameworkBusinessException;
import com.huawei.imp.framework.model.privilege.cacheModel.ReadOnlyCacheModel;
import com.huawei.imp.framework.model.privilege.domain.Account;
import com.huawei.imp.framework.model.privilege.domain.ExplorerIndex;
import com.huawei.imp.framework.model.privilege.domain.Right;
import com.huawei.imp.framework.model.privilege.domain.Role;
import com.huawei.imp.framework.utils.PageResult;

/**
 * Description:
 * 用户管理业务接口
 * @author ahli
 * Apr 24, 2009
 * 
 */
public interface PrivilegeService {

	/**
	 * 激活账户对象
	 * @param bo
	 * @param roleID
	 */
	void activeAccount(long[] ids);
	
	/**
	 * 提交激活请求
	 * @param id
	 */
	void applyAccount2Active(long[] ids);
	
	
	/**
	 * 批量审批通过，激活账户
	 * @param ids
	 */
	void approveAccount2Active(long[] ids);
	
	/**
	 * 修改密码
	 * @param accountID
	 * @param passwrod
	 */
	void changePassword(long accountID, String opsw, String npsw);
	
	/**
	 * 强制修改密码
	 * @param accountID
	 * @param passwrod
	 */
	void changePassword(long accountID, String npsw);
	
	/**
	 * 获取角色对象
	 * @return
	 */
	List<Role> findAllRole();
	
	/**
	 * 用户登录
	 * @param accountName	账户名
	 * @param accountPSW	账户密码
	 * @return
	 * 	&lt;AccountBO&gt; 用户信息
	 * @exception
	 * FrameworkBusinessException	当账户名不存在、账户密码不匹配、账户状态非正常的情况下抛出业务异常。
	 */
	Account loadAccount(String accountName, String accountPSW);

	/**
	 * 获取账户对象
	 * @param id	ID
	 * @return
	 */
	Account loadAccount(long id);
	
	/**
	 * 用户登录
	 * @param accountName	账户名
	 * @param accountPSW	账户密码
	 * @param flag          是否更新登录时间
	 * @return
	 * 	&lt;AccountBO&gt; 用户信息
	 * @exception
	 * FrameworkBusinessException	当账户名不存在、账户密码不匹配、账户状态非正常的情况下抛出业务异常。
	 */
	Account loadAccount(String accountName, String accountPSW, boolean flag);
	
	/**
	 * 用户登录
	 * @param accountName	账户名
	 * @param accountPSW	账户密码(密文)
	 * @return
	 * 	&lt;AccountBO&gt; 用户信息
	 * @exception
	 * FrameworkBusinessException	当账户名不存在、账户密码不匹配、账户状态非正常的情况下抛出业务异常。
	 */
	Account loadAccountEncodePsw(String accountName, String accountPSW);
	
	/**
	 * 用户登录
	 * @param accountName	账户名
	 * @param accountPSW	账户密码(密文)
	 * @param flag          是否更新登录时间
	 * @return
	 * 	&lt;AccountBO&gt; 用户信息
	 * @exception
	 * FrameworkBusinessException	当账户名不存在、账户密码不匹配、账户状态非正常的情况下抛出业务异常。
	 */
	Account loadAccountEncodePsw(String accountName, String accountPSW, boolean flag);
	
	/**
	 * 获取系统全部导航条菜单对象
	 * @return
	 */
	ExplorerIndex loadExplorerIndex();
	
	/**
	 * 获取导航条菜单对象
	 * @param id 菜单ID
	 * @return
	 */
	ExplorerIndex loadExplorerIndexByID(long id);
	
	/**
	 * 从指定菜单项目开始，配合当前线程用户权限，生成导航条菜单对象根目录
	 * @param id
	 * @return
	 */
	ExplorerIndex loadExplorerIndexWithPermission(long id);
	
	/**
	 * 获取权限对象
	 * @param id	权限ID
	 * @return
	 */
	Right loadRight(long id);
	
	/**
	 * 获取权限对象
	 * @param id		权限ID
	 * @param roleID	角色ID
	 * @return
	 */
	Right loadRightWidthRoleID(long id, long roleID);
	
	/**
	 * 获取角色对象
	 * @param id	权限ID
	 * @return
	 */
	Role loadRole(long id);
	
	/**
	 * 分页查询账户信息
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	PageResult queryAccount(int pageSize, int pageNum);
	
	/**
	 * 列出指定角色ID下的所有账户
	 * @param roleID    角色ID
	 * @return
	 */
	List<Account> listAccountByRoleID(long roleID);
	
	/**
	 * 列出拥有指定权限编码的所有账户
	 * @param code    权限编码
	 * @return
	 */
	List<Account> listAccountByRightCode(String code);
	
	/**
	 * 分页查询账户信息
	 * @param pageSize	每页显示记录数
	 * @param pageNum	当前页码
	 * @param params	条件参数	
	 * @return
	 */
	PageResult queryAccount(int pageSize, int pageNum, Map<String, Object> params);
	
	/**
	 * 分页查询角色信息
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	PageResult queryRole(int pageSize, int pageNum);
	
	/**
	 * 清除内存模型中的数据
	 */
	void refreshCacheModle();
	
	/**
	 * 批量拒绝，退回账户激活请求
	 * @param ids
	 */
	void rejectAccount2Active(long[] ids);
	
	/**
	 * 删除账户对象
	 * @param id
	 */
	void removeAccount(long[] ids);
	
	/**
	 * 删除导航菜单
	 * @param id
	 */
	void removeExplorerIndex(long id);
	
	/**
	 * 删除权限对象
	 * @param id
	 */
	void removeRight(long id);
	
	/**
	 * 删除角色对象
	 * @param id
	 */
	void removeRole(long id);
	
	/**
	 * 保存账户对象
	 * @param bo
	 * @param roleID	角色IDs
	 */
	void saveAccount(Account bo);
	
	/**
	 * 保存导航菜单
	 * @param bo
	 */
	void saveExplorerIndex(ExplorerIndex bo);
	
	/**
	 * 保存权限对象
	 * @param bo
	 */
	void saveRight(Right bo);
	
	/**
	 * 保存角色对象
	 * @param bo
	 */
	void saveRole(Role bo, List<Long> rightID);
	
	/**
	 * 停用账户对象
	 * @param bo
	 * @param roleID
	 */
	void stopAccount(long[] ids);
	
	/**
	 * 更新账户对象
	 * @param bo
	 * @param roleID
	 */
	void updateAccountWithoutRight(Account bo);
	
	/**
	 * 更新账户对象的权限关系
	 * @param bo
	 * @param roleID
	 */
	void updateAccountRightOnly(Account bo);
	
	/**
	 * 导航菜单更新
	 * @param bo
	 */
	void updateExplorerIndex(ExplorerIndex bo);
	/**
	 * 更新权限对象
	 * @param bo
	 */
	void updateRight(Right bo);
	
	/**
	 * 更新角色对象
	 * @param bo
	 */
	void updateRole(Role bo, List<Long> rightID);
	
	/**
	 * 验证操作权限;
	 * 如果账户不存在，或者账户的状态非激活状态，验证都会失败
	 * @param accountID	账户ID
	 * @param code		权限编码
	 * @return
	 * 	true 或者 false
	 */
	boolean validatePersission(long accountID, String code);
	
	/**
	 * 验证操作权限
	 * @param accountName	账户登录名
	 * @param code			权限编码
	 * @return
	 */
	boolean validatePersission(String accountName, String code);
	
	/**
	 * 验证权限CODE是否存在
	 * @param persissionCode	权限ID
	 * @return
	 */
	boolean validatePersissionCode(String persissionCode);
	
	/**
	 * 校验用户名是否已经被使用
	 * @param name    用户名
	 * @return
	 */
	boolean validateAccount(String name);
	
//	/**
//	 * 创建账户ID序列号
//	 * @return
//	 */
//	Long generateAccountID();
	
	/**
	 * 获取缓存对象
	 * @return    缓存对象
	 */
	ReadOnlyCacheModel getCacheModel();
}
