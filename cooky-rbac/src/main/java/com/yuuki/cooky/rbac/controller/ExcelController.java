package com.yuuki.cooky.rbac.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import com.yuuki.cooky.common.exception.CookyException;
import com.yuuki.cooky.common.model.PageInfo;
import com.yuuki.cooky.common.model.QueryRequest;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.rbac.model.entity.Excel;
import com.yuuki.cooky.rbac.model.entity.Role;
import com.yuuki.cooky.rbac.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("Excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping
    public Page findExcels(PageInfo params) {
        return (Page) excelService.page(new QueryRequest<>(params));
    }

    @PostMapping("template")
    public void generateImportTemplate(HttpServletResponse response) {
        List<Excel> list = new ArrayList<>();
        IntStream.range(0, 2).forEach(i -> {
            Excel Excel = new Excel();
            Excel.setDateFiled(new Date());
            Excel.setNumberFiled(1);
            Excel.setStringFiled("yuuki");
            list.add(Excel);
        });
        ExcelKit.$Export(Excel.class, response).downXlsx(list, true);
    }

    @PostMapping("import")
    public Response importExcels(MultipartFile file) throws IOException, CookyException {
        if (file.isEmpty()) {
            throw new CookyException("导入数据为空");
        }
        String filename = file.getOriginalFilename();
        if (!StringUtils.endsWith(filename, ".xlsx")) {
            throw new CookyException("只支持.xlsx类型文件导入");
        }
        Stopwatch stopwatch = Stopwatch.createStarted();
        final List<Excel> data = Lists.newArrayList();
        final List<Map<String, Object>> error = Lists.newArrayList();
        ExcelKit.$Import(Excel.class).readXlsx(file.getInputStream(), new ExcelReadHandler<Excel>() {
            @Override
            public void onSuccess(int sheet, int row, Excel Excel) {
                data.add(Excel);
            }

            @Override
            public void onError(int sheet, int row, List<ExcelErrorField> errorFields) {
                error.add(ImmutableMap.of("row", row, "errorFields", errorFields));
            }
        });
        if (CollectionUtils.isNotEmpty(data)) {
            this.excelService.saveBatch(data);
        }
        ImmutableMap<String, Object> result = ImmutableMap.of(
                "time", stopwatch.stop().toString(),
                "data", data,
                "error", error
        );
        return Response.success(result);
    }

    @PostMapping("excel")
    public void export(PageInfo pageInfo, HttpServletResponse response) {
        List<Excel> Excels = this.excelService.page(new QueryRequest<>(pageInfo)).getRecords();
        ExcelKit.$Export(Excel.class, response).downXlsx(Excels, false);
    }
}
