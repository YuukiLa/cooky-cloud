package com.yuuki.cooky.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuuki.cooky.auth.mapper.OauthCliendetailsMapper;
import com.yuuki.cooky.auth.model.entity.ClientDetail;
import com.yuuki.cooky.auth.service.IClientDetailService;
import com.yuuki.cooky.auth.service.RedisClientDetailsService;
import com.yuuki.cooky.common.model.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientDetailServiceImpl extends ServiceImpl<OauthCliendetailsMapper, ClientDetail> implements IClientDetailService {

    @Autowired
    private OauthCliendetailsMapper oauthCliendetailsMapper;
    @Autowired
    RedisClientDetailsService redisClientDetailsService;

    @Override
    public IPage<ClientDetail> findClientDetails(QueryRequest request, ClientDetail oauthCliendetails) {
        LambdaQueryWrapper<ClientDetail> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<ClientDetail> page = new Page<>(request.getCurrent(), request.getSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<ClientDetail> findClientDetails(ClientDetail oauthCliendetails) {
        LambdaQueryWrapper<ClientDetail> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createClientDetail(ClientDetail oauthCliendetails) {
        this.save(oauthCliendetails);
    }

    @Override
    @Transactional
    public void updateClientDetail(ClientDetail oauthCliendetails) {
        this.updateById(oauthCliendetails);
        redisClientDetailsService.cacheAndGetClient(oauthCliendetails.getClientId());
    }

    @Override
    @Transactional
    public void deleteClientDetail(ClientDetail oauthCliendetails) {
        LambdaQueryWrapper<ClientDetail> wapper = new LambdaQueryWrapper<>();
        // TODO 设置删除条件
        this.remove(wapper);
    }
}
