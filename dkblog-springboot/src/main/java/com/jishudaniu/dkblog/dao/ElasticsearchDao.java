package com.jishudaniu.dkblog.dao;

import com.jishudaniu.dkblog.pojo.dto.ArticleSearchDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


/**
 * elasticsearch
 *
 * @author darkchen
 * @date 2022/01/10
 */
@Repository
public interface ElasticsearchDao extends ElasticsearchRepository<ArticleSearchDTO,Integer> {
}
