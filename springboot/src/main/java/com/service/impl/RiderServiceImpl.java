package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entity.RiderEntity;
import com.entity.view.RiderView;
import com.mapper.RiderMapper;
import com.service.RiderService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.TokenGenerator;
import java.util.Map;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * 骑手
 * 服务实现类
 */
@Service("riderService")
public class RiderServiceImpl extends ServiceImpl<RiderMapper, RiderEntity> implements RiderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        int page = 1;
        int limit = 10;

        if (params.get("page") != null) {
            page = Integer.parseInt(params.get("page").toString());
        }
        if (params.get("limit") != null) {
            limit = Integer.parseInt(params.get("limit").toString());
        }

        EntityWrapper<RiderEntity> ew = new EntityWrapper<>();
        if (params.get("username") != null && !params.get("username").toString().isEmpty()) {
            ew.like("username", params.get("username").toString());
        }
        if (params.get("name") != null && !params.get("name").toString().isEmpty()) {
            ew.like("name", params.get("name").toString());
        }
        if (params.get("phone") != null && !params.get("phone").toString().isEmpty()) {
            ew.like("phone", params.get("phone").toString());
        }

        String sort = params.get("sort") != null ? params.get("sort").toString() : "id";
        boolean isAsc = "asc".equalsIgnoreCase(params.get("order") != null ? params.get("order").toString() : "desc") ? false : true;
        if (!isAsc) {
            ew.orderBy(sort, false);
        } else {
            ew.orderBy(sort, true);
        }

        Page<RiderEntity> pageInfo = this.selectPage(
                new Page<>(page, limit),
                ew
        );
        return new PageUtils(pageInfo);
    }

    @Override
    public RiderEntity login(String username, String password) {
        RiderEntity rider = this.selectOne(new EntityWrapper<RiderEntity>()
                .eq("username", username)
                .eq("password", password)
                .eq("status", 1));
        return rider;
    }

    @Override
    public String generateToken(Long riderId) {
        return TokenGenerator.generateToken(riderId, "rider");
    }

    @Override
    public RiderEntity getRiderByToken(String token) {
        // 解析token获取riderId
        Long riderId = TokenGenerator.getUserIdFromToken(token, "rider");
        if (riderId == null) {
            return null;
        }
        return this.selectById(riderId);
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.selectCount(new EntityWrapper<RiderEntity>()
                .eq("username", username)) > 0;
    }

    @Override
    public boolean existsByPhone(String phone) {
        return this.selectCount(new EntityWrapper<RiderEntity>()
                .eq("phone", phone)) > 0;
    }

    @Override
    public boolean existsByIdCard(String idCard) {
        return this.selectCount(new EntityWrapper<RiderEntity>()
                .eq("id_card", idCard)) > 0;
    }

    @Override
    public RiderView selectView(RiderEntity rider) {
        RiderView view = new RiderView();
        view.setId(rider.getId());
        view.setUsername(rider.getUsername());
        view.setPassword(rider.getPassword());
        view.setPhone(rider.getPhone());
        view.setName(rider.getName());
        view.setIdCard(rider.getIdCard());
        view.setAvatar(rider.getAvatar());
        view.setStatus(rider.getStatus());
        view.setCreateTime(rider.getCreateTime());
        return view;
    }
}
