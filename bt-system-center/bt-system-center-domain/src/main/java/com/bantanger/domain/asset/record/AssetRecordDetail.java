package com.bantanger.domain.asset.record;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/22
 */

import com.bantanger.codegen.processor.creator.GenCreator;
import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.updater.GenUpdater;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.asset.record.vo")
@GenCreator(pkgName = "com.bantanger.domain.asset.record.creator")
@GenUpdater(pkgName = "com.bantanger.domain.asset.record.updater")
@GenRepository(pkgName = "com.bantanger.domain.asset.record.repository")
@Entity
@Table(name = "asset_record_detail")
@Data
public class AssetRecordDetail extends BaseJpaAggregate {

    private Long recordId;

    private String uniqueCode;
}
