package com.bantanger.domain.message.verify.service.check;

import com.bantanger.domain.message.verify.service.IVerifyRecordService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.matcher.ElementMatcher;
import org.springframework.stereotype.Component;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/28
 */
@Component
@RequiredArgsConstructor
public class SendMaxTimesChecker implements ElementMatcher<CheckContext> {

    private final IVerifyRecordService verifyRecordService;

    /**
     * @param checkContext The instance to be matched or {@code null}.
     * @return
     */
    @Override
    public boolean matches(CheckContext checkContext) {
        return verifyRecordService.checkSendMaxTimes(
            checkContext.getAccount(), checkContext.getTemplateCode());
    }
}
