package com.jishudaniu.dkblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jishudaniu.dkblog.pojo.dto.ResourceDTO;
import com.jishudaniu.dkblog.pojo.dto.LabelOptionDTO;
import com.jishudaniu.dkblog.pojo.entity.Resource;
import com.jishudaniu.dkblog.pojo.vo.ConditionVO;
import com.jishudaniu.dkblog.pojo.vo.ResourceVO;

import java.util.List;


/**
 * 资源服务
 *
 * @author darkchen
 * @date 2021/12/29
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 导入swagger权限
     */
    void importSwagger();

    /**
     * 添加或修改资源
     *
     * @param resourceVO 资源对象
     */
    void saveOrUpdateResource(ResourceVO resourceVO);

    /***
     * 删除资源
     *
     @param resourceId 资源id
     */
    void deleteResource(Integer resourceId);

    /**
     * 查看资源列表
     *
     * @param conditionVO 条件
     * @return 资源列表
     */
    List<ResourceDTO> listResources(ConditionVO conditionVO);

    /**
     * 查看资源选项
     *
     * @return 资源选项
     */
    List<LabelOptionDTO> listResourceOption();

}
