/**
 * 骑手端专用网络请求模块
 * 基于 Promise 对象实现，使用 riderToken 鉴权
 */
import base from './base'

export default {
	config: {
		baseUrl: base.url,
		header: {
			'Content-Type': 'application/json;charset=UTF-8'
		},
		data: {},
		method: "GET",
		dataType: "json",
		timeout: 10000,
		success() {},
		fail() {},
		complete() {}
	},
	interceptor: {
		request: null,
		response: null
	},
	request(options) {
		if (!options) {
			options = {}
		}
		options.baseUrl = options.baseUrl || this.config.baseUrl
		options.dataType = options.dataType || this.config.dataType
		options.url = options.baseUrl + options.url
		options.data = options.data || {}
		options.method = options.method || this.config.method
		options.timeout = options.timeout || this.config.timeout
		let token = uni.getStorageSync("riderToken")
		if (token) {
			options.header = Object.assign({}, options.header, { 'Token': token })
		}
		return new Promise((resolve, reject) => {
			let _config = null
			options.complete = (response) => {
				let statusCode = response.statusCode
				response.config = _config
				if (statusCode === 200) {
					var rs = response.data
					if (rs.code === 0) {
						resolve(response.data)
					} else if (rs.code == 401) {
						uni.clearStorageSync()
						uni.reLaunch({
							url: '/pages/login/login'
						})
						reject(response.data)
					} else {
						// 业务错误不弹 toast，由调用方处理
						reject(response.data)
					}
				} else if (statusCode === 404) {
					uni.showToast({
						title: "接口不存在(404)",
						icon: 'none',
						duration: 3000
					})
					reject(response)
				} else if (statusCode === 500) {
					uni.showToast({
						title: "服务器内部错误(500)",
						icon: 'none',
						duration: 3000
					})
					reject(response)
				} else {
					uni.showToast({
						title: "请求失败(" + statusCode + ")",
						icon: 'none',
						duration: 3000
					})
					reject(response)
				}
			}
			options.fail = (err) => {
				console.error("【骑手请求失败】", JSON.stringify(err))
				uni.showToast({
					title: "网络请求超时或失败，请检查后端服务",
					icon: 'none',
					duration: 3000
				})
				reject(err)
			}
			_config = Object.assign({}, this.config, options)
			_config.requestId = new Date().getTime()
			if (this.interceptor.request) {
				this.interceptor.request(_config)
			}
			uni.request(_config)
		})
	},
	get(url, data, options) {
		if (!options) {
			options = {}
		}
		options.url = url
		options.data = data
		options.method = 'GET'
		return this.request(options)
	},
	post(url, data, options) {
		if (!options) {
			options = {}
		}
		options.url = url
		options.data = data
		options.method = 'POST'
		return this.request(options)
	}
}
