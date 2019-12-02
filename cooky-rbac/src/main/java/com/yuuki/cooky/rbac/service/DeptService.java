package com.yuuki.cooky.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.rbac.model.entity.Dept;

/**
 * (Dept)表服务接口
 *
 * @author makejava
 * @since 2019-11-13 11:13:25
 */
public interface DeptService extends IService<Dept> {

    Response deleteDepts(String ids);
}