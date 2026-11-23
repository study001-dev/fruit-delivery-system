<template>
	<view class="container">
		<view class="header">
			<view class="user-card">
				<image class="avatar" :src="riderInfo.avatar || '../../../static/default-avatar.png'" mode="aspectFill"></image>
				<view class="info">
					<text class="name">{{ riderInfo.name || '骑手' }}</text>
					<text class="id">骑手ID: {{ riderInfo.id }}</text>
				</view>
				<view class="status-tag" :class="{ active: riderInfo.status === 1 }">
					{{ riderInfo.status === 1 ? '已认证' : '待审核' }}
				</view>
			</view>
		</view>
		
		<view class="stats-card">
			<view class="stat-item">
				<text class="stat-value">{{ stats.totalOrder }}</text>
				<text class="stat-label">总订单</text>
			</view>
			<view class="stat-divider"></view>
			<view class="stat-item">
				<text class="stat-value">{{ stats.completedOrder }}</text>
				<text class="stat-label">已完成</text>
			</view>
			<view class="stat-divider"></view>
			<view class="stat-item">
				<text class="stat-value">{{ stats.rating }}</text>
				<text class="stat-label">评分</text>
			</view>
		</view>
		
		<view class="menu-card">
			<view class="menu-item" @click="goEdit">
				<view class="menu-icon edit">编辑资料</view>
				<text class="menu-text">编辑资料</text>
				<text class="menu-arrow">></text>
			</view>
			<view class="menu-item" @click="goOrders">
				<view class="menu-icon orders">我的订单</view>
				<text class="menu-text">我的订单</text>
				<text class="menu-arrow">></text>
			</view>
			<view class="menu-item" @click="goWallet">
				<view class="menu-icon wallet">我的钱包</view>
				<text class="menu-text">我的钱包</text>
				<text class="menu-arrow">></text>
			</view>
			<view class="menu-item" @click="goSettings">
				<view class="menu-icon settings">设置</view>
				<text class="menu-text">设置</text>
				<text class="menu-arrow">></text>
			</view>
		</view>
		
		<view class="logout-btn" @click="logout">
			<text>退出登录</text>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			riderInfo: {},
			stats: {
				totalOrder: 0,
				completedOrder: 0,
				rating: 0
			}
		}
	},
	onShow() {
		this.init()
	},
	methods: {
		init() {
			this.loadRiderInfo()
			this.loadStats()
		},
		loadRiderInfo() {
			try {
				const riderStr = uni.getStorageSync('riderInfo')
				if (riderStr) {
					this.riderInfo = JSON.parse(riderStr)
				}
			} catch (e) {
				console.error(e)
			}
		},
		loadStats() {
			this.stats = {
				totalOrder: 128,
				completedOrder: 120,
				rating: 4.9
			}
		},
		goEdit() {
			uni.navigateTo({ url: '/pages/rider/edit/edit' })
		},
		goOrders() {
			uni.switchTab({ url: '/pages/rider/index/index' })
		},
		goWallet() {
			uni.showToast({ title: '钱包功能开发中', icon: 'none' })
		},
		goSettings() {
			uni.showToast({ title: '设置功能开发中', icon: 'none' })
		},
		logout() {
			uni.showModal({
				title: '退出登录',
				content: '退出后需要重新登录，确定要退出吗？',
				success: (res) => {
					if (res.confirm) {
						uni.clearStorageSync()
						
						uni.reLaunch({ url: '/pages/login/login' })
					}
				}
			})
		}
	}
}
</script>

<style lang="scss">
.container {
	min-height: 100vh;
	background: #f5f5f5;
}

.header {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	padding: 40rpx;
	padding-top: 80rpx;
}

.user-card {
	display: flex;
	align-items: center;
	color: #fff;
}

.avatar {
	width: 120rpx;
	height: 120rpx;
	border-radius: 50%;
	background: #fff;
	border: 4rpx solid rgba(255, 255, 255, 0.3);
}

.info {
	flex: 1;
	margin-left: 20rpx;
}

.name {
	font-size: 36rpx;
	font-weight: bold;
}

.id {
	font-size: 24rpx;
	opacity: 0.8;
	margin-top: 10rpx;
}

.status-tag {
	background: rgba(255, 255, 255, 0.2);
	padding: 8rpx 20rpx;
	border-radius: 20rpx;
	font-size: 22rpx;
	&.active {
		background: #52c41a;
	}
}

.stats-card {
	display: flex;
	justify-content: space-around;
	background: #fff;
	margin: -40rpx 20rpx 20rpx;
	border-radius: 10rpx;
	padding: 30rpx;
	box-shadow: 0 10rpx 30rpx rgba(0, 0, 0, 0.1);
}

.stat-item {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.stat-value {
	font-size: 40rpx;
	font-weight: bold;
	color: #333;
}

.stat-label {
	font-size: 24rpx;
	color: #999;
	margin-top: 10rpx;
}

.stat-divider {
	width: 1rpx;
	background: #eee;
}

.menu-card {
	background: #fff;
	margin: 0 20rpx 20rpx;
	border-radius: 10rpx;
	overflow: hidden;
}

.menu-item {
	display: flex;
	align-items: center;
	padding: 25rpx 20rpx;
	border-bottom: 1rpx solid #f5f5f5;
	&:last-child {
		border-bottom: none;
	}
}

.menu-icon {
	width: 48rpx;
	height: 48rpx;
	border-radius: 12rpx;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 24rpx;
	margin-right: 20rpx;
	&.edit {
		background: #e6f7ff;
		color: #1890ff;
	}
	&.orders {
		background: #f6ffed;
		color: #52c41a;
	}
	&.wallet {
		background: #fff7e6;
		color: #fa8c16;
	}
	&.settings {
		background: #f9f0ff;
		color: #722ed1;
	}
}

.menu-text {
	flex: 1;
	font-size: 28rpx;
	color: #333;
}

.menu-arrow {
	font-size: 28rpx;
	color: #ccc;
}

.logout-btn {
	background: #fff;
	margin: 0 20rpx;
	border-radius: 10rpx;
	padding: 25rpx;
	text-align: center;
	font-size: 28rpx;
	color: #999;
}
</style>