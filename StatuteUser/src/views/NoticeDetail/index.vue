<template>
  <div>
    <BasicHeader :activeIndex="-1"></BasicHeader>
    <div class="mc mt20">
      <el-breadcrumb separator="/" class="mb20">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>资讯详情</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="mc mb20">
      <el-card>
        <div class="content-container">
          <div class="title">{{ detail.title }}</div>
          <div style="margin: 10px 0">
            <img-preview :src="detail.bannerImg" width="600px" height="300px"></img-preview>
          </div>
          <div class="create-time">发布时间：{{ detail.createTime }} <i class="el-icon-view"></i> {{detail.seeNum}}</div>
          <div class="content">
            <span v-html="detail.content"></span>
          </div>
        </div>
        <CommentList :dataList="dataList" @delInfo="delInfo" @submitInfo="submitInfo" headerImgKey="headImg"
                     fromUserKey="userId"
                     contentKey="commentInfo" @saveInfo="saveInfo"
                     childrenListKey='noticeCommentsDTOS'></CommentList>
      </el-card>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import {
  allNoticeComments,
  delNoticeComment,
  getNoticeById,
  saveNoticeComment,
  addNoticeClick,
  getVideoByType
} from "@/api/index";
import {Message} from "element-ui";
import CommentList from '@/components/CommentList'

export default {
  name: 'index',
  components: {
    CommentList
  },
  data() {
    return {
      isLogin: false,
      homeList: [],
      noticeId: this.$route.params.id,
      detail: {},
      dataList: []
    }
  },
  created() {
    this.init()
    this.isLogin = this.$store.state.isLogin
  },
  methods: {
    handleMd(content) {
      if (!content) content = ""
      let MardownIt = require('markdown-it')
      var md = new MardownIt()
      return md.render(content)
    },
    init() {
      addNoticeClick(this.noticeId)
      .then(res => {
        getNoticeById(this.noticeId).then(res => {
          this.detail = res.data
        })
      })

      this.getComment()
    },
    getComment() {
      let param = {}
      param.noticeId = this.noticeId
      param.parentId = 0
      allNoticeComments(param).then(res => {
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
      param.noticeId = this.noticeId
      param.commentInfo = info
      param.parentId = 0
      saveNoticeComment(param).then(res => {
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
      param.noticeId = this.noticeId
      saveNoticeComment(param).then(res => {
        Message.success('回复成功~')
        this.getComment()
      })
    },
    delInfo(item) {
      delNoticeComment(item.id).then(res => {
        Message.success('删除成功~')
        this.getComment()
      })
    },
  }
}
</script>

<style scoped>
.content-container {
  min-height: 800px;
  margin-bottom: 50px;
  border-bottom: 1px solid #eee;
}

.title {
  color: #3b4144;
  font-size: 30px;
  font-weight: bold;
  margin-top: 20px;
}

.create-time {
  color: #869099;
  font-size: 14px;
  margin: 20px 0;
}

.content {
  color: #3b4144;
  font-size: 16px;
  line-height: 32px;
  margin-top: 10px;
  word-wrap: break-word;
  line-height: 2rem !important;

}
</style>
