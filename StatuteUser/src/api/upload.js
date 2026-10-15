import request from '@/utils/request'

export function uploadFile(data) {
  return request({
    url: '/upload/file',
    method: 'post',
    headers: {'Content-Type': 'multipart/form-data', Accept: '*/*'},
    data: data,
  });
}
