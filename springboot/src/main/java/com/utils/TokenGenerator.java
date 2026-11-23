package com.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Token生成器
 */
public class TokenGenerator {
    
    // 令牌有效期（毫秒）
    private static final long TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 1000;
    
    // 令牌存储
    private static Map<String, TokenInfo> tokenMap = new HashMap<>();
    
    /**
     * 生成token
     */
    public static String generateToken(Long userId, String type) {
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(userId);
        tokenInfo.setType(type);
        tokenInfo.setCreateTime(new Date());
        tokenMap.put(token, tokenInfo);
        return token;
    }
    
    /**
     * 从token中获取用户ID
     */
    public static Long getUserIdFromToken(String token, String type) {
        TokenInfo tokenInfo = tokenMap.get(token);
        if (tokenInfo == null) {
            return null;
        }
        
        // 检查token类型
        if (!type.equals(tokenInfo.getType())) {
            return null;
        }
        
        // 检查token是否过期
        if (new Date().getTime() - tokenInfo.getCreateTime().getTime() > TOKEN_EXPIRE_TIME) {
            tokenMap.remove(token);
            return null;
        }
        
        return tokenInfo.getUserId();
    }
    
    /**
     * 刷新token
     */
    public static void refreshToken(String token) {
        TokenInfo tokenInfo = tokenMap.get(token);
        if (tokenInfo != null) {
            tokenInfo.setCreateTime(new Date());
        }
    }
    
    /**
     * 移除token
     */
    public static void removeToken(String token) {
        tokenMap.remove(token);
    }
    
    /**
     * Token信息类
     */
    private static class TokenInfo {
        private Long userId;
        private String type;
        private Date createTime;
        
        public Long getUserId() {
            return userId;
        }
        public void setUserId(Long userId) {
            this.userId = userId;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public Date getCreateTime() {
            return createTime;
        }
        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
    }
}
