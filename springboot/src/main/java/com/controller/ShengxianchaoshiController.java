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
import com.entity.OrdersEntity;
import com.service.OrdersService;

import com.entity.ShengxianchaoshiEntity;
import com.entity.view.ShengxianchaoshiView;

import com.service.ShengxianchaoshiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 水果超市
 * 后端接口
 */
@RestController
@RequestMapping("/shengxianchaoshi")
public class ShengxianchaoshiController {
    @Autowired
    private ShengxianchaoshiService shengxianchaoshiService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private OrdersService ordersService;
    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShengxianchaoshiEntity shengxianchaoshi,
		HttpServletRequest request){
        EntityWrapper<ShengxianchaoshiEntity> ew = new EntityWrapper<ShengxianchaoshiEntity>();

		PageUtils page = shengxianchaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shengxianchaoshi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShengxianchaoshiEntity shengxianchaoshi, 
		HttpServletRequest request){
        EntityWrapper<ShengxianchaoshiEntity> ew = new EntityWrapper<ShengxianchaoshiEntity>();

		PageUtils page = shengxianchaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shengxianchaoshi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShengxianchaoshiEntity shengxianchaoshi){
       	EntityWrapper<ShengxianchaoshiEntity> ew = new EntityWrapper<ShengxianchaoshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shengxianchaoshi, "shengxianchaoshi")); 
        return R.ok().put("data", shengxianchaoshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShengxianchaoshiEntity shengxianchaoshi){
        EntityWrapper< ShengxianchaoshiEntity> ew = new EntityWrapper< ShengxianchaoshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shengxianchaoshi, "shengxianchaoshi")); 
		ShengxianchaoshiView shengxianchaoshiView =  shengxianchaoshiService.selectView(ew);
		return R.ok("查询水果超市成功").put("data", shengxianchaoshiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShengxianchaoshiEntity shengxianchaoshi = shengxianchaoshiService.selectById(id);
		shengxianchaoshi.setClicknum(shengxianchaoshi.getClicknum()+1);
		shengxianchaoshi.setClicktime(new Date());
		shengxianchaoshiService.updateById(shengxianchaoshi);
        return R.ok().put("data", shengxianchaoshi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShengxianchaoshiEntity shengxianchaoshi = shengxianchaoshiService.selectById(id);
		shengxianchaoshi.setClicknum(shengxianchaoshi.getClicknum()+1);
		shengxianchaoshi.setClicktime(new Date());
		shengxianchaoshiService.updateById(shengxianchaoshi);
        return R.ok().put("data", shengxianchaoshi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShengxianchaoshiEntity shengxianchaoshi, HttpServletRequest request){
    	shengxianchaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shengxianchaoshi);
        shengxianchaoshiService.insert(shengxianchaoshi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShengxianchaoshiEntity shengxianchaoshi, HttpServletRequest request){
    	shengxianchaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shengxianchaoshi);
        shengxianchaoshiService.insert(shengxianchaoshi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ShengxianchaoshiEntity shengxianchaoshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shengxianchaoshi);
        shengxianchaoshiService.updateById(shengxianchaoshi);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shengxianchaoshiService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<ShengxianchaoshiEntity> wrapper = new EntityWrapper<ShengxianchaoshiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = shengxianchaoshiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ShengxianchaoshiEntity shengxianchaoshi, HttpServletRequest request,String pre){
        EntityWrapper<ShengxianchaoshiEntity> ew = new EntityWrapper<ShengxianchaoshiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicknum");
        params.put("order", "desc");
		PageUtils page = shengxianchaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shengxianchaoshi), params), params));
        return R.ok().put("data", page);
    }


        /**
     * 协同算法（按用户购买推荐）
     */
    @IgnoreAuth
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params, ShengxianchaoshiEntity shengxianchaoshi, HttpServletRequest request) {
        List<ShengxianchaoshiEntity> shengxianchaoshiList = new ArrayList<ShengxianchaoshiEntity>();
        Integer limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        
        Object userIdObj = request.getSession().getAttribute("userId");
        if (userIdObj != null) {
            String userId = userIdObj.toString();
            String goodtypeColumn = "shangpinfenlei";
            try {
                List<OrdersEntity> orders = ordersService.selectList(new EntityWrapper<OrdersEntity>()
                        .eq("userid", userId)
                        .eq("tablename", "shengxianchaoshi")
                        .orderBy("addtime", false));
                
                List<OrdersEntity> ordersDist = new ArrayList<OrdersEntity>();
                for (OrdersEntity o1 : orders) {
                    boolean addFlag = true;
                    for (OrdersEntity o2 : ordersDist) {
                        if ((o1.getGoodid() != null && o1.getGoodid().equals(o2.getGoodid())) 
                                || (o1.getGoodtype() != null && o1.getGoodtype().equals(o2.getGoodtype()))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        ordersDist.add(o1);
                    }
                }
                
                if (ordersDist != null && ordersDist.size() > 0) {
                    for (OrdersEntity o : ordersDist) {
                        if (o.getGoodtype() != null) {
                            List<ShengxianchaoshiEntity> tempList = shengxianchaoshiService.selectList(
                                    new EntityWrapper<ShengxianchaoshiEntity>().eq(goodtypeColumn, o.getGoodtype()));
                            if (tempList != null) {
                                shengxianchaoshiList.addAll(tempList);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        try {
            EntityWrapper<ShengxianchaoshiEntity> ew = new EntityWrapper<ShengxianchaoshiEntity>();
            params.put("sort", "id");
            params.put("order", "desc");
            PageUtils page = shengxianchaoshiService.queryPage(params, 
                    MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shengxianchaoshi), params), params));
            
            List<ShengxianchaoshiEntity> pageList = page != null ? (List<ShengxianchaoshiEntity>) page.getList() : new ArrayList<>();
            if (pageList == null) {
                pageList = new ArrayList<>();
            }
            
            if (shengxianchaoshiList.size() < limit) {
                int toAddNum = (limit - shengxianchaoshiList.size()) <= pageList.size() 
                        ? (limit - shengxianchaoshiList.size()) : pageList.size();
                for (ShengxianchaoshiEntity o1 : pageList) {
                    boolean addFlag = true;
                    for (ShengxianchaoshiEntity o2 : shengxianchaoshiList) {
                        if (o1.getId() != null && o2.getId() != null 
                                && o1.getId().intValue() == o2.getId().intValue()) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        shengxianchaoshiList.add(o1);
                        if (--toAddNum == 0) {
                            break;
                        }
                    }
                }
            } else if (shengxianchaoshiList.size() > limit) {
                shengxianchaoshiList = shengxianchaoshiList.subList(0, limit);
            }
            
            if (shengxianchaoshiList.isEmpty()) {
                shengxianchaoshiList = pageList;
            }
            
            if (page != null) {
                page.setList(shengxianchaoshiList);
            } else {
                page = new PageUtils(new ArrayList<>(), 0, limit, 0);
                page.setList(shengxianchaoshiList);
            }
            
            return R.ok().put("data", page);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(500, "服务器内部错误: " + e.getMessage());
        }
    }







}
