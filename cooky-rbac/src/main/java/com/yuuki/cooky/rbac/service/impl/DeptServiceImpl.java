package com.yuuki.cooky.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.common.util.Strings;
import com.yuuki.cooky.rbac.model.entity.Dept;
import com.yuuki.cooky.rbac.mapper.DeptMapper;
import com.yuuki.cooky.rbac.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * (Dept)表服务实现类
 *
 * @author makejava
 * @since 2019-11-13 11:13:25
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = false)
    public Response deleteDepts(String ids) {
        String[] strings = Strings.split(ids, ",");

        this.update(new UpdateWrapper<Dept>()
                .in(strings.length>0,"parent_id",Arrays.asList(strings))
                .set("parent_id",0));
        this.removeByIds(Arrays.asList(strings));
        return Response.success("删除成功");
    }
}