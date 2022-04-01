package com.jishudaniu.dkblog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * 归档文章
 *
 * @author darkchen
 * @date 2022/01/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveDTO {

    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 发表时间
     */
    private LocalDateTime createTime;

}
