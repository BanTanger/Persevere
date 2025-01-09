// --- Auto Generated by BanTanger ---
package com.bantanger.domain.trade.orderitem.mapper;

import com.bantanger.common.mapper.DateMapper;
import com.bantanger.common.mapper.GenericEnumMapper;
import com.bantanger.domain.trade.order.domainservice.model.OrderItemModel;
import com.bantanger.domain.trade.orderitem.OrderItem;
import com.bantanger.domain.trade.orderitem.creator.OrderItemCreator;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
      uses = {
            GenericEnumMapper.class,
            DateMapper.class
      }
)
public interface OrderItemEntityMapper {
   OrderItemEntityMapper INSTANCE = Mappers.getMapper(OrderItemEntityMapper.class);

   OrderItem dtoToEntity(OrderItemCreator dto);

   OrderItemCreator model2Creator(OrderItemModel orderItemModel);
}
