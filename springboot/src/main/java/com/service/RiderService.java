package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.RiderEntity;
import com.entity.view.RiderView;
import com.utils.PageUtils;
import java.util.Map;

/**
 * 骑手
 * 服务层接口
 */
public interface RiderService extends IService<RiderEntity> {
    
    /**
     * 分页查询
     */
    PageUtils queryPage(Map<String, Object> params);
    
    /**
     * 骑手登录
     */
    RiderEntity login(String username, String password);
    
    /**
     * 生成token
     */
    String generateToken(Long riderId);
    
    /**
     * 根据token获取骑手信息
     */
    RiderEntity getRiderByToken(String token);
    
    /**
     * 检查用户名是否已存在
     */
    boolean existsByUsername(String username);
    
    /**
     * 检查手机号是否已存在
     */
    boolean existsByPhone(String phone);
    
    /**
     * 检查身份证号是否已存在
     */
    boolean existsByIdCard(String idCard);
    
    /**
     * 获取骑手视图
     */
    RiderView selectView(RiderEntity rider);
}
