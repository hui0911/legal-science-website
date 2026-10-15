<template>
  <div>
    <BasicHeader :activeIndex="-1"></BasicHeader>
    <div class="mc mt20">
      <el-breadcrumb separator="/" class="mb20">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>视频详情</el-breadcrumb-item>
      </el-breadcrumb>
      <el-card class="mb20">
        <h2 slot="header">
          {{ detail.title }}
        </h2>
        <div style="display: flex;font-size: 14px;color: #999;align-items: center;margin-bottom: 10px">
          <div style="margin-right: 20px">{{ detail.typeName }}</div>
          <div style="margin-right: 20px">{{ detail.createTime }}</div>
          <div><i class="el-icon-view"> </i> {{ detail.seeNum }}</div>
          <div style="flex-grow: 1"></div>
        </div>
        <div style="margin: 20px;width: 100%;display: flex;justify-content: center">
          <video controls style="height: 500px;width: 80%" :src="detail.videoUrl" :poster="detail.cover">
          </video>
        </div>
        <CommentList :dataList="dataList" @delInfo="delInfo" @submitInfo="submitInfo" headerImgKey="headImg"
                     fromUserKey="userId"
                     contentKey="commentInfo" @saveInfo="saveInfo"
                     childrenListKey='videoCommentsDTOS'></CommentList>
      </el-card>
    </div>
    <Footer></Footer>
    <el-backtop></el-backtop>
  </div>
</template>

<script>
import {
  saveVideoComment,
  getVideoByType, addVideoClick, allVideoComments, delVideoComment
} from "../../api/index";
import CommentList from '@/components/CommentList'
import {Message} from "element-ui";

export default {
  name: "index",
  components: {
    CommentList
  },
  data() {
    return {
      id: 0,
      detail: {},
      isLogin: false,
      collectId: 0,
      dataList: [],
      info: '',
      currentComment: {},
    }
  },
  created() {
    this.isLogin = this.$store.state.isLogin
    this.id = this.$route.params.id
    if (this.id != 0) {
      this.$nextTick(() => {
        this.init()
      })
    }
  },
  methods: {
    init() {
      addVideoClick(this.id).then(res => {
        let param = {}
        param.id = this.id
        getVideoByType(param).then(res => {
          this.detail = res.data.records[0]
        })
      })
      this.getComment()
    },
    getComment() {
      let param = {}
      param.videoId = this.id
      param.parentId = 0
      allVideoComments(param).then(res => {
        this.dataList = res.data
      })
    },
    submitInfo(info) {
      if (!this.isLogin) {
        Message.warning("尚未登录！")
        return
      }
      let param = {}
      param.userId = this.$store.state.userInfo.id
      param.videoId = this.id
      param.commentInfo = info
      param.parentId = 0
      saveVideoComment(param).then(res => {
        Message.success('评论成功~')
        this.getComment()
      })
    },
    saveInfo(param) {
      if (!this.isLogin) {
        Message.warning("尚未登录！")
        return
      }
      param.userId = this.$store.state.userInfo.id
      param.videoId = this.id
      saveVideoComment(param).then(res => {
        Message.success('回复成功~')
        this.getComment()
      })
    },
    delInfo(item) {
      delVideoComment(item.id).then(res => {
        Message.success('删除成功~')
        this.getComment()
      })
    },
  }
}
</script>

<style scoped>

</style>
