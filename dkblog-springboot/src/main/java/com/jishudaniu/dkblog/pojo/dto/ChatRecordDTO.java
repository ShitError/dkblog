package com.jishudaniu.dkblog.pojo.dto;

import com.jishudaniu.dkblog.pojo.entity.ChatRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 聊天记录
 *
 * @author darkchen
 * @date 2022/01/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRecordDTO {

    /**
     * 聊天记录
     */
    private List<ChatRecord> chatRecordList;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * ip来源
     */
    private String ipSource;

}
