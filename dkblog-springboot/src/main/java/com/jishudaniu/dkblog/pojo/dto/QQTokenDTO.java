package com.jishudaniu.dkblog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * qq token信息
 *
 * @author darkchen
 * @date 2021/06/15

 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QQTokenDTO {

    /**
     * openid
     */
    private String openid;

    /**
     * 客户端id
     */
    private String client_id;

}
