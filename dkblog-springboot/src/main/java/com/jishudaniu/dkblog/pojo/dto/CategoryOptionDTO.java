package com.jishudaniu.dkblog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类选项
 *
 * @author darkchen
 * @date 2022/01/02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryOptionDTO {

    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名
     */
    private String categoryName;

}
