package com.jishudaniu.dkblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jishudaniu.dkblog.pojo.vo.PageResult;
import com.jishudaniu.dkblog.pojo.dto.RoleDTO;
import com.jishudaniu.dkblog.pojo.dto.UserRoleDTO;
import com.jishudaniu.dkblog.pojo.entity.Role;
import com.jishudaniu.dkblog.pojo.vo.ConditionVO;
import com.jishudaniu.dkblog.pojo.vo.RoleVO;

import java.util.List;

/**
 * 角色服务
 *
 * @author darkchen
 * @date 2022/01/10
 */
public interface RoleService extends IService<Role> {

    /**
     * 获取用户角色选项
     *
     * @return 角色
     */
    List<UserRoleDTO> listUserRoles();

    /**
     * 查询角色列表
     *
     * @param conditionVO 条件
     * @return 角色列表
     */
    PageResult<RoleDTO> listRoles(ConditionVO conditionVO);

    /**
     * 保存或更新角色
     *
     * @param roleVO 角色
     */
    void saveOrUpdateRole(RoleVO roleVO);

    /**
     * 删除角色
     * @param roleIdList 角色id列表
     */
    void deleteRoles(List<Integer> roleIdList);

}
