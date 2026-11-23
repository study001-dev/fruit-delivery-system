package com.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.OrdersEntity;
import com.entity.RiderEntity;
import com.service.OrdersService;
import com.service.RiderService;
import com.utils.R;

/**
 * 订单分派骑手
 * 后端接口
 */
@RestController
@RequestMapping("/order/rider")
public class OrderRiderController {
    @Autowired
    private OrdersService ordersService;
    
    @Autowired
    private RiderService riderService;

    /**
     * 分派骑手
     */
    @RequestMapping("/assign")
    public R assignRider(@RequestBody Map<String, Object> params) {
        Long orderId = Long.parseLong(params.get("orderId").toString());
        Long riderId = Long.parseLong(params.get("riderId").toString());
        
        // 检查订单是否存在
        OrdersEntity order = ordersService.selectById(orderId);
        if (order == null) {
            return R.error("订单不存在");
        }
        
        // 检查骑手是否存在
        RiderEntity rider = riderService.selectById(riderId);
        if (rider == null) {
            return R.error("骑手不存在");
        }
        
        // 检查骑手状态是否启用
        if (rider.getStatus() != 1) {
            return R.error("骑手未启用");
        }
        
        // 更新订单信息
        order.setRiderId(riderId);
        order.setRiderName(rider.getName());
        order.setStatus("待配送");
        ordersService.updateById(order);
        
        return R.ok("分派骑手成功");
    }

    /**
     * 取消分派
     */
    @RequestMapping("/cancel")
    public R cancelAssign(@RequestBody Map<String, Object> params) {
        Long orderId = Long.parseLong(params.get("orderId").toString());
        
        // 检查订单是否存在
        OrdersEntity order = ordersService.selectById(orderId);
        if (order == null) {
            return R.error("订单不存在");
        }
        
        // 更新订单信息
        order.setRiderId(null);
        order.setRiderName(null);
        order.setStatus("待接单");
        ordersService.updateById(order);
        
        return R.ok("取消分派成功");
    }
}
