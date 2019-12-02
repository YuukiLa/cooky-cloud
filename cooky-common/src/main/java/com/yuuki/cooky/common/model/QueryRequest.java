package com.yuuki.cooky.common.model;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuuki.cooky.common.util.Strings;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Data
//@ToString
@SuppressWarnings("unchecked")
public class QueryRequest<T> extends Page<T> implements Serializable {


    private static final long serialVersionUID = -4869594085374385813L;
    private static final String SPLIT_4_SORT = ",";
    private static final String DESC_SORT_STARTSWITH = "-";

    public QueryRequest(PageInfo pageInfo) {
        super(pageInfo.getCur(), pageInfo.getSize());

        List<String> ascList = new ArrayList<String>();
        List<String> descList =  new ArrayList<String>();
        final String sortField = pageInfo.getSort();
        if (Strings.isNotEmpty(sortField)) {
            for (String sort : sortField.split(SPLIT_4_SORT)) {
                if (sort.startsWith(DESC_SORT_STARTSWITH)) {
                    descList.add(Strings.toUnderScoreCase(sort.substring(1)));
                } else {
                    ascList.add(Strings.toUnderScoreCase(sort));
                }
            }
        }
        this.addOrder(OrderItem.ascs(ascList.toArray(new String[ascList.size()])));
        this.addOrder(OrderItem.descs(ascList.toArray(new String[ascList.size()])));
//        this.setAscs(ascList);
//        this.setDescs(descList);
    }

}
