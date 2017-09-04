package com.swagger.web;

import com.swagger.entity.Seckill;
import com.swagger.service.SeckillService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wuy on 2017/9/4.
 */
@RestController
@RequestMapping("/seckill") // url:/模块/资源/{id}/细分 /seckill/list
public class SeckillController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @ApiOperation("获取商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public  List<Seckill> list() {
        // 获取列表页
        List<Seckill> list = seckillService.getSeckillList();
        return list;
    }

    @ApiOperation("获取商品详情信息")
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public Seckill detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return new Seckill();
        }
        Seckill seckill = seckillService.getById(seckillId);
        return seckill;
    }
}