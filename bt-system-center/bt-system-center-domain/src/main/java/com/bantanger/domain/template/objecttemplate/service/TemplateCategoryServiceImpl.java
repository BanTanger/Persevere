// --- Auto Generated by BanTanger ---
package com.bantanger.domain.template.objecttemplate.service;

import com.bantanger.common.enums.CodeEnum;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.domain.template.objecttemplate.TemplateCategory;
import com.bantanger.domain.template.objecttemplate.creator.TemplateCategoryCreator;
import com.bantanger.domain.template.objecttemplate.mapper.TemplateCategoryMapper;
import com.bantanger.domain.template.objecttemplate.query.TemplateCategoryQuery;
import com.bantanger.domain.template.objecttemplate.repository.TemplateCategoryRepository;
import com.bantanger.domain.template.objecttemplate.updater.TemplateCategoryUpdater;
import com.bantanger.domain.template.objecttemplate.vo.TemplateCategoryVO;
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
public class TemplateCategoryServiceImpl implements ITemplateCategoryService {
   private final TemplateCategoryRepository templateCategoryRepository;

   /**
    * createImpl
    */
   @Override
   public Long createTemplateCategory(TemplateCategoryCreator creator) {
      Optional<TemplateCategory> templateCategory = EntityOperations.doCreate(templateCategoryRepository)
      .create(() -> TemplateCategoryMapper.INSTANCE.dtoToEntity(creator))
      .update(e -> e.init())
      .execute();
      return templateCategory.isPresent() ? templateCategory.get().getId() : 0;
   }

   /**
    * update
    */
   @Override
   public void updateTemplateCategory(TemplateCategoryUpdater updater) {
      EntityOperations.doUpdate(templateCategoryRepository)
      .loadById(updater.getId())
      .update(e -> updater.updateTemplateCategory(e))
      .execute();
   }

   /**
    * valid
    */
   @Override
   public void validTemplateCategory(Long id) {
      EntityOperations.doUpdate(templateCategoryRepository)
      .loadById(id)
      .update(e -> e.valid())
      .execute();
   }

   /**
    * invalid
    */
   @Override
   public void invalidTemplateCategory(Long id) {
      EntityOperations.doUpdate(templateCategoryRepository)
      .loadById(id)
      .update(e -> e.invalid())
      .execute();
   }

   /**
    * findById
    */
   @Override
   public TemplateCategoryVO findById(Long id) {
      Optional<TemplateCategory> templateCategory =  templateCategoryRepository.findById(id);
      return new TemplateCategoryVO(templateCategory.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
   }

   /**
    * findByPage
    */
   @Override
   public Page<TemplateCategoryVO> findByPage(PageRequestWrapper<TemplateCategoryQuery> query) {
      BooleanBuilder booleanBuilder = new BooleanBuilder();
      Page<TemplateCategory> page = templateCategoryRepository.findAll(booleanBuilder,
              PageRequest.of(query.getPage() - 1, query.getPageSize(),
              Sort.by(Sort.Direction.DESC, "createdAt")));
      return new PageImpl<>(page.getContent().stream().map(TemplateCategoryVO::new)
              .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
   }
}
