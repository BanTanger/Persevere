// --- Auto Generated by BanTanger ---
package com.bantanger.domain.stock.seat.service;

import com.bantanger.common.enums.CodeEnum;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.domain.stock.seat.SeatStockDetail;
import com.bantanger.domain.stock.seat.creator.SeatStockDetailCreator;
import com.bantanger.domain.stock.seat.mapper.SeatStockDetailMapper;
import com.bantanger.domain.stock.seat.query.SeatStockDetailQuery;
import com.bantanger.domain.stock.seat.repository.SeatStockDetailRepository;
import com.bantanger.domain.stock.seat.updater.SeatStockDetailUpdater;
import com.bantanger.domain.stock.seat.vo.SeatStockDetailVO;
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
public class SeatStockDetailServiceImpl implements ISeatStockDetailService {
   private final SeatStockDetailRepository seatStockDetailRepository;

   /**
    * createImpl
    */
   @Override
   public Long createSeatStockDetail(SeatStockDetailCreator creator) {
      Optional<SeatStockDetail> seatStockDetail = EntityOperations.doCreate(seatStockDetailRepository)
      .create(() -> SeatStockDetailMapper.INSTANCE.dtoToEntity(creator))
      .update(e -> e.init())
      .execute();
      return seatStockDetail.isPresent() ? seatStockDetail.get().getId() : 0;
   }

   /**
    * update
    */
   @Override
   public void updateSeatStockDetail(SeatStockDetailUpdater updater) {
      EntityOperations.doUpdate(seatStockDetailRepository)
      .loadById(updater.getId())
      .update(e -> updater.updateSeatStockDetail(e))
      .execute();
   }

   /**
    * valid
    */
   @Override
   public void validSeatStockDetail(Long id) {
      EntityOperations.doUpdate(seatStockDetailRepository)
      .loadById(id)
      .update(e -> e.valid())
      .execute();
   }

   /**
    * invalid
    */
   @Override
   public void invalidSeatStockDetail(Long id) {
      EntityOperations.doUpdate(seatStockDetailRepository)
      .loadById(id)
      .update(e -> e.invalid())
      .execute();
   }

   /**
    * findById
    */
   @Override
   public SeatStockDetailVO findById(Long id) {
      Optional<SeatStockDetail> seatStockDetail =  seatStockDetailRepository.findById(id);
      return new SeatStockDetailVO(seatStockDetail.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
   }

   /**
    * findByPage
    */
   @Override
   public Page<SeatStockDetailVO> findByPage(PageRequestWrapper<SeatStockDetailQuery> query) {
      BooleanBuilder booleanBuilder = new BooleanBuilder();
      Page<SeatStockDetail> page = seatStockDetailRepository.findAll(booleanBuilder,
              PageRequest.of(query.getPage() - 1, query.getPageSize(),
              Sort.by(Sort.Direction.DESC, "createdAt")));
      return new PageImpl<>(page.getContent().stream().map(SeatStockDetailVO::new)
              .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
   }
}
