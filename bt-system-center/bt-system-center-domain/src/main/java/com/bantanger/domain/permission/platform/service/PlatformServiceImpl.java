// --- Auto Generated by BanTanger ---
package com.bantanger.domain.permission.platform.service;

import com.bantanger.common.enums.CodeEnum;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.domain.permission.platform.Platform;
import com.bantanger.domain.permission.platform.creator.PlatformCreator;
import com.bantanger.domain.permission.platform.mapper.PlatformMapper;
import com.bantanger.domain.permission.platform.query.PlatformQuery;
import com.bantanger.domain.permission.platform.repository.PlatformRepository;
import com.bantanger.domain.permission.platform.updater.PlatformUpdater;
import com.bantanger.domain.permission.platform.vo.PlatformVO;
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
public class PlatformServiceImpl implements IPlatformService {
   private final PlatformRepository platformRepository;

   /**
    * createImpl
    */
   @Override
   public Long createPlatform(PlatformCreator creator) {
      Optional<Platform> platform = EntityOperations.doCreate(platformRepository)
      .create(() -> PlatformMapper.INSTANCE.dtoToEntity(creator))
      .update(e -> e.init())
      .execute();
      return platform.isPresent() ? platform.get().getId() : 0;
   }

   /**
    * update
    */
   @Override
   public void updatePlatform(PlatformUpdater updater) {
      EntityOperations.doUpdate(platformRepository)
      .loadById(updater.getId())
      .update(e -> updater.updatePlatform(e))
      .execute();
   }

   /**
    * valid
    */
   @Override
   public void validPlatform(Long id) {
      EntityOperations.doUpdate(platformRepository)
      .loadById(id)
      .update(e -> e.valid())
      .execute();
   }

   /**
    * invalid
    */
   @Override
   public void invalidPlatform(Long id) {
      EntityOperations.doUpdate(platformRepository)
      .loadById(id)
      .update(e -> e.invalid())
      .execute();
   }

   /**
    * findById
    */
   @Override
   public PlatformVO findById(Long id) {
      Optional<Platform> platform =  platformRepository.findById(id);
      return new PlatformVO(platform.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
   }

   /**
    * findByPage
    */
   @Override
   public Page<PlatformVO> findByPage(PageRequestWrapper<PlatformQuery> query) {
      BooleanBuilder booleanBuilder = new BooleanBuilder();
      Page<Platform> page = platformRepository.findAll(booleanBuilder,
              PageRequest.of(query.getPage() - 1, query.getPageSize(),
              Sort.by(Sort.Direction.DESC, "createdAt")));
      return new PageImpl<>(page.getContent().stream().map(PlatformVO::new)
              .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
   }
}
