package com.jishudaniu.dkblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jishudaniu.dkblog.pojo.dto.MessageBackDTO;
import com.jishudaniu.dkblog.service.BlogInfoService;
import com.jishudaniu.dkblog.util.PageUtils;
import com.jishudaniu.dkblog.pojo.vo.ConditionVO;
import com.jishudaniu.dkblog.pojo.vo.PageResult;
import com.jishudaniu.dkblog.pojo.vo.MessageVO;
import com.jishudaniu.dkblog.pojo.dto.MessageDTO;
import com.jishudaniu.dkblog.pojo.entity.Message;
import com.jishudaniu.dkblog.dao.MessageDao;
import com.jishudaniu.dkblog.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jishudaniu.dkblog.util.BeanCopyUtils;
import com.jishudaniu.dkblog.util.IpUtils;
import com.jishudaniu.dkblog.pojo.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.jishudaniu.dkblog.constant.CommonConst.FALSE;
import static com.jishudaniu.dkblog.constant.CommonConst.TRUE;

/**
 * 留言服务
 *
 * @author darkchen
 * @date 2021/12/28
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements MessageService {
    @Autowired
    private MessageDao messageDao;
    @Resource
    private HttpServletRequest request;
    @Autowired
    private BlogInfoService blogInfoService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveMessage(MessageVO messageVO) {
        // 判断是否需要审核
        Integer isReview = blogInfoService.getWebsiteConfig().getIsMessageReview();
        // 获取用户ip
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        Message message = BeanCopyUtils.copyObject(messageVO, Message.class);
        message.setIpAddress(ipAddress);
        message.setIsReview(isReview == TRUE ? FALSE : TRUE);
        message.setIpSource(ipSource);
        messageDao.insert(message);
    }

    @Override
    public List<MessageDTO> listMessages() {
        // 查询留言列表
        List<Message> messageList = messageDao.selectList(new LambdaQueryWrapper<Message>()
                .select(Message::getId, Message::getNickname, Message::getAvatar, Message::getMessageContent, Message::getTime)
                .eq(Message::getIsReview, TRUE));
        return BeanCopyUtils.copyList(messageList, MessageDTO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateMessagesReview(ReviewVO reviewVO) {
        // 修改留言审核状态
        List<Message> messageList = reviewVO.getIdList().stream().map(item -> Message.builder()
                        .id(item)
                        .isReview(reviewVO.getIsReview())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(messageList);
    }

    @Override
    public PageResult<MessageBackDTO> listMessageBackDTO(ConditionVO condition) {
        // 分页查询留言列表
        Page<Message> page = new Page<>(PageUtils.getCurrent(), PageUtils.getSize());
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<Message>()
                .like(StringUtils.isNotBlank(condition.getKeywords()), Message::getNickname, condition.getKeywords())
                .eq(Objects.nonNull(condition.getIsReview()), Message::getIsReview, condition.getIsReview())
                .orderByDesc(Message::getId);
        Page<Message> messagePage = messageDao.selectPage(page, messageLambdaQueryWrapper);
        // 转换DTO
        List<MessageBackDTO> messageBackDTOList = BeanCopyUtils.copyList(messagePage.getRecords(), MessageBackDTO.class);
        return new PageResult<>(messageBackDTOList, (int) messagePage.getTotal());
    }

}
