package com.example.elasticsearch.thread;

import com.example.elasticsearch.dao.EnvTestRepository;
import com.example.elasticsearch.entity.EnvTest;
import com.example.elasticsearch.util.SpringContextUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 线程
 * @Author qinweisi
 * @Date 2019/11/1 18:58
 **/
@Component
public class AsyncTask {

    /**
     * 数据处理
     *
     * @param data mqtt订阅到的数据
     */
    @Async("taskExecutor")
    public void dataDeal(String data) {
        List<EnvTest> lists = new ArrayList<>();
        // 字符串拆分
        String[] dataArr = data.split("\r\n");
        for (String i : dataArr) {
            // 截断
            String[] split = i.split(";");
            if (split.length >= 5) {
                EnvTest env = new EnvTest();
                // 手机ID
                env.setPhoneLogo(split[0]);
                // 卡号
                env.setCardId(split[1]);
                // 保留到分钟
                env.setTime(split[2].substring(0, split[2].length() - 3));
                // 电压
                env.setVoltage(split[3]);
                // 温度
                env.setTemperature(split[4]);
                // 湿度
                env.setHumidity(split[5]);
                env.setId(split[1] + split[0] + env.getTime().trim());
                lists.add(env);
            }
        }
        // 获取实例
        EnvTestRepository envTestDao = SpringContextUtils.getBean(EnvTestRepository.class);
        // 保存
        envTestDao.saveAll(lists);
    }
}
