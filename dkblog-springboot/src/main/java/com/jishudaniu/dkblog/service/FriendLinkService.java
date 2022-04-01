package com.jishudaniu.dkblog.service;

import com.jishudaniu.dkblog.pojo.dto.FriendLinkBackDTO;
import com.jishudaniu.dkblog.pojo.dto.FriendLinkDTO;
import com.jishudaniu.dkblog.pojo.vo.ConditionVO;
import com.jishudaniu.dkblog.pojo.vo.PageResult;
import com.jishudaniu.dkblog.pojo.entity.FriendLink;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jishudaniu.dkblog.pojo.vo.FriendLinkVO;

import java.util.List;

/**
 * 友链服务
 *
 * @author darkchen
 * @date 2021/12/29
 */
public interface FriendLinkService extends IService<FriendLink> {

    /**
     * 查看友链列表
     *
     * @return 友链列表
     */
    List<FriendLinkDTO> listFriendLinks();

    /**
     * 查看后台友链列表
     *
     * @param condition 条件
     * @return 友链列表
     */
    PageResult<FriendLinkBackDTO> listFriendLinkDTO(ConditionVO condition);

    /**
     * 保存或更新友链
     *
     * @param friendLinkVO 友链
     */
    void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO);

}
