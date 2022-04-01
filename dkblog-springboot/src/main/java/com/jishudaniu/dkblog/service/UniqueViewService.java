package com.jishudaniu.dkblog.service;

import com.jishudaniu.dkblog.pojo.dto.UniqueViewDTO;
import com.jishudaniu.dkblog.pojo.entity.UniqueView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户量统计
 *
 * @author darkchen
 * @date 2021/12/29
 */
public interface UniqueViewService extends IService<UniqueView> {

    /**
     * 获取7天用户量统计
     *
     * @return 用户量
     */
    List<UniqueViewDTO> listUniqueViews();

}
