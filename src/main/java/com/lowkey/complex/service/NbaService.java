package com.lowkey.complex.service;

import com.lowkey.complex.entity.Nba;

import java.util.List;

/**
 * @author yuanjifan
 * @description nba数据ES服务
 * @date 2021年11月24日 10:03
 */
public interface NbaService {
    List<Nba> findAll();
}
