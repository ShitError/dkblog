package com.jishudaniu.dkblog.service;

import com.jishudaniu.dkblog.pojo.dto.MessageBackDTO;
import com.jishudaniu.dkblog.pojo.vo.PageResult;
import com.jishudaniu.dkblog.pojo.vo.ConditionVO;
import com.jishudaniu.dkblog.pojo.vo.MessageVO;
import com.jishudaniu.dkblog.pojo.dto.MessageDTO;
import com.jishudaniu.dkblog.pojo.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jishudaniu.dkblog.pojo.vo.ReviewVO;

import java.util.List;

/**
 * 留言服务
 *
 * @author darkchen
 * @date 2021/12/29
 */
public interface MessageService extends IService<Message> {

    /**
     * 添加留言弹幕
     *
     * @param messageVO 留言对象
     */
    void saveMessage(MessageVO messageVO);

    /**
     * 查看留言弹幕
     *
     * @return 留言列表
     */
    List<MessageDTO> listMessages();

    /**
     * 审核留言
     *
     * @param reviewVO 审查签证官
     */
    void updateMessagesReview(ReviewVO reviewVO);

    /**
     * 查看后台留言
     *
     * @param condition 条件
     * @return 留言列表
     */
    PageResult<MessageBackDTO> listMessageBackDTO(ConditionVO condition);

}
