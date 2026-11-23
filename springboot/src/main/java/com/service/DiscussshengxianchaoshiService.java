package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussshengxianchaoshiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussshengxianchaoshiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussshengxianchaoshiView;


/**
 * 水果超市评论表
 *
 */
public interface DiscussshengxianchaoshiService extends IService<DiscussshengxianchaoshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussshengxianchaoshiVO> selectListVO(Wrapper<DiscussshengxianchaoshiEntity> wrapper);
   	
   	DiscussshengxianchaoshiVO selectVO(@Param("ew") Wrapper<DiscussshengxianchaoshiEntity> wrapper);
   	
   	List<DiscussshengxianchaoshiView> selectListView(Wrapper<DiscussshengxianchaoshiEntity> wrapper);
   	
   	DiscussshengxianchaoshiView selectView(@Param("ew") Wrapper<DiscussshengxianchaoshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussshengxianchaoshiEntity> wrapper);
   	

}

