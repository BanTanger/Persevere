// --- Auto Generated by BanTanger ---
package com.bantanger.domain.trade.orderitem.mapper;

import com.bantanger.api.trade.orderitem.request.OrderItemCreateRequest;
import com.bantanger.api.trade.orderitem.request.OrderItemQueryRequest;
import com.bantanger.api.trade.orderitem.request.OrderItemUpdateRequest;
import com.bantanger.api.trade.orderitem.response.OrderItemResponse;
import com.bantanger.common.mapper.DateMapper;
import com.bantanger.common.mapper.GenericEnumMapper;
import com.bantanger.domain.CustomMapper;
import com.bantanger.domain.trade.order.domainservice.model.OrderItemModel;
import com.bantanger.domain.trade.orderitem.OrderItem;
import com.bantanger.domain.trade.orderitem.creator.OrderItemCreator;
import com.bantanger.domain.trade.orderitem.query.OrderItemQuery;
import com.bantanger.domain.trade.orderitem.updater.OrderItemUpdater;
import com.bantanger.domain.trade.orderitem.vo.OrderItemVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    uses = {
        GenericEnumMapper.class,
        DateMapper.class,
        CustomMapper.class
    }
)
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItem dtoToEntity(OrderItemCreator dto);

    OrderItemUpdater request2Updater(OrderItemUpdateRequest request);

    OrderItemCreator request2Dto(OrderItemCreateRequest request);

    OrderItemQuery request2Query(OrderItemQueryRequest request);

    OrderItemCreator model2Creator(OrderItemModel model);

    OrderItemResponse vo2Response(OrderItemVO vo);

    default OrderItemResponse vo2CustomResponse(OrderItemVO vo) {
        OrderItemResponse response = vo2Response(vo);
        return response;
    }
}
