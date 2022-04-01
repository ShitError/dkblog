package com.jishudaniu.dkblog.controller;

import com.jishudaniu.dkblog.pojo.dto.OperationLogDTO;
import com.jishudaniu.dkblog.pojo.vo.ConditionVO;
import com.jishudaniu.dkblog.pojo.vo.PageResult;
import com.jishudaniu.dkblog.service.OperationLogService;
import com.jishudaniu.dkblog.pojo.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 日志控制器
 *
 * @author darkchen
 * @date 2021/12/27
 */
@Api(tags = "日志模块")
@RestController
public class LogController {
    @Autowired
    private OperationLogService operationLogService;

    /**
     * 查看操作日志
     *
     * @param conditionVO 条件
     * @return {@link Result<OperationLogDTO>} 日志列表
     */
    @ApiOperation(value = "查看操作日志")
    @GetMapping("/admin/operation/logs")
    public Result<PageResult<OperationLogDTO>> listOperationLogs(ConditionVO conditionVO) {
        return Result.ok(operationLogService.listOperationLogs(conditionVO));
    }

    /**
     * 删除操作日志
     *
     * @param logIdList 日志id列表
     * @return {@link Result<>}
     */
    @ApiOperation(value = "删除操作日志")
    @DeleteMapping("/admin/operation/logs")
    public Result<?> deleteOperationLogs(@RequestBody List<Integer> logIdList) {
        operationLogService.removeByIds(logIdList);
        return Result.ok();
    }

}
