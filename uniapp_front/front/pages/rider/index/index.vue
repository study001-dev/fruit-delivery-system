<template>
	<view class="container">
		<view class="header">
			<view class="user-info">
				<image class="avatar" :src="riderInfo.avatar || '../../../static/default-avatar.png'" mode="aspectFill"></image>
				<view class="info">
					<text class="name">{{ riderInfo.name || '骑手' }}</text>
					<text class="status" :class="{ active: riderInfo.status === 1 }">
						{{ riderInfo.status === 1 ? '在线' : '离线' }}
					</text>
				</view>
			</view>
			<view class="order-count">
				<view class="count-item">
					<text class="count">{{ waitingCount }}</text>
					<text class="label">待接单</text>
				</view>
				<view class="count-item">
					<text class="count">{{ deliveringCount }}</text>
					<text class="label">配送中</text>
				</view>
				<view class="count-item">
					<text class="count">{{ completedCount }}</text>
					<text class="label">已完成</text>
				</view>
			</view>
		</view>
		
		<view class="tabs">
			<view 
				class="tab" 
				:class="{ active: activeTab === 'waiting' }"
				@click="switchTab('waiting')"
			>
				<text>待接单</text>
				<view v-if="waitingCount > 0" class="badge">{{ waitingCount }}</view>
			</view>
			<view 
				class="tab" 
				:class="{ active: activeTab === 'delivering' }"
				@click="switchTab('delivering')"
			>
				<text>配送中</text>
				<view v-if="deliveringCount > 0" class="badge">{{ deliveringCount }}</view>
			</view>
			<view 
				class="tab" 
				:class="{ active: activeTab === 'completed' }"
				@click="switchTab('completed')"
			>
				<text>已完成</text>
			</view>
		</view>
		
		<scroll-view class="order-list" scroll-y>
			<view v-if="orderList.length === 0" class="empty">
				<text class="empty-icon">暂无订单</text>
				<text class="empty-text">{{ activeTab === 'waiting' ? '暂无待接单订单' : activeTab === 'delivering' ? '暂无配送中订单' : '暂无已完成订单' }}</text>
			</view>
			
			<view v-for="order in orderList" :key="order.id" class="order-card" @click="goDetail(order.id)">
				<view class="order-header">
					<text class="order-id">订单号: {{ order.id }}</text>
					<text class="order-status" :class="order.statusClass">{{ order.status }}</text>
				</view>
				<view class="order-info">
					<view class="address-item">
						<text class="icon">收货地址：</text>
						<text class="address">{{ order.address }}</text>
					</view>
					<view class="user-info">
						<text class="icon">收货人：</text>
						<text>{{ order.consignee }} {{ order.tel }}</text>
					</view>
					<view class="goods-info">
						<text class="icon">商品：</text>
						<text>{{ order.goodname }} x{{ order.buynumber }}</text>
					</view>
				</view>
				<view class="order-footer">
					<text class="price">¥{{ order.total }}</text>
					<button 
						v-if="activeTab === 'waiting'" 
						class="action-btn accept"
						@click.stop="acceptOrder(order.id)"
					>接单</button>
					<button 
						v-if="activeTab === 'delivering'" 
						class="action-btn deliver"
						@click.stop="deliverOrder(order.id)"
					>确认送达</button>
				</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
import { getWaitingOrders, getDeliveringOrders, getCompletedOrders, acceptOrder, updateOrderStatus, getRiderInfo } from '@/api/rider.js'

export default {
	data() {
		return {
			riderInfo: {},
			activeTab: 'waiting',
			orderList: [],
			waitingCount: 0,
			deliveringCount: 0,
			completedCount: 0,
			page: 1,
			limit: 10
		}
	},
	onShow() {
		this.init()
	},
	methods: {
		async init() {
			await this.loadRiderInfo()
			await this.loadOrders()
		},
		async loadRiderInfo() {
			try {
				const riderStr = uni.getStorageSync('riderInfo')
				if (riderStr) {
					this.riderInfo = JSON.parse(riderStr)
				}
			} catch (e) {
				console.error(e)
			}
		},
		async switchTab(tab) {
			this.activeTab = tab
			this.page = 1
			await this.loadOrders()
		},
		async loadOrders() {
			uni.showLoading({ title: '加载中...' })
			try {
				let res
				switch (this.activeTab) {
					case 'waiting':
						res = await getWaitingOrders({ page: this.page, limit: this.limit })
						this.waitingCount = res.data.total || 0
						break
					case 'delivering':
						res = await getDeliveringOrders({ page: this.page, limit: this.limit, riderId: this.riderInfo.id })
						this.deliveringCount = res.data.total || 0
						break
					case 'completed':
						res = await getCompletedOrders({ page: this.page, limit: this.limit, riderId: this.riderInfo.id })
						this.completedCount = res.data.total || 0
						break
				}
				if (res.code === 0) {
					this.orderList = (res.data.list || []).map(order => {
						return {
							...order,
							statusClass: this.getStatusClassValue(order.status)
						}
					})
				}
			} catch (error) {
				console.error(error)
				this.orderList = []
			}
			uni.hideLoading()
		},
		getStatusClassValue(status) {
			switch (status) {
				case '已支付': return 'waiting'
				case '待接单': return 'waiting'
				case '待配送': return 'delivering'
				case '已完成': return 'completed'
				default: return ''
			}
		},
		async acceptOrder(orderId) {
			uni.showModal({
				title: '确认接单',
				content: '确定要接此订单吗？',
				success: async (res) => {
					if (res.confirm) {
						uni.showLoading({ title: '接单中...' })
						try {
							const result = await acceptOrder({
								orderId,
								riderId: this.riderInfo.id,
								riderName: this.riderInfo.name
							})
							if (result.code === 0) {
								uni.hideLoading()
								uni.showToast({ title: '接单成功', icon: 'success' })
								await this.loadOrders()
							} else {
								uni.hideLoading()
								uni.showToast({ title: result.msg, icon: 'none' })
							}
						} catch (error) {
							uni.hideLoading()
							uni.showToast({ title: '接单失败', icon: 'none' })
						}
					}
				}
			})
		},
		async deliverOrder(orderId) {
			uni.showModal({
				title: '确认送达',
				content: '确定订单已送达吗？',
				success: async (res) => {
					if (res.confirm) {
						uni.showLoading({ title: '确认中...' })
						try {
							const result = await updateOrderStatus({
								orderId,
								status: '已完成'
							})
							if (result.code === 0) {
								uni.hideLoading()
								uni.showToast({ title: '送达成功', icon: 'success' })
								await this.loadOrders()
							} else {
								uni.hideLoading()
								uni.showToast({ title: result.msg, icon: 'none' })
							}
						} catch (error) {
							uni.hideLoading()
							uni.showToast({ title: '确认失败', icon: 'none' })
						}
					}
				}
			})
		},
		goDetail(orderId) {
			uni.navigateTo({ url: `/pages/rider/order-detail/order-detail?id=${orderId}` })
		}
	}
}
</script>

<style lang="scss">
.container {
	min-height: 100vh;
	background: #f5f5f5;
	display: flex;
	flex-direction: column;
}

.header {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	padding: 40rpx;
	color: #fff;
}

.user-info {
	display: flex;
	align-items: center;
	margin-bottom: 30rpx;
}

.avatar {
	width: 80rpx;
	height: 80rpx;
	border-radius: 50%;
	background: #fff;
}

.info {
	margin-left: 20rpx;
}

.name {
	font-size: 32rpx;
	font-weight: bold;
}

.status {
	font-size: 24rpx;
	background: rgba(255, 255, 255, 0.3);
	padding: 4rpx 16rpx;
	border-radius: 20rpx;
	margin-left: 10rpx;
	&.active {
		background: #52c41a;
	}
}

.order-count {
	display: flex;
	justify-content: space-around;
	background: rgba(255, 255, 255, 0.2);
	border-radius: 10rpx;
	padding: 20rpx;
}

.count-item {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.count {
	font-size: 40rpx;
	font-weight: bold;
}

.label {
	font-size: 24rpx;
	opacity: 0.8;
}

.tabs {
	display: flex;
	background: #fff;
	padding: 20rpx 0;
	border-bottom: 1rpx solid #eee;
}

.tab {
	flex: 1;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 28rpx;
	color: #666;
	position: relative;
	&.active {
		color: #667eea;
		font-weight: bold;
		&::after {
			content: '';
			position: absolute;
			bottom: -20rpx;
			left: 50%;
			transform: translateX(-50%);
			width: 40rpx;
			height: 4rpx;
			background: #667eea;
			border-radius: 2rpx;
		}
	}
}

.badge {
	background: #ff4d4f;
	color: #fff;
	font-size: 20rpx;
	padding: 2rpx 10rpx;
	border-radius: 20rpx;
	margin-left: 10rpx;
}

.order-list {
	flex: 1;
	padding: 20rpx;
}

.empty {
	display: flex;
	flex-direction: column;
	align-items: center;
	padding: 100rpx 0;
}

.empty-icon {
	font-size: 80rpx;
	color: #ddd;
	margin-bottom: 20rpx;
}

.empty-text {
	font-size: 28rpx;
	color: #999;
}

.order-card {
	background: #fff;
	border-radius: 10rpx;
	padding: 20rpx;
	margin-bottom: 20rpx;
	box-shadow: 0 2rpx 10rpx rgba(0, 0, 0, 0.05);
}

.order-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 15rpx;
}

.order-id {
	font-size: 24rpx;
	color: #999;
}

.order-status {
	font-size: 24rpx;
	padding: 4rpx 16rpx;
	border-radius: 20rpx;
	&.waiting {
		background: #fff7e6;
		color: #fa8c16;
	}
	&.delivering {
		background: #e6f7ff;
		color: #1890ff;
	}
	&.completed {
		background: #f6ffed;
		color: #52c41a;
	}
}

.order-info {
	padding: 15rpx 0;
	border-top: 1rpx solid #f5f5f5;
	border-bottom: 1rpx solid #f5f5f5;
}

.address-item, .user-info, .goods-info {
	margin-bottom: 10rpx;
	font-size: 26rpx;
	color: #666;
}

.icon {
	color: #999;
	margin-right: 10rpx;
}

.address {
	display: -webkit-box;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 2;
	overflow: hidden;
}

.order-footer {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-top: 15rpx;
}

.price {
	font-size: 32rpx;
	font-weight: bold;
	color: #ff4d4f;
}

.action-btn {
	padding: 12rpx 32rpx;
	border-radius: 30rpx;
	font-size: 26rpx;
	border: none;
	&.accept {
		background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
		color: #fff;
	}
	&.deliver {
		background: #52c41a;
		color: #fff;
	}
}
</style>