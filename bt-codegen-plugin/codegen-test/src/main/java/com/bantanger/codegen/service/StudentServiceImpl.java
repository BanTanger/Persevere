// --- Auto Generated by BanTanger ---
package com.bantanger.codegen.service;

import com.bantanger.codegen.Student;
import com.bantanger.codegen.creator.StudentCreator;
import com.bantanger.codegen.mapper.StudentEntityMapper;
import com.bantanger.codegen.query.StudentQuery;
import com.bantanger.codegen.repository.StudentRepository;
import com.bantanger.codegen.updater.StudentUpdater;
import com.bantanger.codegen.vo.StudentVO;
import com.bantanger.common.enums.CodeEnum;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.jpa.support.EntityOperations;
import com.querydsl.core.BooleanBuilder;
import java.lang.Long;
import java.lang.Override;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {
   private final StudentRepository studentRepository;

   /**
    * createImpl
    */
   @Override
   public Long createStudent(StudentCreator creator) {
      Optional<Student> student = EntityOperations.doCreate(studentRepository)
      .create(() -> StudentEntityMapper.INSTANCE.dtoToEntity(creator))
      .update(e -> e.init())
      .execute();
      return student.isPresent() ? student.get().getId() : 0;
   }

   /**
    * update
    */
   @Override
   public void updateStudent(StudentUpdater updater) {
      EntityOperations.doUpdate(studentRepository)
      .loadById(updater.getId())
      .update(e -> updater.updateStudent(e))
      .execute();
   }

   /**
    * valid
    */
   @Override
   public void validStudent(Long id) {
      EntityOperations.doUpdate(studentRepository)
      .loadById(id)
      .update(e -> e.valid())
      .execute();
   }

   /**
    * invalid
    */
   @Override
   public void invalidStudent(Long id) {
      EntityOperations.doUpdate(studentRepository)
      .loadById(id)
      .update(e -> e.invalid())
      .execute();
   }

   /**
    * findById
    */
   @Override
   public StudentVO findById(Long id) {
      Optional<Student> student =  studentRepository.findById(id);
      return new StudentVO(student.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
   }

   /**
    * findByPage
    */
   @Override
   public Page<StudentVO> findByPage(PageRequestWrapper<StudentQuery> query) {
      BooleanBuilder booleanBuilder = new BooleanBuilder();
      Page<Student> page = studentRepository.findAll(booleanBuilder,
              PageRequest.of(query.getPage() - 1, query.getPageSize(),
              Sort.by(Sort.Direction.DESC, "createdAt")));
      return new PageImpl<>(page.getContent().stream().map(StudentVO::new)
              .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
   }
}
