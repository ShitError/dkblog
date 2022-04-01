package com.jishudaniu.dkblog.dao;

import com.jishudaniu.dkblog.pojo.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


/**
 * 留言
 *
 * @author darkchen
 * @date 2022/01/10
 */
@Repository
public interface MessageDao extends BaseMapper<Message> {

}
