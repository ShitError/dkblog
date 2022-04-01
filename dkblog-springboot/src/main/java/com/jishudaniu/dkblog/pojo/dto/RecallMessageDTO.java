package com.jishudaniu.dkblog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 撤回消息dto
 *
 * @author darkchen
 * @date 2022/01/01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecallMessageDTO {

    /**
     * 消息id
     */
    private Integer id;

    /**
     * 是否为语音
     */
    private Boolean isVoice;

}
