package com.lowkey.complex.repository;

import com.lowkey.complex.entity.Nba;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NbaRepository extends ElasticsearchRepository<Nba, Integer> {

}
