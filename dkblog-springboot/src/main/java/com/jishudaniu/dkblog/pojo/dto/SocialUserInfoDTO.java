package com.jishudaniu.dkblog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社交账号信息
 *
 * @author darkchen
 * @date 2021/12/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialUserInfoDTO {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

}
