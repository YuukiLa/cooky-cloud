package com.yuuki.cooky.rbac.model.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;

/**
 * (Excel)表实体类
 *
 * @author yuuki
 * @since 2020-01-14 10:53:00
 */
@Data
@TableName("t_excel")
@com.wuwenze.poi.annotation.Excel("测试导入导出数据")
@SuppressWarnings("serial")
public class Excel {
    @ExcelField(value = "字符串类型字段", required = true, maxLength = 20,
            comment = "提示")
    private String stringFiled;
    @ExcelField(value = "数字类型字段", required = true, maxLength = 11, regularExp = "[0-9]+",
            regularExpMessage = "必须是数字", comment = "提示")
    private Integer numberFiled;
    @ExcelField(value = "时间类型字段", dateFormat = "yyyy/MM/dd")
    private Date dateFiled;


}