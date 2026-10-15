<template>
  <div>
    <div class="header-container">
      <div class="header-content mc">
        <el-row>
          <el-col :span="6">
            <div class="header-left" @click="toHome">
              <img src="../../assets/alogo.png"
                   style="height: 40px">
              <span style="margin-left: 4px"> {{ pcTitle }}</span>
            </div>
          </el-col>
          <el-col :span="14">
            <div>
              <ul class="menu-list">
                <li v-for="(item,index) in menuList" :key="index" v-on:mouseover="hoverHandler(index,0)"
                    v-on:mouseleave="hoverHandler(index,1)">
                  <div v-if="item.children && item.children.length > 0">
                    <i :class="item.icon" class="menu-icon"/>{{ item.name }} <i class="el-icon-arrow-down"
                                                                                :class="item.show? 'tra-icon' : ''"></i>
                    <transition name="el-zoom-in-top">
                      <div v-show="item.show" class="children-list">
                        <div v-for="(child,childIndex) in item.children" :key="childIndex">
                          <router-link :to="child.url">{{ child.name }}
                          </router-link>
                        </div>
                      </div>
                    </transition>
                  </div>
                  <router-link :to="item.url" :class="index == activeIndex ? 'active-menu' : ''" v-else><i
                    :class="item.icon" class="menu-icon"/>{{ item.name }}
                  </router-link>
                </li>
              </ul>
            </div>
          </el-col>
          <el-col :span="4" class="search" style="font-size: 14px">
            <el-button size="mini" @click="toLogin" v-if="!$store.state.isLogin">去登录
            </el-button>
            <el-button size="mini" @click="exit" v-if="$store.state.isLogin">退出登录
            </el-button>
            <div style="margin-right: 10px" v-if="$store.state.isLogin"> 欢迎 {{ $store.state.userInfo.nickName }}</div>
          </el-col>
        </el-row>
      </div>
    </div>
    <div style="height: 60px"></div>
  </div>
</template>

<script>
export default {
  name: "index",
  props: {
    activeIndex: {
      type: Number,
      default: 0
    },
    showSearch: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      pcTitle: '法律科普网站',
      pcLogo: '',
      showLogo: false,
      keyword: '',
      menuList: []
    }
  },
  created() {
    this.handleMenu()
  },
  methods: {
    handleMenu() {
      let arr = this.$router.options.routes
      if (!arr && arr.length <= 0) return
      let menuArr = []
      for (let i in arr) {
        if (arr[i].meta.isMenu) {
          let menu = {
            name: arr[i].meta.title,
            icon: arr[i].meta.icon,
            url: arr[i].path,
            children: []
          }
          menuArr.push(menu)
        }
      }
      this.menuList = menuArr
    },
    toSearch() {
      console.log("点击搜索按钮")
      // localStorage.setItem("keyword", this.keyword)
      // this.keyword = ""
      // this.$router.push("/list")
    },
    toLogin() {
      this.$router.push("/login")
    },
    exit() {
      this.$store.commit("setIsLogin", false)
      this.$store.commit("setUserInfo", {})
      this.$router.push("/")
    },
    toHome() {
      this.$router.push("/index")
    },
    hoverHandler(index, type) {
      this.menuList[index].show = type == 0
      this.$forceUpdate()
    }
  }
}
</script>

<style scoped lang="scss">
.header-container {
  background-color: #1C2327;
  position: fixed;
  width: 100%;
  z-index: 1501;
}

.header-content {
  height: 60px;
  line-height: 60px;
}

.header-left {
  display: flex;
  align-items: center;
  height: 60px;
  font-weight: bold;
  color: white;
  font-size: 20px;
  cursor: pointer;
}

.menu-list {
  display: flex;
  align-items: center;
}


.menu-list li {
  padding: 0 10px;
  margin: 0 10px;
  position: relative;
}

.menu-list li div {
  font-size: 14px;
  cursor: pointer;
  color: white;
}

.menu-list li a {
  color: white;
  font-size: 14px;
  text-decoration: none;
}

.menu-list li a:hover {
  color: $basic-font-color;
}


.tra-icon {
  transform: rotate(180deg);
  transition-duration: 0.5s;
}


.menu-icon {
  font-size: 14px;
  margin-right: 3px;
}

.active-menu {
  color: $basic-font-color !important;
}

.children-list {
  text-align: center;
  width: calc(100% - 20px);
  position: absolute;
  background-color: white;
  border-radius: 4px;
  color: white;
  z-index: 3;
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);
}

.children-list div {
  line-height: 40px;
}


.children-list div a {
  color: #1C2327;
}

.children-list div a:hover {
  color: $basic-font-color;
}

.header-right {
  width: 100%;
  display: flex;
  justify-content: right;
  height: 60px;
  align-items: center;
}

.search {
  color: white;
  display: flex;
  flex-direction: row-reverse;
  padding-right: 20px;
  align-items: center;
  height: 60px;
  line-height: 60px;
  cursor: pointer;
}


</style>
