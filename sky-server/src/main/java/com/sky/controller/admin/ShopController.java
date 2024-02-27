package com.sky.controller.admin;

import com.sky.bloom.BloomFilterHelper;
import com.sky.bloom.RedisBloomFilter;
import com.sky.constant.StatusConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: cyr
 * @Date: 2024/01/22/23:30
 * @Description:
 */
@RestController("adminShopController")
@Api(tags = "管理端营业状态管理")
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {
    public static final String KEY = "SHOP_STATUS";
    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;
    @Autowired
    private RedisBloomFilter redisBloomFilter;
    @Autowired
    private BloomFilterHelper<Integer> bloomFilterHelper;

    /**
     * 设置营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置营业状态")
    public Result setStatus(@PathVariable Integer status){
        redisBloomFilter.addByBloomFilter(bloomFilterHelper,KEY,status);
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }

    /**
     * 获得营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获得营业状态")
    public Result<Integer> getStatus(){
        if (!redisBloomFilter.includeByBloomFilter(bloomFilterHelper,KEY, StatusConstant.ENABLE) &&
                !redisBloomFilter.includeByBloomFilter(bloomFilterHelper,KEY, StatusConstant.DISABLE)){
            System.out.println("都不存在");
            redisTemplate.opsForValue().set(KEY,StatusConstant.DISABLE);
        }
        Integer status = redisTemplate.opsForValue().get(KEY);
        return Result.success(status);
    }
}
