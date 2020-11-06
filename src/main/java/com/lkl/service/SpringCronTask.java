package com.lkl.service;


import com.lkl.entity.ConfigInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

@Lazy(false)
@Component
@EnableScheduling
@Slf4j
public class SpringCronTask implements SchedulingConfigurer {


    @Autowired
    ApplicationContext applicationContext;


    public SpringCronTask() {


    }

    private boolean exists(List<ConfigInfo> cronTask, Integer tid) {
        for (ConfigInfo scheduledConfigItem : cronTask) {

            if (scheduledConfigItem.getId() == tid) {
                return true;
            }
        }
        return false;
    }

    public void refresh(List<ConfigInfo> scheduledConfigItemList) {

        //取消已经删除的策略任务
        Set<Integer> sids = scheduledFutures.keySet();
        for (Integer sid : sids) {

            if (!exists(scheduledConfigItemList, sid)) {
                scheduledFutures.get(sid).cancel(false);
                scheduledFutures.remove(sid);
            }

        }
        //
        if (scheduledConfigItemList == null) return;

        for (ConfigInfo configItem : scheduledConfigItemList) {

            String expression = configItem.getCron();
            //计划时间任务表达式为空
            if (StringUtils.isBlank(expression)) {
                continue;
            }
            //计划任务存在且表达式未发生变化则跳过
            if (scheduledFutures.containsKey(configItem.getId()) && cronTasks.get(configItem.getId()).getExpression().equals(expression)) {
                continue;
            }
            //策略发生变化，取消当前的任务
            if (scheduledFutures.containsKey(configItem.getId())) {
                scheduledFutures.get(configItem.getId()).cancel(false);
                scheduledFutures.remove(configItem.getId());
                cronTasks.remove(configItem.getId());
            }

            CronTask task = new CronTask(new Runnable() {
                @Override
                public void run() {

                    Class<?> clazz;
                    try {
                        clazz = Class.forName(configItem.getCronClazz());
                        String className = StringUtils.uncapitalize(clazz.getSimpleName());
                        Object bean = (Object) applicationContext.getBean(className);
                        Method method = ReflectionUtils.findMethod(bean.getClass(), configItem.getCronMethod(),ConfigInfo.class);
                        log.info("任务{}已启动", configItem.getAppName());
                        ReflectionUtils.invokeMethod(method, bean,configItem);// , new Object[]{configItem});
                        log.info("任务{}已结束", configItem.getAppName());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }, expression);

            ScheduledFuture<?> future = scheduledTaskRegistrar.getScheduler().schedule(task.getRunnable(), task.getTrigger());
            cronTasks.put(configItem.getId(), task);
            scheduledFutures.put(configItem.getId(), future);

        }


    }
    //立刻生效，参考 http://mbcoder.com/dynamic-task-scheduling-with-spring/
    //https://blog.csdn.net/xht555/article/details/53121962/


    private volatile ScheduledTaskRegistrar scheduledTaskRegistrar;

    private final ConcurrentHashMap<Integer, CronTask> cronTasks = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(30));
        this.scheduledTaskRegistrar = scheduledTaskRegistrar;

    }


}
