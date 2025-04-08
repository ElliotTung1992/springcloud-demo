package com.github.elliot.apollodemo.doamin;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Data
@Slf4j
public class CarConfig {

    private static final String NAMESPACE = "application";

    private static final String CAR_NAME_KEY = "car.name";

    @ApolloConfig
    private Config config;

    private String name;

    @ApolloConfigChangeListener(NAMESPACE)
    public void onChange(ConfigChangeEvent changeEvent){
        if(changeEvent.isChanged(CAR_NAME_KEY)){
            this.name = changeEvent.getChange(CAR_NAME_KEY).getNewValue();
            log.info(CAR_NAME_KEY + ":" + this.name);
        }
    }

    @PostConstruct
    private void initialize(){
        this.name = config.getProperty(CAR_NAME_KEY, "default-name");
    }
}
