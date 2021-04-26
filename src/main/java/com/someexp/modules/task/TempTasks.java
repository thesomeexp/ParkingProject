package com.someexp.modules.task;

import com.someexp.modules.user.service.TempService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author someexp
 * @Date 2021/4/16
 */
@Slf4j
@Component
public class TempTasks {

    @Resource
    private TempService tempService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The task is execute {}", dateFormat.format(new Date()));
        tempService.resetTempTask();
    }

}