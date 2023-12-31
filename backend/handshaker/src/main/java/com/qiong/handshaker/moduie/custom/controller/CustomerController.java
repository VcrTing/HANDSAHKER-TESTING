package com.qiong.handshaker.moduie.custom.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiong.handshaker.utils.anno.result.QResponseAdvice;
import com.qiong.handshaker.data.router.DataRouterCustom;
import com.qiong.handshaker.data.security.DataSecurityRoleConf;
import com.qiong.handshaker.utils.define.query.QLikes;
import com.qiong.handshaker.utils.define.query.QPage;
import com.qiong.handshaker.utils.define.query.QSort;
import com.qiong.handshaker.utils.define.result.QResponse;
import com.qiong.handshaker.entity.moduie.custom.Customer;
import com.qiong.handshaker.moduie.custom.service.CustomerService;
import com.qiong.handshaker.utils.tool.result.QResponseTool;
import com.qiong.handshaker.entity.vo.custom.VoCustomOptionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@QResponseAdvice
@RequestMapping(DataRouterCustom.CUSTOMER)
public class CustomerController {

    @Autowired
    CustomerService service;

    /**
     * 深层 分页 列表
     * @params
     * @return
     */
    @PreAuthorize(DataSecurityRoleConf.AUTH_CASHIER)
    @GetMapping
    public QResponse<IPage<Customer>> page(@RequestParam HashMap<String, Object> qry) {

        QueryWrapper<Customer> qw = new QueryWrapper<>();
        qw.orderBy(QSort.hasSort(qry), QSort.isAsc(qry), "me.id");

        QLikes likes = QLikes.ofMap(qry, new String[] { "search" });
        String search = likes.one("search");
        if (!search.isEmpty()) {
            qw
                .like("me.member_id", search).or()
                .like("me.phone_no", search).or()
                .like("me.name", search).or()
                .like("me.email", search).or();
        }

        return QResponseTool.restfull(true, service.pageDeep(new Page<Customer>(QPage.easyCurrent(qry), QPage.easySize(qry)), qw));
    }

    /**
     * 深层 查詢 单个
     * @params
     * @return
     */
    @PreAuthorize(DataSecurityRoleConf.AUTH_ADMIN_ONLY)
    @GetMapping("/{id}")
    public QResponse<Customer> one(@PathVariable Long id) {
        return QResponseTool.restfull(id != null, service.oneDeep(id));
    }

    /**
     * 常规 新增
     * @params
     * @return
     */
    @PreAuthorize(DataSecurityRoleConf.AUTH_ADMIN_ONLY)
    @PostMapping
    public QResponse<Object> pos(@RequestBody @Validated VoCustomOptionForm form) {
        return service.posCustom(Customer.init(form, null));
    }

    /**
     * 常规 修改
     * @params
     * @return
     */
    @PreAuthorize(DataSecurityRoleConf.AUTH_ADMIN_ONLY)
    @PatchMapping("/{id}")
    public QResponse<Customer> upd(@PathVariable Long id, @RequestBody @Validated VoCustomOptionForm form) {
        Customer entity = Customer.init(form, id);
        return QResponseTool.restfull(service.updateById(entity), entity);
    }

    /**
     * 常规 删除
     * @params
     * @return
     */
    @PreAuthorize(DataSecurityRoleConf.AUTH_ADMIN_ONLY)
    @DeleteMapping("/{id}")
    public QResponse<Customer> dei(@PathVariable Long id) {
        return QResponseTool.restfull(service.removeById(id), service.getById(id));
    }
}