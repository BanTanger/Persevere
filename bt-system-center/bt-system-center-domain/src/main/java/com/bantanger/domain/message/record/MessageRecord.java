package com.bantanger.domain.message.record;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
import com.bantanger.codegen.processor.api.GenCreateRequest;
import com.bantanger.codegen.processor.api.GenQueryRequest;
import com.bantanger.codegen.processor.api.GenResponse;
import com.bantanger.codegen.processor.api.GenUpdateRequest;
import com.bantanger.codegen.processor.controller.GenController;
import com.bantanger.codegen.processor.creator.GenCreator;
import com.bantanger.codegen.processor.mapper.GenMapper;
import com.bantanger.codegen.processor.query.GenQuery;
import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.service.GenService;
import com.bantanger.codegen.processor.creator.IgnoreCreator;
import com.bantanger.codegen.processor.updater.IgnoreUpdater;
import com.bantanger.codegen.processor.service.GenServiceImpl;
import com.bantanger.codegen.processor.updater.GenUpdater;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.common.annotation.TypeConverter;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.constants.ValidStatus;
import com.bantanger.domain.message.record.domainservice.model.MessageSendModel;
import com.bantanger.domain.message.record.enums.MsgType;
import com.bantanger.domain.message.record.enums.MsgTypeConverter;
import com.bantanger.domain.message.record.enums.NotifyType;
import com.bantanger.domain.message.record.enums.NotifyTypeConverter;
import com.bantanger.domain.message.record.events.MessageRecordEvent;
import com.bantanger.domain.message.record.events.MessageRecordEvent.MessageRecordCreateEvent;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.tool.schema.extract.spi.ColumnTypeInformation;

@GenVo(pkgName = "com.bantanger.domain.message.record.vo")
@GenCreator(pkgName = "com.bantanger.domain.message.record.creator")
@GenUpdater(pkgName = "com.bantanger.domain.message.record.updater")
@GenRepository(pkgName = "com.bantanger.domain.message.record.repository")
@GenService(pkgName = "com.bantanger.domain.message.record.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.message.record.service")
@GenQuery(pkgName = "com.bantanger.domain.message.record.query")
@GenMapper(pkgName = "com.bantanger.domain.message.record.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.message", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.message.record.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.message.record.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.message.record.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.message.record.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.message.record.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "mesasge_record")
@Data
public class MessageRecord extends BaseJpaAggregate {

    @FieldDesc(name = "模板编码")
    private String templateCode;

    @FieldDesc(name = "消息内容")
    @Column(columnDefinition = "text")
    private String messageContent;

    @FieldDesc(name = "执行参数")
    private String executeParams;

    @FieldDesc(name = "消息类型")
    @Convert(converter = MsgTypeConverter.class)
    @TypeConverter
    private MsgType msgType;

    @FieldDesc(name = "通知类型")
    @Convert(converter = NotifyTypeConverter.class)
    @TypeConverter
    private NotifyType notifyType;

    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus validStatus;

    public void init(String msgContent) {
        this.setMessageContent(msgContent);
        this.setValidStatus(ValidStatus.VALID);
        registerEvent(new MessageRecordCreateEvent(this));
    }

    public void valid() {
        setValidStatus(ValidStatus.VALID);
    }

    public void invalid() {
        setValidStatus(ValidStatus.INVALID);
    }
}
