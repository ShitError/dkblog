package com.jishudaniu.dkblog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 文章排行
 *
 * @author darkchen
 * @date 2022/01/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRankDTO {
    /**
     * id
     */
    private Integer articleId;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 浏览量
     */
    private Integer viewsCount;


}
