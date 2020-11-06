package com.lkl.controller.cms;

import com.lkl.entity.ConfigInfo;
import com.lkl.service.ConfigInfoService;
import com.lkl.service.SpringCronTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 刘奎亮
 */
@RestController
@RequestMapping("/info")
public class CmsConfigInfoController {

    @Autowired
    private ConfigInfoService configInfoService;

    @Autowired
    private SpringCronTask springCronTask;

    @RequestMapping(value = "/task",method = {RequestMethod.POST,RequestMethod.GET})
    public void infoTest(){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setStatus(-1);
        configInfo.setAppName("测试接口任务");
        configInfo.setCron("0/3 * * * * ?");
        configInfo.setCronMethod("taskMethod");
        configInfo.setCronClazz("com.lkl.task.testTask");
        springCronTask.refresh(configInfoService.findByNamedParamList(configInfo));

    }

}
