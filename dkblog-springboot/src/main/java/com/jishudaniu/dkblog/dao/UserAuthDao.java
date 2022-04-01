package com.jishudaniu.dkblog.dao;

import com.jishudaniu.dkblog.pojo.dto.UserBackDTO;
import com.jishudaniu.dkblog.pojo.entity.UserAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jishudaniu.dkblog.pojo.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户账号
 *
 * @author darkchen
 * @date 2022/01/10
 */
@Repository
public interface UserAuthDao extends BaseMapper<UserAuth> {

    /**
     * 查询后台用户列表
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return {@link List<UserBackDTO>} 用户列表
     */
    List<UserBackDTO> listUsers(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

    /**
     * 查询后台用户数量
     *
     * @param condition 条件
     * @return 用户数量
     */
    Integer countUser(@Param("condition") ConditionVO condition);

}
