import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/manage/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/manage/login',
    method: 'get',
    params: { token }
  })
}

export function logout(token) {
  return request({
    url: '/manage/login/logout',
    method: 'get',
    params: { token }
  })
}
