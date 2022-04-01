package com.jishudaniu.dkblog.strategy;

import com.jishudaniu.dkblog.pojo.dto.UserInfoDTO;

/**
 * 第三方登录策略
 *
 * @author darkchen
 * @date 2021/12/28
 */
public interface SocialLoginStrategy {

    /**
     * 登录
     *
     * @param data 数据
     * @return {@link UserInfoDTO} 用户信息
     */
    UserInfoDTO login(String data);

}
