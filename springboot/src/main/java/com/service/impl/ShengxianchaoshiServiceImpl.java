package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.ShengxianchaoshiDao;
import com.entity.ShengxianchaoshiEntity;
import com.service.ShengxianchaoshiService;
import com.entity.vo.ShengxianchaoshiVO;
import com.entity.view.ShengxianchaoshiView;

@Service("shengxianchaoshiService")
public class ShengxianchaoshiServiceImpl extends ServiceImpl<ShengxianchaoshiDao, ShengxianchaoshiEntity> implements ShengxianchaoshiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShengxianchaoshiEntity> page = this.selectPage(
                new Query<ShengxianchaoshiEntity>(params).getPage(),
                new EntityWrapper<ShengxianchaoshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ShengxianchaoshiEntity> wrapper) {
		  Page<ShengxianchaoshiView> page =new Query<ShengxianchaoshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<ShengxianchaoshiVO> selectListVO(Wrapper<ShengxianchaoshiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ShengxianchaoshiVO selectVO(Wrapper<ShengxianchaoshiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ShengxianchaoshiView> selectListView(Wrapper<ShengxianchaoshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShengxianchaoshiView selectView(Wrapper<ShengxianchaoshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
