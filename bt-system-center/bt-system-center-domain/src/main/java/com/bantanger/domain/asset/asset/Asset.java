package com.bantanger.domain.asset.asset;

/**
 * @author chensongmin
 * @description 资产
 * @date 2025/1/15
 */

import com.bantanger.codegen.processor.api.GenCreateRequest;
import com.bantanger.codegen.processor.api.GenQueryRequest;
import com.bantanger.codegen.processor.api.GenResponse;
import com.bantanger.codegen.processor.api.GenUpdateRequest;
import com.bantanger.codegen.processor.controller.GenController;
import com.bantanger.codegen.processor.creator.GenCreator;
import com.bantanger.codegen.processor.creator.IgnoreCreator;
import com.bantanger.codegen.processor.mapper.GenMapper;
import com.bantanger.codegen.processor.query.GenQuery;
import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.service.GenService;
import com.bantanger.codegen.processor.service.GenServiceImpl;
import com.bantanger.codegen.processor.updater.GenUpdater;
import com.bantanger.codegen.processor.updater.IgnoreUpdater;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.constants.ValidStatus;
import com.bantanger.common.enumtype.AssetErrorType;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.domain.asset.asset.domainservice.model.AssetBizInfo;
import com.bantanger.domain.asset.asset.events.AssetEvents;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.asset.asset.vo")
@GenCreator(pkgName = "com.bantanger.domain.asset.asset.creator")
@GenUpdater(pkgName = "com.bantanger.domain.asset.asset.updater")
@GenRepository(pkgName = "com.bantanger.domain.asset.asset.repository")
@GenService(pkgName = "com.bantanger.domain.asset.asset.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.asset.asset.service")
@GenQuery(pkgName = "com.bantanger.domain.asset.asset.query")
@GenMapper(pkgName = "com.bantanger.domain.asset.asset.mapper")
@GenController(pkgName = "com.bantanger.trigger.asset.controller", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.asset.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.asset.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.asset.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.asset.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.trigger.asset.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName = "trigger")
@Entity
@Table(name = "asset")
@Data
public class Asset extends BaseJpaAggregate {

    @FieldDesc(name = "仓库编码ID")
    private Long warehouseId;

    @FieldDesc(name = "资产名称")
    private String name;

    @FieldDesc(name = "资产唯一编码")
    private String uniqueCode;

    @FieldDesc(name = "资产skuId")
    private Long skuId;

    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus validStatus;

    public void init() {
        setValidStatus(ValidStatus.VALID);
    }

    /**
     * 充血模型，资产入库逻辑方法
     */
    public void in(AssetBizInfo bizInfo) {
        if (Objects.equals(ValidStatus.VALID, this.getValidStatus())) {
            throw new BusinessException(AssetErrorType.ASSET_HAS_IN);
        }
        setValidStatus(ValidStatus.VALID);
        registerEvent(new AssetEvents.AssetInEvent(this, bizInfo));
    }

    /**
     * 充血模型，资产出库逻辑方法
     */
    public void out(AssetBizInfo bizInfo) {
        if (Objects.equals(ValidStatus.INVALID, this.getValidStatus())) {
            throw new BusinessException(AssetErrorType.ASSET_HAS_OUT);
        }
        setValidStatus(ValidStatus.INVALID);
        registerEvent(new AssetEvents.AssetOutEvent(this, bizInfo));
    }
}
