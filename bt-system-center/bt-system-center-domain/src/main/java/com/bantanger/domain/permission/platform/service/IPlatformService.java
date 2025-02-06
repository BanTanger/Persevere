// --- Auto Generated by BanTanger ---
package com.bantanger.domain.permission.platform.service;

import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.domain.permission.platform.creator.PlatformCreator;
import com.bantanger.domain.permission.platform.query.PlatformQuery;
import com.bantanger.domain.permission.platform.updater.PlatformUpdater;
import com.bantanger.domain.permission.platform.vo.PlatformVO;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface IPlatformService {
   /**
    * create
    */
   Long createPlatform(PlatformCreator creator);

   /**
    * update
    */
   void updatePlatform(PlatformUpdater updater);

   /**
    * valid
    */
   void validPlatform(Long id);

   /**
    * invalid
    */
   void invalidPlatform(Long id);

   /**
    * findById
    */
   PlatformVO findById(Long id);

   /**
    * findByPage
    */
   Page<PlatformVO> findByPage(PageRequestWrapper<PlatformQuery> query);
}
