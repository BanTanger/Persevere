// --- Auto Generated by BanTanger ---
package com.bantanger.trigger.http.message;

import com.bantanger.api.message.record.request.MessageRecordCreateRequest;
import com.bantanger.api.message.record.request.MessageRecordQueryRequest;
import com.bantanger.api.message.record.request.MessageRecordUpdateRequest;
import com.bantanger.api.message.record.response.MessageRecordResponse;
import com.bantanger.common.enums.CodeEnum;
import com.bantanger.common.model.JsonObject;
import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.common.model.PageResult;
import com.bantanger.domain.message.record.creator.MessageRecordCreator;
import com.bantanger.domain.message.record.mapper.MessageRecordMapper;
import com.bantanger.domain.message.record.query.MessageRecordQuery;
import com.bantanger.domain.message.record.service.IMessageRecordService;
import com.bantanger.domain.message.record.updater.MessageRecordUpdater;
import com.bantanger.domain.message.record.vo.MessageRecordVO;
import java.lang.Long;
import java.lang.String;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("messageRecord/v1/")
@RequiredArgsConstructor
public class MessageRecordController {
   private final IMessageRecordService messageRecordService;

   /**
    * createRequest
    */
   @PostMapping("createMessageRecord")
   public JsonObject<Long> createMessageRecord(@RequestBody MessageRecordCreateRequest request) {
      MessageRecordCreator creator = MessageRecordMapper.INSTANCE.request2Dto(request);
      return JsonObject.success(messageRecordService.createMessageRecord(creator));
   }

   /**
    * update request
    */
   @PostMapping("updateMessageRecord")
   public JsonObject<String> updateMessageRecord(@RequestBody MessageRecordUpdateRequest request) {
      MessageRecordUpdater updater = MessageRecordMapper.INSTANCE.request2Updater(request);
      messageRecordService.updateMessageRecord(updater);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * valid
    */
   @PostMapping("valid/{id}")
   public JsonObject<String> validMessageRecord(@PathVariable Long id) {
      messageRecordService.validMessageRecord(id);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * invalid
    */
   @PostMapping("invalid/{id}")
   public JsonObject<String> invalidMessageRecord(@PathVariable Long id) {
      messageRecordService.invalidMessageRecord(id);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * findById
    */
   @GetMapping("findById/{id}")
   public JsonObject<MessageRecordResponse> findById(@PathVariable Long id) {
      MessageRecordVO vo = messageRecordService.findById(id);
      MessageRecordResponse response = MessageRecordMapper.INSTANCE.vo2CustomResponse(vo);
      return JsonObject.success(response);
   }

   /**
    * findByPage request
    */
   @PostMapping("findByPage")
   public JsonObject<PageResult<MessageRecordResponse>> findByPage(
         @RequestBody PageRequestWrapper<MessageRecordQueryRequest> request) {
      PageRequestWrapper<MessageRecordQuery> wrapper = new PageRequestWrapper<>();
      wrapper.setBean(MessageRecordMapper.INSTANCE.request2Query(request.getBean()));
      wrapper.setSorts(request.getSorts());
          wrapper.setPageSize(request.getPageSize());
          wrapper.setPage(request.getPage());
      Page<MessageRecordVO> page = messageRecordService.findByPage(wrapper);
      return JsonObject.success(
              PageResult.of(
                  page.getContent().stream()
                      .map(vo -> MessageRecordMapper.INSTANCE.vo2CustomResponse(vo))
                      .collect(Collectors.toList()),
                  page.getTotalElements(),
                  page.getSize(),
                  page.getNumber())
          );
   }
}
