package com.entity.vo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.RiderEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * 骑手
 * 前端VO类
 */
@TableName("rider")
public class RiderVO extends RiderEntity {

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        super.setCreateTime(createTime);
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
}
