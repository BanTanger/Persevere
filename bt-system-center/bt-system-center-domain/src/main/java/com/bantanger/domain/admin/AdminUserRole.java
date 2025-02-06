package com.bantanger.domain.admin;

/**
 * @author chensongmin
 * @description 用户角色关联表
 * @date 2025/2/6
 */

import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.admin.vo")
@GenRepository(pkgName = "com.bantanger.domain.admin.repository")
@Entity
@Table(name = "admin_user_role")
@Data
public class AdminUserRole extends BaseJpaAggregate {

    @FieldDesc(name = "用户ID")
    private Long userId;

    @FieldDesc(name = "角色ID")
    private Long roleId;
}
