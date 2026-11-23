package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DiscussshengxianchaoshiEntity;
import com.entity.view.DiscussshengxianchaoshiView;

import com.service.DiscussshengxianchaoshiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 水果超市评论表
 * 后端接口
 */
@RestController
@RequestMapping("/discussshengxianchaoshi")
public class DiscussshengxianchaoshiController {
    @Autowired
    private DiscussshengxianchaoshiService discussshengxianchaoshiService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussshengxianchaoshiEntity discussshengxianchaoshi,
		HttpServletRequest request){
        EntityWrapper<DiscussshengxianchaoshiEntity> ew = new EntityWrapper<DiscussshengxianchaoshiEntity>();

		PageUtils page = discussshengxianchaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshengxianchaoshi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussshengxianchaoshiEntity discussshengxianchaoshi, 
		HttpServletRequest request){
        EntityWrapper<DiscussshengxianchaoshiEntity> ew = new EntityWrapper<DiscussshengxianchaoshiEntity>();

		PageUtils page = discussshengxianchaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshengxianchaoshi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussshengxianchaoshiEntity discussshengxianchaoshi){
       	EntityWrapper<DiscussshengxianchaoshiEntity> ew = new EntityWrapper<DiscussshengxianchaoshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussshengxianchaoshi, "discussshengxianchaoshi")); 
        return R.ok().put("data", discussshengxianchaoshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussshengxianchaoshiEntity discussshengxianchaoshi){
        EntityWrapper< DiscussshengxianchaoshiEntity> ew = new EntityWrapper< DiscussshengxianchaoshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussshengxianchaoshi, "discussshengxianchaoshi")); 
		DiscussshengxianchaoshiView discussshengxianchaoshiView =  discussshengxianchaoshiService.selectView(ew);
		return R.ok("查询水果超市评论表成功").put("data", discussshengxianchaoshiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussshengxianchaoshiEntity discussshengxianchaoshi = discussshengxianchaoshiService.selectById(id);
        return R.ok().put("data", discussshengxianchaoshi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussshengxianchaoshiEntity discussshengxianchaoshi = discussshengxianchaoshiService.selectById(id);
        return R.ok().put("data", discussshengxianchaoshi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussshengxianchaoshiEntity discussshengxianchaoshi, HttpServletRequest request){
    	discussshengxianchaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussshengxianchaoshi);
        discussshengxianchaoshiService.insert(discussshengxianchaoshi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussshengxianchaoshiEntity discussshengxianchaoshi, HttpServletRequest request){
    	discussshengxianchaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussshengxianchaoshi);
        discussshengxianchaoshiService.insert(discussshengxianchaoshi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DiscussshengxianchaoshiEntity discussshengxianchaoshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussshengxianchaoshi);
        discussshengxianchaoshiService.updateById(discussshengxianchaoshi);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussshengxianchaoshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<DiscussshengxianchaoshiEntity> wrapper = new EntityWrapper<DiscussshengxianchaoshiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = discussshengxianchaoshiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	









}
