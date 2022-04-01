package com.jishudaniu.dkblog.strategy.context;

import com.jishudaniu.dkblog.pojo.dto.ArticleSearchDTO;
import com.jishudaniu.dkblog.strategy.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.jishudaniu.dkblog.enums.SearchModeEnum.getStrategy;

/**
 * 搜索策略上下文
 *
 * @author darkchen
 * @date 2021/12/27
 */
@Service
public class SearchStrategyContext {
    /**
     * 搜索模式
     */
    @Value("${search.mode}")
    private String searchMode;

    @Autowired
    private Map<String, SearchStrategy> searchStrategyMap;

    /**
     * 执行搜索策略
     *
     * @param keywords 关键字
     * @return {@link List<ArticleSearchDTO>} 搜索文章
     */
    public List<ArticleSearchDTO> executeSearchStrategy(String keywords) {
        return searchStrategyMap.get(getStrategy(searchMode)).searchArticle(keywords);
    }

}
