package com.controller;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.RiderEntity;
import com.entity.OrdersEntity;
import com.service.RiderService;
import com.service.OrdersService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.annotation.IgnoreAuth;

/**
 * 骑手
 * 后端接口
 */
@RestController
@RequestMapping("/rider")
@IgnoreAuth
public class RiderController {
    @Autowired
    private RiderService riderService;
    
    @Autowired
    private OrdersService ordersService;
    
    @Autowired
    private javax.sql.DataSource dataSource;

    /**
     * 初始化骑手表
     */
    @RequestMapping("/init")
    public R initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS rider (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50) NOT NULL UNIQUE," +
                "password VARCHAR(100) NOT NULL," +
                "phone VARCHAR(20) NOT NULL UNIQUE," +
                "name VARCHAR(50) NOT NULL," +
                "id_card VARCHAR(20) NOT NULL UNIQUE," +
                "avatar VARCHAR(255)," +
                "status INT DEFAULT 0," +
                "create_time DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "INDEX idx_username (username)," +
                "INDEX idx_phone (phone)," +
                "INDEX idx_status (status)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci";
        
        try (java.sql.Connection conn = dataSource.getConnection();
             java.sql.Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            return R.ok("骑手表初始化成功");
        } catch (Exception e) {
            return R.error("初始化失败: " + e.getMessage());
        }
    }

    /**
     * 初始化订单表字段
     */
    @RequestMapping("/init/orders")
    public R initOrdersTable() {
        String sql = "ALTER TABLE orders ADD COLUMN rider_id BIGINT;";
        String sql2 = "ALTER TABLE orders ADD COLUMN rider_name VARCHAR(50);";
        
        try (java.sql.Connection conn = dataSource.getConnection();
             java.sql.Statement stmt = conn.createStatement()) {
            try {
                stmt.execute(sql);
            } catch (Exception e) {
                // 如果字段已存在，忽略错误
            }
            try {
                stmt.execute(sql2);
            } catch (Exception e) {
                // 如果字段已存在，忽略错误
            }
            return R.ok("订单表字段初始化成功");
        } catch (Exception e) {
            return R.error("初始化失败: " + e.getMessage());
        }
    }

    /**
     * 骑手注册
     */
    @IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody RiderEntity rider) {
        // 检查用户名是否已存在
        if (riderService.existsByUsername(rider.getUsername())) {
            return R.error("用户名已存在");
        }
        
        // 检查手机号是否已存在
        if (riderService.existsByPhone(rider.getPhone())) {
            return R.error("手机号已存在");
        }
        
        // 检查身份证号是否已存在
        if (riderService.existsByIdCard(rider.getIdCard())) {
            return R.error("身份证号已存在");
        }
        
        // 设置默认状态为禁用，需要管理员审核
        rider.setStatus(0);
        rider.setCreateTime(new java.util.Date());
        
        // 注册骑手
        riderService.insert(rider);
        return R.ok("注册成功，请等待管理员审核");
    }

    /**
     * 骑手登录
     */
    @IgnoreAuth
    @RequestMapping("/login")
    public R login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        
        // 调用服务层进行登录验证
        RiderEntity rider = riderService.login(username, password);
        if (rider == null) {
            return R.error("用户名或密码错误，或账号未审核通过");
        }
        
        // 生成token并返回
        String token = riderService.generateToken(rider.getId());
        return R.ok().put("token", token).put("rider", rider);
    }

    /**
     * 根据token获取骑手信息（骑手端）
     */
    @RequestMapping("/info")
    public R info(@RequestParam String token) {
        RiderEntity rider = riderService.getRiderByToken(token);
        if (rider == null) {
            return R.error("token无效");
        }
        return R.ok().put("data", rider);
    }

    /**
     * 根据ID获取骑手信息（管理后台使用）
     */
    @RequestMapping("/info/{id}")
    public R infoById(@PathVariable Long id) {
        RiderEntity rider = riderService.selectById(id);
        if (rider == null) {
            return R.error("骑手不存在");
        }
        return R.ok().put("data", rider);
    }

    /**
     * 新增骑手
     */
    @RequestMapping("/save")
    public R save(@RequestBody RiderEntity rider) {
        rider.setStatus(1); // 默认启用
        rider.setCreateTime(new java.util.Date());
        riderService.insert(rider);
        return R.ok();
    }

    /**
     * 修改骑手信息
     */
    @RequestMapping("/update")
    public R update(@RequestBody RiderEntity rider) {
        riderService.updateById(rider);
        return R.ok();
    }

    /**
     * 待接单列表
     */
    @RequestMapping("/order/list/waiting")
    public R waitingOrderList(@RequestParam Map<String, Object> params) {
        PageUtils page = ordersService.queryPage(params, new EntityWrapper<OrdersEntity>()
                .eq("status", "已支付")
                .isNull("rider_id"));
        return R.ok().put("data", page);
    }

    /**
     * 待配送列表
     */
    @RequestMapping("/order/list/delivering")
    public R deliveringOrderList(@RequestParam Map<String, Object> params, @RequestParam Long riderId) {
        PageUtils page = ordersService.queryPage(params, new EntityWrapper<OrdersEntity>()
                .eq("status", "待配送")
                .eq("rider_id", riderId));
        return R.ok().put("data", page);
    }

    /**
     * 已完成列表
     */
    @RequestMapping("/order/list/completed")
    public R completedOrderList(@RequestParam Map<String, Object> params, @RequestParam Long riderId) {
        PageUtils page = ordersService.queryPage(params, new EntityWrapper<OrdersEntity>()
                .eq("status", "已完成")
                .eq("rider_id", riderId));
        return R.ok().put("data", page);
    }

    /**
     * 接单
     */
    @RequestMapping("/order/accept")
    public R acceptOrder(@RequestBody Map<String, Object> params) {
        Long orderId = Long.parseLong(params.get("orderId").toString());
        Long riderId = Long.parseLong(params.get("riderId").toString());
        String riderName = params.get("riderName").toString();
        
        OrdersEntity order = ordersService.selectById(orderId);
        if (order == null) {
            return R.error("订单不存在");
        }
        
        if (!"已支付".equals(order.getStatus())) {
            return R.error("订单已被接单或状态不正确");
        }
        
        if (order.getRiderId() != null) {
            return R.error("订单已被其他骑手接单");
        }
        
        order.setRiderId(riderId);
        order.setRiderName(riderName);
        order.setStatus("待配送");
        ordersService.updateById(order);
        
        return R.ok("接单成功");
    }

    /**
     * 更新配送状态
     */
    @RequestMapping("/order/update/status")
    public R updateOrderStatus(@RequestBody Map<String, Object> params) {
        Long orderId = Long.parseLong(params.get("orderId").toString());
        String status = params.get("status").toString();
        
        OrdersEntity order = ordersService.selectById(orderId);
        if (order == null) {
            return R.error("订单不存在");
        }
        
        order.setStatus(status);
        ordersService.updateById(order);
        
        return R.ok("状态更新成功");
    }

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, RiderEntity rider) {
        // 手动构建参数
        Map<String, Object> queryParams = new HashMap<>(params);
        
        PageUtils page = riderService.queryPage(queryParams);
        return R.ok().put("data", page);
    }

    /**
     * 启用/禁用骑手
     */
    @RequestMapping("/status")
    public R updateStatus(@RequestBody Map<String, Object> params) {
        Long id = Long.parseLong(params.get("id").toString());
        Integer status = Integer.parseInt(params.get("status").toString());
        
        RiderEntity rider = riderService.selectById(id);
        if (rider == null) {
            return R.error("骑手不存在");
        }
        
        rider.setStatus(status);
        riderService.updateById(rider);
        
        return R.ok("状态更新成功");
    }

    /**
     * 删除骑手
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        riderService.deleteBatchIds(java.util.Arrays.asList(ids));
        return R.ok();
    }
}
