package com.someexp.modules.task;

import com.someexp.modules.user.service.TempService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author someexp
 * @Date 2021/4/16
 */
@Component
public class TempTasks {

    @Resource
    private TempService tempService;

    private static final Logger log = LoggerFactory.getLogger(TempTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The task is execute {}", dateFormat.format(new Date()));
        tempService.resetTempTask();
    }

}