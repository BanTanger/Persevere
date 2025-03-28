// --- Auto Generated by BanTanger ---
package com.bantanger.domain.template.verifyrule.service;

import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.domain.template.verifyrule.creator.VerifyRuleConfigCreator;
import com.bantanger.domain.template.verifyrule.query.VerifyRuleConfigQuery;
import com.bantanger.domain.template.verifyrule.updater.VerifyRuleConfigUpdater;
import com.bantanger.domain.template.verifyrule.vo.VerifyRuleConfigVO;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface IVerifyRuleConfigService {
   /**
    * create
    */
   Long createVerifyRuleConfig(VerifyRuleConfigCreator creator);

   /**
    * update
    */
   void updateVerifyRuleConfig(VerifyRuleConfigUpdater updater);

   /**
    * valid
    */
   void validVerifyRuleConfig(Long id);

   /**
    * invalid
    */
   void invalidVerifyRuleConfig(Long id);

   /**
    * findById
    */
   VerifyRuleConfigVO findById(Long id);

   /**
    * findByPage
    */
   Page<VerifyRuleConfigVO> findByPage(PageRequestWrapper<VerifyRuleConfigQuery> query);
}
