package com.jishudaniu.dkblog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jishudaniu.dkblog.dao.PageDao;
import com.jishudaniu.dkblog.pojo.entity.Page;
import com.jishudaniu.dkblog.service.PageService;
import com.jishudaniu.dkblog.service.RedisService;
import com.jishudaniu.dkblog.util.BeanCopyUtils;
import com.jishudaniu.dkblog.pojo.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;

import static com.jishudaniu.dkblog.constant.RedisPrefixConst.PAGE_COVER;

/**
 * 页面服务
 *
 * @author darkchen
 * @date 2022/01/07
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageDao, Page> implements PageService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private PageDao pageDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdatePage(PageVO pageVO) {
        Page page = BeanCopyUtils.copyObject(pageVO, Page.class);
        this.saveOrUpdate(page);
        // 删除缓存
        redisService.del(PAGE_COVER);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePage(Integer pageId) {
        pageDao.deleteById(pageId);
        // 删除缓存
        redisService.del(PAGE_COVER);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<PageVO> listPages() {
        List<PageVO> pageVOList;
        // 查找缓存信息，不存在则从mysql读取，更新缓存
        Object pageList = redisService.get(PAGE_COVER);
        if (Objects.nonNull(pageList)) {
            pageVOList = JSON.parseObject(pageList.toString(), List.class);
        } else {
            pageVOList = BeanCopyUtils.copyList(pageDao.selectList(null), PageVO.class);
            redisService.set(PAGE_COVER, JSON.toJSONString(pageVOList));
        }
        return pageVOList;
    }

}




