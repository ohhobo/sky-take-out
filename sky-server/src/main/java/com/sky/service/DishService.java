package com.sky.service;

import com.sky.dto.DishDTO;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: cyr
 * @Date: 2024/01/17/22:59
 * @Description:
 */
public interface DishService {
    /**
     *
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
