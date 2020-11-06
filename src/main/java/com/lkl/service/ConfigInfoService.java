package com.lkl.service;

import com.lkl.entity.ConfigInfo;

import java.util.List;


public interface ConfigInfoService {
    List<ConfigInfo> findByNamedParamList(ConfigInfo configInfo);
    void taskMethod(ConfigInfo configInfo);
}
