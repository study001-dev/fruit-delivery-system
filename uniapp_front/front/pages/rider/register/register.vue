<template>
	<view class="container">
		<view class="header">
			<text class="back-btn" @click="goBack">返回</text>
			<text class="title">骑手注册</text>
			<view class="placeholder"></view>
		</view>

		<view class="form-area">
			<view class="form-item">
				<text class="label">用户名</text>
				<input
					v-model="form.username"
					class="input"
					placeholder="请输入用户名"
					type="text"
				/>
			</view>

			<view class="form-item">
				<text class="label">密码</text>
				<input
					v-model="form.password"
					class="input"
					placeholder="请输入密码"
					type="password"
				/>
			</view>

			<view class="form-item">
				<text class="label">姓名</text>
				<input
					v-model="form.name"
					class="input"
					placeholder="请输入姓名"
					type="text"
				/>
			</view>

			<view class="form-item">
				<text class="label">手机号</text>
				<input
					v-model="form.phone"
					class="input"
					placeholder="请输入手机号"
					type="number"
				/>
			</view>

			<view class="form-item">
				<text class="label">身份证号</text>
				<input
					v-model="form.idCard"
					class="input"
					placeholder="请输入身份证号"
					type="text"
				/>
			</view>

			<button class="register-btn" @click="register">注册</button>

			<view class="tips">
				<text>注册后需要等待管理员审核通过才能登录</text>
			</view>
		</view>
	</view>
</template>

<script>
import { riderRegister } from '@/api/rider.js'

export default {
	data() {
		return {
			form: {
				username: '',
				password: '',
				name: '',
				phone: '',
				idCard: ''
			}
		}
	},
	methods: {
		goBack() {
			uni.navigateBack()
		},
		async register() {
			if (!this.form.username) {
				uni.showToast({ title: '请输入用户名', icon: 'none' })
				return
			}
			if (!this.form.password) {
				uni.showToast({ title: '请输入密码', icon: 'none' })
				return
			}
			if (!this.form.name) {
				uni.showToast({ title: '请输入姓名', icon: 'none' })
				return
			}
			if (!this.form.phone) {
				uni.showToast({ title: '请输入手机号', icon: 'none' })
				return
			}
			if (!this.form.idCard) {
				uni.showToast({ title: '请输入身份证号', icon: 'none' })
				return
			}

			uni.showLoading({ title: '注册中...' })

			try {
				const res = await riderRegister(this.form)
				uni.hideLoading()
				if (res.code === 0) {
					uni.showToast({ title: res.msg || '注册成功', icon: 'success' })
					setTimeout(() => {
						uni.navigateBack()
					}, 2000)
				} else {
					uni.showToast({ title: res.msg || '注册失败', icon: 'none', duration: 3000 })
				}
			} catch (error) {
				uni.hideLoading()
				let msg = '注册失败'
				if (error && error.msg) {
					msg = error.msg
				} else if (error && error.data && error.data.msg) {
					msg = error.data.msg
				}
				uni.showToast({ title: msg, icon: 'none', duration: 3000 })
				console.error('骑手注册失败:', JSON.stringify(error))
			}
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

.form-area {
	margin: 20rpx;
	background: #fff;
	border-radius: 10rpx;
	padding: 30rpx;
}

.form-item {
	display: flex;
	align-items: center;
	padding: 20rpx 0;
	border-bottom: 1rpx solid #eee;
}

.label {
	width: 140rpx;
	font-size: 28rpx;
	color: #666;
}

.input {
	flex: 1;
	font-size: 28rpx;
	color: #333;
}

.register-btn {
	width: 100%;
	height: 88rpx;
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	color: #fff;
	border: none;
	border-radius: 44rpx;
	font-size: 32rpx;
	margin-top: 40rpx;
}

.tips {
	text-align: center;
	margin-top: 30rpx;
	font-size: 24rpx;
	color: #999;
}
</style>
