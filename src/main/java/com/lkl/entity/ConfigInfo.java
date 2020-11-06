package com.lkl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 刘奎亮
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String dbName;
    private String viewName;
    private String ip;
    private Integer port;
    private String username;  //
    private String password;  //
    private String cron;  //
    private String cronMethod;  //
    private String cronClazz;  //
    private Date syncLastTime;  //
    private Long syncID;
    private Integer appId;  //
    private String appName;
    private Integer departmentId;  //
    private String driverClassName;
    private String jdbcUrl;

    private Integer status;
    private String departmentName;
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
