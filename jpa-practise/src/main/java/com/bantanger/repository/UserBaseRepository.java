package com.bantanger.repository;

import com.bantanger.dto.UserInfoDto;
import com.bantanger.entity.UserBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
public interface UserBaseRepository extends JpaRepository<UserBase, Long> {

    @Query("""
            select new com.bantanger.dto.UserInfoDto(CONCAT(u.name, 'JK123'), u.email, e.idCard)
            from UserBase u, UserExtend e where u.id = e.userId and u.id = :id
            """)
    UserInfoDto findByUserInfoId(@Param("id") Long id);

    /**
     * 使用接口 DTO 获取返回结果，需要注意的是投影 projections 的字段需要 as 和接口的 get 方法名字保持一致
     */
    @Query("""
            select concat(u.name, 'JK123') as userName, u.email as email, e.idCard as idCard
            from UserBase u, UserExtend e where u.id = e.userId and u.id = :id
            """)
    UserSimpleDto findByUserSimpleDtoId(@Param("id") Long id);

    /**
     * use interface for projections
     */
    interface UserSimpleDto {
        String getUserName();
        String getEmail();
        String getIdCard();
    }
}
