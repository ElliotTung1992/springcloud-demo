package com.github.dge1992.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/22
 **/
@Log4j2
@Component
public class PostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("这个是PostFilter");
        //获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //处理返回中文乱码
        ctx.getResponse().setCharacterEncoding("gbk");
        //获取上下文中的ResponseBody
        String responseBody = ctx.getResponseBody();
        if(!StringUtils.isEmpty(responseBody)){
            //设置返回码
            ctx.setResponseStatusCode(500);
            //设置返回报文
            ctx.setResponseBody(responseBody);
        }
        return null;
    }
}
