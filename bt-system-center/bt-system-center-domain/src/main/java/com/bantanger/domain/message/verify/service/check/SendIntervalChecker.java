package com.bantanger.domain.message.verify.service.check;

import com.bantanger.domain.message.verify.service.IVerifyRecordService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.matcher.ElementMatcher;
import org.springframework.stereotype.Component;

/**
 * @author chensongmin
 * @description 发送间隔校验器
 * @date 2025/1/28
 */
@Component
@RequiredArgsConstructor
public class SendIntervalChecker implements ElementMatcher<CheckContext> {

    private final IVerifyRecordService verifyRecordService;

    /**
     * @param checkContext The instance to be matched or {@code null}.
     * @return
     */
    @Override
    public boolean matches(CheckContext checkContext) {
        return verifyRecordService.checkSendInterval(
            checkContext.getAccount(), checkContext.getTemplateCode());
    }
}
