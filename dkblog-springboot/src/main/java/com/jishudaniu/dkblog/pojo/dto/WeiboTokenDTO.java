package com.jishudaniu.dkblog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微博token
 *
 * @author darkchen
 * @date 2021/06/14

 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeiboTokenDTO {

    /**
     * 微博uid
     */
    private String uid;

    /**
     * 访问令牌
     */
    private String access_token;

}
