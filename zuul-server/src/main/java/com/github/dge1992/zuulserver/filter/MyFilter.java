package com.github.dge1992.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/16
 **/
@Log4j2
@Component
public class MyFilter extends ZuulFilter {

    /**
     * @author dongganen
     * @date 2019/7/16
     * @desc:
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @author dongganen
     * @date 2019/7/16
     * @desc: 过滤的顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @author dongganen
     * @date 2019/7/16
     * @desc: 这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * @author dongganen
     * @date 2019/7/16
     * @desc: 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
//        Object accessToken = request.getParameter("token");
//        if(accessToken == null) {
//            log.warn("token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            try {
//                ctx.getResponse().getWriter().write("token is empty");
//            }catch (Exception e){}
//
//            return null;
//        }
        log.info("ok");
        return null;
    }
}
