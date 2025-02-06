package com.bantanger.domain.permission.role;

/**
 * @author chensongmin
 * @description 角色-资源关联表
 * @date 2025/2/6
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

@GenVo(pkgName = "com.bantanger.domain.permission.vo")
@GenCreator(pkgName = "com.bantanger.domain.permission.creator")
@GenUpdater(pkgName = "com.bantanger.domain.permission.updater")
@GenRepository(pkgName = "com.bantanger.domain.permission.repository")
@Entity
@Table(name = "role_resource_ref")
@Data
public class RoleResourceRef extends BaseJpaAggregate {

    @FieldDesc(name = "角色ID")
    private Long roleId;

    @FieldDesc(name = "资源ID")
    private Long resourceId;

}
