package com.jishudaniu.dkblog.handler;

import com.alibaba.fastjson.JSON;
import com.jishudaniu.dkblog.pojo.vo.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销处理
 *
 * @author darkchen
 * @date 2021/12/28
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.ok()));
    }

}
