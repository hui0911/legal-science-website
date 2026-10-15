<template>
  <div>
    <BasicHeader></BasicHeader>
    <div class="mc mt10 mb10">
      <el-row :gutter="20">
        <el-col :span="14" style="margin-bottom: 10px">
          <el-card>
            <el-carousel height="315px">
              <el-carousel-item v-for="(item,index) in bannerList" :key="index">
                <div style="display: flex;justify-content: center">
                  <img :src="item.bannerUrl" style="height: 315px;object-fit: cover"/>
                </div>
              </el-carousel-item>
            </el-carousel>
          </el-card>
          <div style="margin-top: 10px">
            <el-card>
              <div slot="header">热门视频</div>
              <notice-list :data-list="videoList" img-key="cover"  @itemClick="itemClickVideo"></notice-list>
            </el-card>
          </div>
        </el-col>
        <el-col :span="10" style="margin-bottom: 10px">
          <el-card>
            <el-tabs v-model="activeName">
              <el-tab-pane label="最新资讯" name="first">
                <notice-list :data-list="newList" @itemClick="itemClickNew"></notice-list>
              </el-tab-pane>
              <el-tab-pane label="最热资讯" name="second">
                <notice-list :data-list="clickList" @itemClick="itemClickNew"></notice-list>
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <Footer></Footer>
    <el-backtop></el-backtop>
  </div>
</template>

<script>
import TextLine from '@/components/TextLine'
import ListCard from '@/components/ListCard'
import {getIndexBanner, getClickNotice, getNewNotice, getClickVideo} from '@/api/index/index'
import NoticeList from '@/components/NoticeList'

export default {
  name: "index",
  components: {
    TextLine, ListCard, NoticeList
  },
  data() {
    return {
      activeName: 'first',
      bannerList: [],
      clickList: [],
      newList: [],
      dataList: [],
      videoList: []
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      getIndexBanner({status: 0}).then(res => {
        this.bannerList = res.data
      })
      getClickNotice().then(res => {
        this.clickList = res.data
      })
      getNewNotice().then(res => {
        this.newList = res.data
      })
      getClickVideo({}).then(res => {
        this.videoList = res.data
      })
    },
    itemClickNew(item) {
      this.$router.push("/noticeDetail/" + item.id)
    },
    itemClickVideo(item) {
      this.$router.push("/videoDetail/" + item.id)
    }
  }
}
</script>

<style scoped lang="scss">

</style>
