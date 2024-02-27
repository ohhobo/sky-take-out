package com.sky.controller.user;

import com.sky.bloom.BloomFilterHelper;
import com.sky.bloom.RedisBloomFilter;
import com.sky.constant.StatusConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: cyr
 * @Date: 2024/01/22/23:42
 * @Description:
 */
@RestController("userShopController")
@RequestMapping("/user/shop")
@Api(tags = "用户营业状态管理")
@Slf4j
public class ShopController {
    public static final String KEY = "SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisBloomFilter redisBloomFilter;
    @Autowired
    private BloomFilterHelper<Integer> bloomFilterHelper;
    /**
     * 获得营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获得营业状态")
    public Result<Integer> getStatus(){
        if(!redisBloomFilter.includeByBloomFilter(bloomFilterHelper,KEY, StatusConstant.ENABLE)&&
                !redisBloomFilter.includeByBloomFilter(bloomFilterHelper,KEY,StatusConstant.DISABLE)){
            return Result.error();
        }
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        return Result.success(status);
    }
}
