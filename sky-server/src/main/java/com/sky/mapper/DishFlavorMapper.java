package com.sky.mapper;

import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: cyr
 * @Date: 2024/01/17/23:03
 * @Description:
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);
}
