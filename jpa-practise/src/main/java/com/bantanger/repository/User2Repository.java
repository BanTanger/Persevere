package com.bantanger.repository;

import com.bantanger.entity.User2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
public interface User2Repository extends JpaRepository<User2, Long> {

    /**
     * ===================== Defined Query Method =======================
     */
    UserOnlyNameAndEmailDto findByEmail(String email);
    UserOnlyNameDto findByName(String name);

    /**
     * ===================== @Query =======================
     */
//    @Query("select u from User2 u where u.email = ?1")
    @Query("""
        select u from User2 u where u.email = :emailAddress
            """)
    User2 findByEmailAddress(@Param("emailAddress") String emailAddress);

    @Query("select u from User2 u where u.firstName like %?1")
//    @Query("select u from User2 u where u.firstName like %:firstName")
    List<User2> findByFirstNameEndsWith(String firstName);

    // 分页 pageable，直接用 Page 对象接收返回值
    @Query("select u from User2 u where u.lastName = :lastName")
    Page<User2> findByLastName(@Param("lastName") String lastName, Pageable pageable);

    // 分页 pageable，直接用 Page 对象接收返回值
    @Query("select u from User2 u where u.email = :email")
    Page<User2> findByEmail(@Param("email") String email, Pageable pageable);


    /**
     * ===================== projections =======================
     * use record for projections
     */
    record UserOnlyNameDto(String name) { }
    record UserOnlyNameAndEmailDto(String name, String email) { }
}