package com.lkl.mapper;

import com.lkl.entity.ConfigInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfigInfoMapper {
    List<ConfigInfo> findByNamedParamList(ConfigInfo configInfo);
}
