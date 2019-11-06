package com.example.elasticsearch.dao;

import com.example.elasticsearch.entity.EnvTest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Description Elasticsearch接口
 * @Author qinweisi
 * @Date 2019/10/10 17:16
 **/
@Component
public interface EnvTestRepository extends ElasticsearchRepository<EnvTest, String> {

}

