package com.jishudaniu.dkblog.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 文章标签
 * 标签
 *
 * @author darkchen
 * @date 2021/12/29

 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article_tag")
public class ArticleTag {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 标签id
     */
    private Integer tagId;


}
