import request from '@/utils/request'

const moduleName = '/subscribes'

/**
* 分页查询
* @param data
* @returns {*}
*/
export function getList(data) {
  return request({
    url: moduleName + '/page',
    method: 'post',
    data
  })
}

/**
* 添加
* @param data
* @returns {*}
*/
export function insert(data) {
  return request({
    url: moduleName + '/save/update',
    method: 'post',
    data
  })
}

/**
* 修改
* @param data
* @returns {*}
*/
export function update(data) {
  return request({
    url: moduleName + '/save/update',
    method: 'post',
    data
  })
}

/**
* 根据ID获取
* @param data
* @returns {*}
*/
export function get(id) {
  return request({
    url: moduleName + '/get/' + id,
    method: 'get'
  })
}

/**
* 删除
* @param data
* @returns {*}
*/
export function del(data) {
  return request({
    url: moduleName + '/remove',
    method: 'post',
    data
  })
}

export function getUsersDate() {
  return request({
    url: moduleName + '/all/users',
    method: 'get'
  })
}
export function getLawyersDate() {
  return request({
    url: moduleName + '/all/lawyers',
    method: 'get'
  })
}
