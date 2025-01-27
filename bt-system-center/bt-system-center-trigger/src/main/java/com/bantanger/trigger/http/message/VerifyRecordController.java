// --- Auto Generated by BanTanger ---
package com.bantanger.trigger.http.message;

import com.bantanger.api.message.verify.request.VerifyRecordCreateRequest;
import com.bantanger.api.message.verify.request.VerifyRecordQueryRequest;
import com.bantanger.api.message.verify.request.VerifyRecordUpdateRequest;
import com.bantanger.api.message.verify.response.VerifyRecordResponse;
import com.bantanger.common.constants.CodeEnum;
import com.bantanger.common.model.JsonObject;
import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.common.model.PageResult;
import com.bantanger.domain.message.verify.creator.VerifyRecordCreator;
import com.bantanger.domain.message.verify.mapper.VerifyRecordMapper;
import com.bantanger.domain.message.verify.query.VerifyRecordQuery;
import com.bantanger.domain.message.verify.service.IVerifyRecordService;
import com.bantanger.domain.message.verify.updater.VerifyRecordUpdater;
import com.bantanger.domain.message.verify.vo.VerifyRecordVO;
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
@RequestMapping("verifyRecord/v1/")
@RequiredArgsConstructor
public class VerifyRecordController {
   private final IVerifyRecordService verifyRecordService;

   /**
    * createRequest
    */
   @PostMapping("createVerifyRecord")
   public JsonObject<Long> createVerifyRecord(@RequestBody VerifyRecordCreateRequest request) {
      VerifyRecordCreator creator = VerifyRecordMapper.INSTANCE.request2Dto(request);
      return JsonObject.success(verifyRecordService.createVerifyRecord(creator));
   }

   /**
    * update request
    */
   @PostMapping("updateVerifyRecord")
   public JsonObject<String> updateVerifyRecord(@RequestBody VerifyRecordUpdateRequest request) {
      VerifyRecordUpdater updater = VerifyRecordMapper.INSTANCE.request2Updater(request);
      verifyRecordService.updateVerifyRecord(updater);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * valid
    */
   @PostMapping("valid/{id}")
   public JsonObject<String> validVerifyRecord(@PathVariable Long id) {
      verifyRecordService.validVerifyRecord(id);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * invalid
    */
   @PostMapping("invalid/{id}")
   public JsonObject<String> invalidVerifyRecord(@PathVariable Long id) {
      verifyRecordService.invalidVerifyRecord(id);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * findById
    */
   @GetMapping("findById/{id}")
   public JsonObject<VerifyRecordResponse> findById(@PathVariable Long id) {
      VerifyRecordVO vo = verifyRecordService.findById(id);
      VerifyRecordResponse response = VerifyRecordMapper.INSTANCE.vo2CustomResponse(vo);
      return JsonObject.success(response);
   }

   /**
    * findByPage request
    */
   @PostMapping("findByPage")
   public JsonObject<PageResult<VerifyRecordResponse>> findByPage(
         @RequestBody PageRequestWrapper<VerifyRecordQueryRequest> request) {
      PageRequestWrapper<VerifyRecordQuery> wrapper = new PageRequestWrapper<>();
      wrapper.setBean(VerifyRecordMapper.INSTANCE.request2Query(request.getBean()));
      wrapper.setSorts(request.getSorts());
          wrapper.setPageSize(request.getPageSize());
          wrapper.setPage(request.getPage());
      Page<VerifyRecordVO> page = verifyRecordService.findByPage(wrapper);
      return JsonObject.success(
              PageResult.of(
                  page.getContent().stream()
                      .map(vo -> VerifyRecordMapper.INSTANCE.vo2CustomResponse(vo))
                      .collect(Collectors.toList()),
                  page.getTotalElements(),
                  page.getSize(),
                  page.getNumber())
          );
   }
}
