import request from '@/utils/request'

/**
 * 首页轮播图获取
 * @returns {*}
 */
export function getIndexBanner(data) {
  return request({
    url: '/banner/findList',
    method: 'post',
    data
  })
}

export function getClickNotice() {
  return request({
    url: '/notice/click',
    method: 'get'
  })
}


export function getNewNotice() {
  return request({
    url: '/notice/new',
    method: 'get'
  })
}

export function getClickVideo() {
  return request({
    url: '/videos/click',
    method: 'get'
  })
}

/**
 * 获取所有分类
 * @param data
 * @returns {*}
 */
export function getAllType(data) {
  return request({
    url: '/types/findList',
    method: 'post',
    data
  })
}

/**
 * 根据分类分页获取资讯数据
 * @param data
 * @returns {*}
 */
export function getNoticeByType(data) {
  return request({
    url: '/notice/page',
    method: 'post',
    data
  })
}

export function getNoticeById(id) {
  return request({
    url: '/notice/get/' + id,
    method: 'get'
  })
}

export function getVideoByType(data) {
  return request({
    url: '/videos/page',
    method: 'post',
    data
  })
}

/**
 * 增加点击量
 * @returns {*}
 */
export function addVideoClick(id) {
  return request({
    url: '/videos/addClick/' + id,
    method: 'get'
  });
}

export function addNoticeClick(id) {
  return request({
    url: '/notice/addClick/' + id,
    method: 'get'
  });
}

/**
 * 用户登录
 * @param data
 * @returns {*}
 */
export function login(data) {
  return request({
    url: '/users/login',
    method: 'post',
    data
  })
}

/**
 * 用户注册
 * @param data
 * @returns {*}
 */
export function register(data) {
  return request({
    url: '/users/register',
    method: 'post',
    data
  })
}

/**
 * 所有评论
 * @param data
 * @returns {*}
 */
export function allVideoComments(data) {
  return request({
    url: '/videoComments/allComments',
    method: 'post',
    data
  })
}

/**
 * 保存评论
 * @param data
 * @returns {*}
 */
export function saveVideoComment(data) {
  return request({
    url: '/videoComments/save/update',
    method: 'post',
    data
  })
}

/**
 * 删除评论
 * @param id
 * @returns {*}
 */
export function delVideoComment(id) {
  return request({
    url: '/videoComments/remove/' + id,
    method: 'get'
  })
}

export function allNoticeComments(data) {
  return request({
    url: '/noticeComments/allComments',
    method: 'post',
    data
  })
}

export function saveNoticeComment(data) {
  return request({
    url: '/noticeComments/save/update',
    method: 'post',
    data
  })
}

export function delNoticeComment(id) {
  return request({
    url: '/noticeComments/remove/' + id,
    method: 'get'
  })
}

/**
 * 修改用户信息
 * @param data
 * @returns {*}
 */
export function updateUserInfo(data) {
  return request({
    url: '/users/save/update',
    method: 'post',
    data
  })
}

export function lawyersPage(data) {
  return request({
    url: '/lawyers/page',
    method: 'post',
    data
  })
}

export function query(data) {
  return request({
    url: '/lawyers/query',
    method: 'post',
    data
  })
}

export function lawyersSave(data) {
  return request({
    url: '/lawyers/save/update',
    method: 'post',
    data
  })
}


export function getLawyersById(id) {
  return request({
    url: '/lawyers/get/' + id,
    method: 'get'
  })
}
export function updateMessage(data) {
  return request({
    url: '/message/save/update',
    method: 'post',
    data
  })
}

export function messagePage(data) {
  return request({
    url: '/message/page',
    method: 'post',
    data
  })
}

export function delMessage(id) {
  return request({
    url: '/message/remove/' + id,
    method: 'get'
  })
}

export function updateSubscribes(data) {
  return request({
    url: '/subscribes/save/update',
    method: 'post',
    data
  })
}

export function subscribesPage(data) {
  return request({
    url: '/subscribes/page',
    method: 'post',
    data
  })
}
export function delSubscribes(id) {
  return request({
    url: '/subscribes/remove/' + id,
    method: 'get'
  })
}
