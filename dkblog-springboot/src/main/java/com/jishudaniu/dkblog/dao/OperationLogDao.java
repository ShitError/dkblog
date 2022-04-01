package com.jishudaniu.dkblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jishudaniu.dkblog.pojo.entity.OperationLog;
import org.springframework.stereotype.Repository;


/**
 * 操作日志
 *
 * @author darkchen
 * @date 2022/01/10
 */
@Repository
public interface OperationLogDao extends BaseMapper<OperationLog> {
}
