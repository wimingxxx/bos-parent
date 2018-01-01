package com.qwm.bos.service;

import com.qwm.bos.domain.Role;
import com.qwm.bos.utils.PageBean;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/31 下午9:09
 * @className: IRoleService
 * @description:
 * 角色管理
 */
public interface IRoleService {
    /**
     * 保存角色
     * @param role
     * @param functionIds
     */
    void save(Role role, String functionIds);

    void pageQuery(PageBean pageBean);

    /**
     * 查询全部角色
     * @return
     */
    List<Role> findAll();
}
