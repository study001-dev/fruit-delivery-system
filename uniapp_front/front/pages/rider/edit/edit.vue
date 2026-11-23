<template>
	<view class="container">
		<view class="header">
			<text class="back-btn" @click="goBack">返回</text>
			<text class="title">编辑资料</text>
			<text class="save-btn" @click="save">保存</text>
		</view>
		
		<view class="form-area">
			<view class="form-item avatar-item">
				<text class="label">头像</text>
				<view class="avatar-wrap">
					<image class="avatar" :src="form.avatar || '../../../static/default-avatar.png'" mode="aspectFill"></image>
					<view class="upload-btn" @click="uploadAvatar">
						<text>更换</text>
					</view>
				</view>
			</view>
			
			<view class="form-item">
				<text class="label">用户名</text>
				<input 
					v-model="form.username" 
					class="input" 
					placeholder="请输入用户名" 
					type="text"
					disabled
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
					disabled
				/>
			</view>
		</view>
	</view>
</template>

<script>
import { updateRiderInfo } from '@/api/rider.js'

export default {
	data() {
		return {
			form: {
				id: '',
				username: '',
				name: '',
				phone: '',
				idCard: '',
				avatar: ''
			}
		}
	},
	onLoad() {
		this.loadRiderInfo()
	},
	methods: {
		goBack() {
			uni.navigateBack()
		},
		loadRiderInfo() {
			try {
				const riderStr = uni.getStorageSync('riderInfo')
				if (riderStr) {
					this.form = JSON.parse(riderStr)
				}
			} catch (e) {
				console.error(e)
			}
		},
		uploadAvatar() {
			uni.chooseImage({
				count: 1,
				sizeType: ['compressed'],
				sourceType: ['album', 'camera'],
				success: (res) => {
					const tempFilePath = res.tempFilePaths[0]
					uni.uploadFile({
						url: this.$base.url + 'file/upload',
						filePath: tempFilePath,
						name: 'file',
						success: (uploadRes) => {
							try {
								const data = JSON.parse(uploadRes.data)
								if (data.code === 0) {
									this.form.avatar = data.file
								}
							} catch (e) {
								console.error(e)
							}
						}
					})
				}
			})
		},
		async save() {
			if (!this.form.name) {
				uni.showToast({ title: '请输入姓名', icon: 'none' })
				return
			}
			if (!this.form.phone) {
				uni.showToast({ title: '请输入手机号', icon: 'none' })
				return
			}
			
			uni.showLoading({ title: '保存中...' })
			try {
				const res = await updateRiderInfo(this.form)
				if (res.code === 0) {
					uni.hideLoading()
					uni.showToast({ title: '保存成功', icon: 'success' })
					uni.setStorageSync('riderInfo', JSON.stringify(this.form))
					setTimeout(() => {
						uni.navigateBack()
					}, 1500)
				} else {
					uni.hideLoading()
					uni.showToast({ title: res.msg, icon: 'none' })
				}
			} catch (error) {
				uni.hideLoading()
				uni.showToast({ title: '保存失败', icon: 'none' })
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

.save-btn {
	font-size: 28rpx;
	color: #667eea;
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
	&:last-child {
		border-bottom: none;
	}
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

.avatar-item {
	flex-direction: column;
	align-items: flex-start;
}

.avatar-wrap {
	position: relative;
	margin-top: 10rpx;
}

.avatar {
	width: 160rpx;
	height: 160rpx;
	border-radius: 50%;
	background: #f5f5f5;
}

.upload-btn {
	position: absolute;
	bottom: 0;
	right: -60rpx;
	background: #667eea;
	color: #fff;
	font-size: 22rpx;
	padding: 8rpx 20rpx;
	border-radius: 20rpx;
}
</style>