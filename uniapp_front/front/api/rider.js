import http from './rider-http.js'

export const riderLogin = (data) => {
	return http.request({
		url: 'rider/login',
		method: 'post',
		data
	})
}

export const riderRegister = (data) => {
	return http.request({
		url: 'rider/register',
		method: 'post',
		data
	})
}

export const getRiderInfo = (data) => {
	return http.request({
		url: 'rider/info',
		method: 'get',
		data
	})
}

export const updateRiderInfo = (data) => {
	return http.request({
		url: 'rider/update',
		method: 'post',
		data
	})
}

export const getWaitingOrders = (data) => {
	return http.request({
		url: 'rider/order/list/waiting',
		method: 'get',
		data
	})
}

export const getDeliveringOrders = (data) => {
	return http.request({
		url: 'rider/order/list/delivering',
		method: 'get',
		data
	})
}

export const getCompletedOrders = (data) => {
	return http.request({
		url: 'rider/order/list/completed',
		method: 'get',
		data
	})
}

export const acceptOrder = (data) => {
	return http.request({
		url: 'rider/order/accept',
		method: 'post',
		data
	})
}

export const updateOrderStatus = (data) => {
	return http.request({
		url: 'rider/order/update/status',
		method: 'post',
		data
	})
}

export const getOrderDetail = (id) => {
	return http.request({
		url: `orders/info/${id}`,
		method: 'get'
	})
}