// --- Auto Generated by BanTanger ---
package com.bantanger.domain.user.service;

import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.domain.user.creator.UserBaseCreator;
import com.bantanger.domain.user.query.UserBaseQuery;
import com.bantanger.domain.user.updater.UserBaseUpdater;
import com.bantanger.domain.user.vo.UserBaseVO;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface IUserBaseService {
   /**
    * create
    */
   Long createUserBase(UserBaseCreator creator);

   /**
    * update
    */
   void updateUserBase(UserBaseUpdater updater);

   /**
    * valid
    */
   void validUserBase(Long id);

   /**
    * invalid
    */
   void invalidUserBase(Long id);

   /**
    * findById
    */
   UserBaseVO findById(Long id);

   /**
    * findByPage
    */
   Page<UserBaseVO> findByPage(PageRequestWrapper<UserBaseQuery> query);
}
