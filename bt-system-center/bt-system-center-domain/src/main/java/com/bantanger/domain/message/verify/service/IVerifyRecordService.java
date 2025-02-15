// --- Auto Generated by BanTanger ---
package com.bantanger.domain.message.verify.service;

import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.domain.message.verify.creator.VerifyRecordCreator;
import com.bantanger.domain.message.verify.query.VerifyRecordQuery;
import com.bantanger.domain.message.verify.updater.VerifyRecordUpdater;
import com.bantanger.domain.message.verify.vo.VerifyRecordVO;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface IVerifyRecordService {

   /**
    * 检查发送频率
    * @param account 目的账号
    * @param templateCode 发送模板编码
    * @return
    */
   boolean checkSendInterval(String account, String templateCode);

   /**
    * 检查发送最大次数
    * @param account 目的账号
    * @param templateCode 发送模板编码
    * @return
    */
   boolean checkSendMaxTimes(String account, String templateCode);

   /**
    * create
    */
   Long createVerifyRecord(VerifyRecordCreator creator);

   /**
    * update
    */
   void updateVerifyRecord(VerifyRecordUpdater updater);

   /**
    * valid
    */
   void validVerifyRecord(Long id);

   /**
    * invalid
    */
   void invalidVerifyRecord(Long id);

   /**
    * findById
    */
   VerifyRecordVO findById(Long id);

   /**
    * findByPage
    */
   Page<VerifyRecordVO> findByPage(PageRequestWrapper<VerifyRecordQuery> query);
}
