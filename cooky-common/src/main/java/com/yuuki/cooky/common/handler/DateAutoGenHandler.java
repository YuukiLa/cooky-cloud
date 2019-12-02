package com.yuuki.cooky.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充ct ，mt
 * @author Yuuki
 */
public class DateAutoGenHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("ct",new Date(),metaObject);
        this.setFieldValByName("mt",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("mt",new Date(),metaObject);
    }
}
