<template>
	<view class="container">
		<view class="header">
			<text class="back-btn" @click="goBack">返回</text>
			<text class="title">订单详情</text>
			<view class="placeholder"></view>
		</view>
		
		<view v-if="order" class="content">
			<view class="status-card">
				<view class="status-icon" :class="orderStatusClass">
					<text>{{ orderStatusText }}</text>
				</view>
				<view class="status-info">
					<text class="status-text">{{ order.status }}</text>
					<text class="order-id">订单号: {{ order.id }}</text>
				</view>
			</view>
			
			<view class="info-card">
				<view class="card-title">收货信息</view>
				<view class="address-box">
					<text class="consignee">{{ order.consignee }} {{ order.tel }}</text>
					<text class="address">{{ order.address }}</text>
				</view>
			</view>
			
			<view class="info-card">
				<view class="card-title">商品信息</view>
				<view class="goods-list">
					<view class="goods-item">
						<image class="goods-image" :src="order.picture" mode="aspectFill"></image>
						<view class="goods-info">
							<text class="goods-name">{{ order.goodname }}</text>
							<text class="goods-spec">{{ order.goodtype }}</text>
							<view class="goods-bottom">
								<text class="goods-price">¥{{ order.price }}</text>
								<text class="goods-count">x{{ order.buynumber }}</text>
							</view>
						</view>
					</view>
				</view>
			</view>
			
			<view class="info-card">
				<view class="card-title">订单信息</view>
				<view class="info-list">
					<view class="info-item">
						<text class="label">下单时间</text>
						<text class="value">{{ order.addtime }}</text>
					</view>
					<view class="info-item">
						<text class="label">商品金额</text>
						<text class="value">¥{{ order.total }}</text>
					</view>
					<view class="info-item">
						<text class="label">配送费</text>
						<text class="value">¥{{ order.price }}</text>
					</view>
					<view class="info-item">
						<text class="label">订单总额</text>
						<text class="value price">¥{{ order.total }}</text>
					</view>
				</view>
			</view>
			
			<view v-if="order.riderId" class="info-card">
				<view class="card-title">骑手信息</view>
				<view class="rider-info">
					<image class="rider-avatar" :src="order.picture || '../../../static/default-avatar.png'" mode="aspectFill"></image>
					<view class="rider-detail">
						<text class="rider-name">{{ order.riderName }}</text>
						<text class="rider-phone">{{ order.tel }}</text>
					</view>
				</view>
			</view>
		</view>
		
		<view v-if="order && order.status === '待接单'" class="footer">
			<button class="action-btn" @click="acceptOrder">接单</button>
		</view>
		
		<view v-if="order && order.status === '待配送'" class="footer">
			<button class="action-btn" @click="deliverOrder">确认送达</button>
		</view>
	</view>
</template>

<script>
import { getOrderDetail, acceptOrder, updateOrderStatus } from '@/api/rider.js'

export default {
	data() {
		return {
			order: null,
			orderId: '',
			orderStatusClass: '',
			orderStatusText: ''
		}
	},
	watch: {
		order: function(val) {
			if (val) {
				this.updateStatusInfo(val.status)
			}
		}
	},
	onLoad(options) {
		if (options && options.id) {
			this.orderId = options.id
			this.loadDetail()
		}
	},
	methods: {
		updateStatusInfo(status) {
			switch (status) {
				case '待接单': 
					this.orderStatusClass = 'waiting'
					this.orderStatusText = '待接单'
					break
				case '待配送': 
					this.orderStatusClass = 'delivering'
					this.orderStatusText = '配送中'
					break
				case '已完成': 
					this.orderStatusClass = 'completed'
					this.orderStatusText = '已完成'
					break
				default: 
					this.orderStatusClass = ''
					this.orderStatusText = status
			}
		},
		goBack() {
			uni.navigateBack()
		},
		async loadDetail() {
			uni.showLoading({ title: '加载中...' })
			try {
				const res = await getOrderDetail(this.orderId)
				if (res.code === 0) {
					this.order = res.data
				}
			} catch (error) {
				console.error(error)
			}
			uni.hideLoading()
		},
		async acceptOrder() {
			const riderInfo = JSON.parse(uni.getStorageSync('riderInfo'))
			uni.showModal({
				title: '确认接单',
				content: '确定要接此订单吗？',
				success: async (res) => {
					if (res.confirm) {
						uni.showLoading({ title: '接单中...' })
						try {
							const result = await acceptOrder({
								orderId: this.orderId,
								riderId: riderInfo.id,
								riderName: riderInfo.name
							})
							if (result.code === 0) {
								uni.hideLoading()
								uni.showToast({ title: '接单成功', icon: 'success' })
								setTimeout(() => {
									uni.navigateBack()
								}, 1500)
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
		async deliverOrder() {
			uni.showModal({
				title: '确认送达',
				content: '确定订单已送达吗？',
				success: async (res) => {
					if (res.confirm) {
						uni.showLoading({ title: '确认中...' })
						try {
							const result = await updateOrderStatus({
								orderId: this.orderId,
								status: '已完成'
							})
							if (result.code === 0) {
								uni.hideLoading()
								uni.showToast({ title: '送达成功', icon: 'success' })
								setTimeout(() => {
									uni.navigateBack()
								}, 1500)
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
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 40rpx;
	background: #fff;
}

.back-btn {
	font-size: 28rpx;
	color: #666;
}

.title {
	font-size: 32rpx;
	font-weight: bold;
	color: #333;
}

.placeholder {
	width: 80rpx;
}

.content {
	padding: 20rpx;
	flex: 1;
}

.status-card {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	border-radius: 10rpx;
	padding: 30rpx;
	display: flex;
	align-items: center;
	color: #fff;
	margin-bottom: 20rpx;
}

.status-icon {
	width: 80rpx;
	height: 80rpx;
	border-radius: 50%;
	background: rgba(255, 255, 255, 0.2);
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 24rpx;
	margin-right: 20rpx;
}

.status-info {
	display: flex;
	flex-direction: column;
}

.status-text {
	font-size: 32rpx;
	font-weight: bold;
}

.order-id {
	font-size: 24rpx;
	opacity: 0.8;
	margin-top: 10rpx;
}

.info-card {
	background: #fff;
	border-radius: 10rpx;
	padding: 20rpx;
	margin-bottom: 20rpx;
}

.card-title {
	font-size: 28rpx;
	font-weight: bold;
	color: #333;
	margin-bottom: 20rpx;
	padding-bottom: 15rpx;
	border-bottom: 1rpx solid #f5f5f5;
}

.address-box {
	display: flex;
	flex-direction: column;
}

.consignee {
	font-size: 28rpx;
	color: #333;
	margin-bottom: 10rpx;
}

.address {
	font-size: 26rpx;
	color: #666;
	line-height: 1.5;
}

.goods-list {
	display: flex;
	flex-direction: column;
}

.goods-item {
	display: flex;
}

.goods-image {
	width: 160rpx;
	height: 160rpx;
	border-radius: 10rpx;
	background: #f5f5f5;
}

.goods-info {
	flex: 1;
	margin-left: 20rpx;
	display: flex;
	flex-direction: column;
}

.goods-name {
	font-size: 28rpx;
	color: #333;
	margin-bottom: 10rpx;
}

.goods-spec {
	font-size: 24rpx;
	color: #999;
	margin-bottom: 10rpx;
}

.goods-bottom {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-top: auto;
}

.goods-price {
	font-size: 28rpx;
	color: #ff4d4f;
	font-weight: bold;
}

.goods-count {
	font-size: 24rpx;
	color: #999;
}

.info-list {
	display: flex;
	flex-direction: column;
}

.info-item {
	display: flex;
	justify-content: space-between;
	padding: 15rpx 0;
	border-bottom: 1rpx solid #f5f5f5;
	&:last-child {
		border-bottom: none;
	}
}

.label {
	font-size: 26rpx;
	color: #999;
}

.value {
	font-size: 26rpx;
	color: #333;
	&.price {
		font-size: 32rpx;
		font-weight: bold;
		color: #ff4d4f;
	}
}

.rider-info {
	display: flex;
	align-items: center;
}

.rider-avatar {
	width: 80rpx;
	height: 80rpx;
	border-radius: 50%;
	background: #f5f5f5;
}

.rider-detail {
	margin-left: 20rpx;
	display: flex;
	flex-direction: column;
}

.rider-name {
	font-size: 28rpx;
	color: #333;
}

.rider-phone {
	font-size: 24rpx;
	color: #999;
	margin-top: 5rpx;
}

.footer {
	padding: 20rpx;
	background: #fff;
	border-top: 1rpx solid #eee;
}

.action-btn {
	width: 100%;
	height: 88rpx;
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	color: #fff;
	border: none;
	border-radius: 44rpx;
	font-size: 32rpx;
}
</style>