package com.qiong.handshaker.entity.vo.order;

import com.qiong.handshaker.entity.define.enums.EnumOrderStatus;
import com.qiong.handshaker.entity.vo.order.inner.VoInnerDiscount;
import com.qiong.handshaker.entity.vo.order.inner.VoInnerOrderProduct;
import com.qiong.handshaker.entity.vo.order.inner.VoInnerPaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoOrderPostForm {
    private Long member;

    private EnumOrderStatus status;

    private List<VoInnerOrderProduct> ordered_product;
    private List<VoInnerDiscount> discount;
    private List<VoInnerPaymentMethod> payment_method;
}
