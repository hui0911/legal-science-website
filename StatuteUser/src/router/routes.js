/**
 * 这里用于配置vue各种单页面级组件路由地址
 */
const Index = () => import('@/views/Index/index.vue')
const Login = () => import('@/views/Login/index.vue')
const Me = () => import('@/views/Me/index.vue')
const Type = () => import('@/views/Type/index.vue')
const VideoType = () => import('@/views/VideoType/index.vue')
const VideoDetail = () => import('@/views/VideoDetail/index.vue')
const NoticeDetail = () => import('@/views/NoticeDetail/index.vue')
const LawyersList = () => import('@/views/LawyersList/index.vue')
const LawyerDetail = () => import('@/views/LawyerDetail/index.vue')
const About = () => import('@/views/About/index.vue')
const AddLawyers = () => import('@/views/AddLawyers/index.vue')

export default [
  {
    path: '/',
    redirect: '/index',
    meta: {
      isMenu: false
    }
  },
  {
    path: '/index',
    name: 'index',
    components: {
      mainWindow: Index
    },
    meta: {
      isMenu: true,
      title: '首页',
      isAuthRequired: false
    }
  },
  {
    path: '/type',
    name: 'type',
    components: {
      mainWindow: Type
    },
    meta: {
      isMenu: true,
      title: '法律资讯',
      isAuthRequired: false
    }
  },
  {
    path: '/videoType',
    name: 'videoType',
    components: {
      mainWindow: VideoType
    },
    meta: {
      isMenu: true,
      title: '科普视频',
      isAuthRequired: false
    }
  },
  {
    path: '/lawyersList',
    name: 'lawyersList',
    components: {
      mainWindow: LawyersList
    },
    meta: {
      isMenu: true,
      title: '律师事务所',
      isAuthRequired: false
    }
  },
  {
    path: '/AddLawyers',
    name: 'AddLawyers',
    components: {
      mainWindow: AddLawyers
    },
    meta: {
      isMenu: true,
      title: '律师入住',
      isAuthRequired: false
    }
  },
  // {
  //   path: '/About',
  //   name: 'About',
  //   components: {
  //     mainWindow: About
  //   },
  //   meta: {
  //     isMenu: true,
  //     title: '关于我们',
  //     isAuthRequired: true
  //   }
  // },
  {
    path: '/me',
    name: 'me',
    components: {
      mainWindow: Me
    },
    meta: {
      isMenu: true,
      title: '个人中心',
      isAuthRequired: true
    }
  },
  {
    path: '/login',
    name: 'login',
    components: {
      mainWindow: Login
    },
    meta: {
      isMenu: false,
      title: '用户登录',
      isAuthRequired: false
    }
  },
  {
    path: '/videoDetail/:id',
    name: 'videoDetail',
    components: {
      mainWindow: VideoDetail
    },
    meta: {
      isMenu: false,
      title: '视频详情',
      isAuthRequired: false
    }
  },
  {
    path: '/noticeDetail/:id',
    name: 'noticeDetail',
    components: {
      mainWindow: NoticeDetail
    },
    meta: {
      isMenu: false,
      title: '资讯详情',
      isAuthRequired: false
    }
  },
  {
    path: '/lawyerDetail/:id',
    name: 'lawyerDetail',
    components: {
      mainWindow: LawyerDetail
    },
    meta: {
      isMenu: false,
      title: '律师详情',
      isAuthRequired: false
    }
  }
]
