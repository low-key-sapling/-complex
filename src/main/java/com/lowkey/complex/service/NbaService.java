package com.lowkey.complex.service;

import com.lowkey.complex.entity.Nba;

import java.util.List;

/**
 * @author yuanjifan
 * @description nba数据ES服务
 * @date 2021年11月24日 10:03
 */
public interface NbaService {
    /**
     * @return java.util.List<com.lowkey.complex.entity.Nba>
     * @author yuanjifan
     * @description 从ES获取全部数据
     * @date 2021/11/24 13:20
     */
    List<Nba> findAll();
}
