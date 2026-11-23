package com.entity.view;

import com.entity.DiscussshengxianchaoshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 水果超市评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("discussshengxianchaoshi")
public class DiscussshengxianchaoshiView  extends DiscussshengxianchaoshiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussshengxianchaoshiView(){
	}
 
 	public DiscussshengxianchaoshiView(DiscussshengxianchaoshiEntity discussshengxianchaoshiEntity){
 	try {
			BeanUtils.copyProperties(this, discussshengxianchaoshiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
