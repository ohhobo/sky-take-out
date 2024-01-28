package com.sky.service;

import com.sky.vo.TurnoverReportVO;

import java.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: cyr
 * @Date: 2024/01/28/20:33
 * @Description:
 */
public interface ReportService {
    /**
     * 根据时间区间统计营业额
     * @param beginTime
     * @param endTime
     * @return
     */
    TurnoverReportVO getTurnOver(LocalDate beginTime, LocalDate endTime);
}
