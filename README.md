# 水果即时配送系统（Fruit Instant Delivery System）

一个面向「线上下单 + 骑手配送」场景的 O2O 生鲜水果即时配送系统。项目包含**用户端（uni-app 多端）**、**骑手端（uni-app）**、**管理后台（Vue2 + ElementUI）**三个客户端与一个 **SpringBoot** 后端服务，覆盖从商品浏览、下单支付、骑手接单配送到售后评价的完整业务闭环。

> 本仓库为个人求职展示项目，演示数据均已脱敏处理。

---

## 项目截图

> 占位：运行后可将界面截图放在此处，例如首页、购物车、订单列表、骑手接单页、后台数据看板。

---

## 功能总览

### 用户端（uniapp_front）

- 注册 / 登录 / 忘记密码（密保找回）
- 商品分类浏览、商品搜索、销量与价格排序、商品收藏、点赞踩
- 购物车（单限 / 库存校验）、收货地址管理
- 下单结算：**余额支付、积分兑换、模拟支付宝支付**，余额在线充值
- 订单全流程：待支付 / 已支付 / 已发货 / 已完成 / 退款 / 取消，支持订单催单、退款申请
- 商品评价（评论 / 回复）、商品资讯浏览、在线客服（用户-管理员即时聊天）
- 个人中心：资料修改、余额与积分、收藏夹、订单管理

### 骑手端（uniapp_front，独立分包 pages-rider）

- 骑手注册 / 登录（含身份信息审核状态）
- 接单大厅：查看待配送订单、抢单接单
- 订单配送：配送状态流转（已接单 → 配送中 → 已送达）
- 我的配送单统计、个人资料编辑

### 管理后台（vue_admin）

- 数据看板（首页统计）
- 商品管理：商品 CRUD、上下架、商品分类管理
- 订单管理：全量订单查看、发货、退款处理、物流信息维护
- 用户管理：用户 / 骑手 / 管理员账号管理，骑手审核
- 内容管理：商品资讯、轮播图、系统配置
- 客服管理：即时回复用户咨询
- 评价管理、收藏统计

---

## 技术栈

| 模块 | 技术 |
| ---- | ---- |
| 后端 | Spring Boot 2.2、MyBatis-Plus 2.3、Shiro、MySQL 5.7、FastJSON、POI |
| 用户端 / 骑手端 | uni-app（Vue2），支持 H5 / 微信小程序 / App 多端发布 |
| 管理后台 | Vue 2、ElementUI、ECharts、Axios、Vue Router / Vuex |
| 其他 | WebSocket 即时客服、腾讯位置服务 SDK（骑手配送）、支付宝沙箱支付接口（预留） |

---

## 目录结构

```
fruit-delivery-system
├── springboot/                  # 后端服务
│   ├── db/fruit_mp.sql          # 数据库初始化脚本（含演示数据）
│   └── src/main/java/com/
│       ├── controller/          # 接口层（订单/骑手/客服/商品...）
│       ├── service/             # 业务层
│       ├── dao/ + mapper/       # MyBatis-Plus 数据访问层
│       ├── entity/              # 实体/视图模型/VO
│       ├── config/              # 拦截器、异常处理、MyBatis 配置
│       ├── interceptor/         # Token 鉴权拦截器
│       └── utils/               # 工具类
├── uniapp_front/front/          # 用户端 + 骑手端（uni-app）
│   ├── pages/                   # 用户端页面
│   ├── pages/rider/             # 骑手端页面（pages-rider.json 分包）
│   └── api/                     # 接口封装（base.js 中修改后端地址）
└── vue_admin/admin/             # 管理后台（Vue2 + ElementUI）
    └── src/
        ├── views/               # 页面（含 modules/ 各业务模块）
        ├── router/              # 动态路由
        └── utils/               # 请求封装、菜单、国际化
```

---

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.x
- MySQL 5.7+
- Node.js 12+（前端）

### 1. 初始化数据库

```sql
-- 导入 backend 目录下的初始化脚本（自动建库建表并写入演示数据）
source springboot/db/fruit_mp.sql;
```

### 2. 启动后端

修改 `springboot/src/main/resources/application.yml` 中的数据库账号密码，然后：

```bash
cd springboot
mvn spring-boot:run
# 服务地址: http://localhost:8080/springboot/
```

### 3. 启动管理后台

```bash
cd vue_admin/admin
npm install
npm run serve
# 访问 http://localhost:8081 （开发代理已配置 /api -> 后端）
```

### 4. 启动用户端（H5）

```bash
cd uniapp_front/front
npm install
npm run dev:h5        # H5 开发预览
npm run build:h5      # 构建产物放入 springboot/src/main/resources/static/front/h5 后随后端一起访问
```

> 运行微信小程序 / App 需使用 HBuilderX 打开 `uniapp_front/front` 项目，并自行配置小程序 AppID。

### 默认账号

| 角色 | 账号 | 密码 |
| ---- | ---- | ---- |
| 管理员（后台） | admin | admin |
| 用户（用户端） | 用户名1 ~ 8 | 123456 |

---

## 注意事项

- 仓库已脱敏处理：微信小程序 AppID 已替换为 `touristappid`，需在 `uniapp_front/front/manifest.json` 中配置为自己的 AppID；测试期上传的私人照片未包含在仓库中。
- `application.yml` 中的数据库密码为本地演示默认值，部署时请修改。
- 支付宝沙箱支付、腾讯位置服务等第三方能力需自行申请密钥后配置。
- 后端上传目录 `springboot/upload/` 被 .gitignore 忽略，属运行时生成目录。

## License

[MIT](./LICENSE)
