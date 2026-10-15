<template>
  <div>
    <BasicHeader :activeIndex="4"></BasicHeader>
    <div class="mc mt20">
      <el-breadcrumb separator="/" class="mb20">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>律师入住</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="mc mb20 mt20">

      <div class="mt10">
        <el-card>
          <div class="mb20">
            <h2 style="font-weight: bold;font-style: italic">请填写以下基本信息，欢迎您的加入！</h2>
          </div>
          <el-form :model="formData"
                   :rules="rules"
                   ref="formData"
                   label-width="100px"
          >
            <el-row :gutter="20">
              <el-col :span="6">
                <el-form-item label="您的姓名" prop="name">
                  <el-input v-model="formData.name"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="邮箱" prop="email">
                  <el-input v-model="formData.email"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="年龄"
                              prop="age"
                >
                  <el-input-number v-model="formData.age" :max="100"></el-input-number>
                </el-form-item>
              </el-col>
              <el-col :span="6">

                <el-form-item label="登录密码" prop="avatar">
                  <el-input v-model="formData.password"></el-input>
                </el-form-item>

              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="6">
                <el-form-item label="性别"
                              prop="sex"
                >
                  <el-radio-group v-model="formData.sex">
                    <el-radio :label="0">男</el-radio>
                    <el-radio :label="1">女</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="个人照片" prop="avatar">
                  <image-upload v-model="formData.avatar"></image-upload>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="证书"
                              prop="certificate"
                >
                  <image-upload v-model="formData.certificate"></image-upload>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label="律师等级"
                          prop="level"
            >
              <el-radio-group v-model="formData.level">
                <el-radio :label="0">助理律师</el-radio>
                <el-radio :label="1">四级律师</el-radio>
                <el-radio :label="2">三级律师</el-radio>
                <el-radio :label="3">二级律师</el-radio>
                <el-radio :label="4">一级律师</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="简历"
                          prop="info"
            >
              <el-input v-model="formData.info" type="textarea" :autosize="{ minRows: 8, maxRows: 8}"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="saveInfo">提交审核</el-button>
              <div>
                <a style="cursor: pointer;color: #5af" href="http://localhost:9528/#/login?redirect=%2FLawyers%2Findex"
                   target="_blank">已有账号？立即登录</a>
              </div>
            </el-form-item>
          </el-form>

        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import {lawyersSave} from "@/api/index";
import {Message} from "element-ui";

export default {
  name: "index",
  data() {
    return {
      formData: {},
      rules: {
        name: [
          {required: true, message: '请输入律师名称', trigger: 'blur'}
        ],
        avatar: [
          {required: true, message: '请上传律师头像', trigger: 'change'}
        ],
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入登录密码', trigger: 'blur'}
        ],
        level: [
          {required: true, message: '请选择律师等级', trigger: 'change'}
        ],
        info: [
          {required: true, message: '请输入简历', trigger: 'blur'}
        ],
        certificate: [
          {required: true, message: '请上传证书', trigger: 'change'}
        ],
        age: [
          {required: true, message: '请输入年龄', trigger: 'blur'}
        ],
        sex: [
          {required: true, message: '请选择性别', trigger: 'change'}
        ],
        status: [
          {required: true, message: '请选择状态', trigger: 'change'}
        ]
      }
    }
  },
  methods: {
    saveInfo() {
      this.$refs['formData'].validate((valid) => {
        if (valid) {
          this.formData.status = 0
          lawyersSave(this.formData).then(res => {
            this.formData = {}
            Message.success("提交成功,请等待管理员进行审核")
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })

    }
  }
}
</script>

<style scoped>

</style>
