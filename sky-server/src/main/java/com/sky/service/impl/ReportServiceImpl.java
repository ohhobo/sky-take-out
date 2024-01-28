package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: cyr
 * @Date: 2024/01/28/20:35
 * @Description:
 */
@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper orderMapper;
    /**
     * 营业额统计
     * @param begin
     * @param end
     * @return
     */
    @Override
    public TurnoverReportVO getTurnOver(LocalDate begin, LocalDate end) {
        List<LocalDate> localDateList = new ArrayList<>();
        localDateList.add(begin);
        while(!begin.equals(end)){
            begin = begin.plusDays(1);
            localDateList.add(begin);
        }
        List<Double> list = new ArrayList<>();
        for(LocalDate date:localDateList){
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date,LocalTime.MAX);
            Map map = new HashMap();
            map.put("status", Orders.COMPLETED);
            map.put("begin",beginTime);
            map.put("end",endTime);
            Double turnOver = orderMapper.getTurnOver(map);
            turnOver = turnOver==null?0.0:turnOver;
            list.add(turnOver);
        }
        TurnoverReportVO turnoverReportVO = TurnoverReportVO.builder()
                .dateList(StringUtils.join(localDateList,","))//列表变字符串
                .turnoverList(StringUtils.join(list,","))
                .build();
        return turnoverReportVO;
    }
}
