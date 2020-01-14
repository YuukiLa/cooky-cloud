package com.yuuki.cooky.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuuki.cooky.rbac.model.entity.Excel;
import com.yuuki.cooky.rbac.mapper.ExcelMapper;
import com.yuuki.cooky.rbac.service.ExcelService;
import org.springframework.stereotype.Service;

/**
 * (Excel)表服务实现类
 *
 * @author yuuki
 * @since 2020-01-14 10:53:00
 */
@Service("excelService")
public class ExcelServiceImpl extends ServiceImpl<ExcelMapper, Excel> implements ExcelService {

}