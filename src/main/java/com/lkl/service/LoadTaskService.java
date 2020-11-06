package com.lkl.service;

import com.lkl.entity.ConfigInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author 刘奎亮
 * 预加载 --- 全局加载
 */
@Slf4j
@Component
public class LoadTaskService implements ApplicationRunner {

    @Autowired
    SpringCronTask springCronTask;

    @Autowired
    ConfigInfoService configInfoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("加载task");
        ConfigInfo configInfo = new ConfigInfo();
            configInfo.setStatus(1);
            springCronTask.refresh(configInfoService.findByNamedParamList(configInfo));
    }
}
