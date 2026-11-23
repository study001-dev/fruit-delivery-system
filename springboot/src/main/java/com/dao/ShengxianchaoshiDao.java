package com.dao;

import com.entity.ShengxianchaoshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ShengxianchaoshiVO;
import com.entity.view.ShengxianchaoshiView;


/**
 * 水果超市
 * 
 */
public interface ShengxianchaoshiDao extends BaseMapper<ShengxianchaoshiEntity> {
	
	List<ShengxianchaoshiVO> selectListVO(@Param("ew") Wrapper<ShengxianchaoshiEntity> wrapper);
	
	ShengxianchaoshiVO selectVO(@Param("ew") Wrapper<ShengxianchaoshiEntity> wrapper);
	
	List<ShengxianchaoshiView> selectListView(@Param("ew") Wrapper<ShengxianchaoshiEntity> wrapper);

	List<ShengxianchaoshiView> selectListView(Pagination page,@Param("ew") Wrapper<ShengxianchaoshiEntity> wrapper);
	
	ShengxianchaoshiView selectView(@Param("ew") Wrapper<ShengxianchaoshiEntity> wrapper);
	

}
