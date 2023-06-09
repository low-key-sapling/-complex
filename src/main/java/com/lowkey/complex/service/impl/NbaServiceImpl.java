package com.lowkey.complex.service.impl;

import com.google.common.collect.Lists;
import com.lowkey.complex.entity.Nba;
import com.lowkey.complex.repository.NbaRepository;
import com.lowkey.complex.service.NbaService;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuanjifan
 * @description nba数据ES服务实现
 * @date 2021年11月24日 10:03
 */
@Service
public class NbaServiceImpl implements NbaService {
    //@Autowired
    NbaRepository nbaRepository;

    //@Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<Nba> findAll() {
        Iterable<Nba> nbaIterable = nbaRepository.findAll();
        return Lists.newArrayList(nbaIterable);
    }
}
