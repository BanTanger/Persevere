// --- Auto Generated by BanTanger ---
package com.bantanger.domain.message.record.service;

import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.domain.message.record.creator.MessageRecordCreator;
import com.bantanger.domain.message.record.query.MessageRecordQuery;
import com.bantanger.domain.message.record.updater.MessageRecordUpdater;
import com.bantanger.domain.message.record.vo.MessageRecordVO;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface IMessageRecordService {
   /**
    * create
    */
   Long createMessageRecord(MessageRecordCreator creator);

   /**
    * update
    */
   void updateMessageRecord(MessageRecordUpdater updater);

   /**
    * valid
    */
   void validMessageRecord(Long id);

   /**
    * invalid
    */
   void invalidMessageRecord(Long id);

   /**
    * findById
    */
   MessageRecordVO findById(Long id);

   /**
    * findByPage
    */
   Page<MessageRecordVO> findByPage(PageRequestWrapper<MessageRecordQuery> query);
}
