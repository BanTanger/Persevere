// --- Auto Generated by BanTanger ---
package com.bantanger.codegen.controller;

import com.bantanger.codegen.creator.StudentCreator;
import com.bantanger.codegen.mapper.StudentMapper;
import com.bantanger.codegen.query.StudentQuery;
import com.bantanger.codegen.request.StudentCreateRequest;
import com.bantanger.codegen.request.StudentQueryRequest;
import com.bantanger.codegen.request.StudentUpdateRequest;
import com.bantanger.codegen.response.StudentResponse;
import com.bantanger.codegen.service.IStudentService;
import com.bantanger.codegen.updater.StudentUpdater;
import com.bantanger.codegen.vo.StudentVO;
import com.bantanger.common.constants.CodeEnum;
import com.bantanger.common.model.JsonObject;
import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.common.model.PageResult;
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
@RequestMapping("student/v1")
@RequiredArgsConstructor
public class StudentController {
   private final IStudentService studentService;

   /**
    * createRequest
    */
   @PostMapping("createStudent")
   public JsonObject<Long> createStudent(@RequestBody StudentCreateRequest request) {
      StudentCreator creator = StudentMapper.INSTANCE.request2Dto(request);
      return JsonObject.success(studentService.createStudent(creator));
   }

   /**
    * update request
    */
   @PostMapping("updateStudent")
   public JsonObject<String> updateStudent(@RequestBody StudentUpdateRequest request) {
      StudentUpdater updater = StudentMapper.INSTANCE.request2Updater(request);
      studentService.updateStudent(updater);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * valid
    */
   @PostMapping("valid/{id}")
   public JsonObject<String> validStudent(@PathVariable Long id) {
      studentService.validStudent(id);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * invalid
    */
   @PostMapping("invalid/{id}")
   public JsonObject<String> invalidStudent(@PathVariable Long id) {
      studentService.invalidStudent(id);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * findById
    */
   @GetMapping("findById/{id}")
   public JsonObject<StudentResponse> findById(@PathVariable Long id) {
      StudentVO vo = studentService.findById(id);
      StudentResponse response = StudentMapper.INSTANCE.vo2CustomResponse(vo);
      return JsonObject.success(response);
   }

   /**
    * findByPage request
    */
   @PostMapping("findByPage")
   public JsonObject<PageResult<StudentResponse>> findByPage(
         @RequestBody PageRequestWrapper<StudentQueryRequest> request) {
      PageRequestWrapper<StudentQuery> wrapper = new PageRequestWrapper<>();
      wrapper.setBean(StudentMapper.INSTANCE.request2Query(request.getBean()));
      wrapper.setSorts(request.getSorts());
          wrapper.setPageSize(request.getPageSize());
          wrapper.setPage(request.getPage());
      Page<StudentVO> page = studentService.findByPage(wrapper);
      return JsonObject.success(
              PageResult.of(
                  page.getContent().stream()
                      .map(vo -> StudentMapper.INSTANCE.vo2CustomResponse(vo))
                      .collect(Collectors.toList()),
                  page.getTotalElements(),
                  page.getSize(),
                  page.getNumber())
          );
   }
}
