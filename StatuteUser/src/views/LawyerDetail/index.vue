<template>
  <div>
    <BasicHeader :activeIndex="-1"></BasicHeader>
    <div class="mc mt20">
      <el-breadcrumb separator="/" class="mb20">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>律师详情</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="mc mb20">
      <el-card>
        <div class="content-container" style="display: flex">
          <div style="margin: 10px 0">
            <img-preview :src="detail.avatar" width="300px" height="300px"></img-preview>
          </div>
          <div style="margin-left: 10px">
            <div style="display: flex">
              <h2 style="margin-bottom: 20px">{{ detail.name }}</h2>
              <div style="flex-grow: 1"></div>
              <div>
                <el-button type="primary" @click="showDia">留言咨询</el-button>
                <el-button type="success" @click="showDia1">我要预约</el-button>
              </div>
            </div>
            <div style="font-size: 14px;margin-bottom: 20px">
              性别：{{ detail.sex == 0 ? '男' : '女' }}
            </div>
            <div style="font-size: 14px;margin-bottom: 20px">
              年龄：{{ detail.age }}
            </div>
            <div style="font-size: 14px;margin-bottom: 20px">
              职称：
              <el-tag v-if="detail.level == 0">助理律师</el-tag>
              <el-tag v-if="detail.level == 1">四级律师</el-tag>
              <el-tag v-if="detail.level == 2">三级律师</el-tag>
              <el-tag v-if="detail.level == 3">二级律师</el-tag>
              <el-tag v-if="detail.level == 4">一级律师</el-tag>
            </div>
            <div style="margin: 20px 0;font-size: 14px">
              简介：{{ detail.info }}
            </div>
            <div style="font-size: 14px">
              邮箱：{{ detail.email }}
            </div>
          </div>
        </div>
      </el-card>
    </div>
    <Footer fixed></Footer>
    <el-dialog title="留言"
               :visible.sync="show"
               width="40%"
    >
      <el-form :model="formData"
               :rules="rules"
               ref="formData"
               label-width="100px"
      >
        <el-form-item label="留言内容"
                      prop="content"
        >
          <el-input v-model="formData.content" type="textarea" :autosize="{ minRows: 8, maxRows: 8}"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer"
            class="dialog-footer"
      >
        <el-button @click="show = false">取 消</el-button>
        <el-button type="primary"
                   @click="saveInfo"
        >确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="预约"
               :visible.sync="show1"
               width="40%"
    >
      <el-form :model="formData"
               :rules="rules"
               ref="formData"
               label-width="100px"
      >
        <el-form-item label="预约时间"
                      prop="subscribeTime"
        >
          <el-date-picker v-model="formData.subscribeTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择预约时间"/>
        </el-form-item>
      </el-form>
      <span slot="footer"
            class="dialog-footer"
      >
        <el-button @click="show1 = false">取 消</el-button>
        <el-button type="primary"
                   @click="saveInfo1"
        >确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  getLawyersById, updateMessage, updateSubscribes
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
      id: this.$route.params.id,
      detail: {},
      show: false,
      show1: false,
      formData: {},
      rules: {
        content: [
          {required: true, message: '请输入留言内容', trigger: 'blur'}
        ],
        subscribeTime: [
          {required: true, message: '请选择预约时间', trigger: 'change'}
        ],
      },
    }
  },
  created() {
    this.init()
    this.isLogin = this.$store.state.isLogin
  },
  methods: {
    showDia1() {
      this.show1 = true
    },
    init() {
      getLawyersById(this.id).then(res => {
        this.detail = res.data
      })
    },
    showDia() {
      if (!this.isLogin) {
        Message.warning("尚未登录！")
        return
      }
      this.show = true
    },
    saveInfo1(){
      if (!this.isLogin) {
        Message.warning("尚未登录！")
        return
      }
      if (!this.formData.subscribeTime) {
        Message.warning("请选择预约时间！")
        return
      }
      let param = {}
      param.userId = this.$store.state.userInfo.id
      param.lawyerId = this.id
      param.subscribeTime = this.formData.subscribeTime
      param.status = 0
      updateSubscribes(param).then(res => {
        this.formData = {}
        this.show1 = false
        Message.success("预约成功，等待管理员审核！")
      })
    },
    saveInfo() {
      if (!this.isLogin) {
        Message.warning("尚未登录！")
        return
      }
      if (!this.formData.content) {
        Message.warning("请输入留言内容！")
        return
      }
      let param = {}
      param.userId = this.$store.state.userInfo.id
      param.lawyerId = this.id
      param.content = this.formData.content
      updateMessage(param).then(res => {
        this.formData = {}
        this.show = false
        Message.success("留言成功，请前往个人中心查看留言记录！")
      })
    },

  }
}
</script>

<style scoped>


</style>
