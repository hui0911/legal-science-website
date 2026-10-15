<template>
  <div :class="{'has-logo':showLogo}">
    <logo v-if="showLogo" :collapse="isCollapse" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item v-for="route in routes" :key="route.path" :item="route" :base-path="route.path" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from './Logo'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'
import Layout from '@/layout'

export default {
  components: { SidebarItem, Logo },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    routes() {
      const statuteType = localStorage.getItem('statuteType')
      if (statuteType == 0) {
        return [{
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
              meta: { title: '资讯信息', icon: '' }
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
        }]
      } else {
        return [{
          path: '/',
          component: Layout,
          redirect: '/dashboard',
          children: [{
            path: 'dashboard',
            name: '预约信息',
            component: () => import('@/views/Subscribes/index'),
            meta: { title: '预约信息', icon: '' }
          }]
        }, {
          path: '/Lawyers',
          component: Layout,
          children: [
            {
              path: 'index',
              name: 'Lawyers',
              component: () => import('@/views/Lawyers/index'),
              meta: { title: '个人信息', icon: '' }
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
        }]
      }
    },
    activeMenu() {
      const route = this.$route
      const { meta, path } = route
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  }
}
</script>
