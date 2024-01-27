package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: cyr
 * @Date: 2024/01/25/16:04
 * @Description:
 */
@Mapper
public interface OrderDetailMapper {
    /**
     * 批量插入
     * @param orderDetail
     */
    void insertBatch(List<OrderDetail> orderDetail);

    /**
     * 根据订单id查询订单明细
     * @param orderId
     * @return
     */
    @Select("select * from order_detail where order_id = #{orderId}")
    List<OrderDetail> getByOrderId(Long orderId);
}
