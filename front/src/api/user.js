import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/system/auth/login',
    method: 'POST',
    data: data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/system/auth/logout',
    method: 'post'
  })
}
