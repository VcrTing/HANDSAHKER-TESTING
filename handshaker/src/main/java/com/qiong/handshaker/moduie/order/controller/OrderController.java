package com.qiong.handshaker.moduie.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiong.handshaker.anno.result.QResponseAdvice;
import com.qiong.handshaker.data.router.DataRouterBase;
import com.qiong.handshaker.data.router.DataRouterOrder;
import com.qiong.handshaker.define.dataset.EntityDefineDataset;
import com.qiong.handshaker.define.exception.vaiid.QLogicException;
import com.qiong.handshaker.define.query.QBetweenDate;
import com.qiong.handshaker.define.query.QLikes;
import com.qiong.handshaker.define.query.QPage;
import com.qiong.handshaker.define.query.QSort;
import com.qiong.handshaker.define.result.QPager;
import com.qiong.handshaker.define.result.QResponse;
import com.qiong.handshaker.moduie.order.Order;
import com.qiong.handshaker.moduie.order.service.OrderService;
import com.qiong.handshaker.moduie.product.Product;
import com.qiong.handshaker.tool.result.QResponseTool;
import com.qiong.handshaker.utils.basic.QTypedUtil;
import com.qiong.handshaker.view.order.ViewOrderDetailForm;
import com.qiong.handshaker.view.order.ViewOrderResultForm;
import com.qiong.handshaker.view.product.ViewProductResultForm;
import com.qiong.handshaker.vo.order.VoOrderPostForm;
import com.qiong.handshaker.vo.product.VoProductPostForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@QResponseAdvice
@RequestMapping(DataRouterOrder.ORDER)
public class OrderController {

    @Autowired
    OrderService service;

    /**
    * 深层 查询 ADMIN 订单 列表
    * @params
    * @return
    */
    @GetMapping
    public QResponse<QPager<ViewOrderResultForm>> page(@RequestParam HashMap<String, Object> qry) {

        QueryWrapper<Order> qw = new QueryWrapper<>();
        qw.orderBy(QSort.hasSort(qry), QSort.ofMap(qry).isAsc(), "me.id");

        QLikes likes = QLikes.ofMap(qry, new String[] { "search", "member", "status", "order_id", "time_period", "startDate", "endDate" });

        // search 模糊 查询
        if (likes.has("search")) { qw.like("me.order_id", likes.one("search")).or(); }

        // 模糊 查询
        if (likes.has("order_id")) { qw.like("me.order_id", likes.one("order_id")).or(); }
        if (likes.has("status")) { qw.like("me.order_status", likes.one("status")).or(); }
        if (likes.has("member")) { qw.like("me.member_sql_id", likes.one("member")).or(); }

        // 日期 区间 过滤
        QBetweenDate qbd = null;
        if (likes.has("time_period")) {
            qbd = QBetweenDate.ofWhenDay( - QTypedUtil.serInt(likes.one("time_period"), 0), false);
        } else {
            if (likes.has("startDate") && likes.has("endDate")) {
                qbd = QBetweenDate.init(likes.one("startDate"), likes.one("endDate"), false);
            }
        }

        if (qbd != null) {
            qw.lt("me.order_date", qbd.starDate(false));
            qw.gt("me.order_date", qbd.endDate(false));
        }

        return QResponseTool.restfull(true, service.pageDeep(new Page<Order>(QPage.easyCurrent(qry), QPage.easySize(qry)), qw));
    }

    /**
    * 深度 查询一个 订单 详情
    * @params
    * @return
    */
    @GetMapping("/{id}")
    public QResponse<ViewOrderDetailForm> detail(@PathVariable Object id) {
        if (id == null) throw new QLogicException("未找到 订单 ID");
        return QResponseTool.restfull(true, service.detail(id));
    }
}
