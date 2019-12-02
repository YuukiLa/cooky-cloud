package com.yuuki.cooky.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuuki.cooky.auth.model.entity.ClientDetail;
import com.yuuki.cooky.auth.service.IClientDetailService;
import com.yuuki.cooky.auth.service.RedisClientDetailsService;
import com.yuuki.cooky.common.model.PageInfo;
import com.yuuki.cooky.common.model.QueryRequest;
import com.yuuki.cooky.common.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/client")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OauthClientController {

    private final IClientDetailService clientDetailService;

    @PreAuthorize("hasAuthority('test')")
    @PutMapping
    public Response update(@RequestBody ClientDetail clientDetails) {
        clientDetailService.updateClientDetail(clientDetails);
        log.info("修改client信息：{}", clientDetails);
        return Response.success("修改成功");
    }

    @PreAuthorize("hasAuthority('test')")
    @GetMapping
    public Response query(PageInfo pageInfo, ClientDetail clientDetail) {
        return Response.success(clientDetailService.page(new QueryRequest<>(pageInfo),new QueryWrapper<>(clientDetail)));
    }
}
