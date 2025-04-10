// --- Auto Generated by BanTanger ---
package com.bantanger.domain.template.objecttemplate.service;

import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.domain.template.objecttemplate.creator.ObjectTemplateCreator;
import com.bantanger.domain.template.objecttemplate.query.ObjectTemplateQuery;
import com.bantanger.domain.template.objecttemplate.updater.ObjectTemplateUpdater;
import com.bantanger.domain.template.objecttemplate.vo.ObjectTemplateVO;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface IObjectTemplateService {
   /**
    * create
    */
   Long createObjectTemplate(ObjectTemplateCreator creator);

   /**
    * update
    */
   void updateObjectTemplate(ObjectTemplateUpdater updater);

   /**
    * valid
    */
   void validObjectTemplate(Long id);

   /**
    * invalid
    */
   void invalidObjectTemplate(Long id);

   /**
    * findById
    */
   ObjectTemplateVO findById(Long id);

   /**
    * findByPage
    */
   Page<ObjectTemplateVO> findByPage(PageRequestWrapper<ObjectTemplateQuery> query);
}
