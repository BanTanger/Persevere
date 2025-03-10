// --- Auto Generated by BanTanger ---
package com.bantanger.trigger.http.asset;

import com.bantanger.api.asset.record.request.AssetInOutRecordQueryRequest;
import com.bantanger.api.asset.record.response.AssetInOutRecordResponse;
import com.bantanger.common.model.JsonObject;
import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.common.model.PageResult;
import com.bantanger.domain.asset.record.mapper.AssetInOutRecordMapper;
import com.bantanger.domain.asset.record.query.AssetInOutRecordQuery;
import com.bantanger.domain.asset.record.service.IAssetInOutRecordService;
import com.bantanger.domain.asset.record.vo.AssetInOutRecordVO;
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
@RequestMapping("assetInOutRecord/v1/")
@RequiredArgsConstructor
public class AssetInOutRecordController {
   private final IAssetInOutRecordService assetInOutRecordService;

   /**
    * findById
    */
   @GetMapping("findById/{id}")
   public JsonObject<AssetInOutRecordResponse> findById(@PathVariable Long id) {
      AssetInOutRecordVO vo = assetInOutRecordService.findById(id);
      AssetInOutRecordResponse response = AssetInOutRecordMapper.INSTANCE.vo2CustomResponse(vo);
      return JsonObject.success(response);
   }

   /**
    * findByPage request
    */
   @PostMapping("findByPage")
   public JsonObject<PageResult<AssetInOutRecordResponse>> findByPage(
         @RequestBody PageRequestWrapper<AssetInOutRecordQueryRequest> request) {
      PageRequestWrapper<AssetInOutRecordQuery> wrapper = new PageRequestWrapper<>();
      wrapper.setBean(AssetInOutRecordMapper.INSTANCE.request2Query(request.getBean()));
      wrapper.setSorts(request.getSorts());
          wrapper.setPageSize(request.getPageSize());
          wrapper.setPage(request.getPage());
      Page<AssetInOutRecordVO> page = assetInOutRecordService.findByPage(wrapper);
      return JsonObject.success(
              PageResult.of(
                  page.getContent().stream()
                      .map(vo -> AssetInOutRecordMapper.INSTANCE.vo2CustomResponse(vo))
                      .collect(Collectors.toList()),
                  page.getTotalElements(),
                  page.getSize(),
                  page.getNumber())
          );
   }
}
