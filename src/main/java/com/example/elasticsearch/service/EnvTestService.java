package com.example.elasticsearch.service;

import com.example.elasticsearch.dao.EnvTestRepository;
import com.example.elasticsearch.entity.EnvTest;
import com.example.elasticsearch.util.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * @Description 数据接口
 * @Author qinweisi
 * @Date 2019/10/10 10:01
 **/
@Service
public class EnvTestService  {

    @Autowired
    private EnvTestRepository envTestDao;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 根据条件查询温湿度数据
     *
     * @param start  开始时间
     * @param end    结束时间
     * @param cardId 卡号数组
     * @return
     * @throws ParseException
     */
    public Map<String,Object> getDiagram(String start, String end, String[] cardId) throws ParseException {
        Map<String,Object> result = new HashMap<>();
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 索引
        queryBuilder.withIndices("environment");
        // 表名
        queryBuilder.withTypes("env_test");
        // 手机编号
        queryBuilder.withQuery(QueryBuilders.matchQuery("phoneLogo", ""));
        Date date = null;
        // 开始时间
        if (!StringUtils.isEmpty(start)) {
            // 大于等于
            queryBuilder.withQuery(QueryBuilders.rangeQuery("fullTime").gte(date.getTime()));
        }
        // 结束时间
        if (!StringUtils.isEmpty(end)) {
            // 小于等于
            queryBuilder.withQuery(QueryBuilders.rangeQuery("fullTime").lte(date.getTime()));
        }
        // 卡号
        queryBuilder.withQuery(QueryBuilders.matchQuery("cardId", cardId));
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        for (String cardIds : cardId) {
            // 满足其一即可
            boolQuery.should(QueryBuilders.termQuery("cardId", cardIds));
        }
        queryBuilder.withQuery(boolQuery);
        // 根据条件查询的总数
        long count = elasticsearchTemplate.count(queryBuilder.build());
        // 若查询总条目数 > 0,根据条目数分页查询(NativeSearchQueryBuilder自动分页,只能这么写)
        if (count > 0) {
            queryBuilder.withPageable(PageRequest.of(0, (int) count));
            // 搜索，获取结果
            Page<EnvTest> items = envTestDao.search(queryBuilder.build());
            result.put("data",items.getContent());
        }
        return result;
    }

}
