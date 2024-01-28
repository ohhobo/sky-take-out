package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: cyr
 * @Date: 2024/01/28/20:27
 * @Description:
 */
@RestController
@RequestMapping("/admin/report")
@Api(tags = "统计报表相关接口")
@Slf4j
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额数据统计")
    public Result<TurnoverReportVO> turnOverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        TurnoverReportVO turnoverReportVO = reportService.getTurnOver(begin,end);
        return Result.success(turnoverReportVO);
    }

}
