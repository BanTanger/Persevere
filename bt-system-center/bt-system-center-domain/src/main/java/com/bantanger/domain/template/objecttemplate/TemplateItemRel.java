package com.bantanger.domain.template.objecttemplate;

/**
 * @author chensongmin
 * @description 模板-模板项关联表
 * @date 2025/2/7
 */

import com.bantanger.codegen.processor.creator.GenCreator;
import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.updater.GenUpdater;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.template.objecttemplate.vo")
@GenCreator(pkgName = "com.bantanger.domain.template.objecttemplate.creator")
@GenUpdater(pkgName = "com.bantanger.domain.template.objecttemplate.updater")
@GenRepository(pkgName = "com.bantanger.domain.template.objecttemplate.repository")
@Entity
@Table(name = "template_item_rel")
@Data
public class TemplateItemRel extends BaseJpaAggregate {

    @FieldDesc(name = "模板ID")
    private Long templateId;

    @FieldDesc(name = "模板项ID")
    private Long itemId;
}