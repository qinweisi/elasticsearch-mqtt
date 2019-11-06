package com.example.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description es实体
 * indexName索引名称 可以理解为数据库名 必须为小写 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
 * type类型 可以理解为表名
 * @Author qinweisi
 * @Date 2019/8/28 15:22
 **/
@Document(indexName = "environment", type = "env_test")
@Data
public class EnvTest implements Serializable {

    @Id
    private String id;// 接收时间戳
    private String cardId;// 卡号
    private String phoneLogo;// 手机
    private String humidity;// 湿度
    private String temperature;// 温度
    private String voltage;// 电压
    private String time;// 时间（yyyy-mm-dd HH:mm）
    private Long fullTime;// 完整的时间（yyyy-mm-dd HH:mm:ss）
    private Date createTime = new Date();// 上传时间

}
