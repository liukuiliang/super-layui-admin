package com.lkl.service.serviceImpl;

import com.lkl.entity.ConfigInfo;
import com.lkl.mapper.ConfigInfoMapper;
import com.lkl.service.ConfigInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConfigInfoServiceImpl implements ConfigInfoService {

    @Autowired
    private ConfigInfoMapper configInfoMapper;

    @Override
    public List<ConfigInfo> findByNamedParamList(ConfigInfo configInfo) {
        log.debug("===================任务接口================");
        return configInfoMapper.findByNamedParamList(configInfo);
    }

    @Override
    public void taskMethod(ConfigInfo configInfo) {
        log.debug("===================任务接口================");
    }

}
