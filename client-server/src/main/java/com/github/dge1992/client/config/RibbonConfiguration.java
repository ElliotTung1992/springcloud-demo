package com.github.dge1992.client.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dongganen
 * @date 2019/8/15
 * @desc: 配置ribbon负载均衡策略
 */
@AvoidScan
@Configuration
public class RibbonConfiguration {

//    @Autowired
//    IClientConfig config;

	@Bean
	public IRule ribbonRule() {
		//随机策略
//		return new RandomRule();
		//轮询策略
		return new RoundRobinRule();
	}
}