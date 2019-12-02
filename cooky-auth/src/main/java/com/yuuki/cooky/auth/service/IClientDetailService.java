package com.yuuki.cooky.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuuki.cooky.auth.model.entity.ClientDetail;
import com.yuuki.cooky.common.model.QueryRequest;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2019-09-09 14:13:23
 */
public interface IClientDetailService extends IService<ClientDetail> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param ClientDetail ClientDetail
     * @return IPage<ClientDetail>
     */
    IPage<ClientDetail> findClientDetails(QueryRequest request, ClientDetail ClientDetail);

    /**
     * 查询（所有）
     *
     * @param ClientDetail ClientDetail
     * @return List<ClientDetail>
     */
    List<ClientDetail> findClientDetails(ClientDetail ClientDetail);

    /**
     * 新增
     *
     * @param ClientDetail ClientDetail
     */
    void createClientDetail(ClientDetail ClientDetail);

    /**
     * 修改
     *
     * @param ClientDetail ClientDetail
     */
    void updateClientDetail(ClientDetail ClientDetail);

    /**
     * 删除
     *
     * @param ClientDetail ClientDetail
     */
    void deleteClientDetail(ClientDetail ClientDetail);
}
