package com.bantanger.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bantanger.domain.asset.record.enums.InOutBizType;
import com.bantanger.domain.asset.record.enums.InOutType;
import com.bantanger.domain.message.record.enums.MsgType;
import com.bantanger.domain.message.record.enums.NotifyType;
import com.bantanger.domain.message.template.enums.TemplateType;
import com.bantanger.domain.permission.resource.ResourceType;
import com.bantanger.domain.template.selectdict.DictType;
import com.bantanger.domain.template.templateitem.InputType;
import com.bantanger.domain.trade.order.enums.OrderState;
import com.bantanger.domain.trade.order.enums.OrderType;
import com.bantanger.domain.trade.orderlifecycle.enums.OrderOperateType;
import com.bantanger.domain.user.AccountType;
import java.util.Map;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/9
 */
public class CustomMapper {

    public Integer type2Int(InputType type) {
        return type.getCode();
    }

    public InputType int2Type(Integer code) {
        return InputType.of(code).orElse(InputType.TEXT);
    }

    public Integer dictType2Int(DictType dictType) {
        return dictType.getCode();
    }

    public DictType int2DictType(Integer code) {
        return DictType.of(code).orElse(DictType.SELECT_LIST);
    }

//        public Integer skuType2Int(SkuType skuType) {
//            return skuType.getCode();
//        }
//
//        public SkuType int2SkuType(Integer code) {
//            return SkuType.of(code).orElse(SkuType.SINGLE);
//        }

    public Integer orderType2Int(OrderType orderType) {
        return orderType.getCode();
    }

    public OrderType int2OrderType(Integer code) {
        return OrderType.of(code).orElse(OrderType.CHARGE);
    }

    public Integer accountType2Int(AccountType accountType) {
        return accountType.getCode();
    }

    public AccountType int2AccountType(Integer code) {
        return AccountType.of(code).orElse(AccountType.PERSONAL);
    }

    public Integer opType2Int(OrderOperateType type) {
        return type.getCode();
    }

    public OrderOperateType int2OpType(Integer code) {
        return OrderOperateType.of(code).orElse(OrderOperateType.AUTH_SUCCESS);
    }

    public Integer status2OrderState(OrderState state) {
        return state.getCode();
    }

    public OrderState int2State(Integer code) {
        return OrderState.of(code).orElse(OrderState.WAIT_PAY);
    }

//        public ReceiptStatus int2Status(Integer code) {
//            return ReceiptStatus.of(code).orElse(ReceiptStatus.UNMAKING);
//        }

//        public Integer receiptStatus2Int(ReceiptStatus status) {
//            return status.getCode();
//        }
//
//        public PayType int2PayType(Integer code) {
//            return PayType.of(code).orElse(null);
//        }
//
//        public Integer payType2Int(PayType payType) {
//            return payType.getCode();
//        }

    public Integer inOutBizType2Int(InOutBizType inOutBizType) {
        return inOutBizType.getCode();
    }

    public InOutBizType int2InOutBizType(Integer code) {
        return InOutBizType.of(code).orElse(InOutBizType.IN_INITIAL);
    }

    public Integer inOutType2Int(InOutType inOutType) {
        return inOutType.getCode();
    }

    public InOutType int2InOutType(Integer code) {
        return InOutType.of(code).orElse(InOutType.IN);
    }

    public NotifyType int2NoticeType(Integer code) {
        return NotifyType.of(code).orElse(null);
    }

    public Integer noticeType2Int(NotifyType noticeType) {
        return noticeType.getCode();
    }

    public MsgType int2MsgTypeEnum(Integer code) {
        return MsgType.of(code).orElse(null);
    }

    public Integer msgTypeEnum2Int(MsgType msgTypeEnum) {
        return msgTypeEnum.getCode();
    }

    public TemplateType int2TemplateType(Integer code) {
        return TemplateType.of(code).orElse(null);
    }

    public Integer templateType2Int(TemplateType templateType) {
        return templateType.getCode();
    }

    public Map<String, Object> string2Map(String str) {
        return JSONObject.parseObject(str);
    }

    public String map2String(Map<String, Object> map) {
        return JSON.toJSONString(map);
    }

    public Integer resourceType2Int(ResourceType resourceType) {
        return resourceType.getCode();
    }

    public ResourceType int2ResourceType(Integer code) {
        return ResourceType.of(code).orElse(null);
    }
}
