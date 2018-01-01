package com.qwm.bos.dao;

import com.qwm.bos.dao.base.IBaseDao;
import com.qwm.bos.domain.Function;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/31 下午6:39
 * @className: IFunctionDao
 * @description:
 */
public interface IFunctionDao extends IBaseDao<Function>{
    /**
     * 根据用户id获取对应的权限
     * @param id
     * @return
     */
    List<Function> findFunctionListByUserId(String id);

    /**
     * 查询所有菜单
     * @return
     */
    List<Function> findAllMenu();

    /**
     * 根据用户id查询菜单
     * @param userId
     * @return
     */
    List<Function> findMenuByUserId(String userId);
}
