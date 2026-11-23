package com.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.RiderEntity;
import com.entity.view.RiderView;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import java.util.List;
import java.util.Map;

/**
 * 骑手
 * 数据访问层
 */
public interface RiderMapper extends BaseMapper<RiderEntity> {
    
    /**
     * 查询骑手视图
     */
    List<RiderView> selectListView(Pagination page, Map<String, Object> params);
    
    /**
     * 查询骑手视图
     */
    RiderView selectView(Map<String, Object> params);
}
