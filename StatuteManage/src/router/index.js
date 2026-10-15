import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: '预约信息',
      component: () => import('@/views/Subscribes/index'),
      meta: { title: '预约信息', icon: '' }
    }]
  },
  {
    path: '/Notice',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Notice',
        component: () => import('@/views/Notice/index'),
        meta: { title: '资讯信息表', icon: '' }
      }
    ]
  }, {
    path: '/Message',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Message',
        component: () => import('@/views/Message/index'),
        meta: { title: '留言信息', icon: '' }
      }
    ]
  }, {
    path: '/Users',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Users',
        component: () => import('@/views/Users/index'),
        meta: { title: '用户信息', icon: '' }
      }
    ]
  }, {
    path: '/Videos',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Videos',
        component: () => import('@/views/Videos/index'),
        meta: { title: '科普视频', icon: '' }
      }
    ]
  }, {
    path: '/Admin',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Admin',
        component: () => import('@/views/Admin/index'),
        meta: { title: '管理员', icon: '' }
      }
    ]
  }, {
    path: '/Banner',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Banner',
        component: () => import('@/views/Banner/index'),
        meta: { title: '轮播图', icon: '' }
      }
    ]
  }, {
    path: '/Lawyers',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Lawyers',
        component: () => import('@/views/Lawyers/index'),
        meta: { title: '律师信息', icon: '' }
      }
    ]
  }, {
    path: '/Types',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Types',
        component: () => import('@/views/Types/index'),
        meta: { title: '法律分类', icon: '' }
      }
    ]
  }, {
    path: '/VideoComments',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'VideoComments',
        component: () => import('@/views/VideoComments/index'),
        meta: { title: '视频评论', icon: '' }
      }
    ]
  }, {
    path: '/NoticeComments',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'NoticeComments',
        component: () => import('@/views/NoticeComments/index'),
        meta: { title: '资讯评论', icon: '' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
