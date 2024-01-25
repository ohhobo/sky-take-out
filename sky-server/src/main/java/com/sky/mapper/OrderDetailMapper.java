package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

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
}
