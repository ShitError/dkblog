package com.jishudaniu.dkblog.strategy;

import com.jishudaniu.dkblog.pojo.dto.ArticleSearchDTO;

import java.util.List;

/**
 * 搜索策略
 *
 * @author darkchen
 * @date 2021/12/27
 */
public interface SearchStrategy {

    /**
     * 搜索文章
     *
     * @param keywords 关键字
     * @return {@link List<ArticleSearchDTO>} 文章列表
     */
    List<ArticleSearchDTO> searchArticle(String keywords);

}
