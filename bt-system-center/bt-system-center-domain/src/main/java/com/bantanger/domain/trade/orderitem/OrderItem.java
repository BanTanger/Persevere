package com.bantanger.domain.trade.orderitem;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */

import com.bantanger.codegen.processor.api.GenCreateRequest;
import com.bantanger.codegen.processor.api.GenQueryRequest;
import com.bantanger.codegen.processor.api.GenResponse;
import com.bantanger.codegen.processor.api.GenUpdateRequest;
import com.bantanger.codegen.processor.creator.GenCreator;
import com.bantanger.codegen.processor.mapper.GenMapper;
import com.bantanger.codegen.processor.query.GenQuery;
import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.service.GenService;
import com.bantanger.codegen.processor.service.GenServiceImpl;
import com.bantanger.codegen.processor.updater.GenUpdater;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.trade.orderitem.vo")
@GenCreator(pkgName = "com.bantanger.domain.trade.orderitem.creator")
@GenUpdater(pkgName = "com.bantanger.domain.trade.orderitem.updater")
@GenRepository(pkgName = "com.bantanger.domain.trade.orderitem.repository")
@GenService(pkgName = "com.bantanger.domain.trade.orderitem.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.trade.orderitem.service")
@GenQuery(pkgName = "com.bantanger.domain.trade.orderitem.query")
@GenMapper(pkgName = "com.bantanger.domain.trade.orderitem.mapper")
@GenCreateRequest(pkgName = "com.bantanger.api.trade.orderitem.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.trade.orderitem.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.trade.orderitem.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.trade.orderitem.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "order_item")
@Data
public class OrderItem extends BaseJpaAggregate {

    @FieldDesc(name = "订单ID")
    private Long orderId;

    @FieldDesc(name = "唯一流水号")
    private Long flowNo;

    @FieldDesc(name = "真实金额")
    private BigDecimal realAmount;

    @FieldDesc(name = "计量数量")
    private Integer itemCount;

    @FieldDesc(name = "商品 sku 编号")
    private String skuId;

    @FieldDesc(name = "商品计量单位")
    private String itemUnit;

    @FieldDesc(name = "商品名称")
    private String goodsName;

    @FieldDesc(name = "费用描述")
    private String feeRemark;

}
