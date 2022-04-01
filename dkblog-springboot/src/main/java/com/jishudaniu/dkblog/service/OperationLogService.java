package com.jishudaniu.dkblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jishudaniu.dkblog.pojo.dto.OperationLogDTO;
import com.jishudaniu.dkblog.pojo.vo.PageResult;
import com.jishudaniu.dkblog.pojo.entity.OperationLog;
import com.jishudaniu.dkblog.pojo.vo.ConditionVO;

/**
 * 操作日志服务
 *
 * @author darkchen
 * @date 2021/12/29
 */
public interface OperationLogService extends IService<OperationLog> {

    /**
     * 查询日志列表
     *
     * @param conditionVO 条件
     * @return 日志列表
     */
    PageResult<OperationLogDTO> listOperationLogs(ConditionVO conditionVO);

}
