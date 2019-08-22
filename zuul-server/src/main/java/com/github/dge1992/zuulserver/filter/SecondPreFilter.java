package com.github.dge1992.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/22
 **/
@Log4j2
//@Component
public class SecondPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        //从上下文获取logic-is-success值，用于判断此Filter是否执行
        return (boolean)ctx.get("logic-is-success");
    }

    @Override
    public Object run() throws ZuulException {
        log.info("这个是SecondPreFilter");
        return null;
    }
}
