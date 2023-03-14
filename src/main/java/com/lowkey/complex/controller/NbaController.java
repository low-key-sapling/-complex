package com.lowkey.complex.controller;

import com.lowkey.complex.entity.Nba;
import com.lowkey.complex.service.NbaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuanjifan
 * @description ES nba数据控制器
 * @date 2021年11月18日 9:56
 */
@Slf4j
@RestController
public class NbaController {
    //@Autowired
    private NbaService nbaService;

    /**
     * @return java.lang.String
     * @author yuanjifan
     * @description 从ES获取Nba数据
     * @date 2021/11/18 11:04
     */
    @RequestMapping("/nba/list")
    public List<Nba> getNbaAll() {
        return nbaService.findAll();
    }
}
