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


import com.dao.DiscussshengxianchaoshiDao;
import com.entity.DiscussshengxianchaoshiEntity;
import com.service.DiscussshengxianchaoshiService;
import com.entity.vo.DiscussshengxianchaoshiVO;
import com.entity.view.DiscussshengxianchaoshiView;

@Service("discussshengxianchaoshiService")
public class DiscussshengxianchaoshiServiceImpl extends ServiceImpl<DiscussshengxianchaoshiDao, DiscussshengxianchaoshiEntity> implements DiscussshengxianchaoshiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussshengxianchaoshiEntity> page = this.selectPage(
                new Query<DiscussshengxianchaoshiEntity>(params).getPage(),
                new EntityWrapper<DiscussshengxianchaoshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussshengxianchaoshiEntity> wrapper) {
		  Page<DiscussshengxianchaoshiView> page =new Query<DiscussshengxianchaoshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DiscussshengxianchaoshiVO> selectListVO(Wrapper<DiscussshengxianchaoshiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussshengxianchaoshiVO selectVO(Wrapper<DiscussshengxianchaoshiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussshengxianchaoshiView> selectListView(Wrapper<DiscussshengxianchaoshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussshengxianchaoshiView selectView(Wrapper<DiscussshengxianchaoshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
