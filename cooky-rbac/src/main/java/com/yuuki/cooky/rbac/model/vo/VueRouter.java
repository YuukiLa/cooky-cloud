package com.yuuki.cooky.rbac.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VueRouter<T> implements Serializable {

    private static final long serialVersionUID = -3327478146308500708L;

    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private Integer parentId;

    private String path;
    private String name;
    private String component;
    private String redirect;
    private Map<String,String> meta= new HashMap<>();
    private Boolean hidden = false;
    private List<VueRouter<T>> children;

    @JsonIgnore
    private Boolean hasParent = false;

    @JsonIgnore
    private Boolean hasChildren = false;

    public void initChildren(){
        this.children = new ArrayList<>();
    }

    public void setTitle(String title) {
        meta.put("title",title);
    }

    public void setIcon(String icon) {
        meta.put("icon",icon);
    }
}
