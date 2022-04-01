package com.jishudaniu.dkblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jishudaniu.dkblog.pojo.entity.ChatRecord;
import org.springframework.stereotype.Repository;

/**
 * 聊天记录
 *
 * @author darkchen
 * @date 2022/01/10
 */
@Repository
public interface ChatRecordDao extends BaseMapper<ChatRecord> {
}
