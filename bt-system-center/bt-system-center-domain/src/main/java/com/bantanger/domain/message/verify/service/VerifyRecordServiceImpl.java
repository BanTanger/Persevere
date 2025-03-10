// --- Auto Generated by BanTanger ---
package com.bantanger.domain.message.verify.service;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import com.bantanger.common.enums.CodeEnum;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.domain.message.verify.QVerifyRecord;
import com.bantanger.domain.message.verify.VerifyRecord;
import com.bantanger.domain.message.verify.creator.VerifyRecordCreator;
import com.bantanger.domain.message.verify.mapper.VerifyRecordMapper;
import com.bantanger.domain.message.verify.query.VerifyRecordQuery;
import com.bantanger.domain.message.verify.repository.VerifyRecordRepository;
import com.bantanger.domain.message.verify.service.check.MessageProperties;
import com.bantanger.domain.message.verify.updater.VerifyRecordUpdater;
import com.bantanger.domain.message.verify.vo.VerifyRecordVO;
import com.bantanger.jpa.support.EntityOperations;
import com.google.common.collect.Iterables;
import com.querydsl.core.BooleanBuilder;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
public class VerifyRecordServiceImpl implements IVerifyRecordService {

    private final VerifyRecordRepository verifyRecordRepository;
    private final MessageProperties messageProperties;

    /**
     * @param account      目的账号
     * @param templateCode 发送模板编码
     * @return
     */
    @Override
    public boolean checkSendInterval(String account, String templateCode) {
        Assert.notNull(account, "account is null");
        Assert.notNull(templateCode, "templateCode is null");
        // 查询最近一天的发送记录
        Iterable<VerifyRecord> verifyRecords = searchLastDayVerifyRecord(account, templateCode);
        if (Iterables.isEmpty(verifyRecords)) {
            log.trace("checkSendInterval account:{} templateCode:{} verifyRecords is empty",
                account, templateCode);
            return true;
        } else {
            log.trace("checkSendInterval account:{} templateCode:{} verifyRecords is not empty",
                account, templateCode);
            VerifyRecord verifyRecord = IterUtil.getFirst(verifyRecords);
            return NumberUtil.isGreater(new BigDecimal(Instant.now().toEpochMilli()),
                new BigDecimal(verifyRecord.getEndTime()));
        }
    }

    /**
     * @param account      发送者账号
     * @param templateCode 发送模板编码
     * @return
     */
    @Override
    public boolean checkSendMaxTimes(String account, String templateCode) {
        Assert.notNull(account, "account is null");
        Assert.notNull(templateCode, "templateCode is null");
        // 查询最近一天的发送记录
        Iterable<VerifyRecord> verifyRecords = searchLastDayVerifyRecord(account, templateCode);
        if (Iterables.isEmpty(verifyRecords)) {
            log.trace("checkSendMaxTimes account:{} templateCode:{} verifyRecords is empty",
                account, templateCode);
            return true;
        } else {
            log.trace("checkSendMaxTimes account:{} templateCode:{} verifyRecords is not empty",
                account, templateCode);
            return Iterables.size(verifyRecords) < messageProperties.getSendMaxTimes();
        }
    }

    private Iterable<VerifyRecord> searchLastDayVerifyRecord(String account, String templateCode) {
        return verifyRecordRepository.findAll(new BooleanBuilder()
                .and(QVerifyRecord.verifyRecord.account.eq(account))
                .and(QVerifyRecord.verifyRecord.templateCode.eq(templateCode))
                .and(QVerifyRecord.verifyRecord.createdAt.after(
                    Instant.now().minus(1, ChronoUnit.DAYS))));
    }

    /**
     * createImpl
     */
    @Override
    public Long createVerifyRecord(VerifyRecordCreator creator) {
        Optional<VerifyRecord> verifyRecord = EntityOperations.doCreate(verifyRecordRepository)
            .create(() -> VerifyRecordMapper.INSTANCE.dtoToEntity(creator))
            .update(e -> e.init())
            .execute();
        return verifyRecord.isPresent() ? verifyRecord.get().getId() : 0;
    }

    /**
     * update
     */
    @Override
    public void updateVerifyRecord(VerifyRecordUpdater updater) {
        EntityOperations.doUpdate(verifyRecordRepository)
            .loadById(updater.getId())
            .update(e -> updater.updateVerifyRecord(e))
            .execute();
    }

    /**
     * valid
     */
    @Override
    public void validVerifyRecord(Long id) {
        EntityOperations.doUpdate(verifyRecordRepository)
            .loadById(id)
            .update(e -> e.valid())
            .execute();
    }

    /**
     * invalid
     */
    @Override
    public void invalidVerifyRecord(Long id) {
        EntityOperations.doUpdate(verifyRecordRepository)
            .loadById(id)
            .update(e -> e.invalid())
            .execute();
    }

    /**
     * findById
     */
    @Override
    public VerifyRecordVO findById(Long id) {
        Optional<VerifyRecord> verifyRecord = verifyRecordRepository.findById(id);
        return new VerifyRecordVO(
            verifyRecord.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public Page<VerifyRecordVO> findByPage(PageRequestWrapper<VerifyRecordQuery> query) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Page<VerifyRecord> page = verifyRecordRepository.findAll(booleanBuilder,
            PageRequest.of(query.getPage() - 1, query.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdAt")));
        return new PageImpl<>(page.getContent().stream().map(VerifyRecordVO::new)
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }
}
