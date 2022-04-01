package com.jishudaniu.dkblog.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import lombok.*;

/**
 * 分类
 *
 * @author darkchen

 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_category")
public class Category {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名
     */
    private String categoryName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}
