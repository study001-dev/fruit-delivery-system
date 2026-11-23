	<template>
		<view class="container">
			<view class="logo-area">
				<image class="logo" src="../../../static/logo.png" mode="aspectFit"></image>
				<text class="title">骑手端</text>
			</view>

			<view class="form-area">
				<view class="form-item">
					<view class="icon-wrap">
						<text class="iconfont">账号</text>
					</view>
					<input
						v-model="form.username"
						class="input"
						placeholder="请输入用户名"
						type="text"
					/>
				</view>

				<view class="form-item">
					<view class="icon-wrap">
						<text class="iconfont">密码</text>
					</view>
					<input
						v-model="form.password"
						class="input"
						placeholder="请输入密码"
						type="password"
					/>
				</view>

				<button class="login-btn" @click="login">登录</button>

				<view class="link-area">
					<text class="link" @click="goRegister">立即注册</text>
				</view>
			</view>
		</view>
	</template>

	<script>
	import { riderLogin } from '@/api/rider.js'

	export default {
		data() {
			return {
				form: {
					username: '',
					password: ''
				}
			}
		},
		methods: {
			async login() {
				if (!this.form.username) {
					uni.showToast({ title: '请输入用户名', icon: 'none' })
					return
				}
				if (!this.form.password) {
					uni.showToast({ title: '请输入密码', icon: 'none' })
					return
				}

				uni.showLoading({ title: '登录中...' })

				try {
					const res = await riderLogin(this.form)
					uni.hideLoading()
					if (res.code === 0) {
						uni.setStorageSync('riderToken', res.token)
						uni.setStorageSync('riderInfo', JSON.stringify(res.rider))
						uni.showToast({ title: '登录成功', icon: 'success' })
						setTimeout(() => {
							uni.reLaunch({ url: '/pages/rider/index/index' })
						}, 1500)
					} else {
						uni.showToast({ title: res.msg || '登录失败', icon: 'none', duration: 3000 })
					}
				} catch (error) {
					uni.hideLoading()
					let msg = '登录失败'
					if (error && error.msg) {
						msg = error.msg
					} else if (error && error.data && error.data.msg) {
						msg = error.data.msg
					}
					uni.showToast({ title: msg, icon: 'none', duration: 3000 })
					console.error('骑手登录失败:', JSON.stringify(error))
				}
			},
			goRegister() {
				uni.navigateTo({ url: '/pages/rider/register/register' })
			}
		}
	}
	</script>

	<style lang="scss">
	.container {
		min-height: 100vh;
		background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
		display: flex;
		flex-direction: column;
		align-items: center;
		padding: 60rpx 40rpx;
		box-sizing: border-box;
	}

	.logo-area {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin-bottom: 80rpx;
		margin-top: 100rpx;
	}

	.logo {
		width: 160rpx;
		height: 160rpx;
		border-radius: 50%;
		background: #fff;
		padding: 20rpx;
		box-sizing: border-box;
	}

	.title {
		font-size: 40rpx;
		color: #fff;
		font-weight: bold;
		margin-top: 20rpx;
	}

	.form-area {
		width: 100%;
		background: #fff;
		border-radius: 20rpx;
		padding: 40rpx;
		box-shadow: 0 10rpx 40rpx rgba(0, 0, 0, 0.1);
	}

	.form-item {
		display: flex;
		align-items: center;
		border-bottom: 1rpx solid #eee;
		padding: 20rpx 0;
		margin-bottom: 10rpx;
	}

	.icon-wrap {
		width: 80rpx;
		display: flex;
		justify-content: center;
	}

	.iconfont {
		font-size: 32rpx;
		color: #999;
	}

	.input {
		flex: 1;
		font-size: 28rpx;
		color: #333;
	}

	.login-btn {
		width: 100%;
		height: 88rpx;
		background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
		color: #fff;
		border: none;
		border-radius: 44rpx;
		font-size: 32rpx;
		margin-top: 40rpx;
	}

	.link-area {
		display: flex;
		justify-content: flex-end;
		margin-top: 20rpx;
	}

	.link {
		font-size: 26rpx;
		color: #667eea;
	}
	</style>