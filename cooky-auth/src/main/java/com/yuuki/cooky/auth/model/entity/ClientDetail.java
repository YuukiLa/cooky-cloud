package com.yuuki.cooky.auth.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oauth_client_details")
public class ClientDetail {

    /**
     *
     */
    @TableId(value = "client_id", type = IdType.AUTO)
    private String clientId;

    /**
     *
     */
    @TableField("resource_ids")
    private String resourceIds;

    /**
     *
     */
    @TableField("client_secret")
    private String clientSecret;

    /**
     *
     */
    @TableField("scope")
    private String scope;

    /**
     *
     */
    @TableField("authorized_grant_types")
    private String authorizedGrantTypes;

    /**
     *
     */
    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;

    /**
     *
     */
    @TableField("authorities")
    private String authorities;

    /**
     *
     */
    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    /**
     *
     */
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    /**
     *
     */
    @TableField("additional_information")
    private String additionalInformation;

    /**
     *
     */
    @TableField("autoapprove")
    private Byte autoapprove;

}
