package com.dao;

import com.entity.DiscussshengxianchaoshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussshengxianchaoshiVO;
import com.entity.view.DiscussshengxianchaoshiView;


/**
 * 水果超市评论表
 * 
 */
public interface DiscussshengxianchaoshiDao extends BaseMapper<DiscussshengxianchaoshiEntity> {
	
	List<DiscussshengxianchaoshiVO> selectListVO(@Param("ew") Wrapper<DiscussshengxianchaoshiEntity> wrapper);
	
	DiscussshengxianchaoshiVO selectVO(@Param("ew") Wrapper<DiscussshengxianchaoshiEntity> wrapper);
	
	List<DiscussshengxianchaoshiView> selectListView(@Param("ew") Wrapper<DiscussshengxianchaoshiEntity> wrapper);

	List<DiscussshengxianchaoshiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussshengxianchaoshiEntity> wrapper);
	
	DiscussshengxianchaoshiView selectView(@Param("ew") Wrapper<DiscussshengxianchaoshiEntity> wrapper);
	

}
