package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShengxianchaoshiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ShengxianchaoshiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ShengxianchaoshiView;


/**
 * 水果超市
 *
 */
public interface ShengxianchaoshiService extends IService<ShengxianchaoshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShengxianchaoshiVO> selectListVO(Wrapper<ShengxianchaoshiEntity> wrapper);
   	
   	ShengxianchaoshiVO selectVO(@Param("ew") Wrapper<ShengxianchaoshiEntity> wrapper);
   	
   	List<ShengxianchaoshiView> selectListView(Wrapper<ShengxianchaoshiEntity> wrapper);
   	
   	ShengxianchaoshiView selectView(@Param("ew") Wrapper<ShengxianchaoshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShengxianchaoshiEntity> wrapper);
   	

}

