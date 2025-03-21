// --- Auto Generated by BanTanger ---
package com.bantanger.domain.message.template.service;

import com.bantanger.common.enums.CodeEnum;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.domain.message.template.MessageTemplate;
import com.bantanger.domain.message.template.creator.MessageTemplateCreator;
import com.bantanger.domain.message.template.mapper.MessageTemplateMapper;
import com.bantanger.domain.message.template.query.MessageTemplateQuery;
import com.bantanger.domain.message.template.repository.MessageTemplateRepository;
import com.bantanger.domain.message.template.updater.MessageTemplateUpdater;
import com.bantanger.domain.message.template.vo.MessageTemplateVO;
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
public class MessageTemplateServiceImpl implements IMessageTemplateService {
   private final MessageTemplateRepository messageTemplateRepository;

   /**
    * createImpl
    */
   @Override
   public Long createMessageTemplate(MessageTemplateCreator creator) {
      Optional<MessageTemplate> messageTemplate = EntityOperations.doCreate(messageTemplateRepository)
      .create(() -> MessageTemplateMapper.INSTANCE.dtoToEntity(creator))
      .update(e -> e.init())
      .execute();
      return messageTemplate.isPresent() ? messageTemplate.get().getId() : 0;
   }

   /**
    * update
    */
   @Override
   public void updateMessageTemplate(MessageTemplateUpdater updater) {
      EntityOperations.doUpdate(messageTemplateRepository)
      .loadById(updater.getId())
      .update(e -> updater.updateMessageTemplate(e))
      .execute();
   }

   /**
    * valid
    */
   @Override
   public void validMessageTemplate(Long id) {
      EntityOperations.doUpdate(messageTemplateRepository)
      .loadById(id)
      .update(e -> e.valid())
      .execute();
   }

   /**
    * invalid
    */
   @Override
   public void invalidMessageTemplate(Long id) {
      EntityOperations.doUpdate(messageTemplateRepository)
      .loadById(id)
      .update(e -> e.invalid())
      .execute();
   }

   /**
    * findById
    */
   @Override
   public MessageTemplateVO findById(Long id) {
      Optional<MessageTemplate> messageTemplate =  messageTemplateRepository.findById(id);
      return new MessageTemplateVO(messageTemplate.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
   }

   /**
    * findByPage
    */
   @Override
   public Page<MessageTemplateVO> findByPage(PageRequestWrapper<MessageTemplateQuery> query) {
      BooleanBuilder booleanBuilder = new BooleanBuilder();
      Page<MessageTemplate> page = messageTemplateRepository.findAll(booleanBuilder,
              PageRequest.of(query.getPage() - 1, query.getPageSize(),
              Sort.by(Sort.Direction.DESC, "createdAt")));
      return new PageImpl<>(page.getContent().stream().map(MessageTemplateVO::new)
              .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
   }
}
