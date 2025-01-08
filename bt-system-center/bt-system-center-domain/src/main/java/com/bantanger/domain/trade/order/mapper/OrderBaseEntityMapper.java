// --- Auto Generated by BanTanger ---
package com.bantanger.domain.trade.order.mapper;

import com.bantanger.common.mapper.DateMapper;
import com.bantanger.common.mapper.GenericEnumMapper;
import com.bantanger.domain.trade.order.model.OrderBase;
import com.bantanger.domain.trade.order.creator.OrderBaseCreator;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
      uses = {
            GenericEnumMapper.class,
            DateMapper.class
      }
)
public interface OrderBaseEntityMapper {
   OrderBaseEntityMapper INSTANCE = Mappers.getMapper(OrderBaseEntityMapper.class);

   OrderBase dtoToEntity(OrderBaseCreator dto);
}
