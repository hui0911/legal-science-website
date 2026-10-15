<template>
  <div style="width: 100%; padding: 20px;">
    <div class="head-img">
      <el-form ref="form" :model="userInfo" label-width="80px" style="width: 50%">
        <el-form-item label="照片">
          <image-upload v-model="userInfo.headImg"></image-upload>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input placeholder="请输入姓名" v-model="userInfo.nickName" style="margin-right: 10px"></el-input>
        </el-form-item>
        <el-button style="margin-bottom: 20px" size="mini" type="success" @click="updateUser">提交</el-button>
      </el-form>
    </div>
    <div style="margin-top: 20px;width: 30%">
      <div style="font-size: 14px;color: #909399;padding: 20px;cursor: pointer" @click="showPass = !showPass">修改密码</div>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" size="mini" v-if="showPass">
        <el-form-item label="新密码" prop="password">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="againPassword">
          <el-input v-model="ruleForm.againPassword"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {Message} from 'element-ui'
import { updateUserInfo} from '@/api/index'

export default {
  name: 'index',
  data() {
    return {
      userInfo: {},
      ruleForm: {
        password: '',
        againPassword: ''
      },
      type: 0,
      showPass: false,
      rules: {
        password: [
          {
            required: true,
            message: '请输入新密码',
            trigger: 'blur'
          }
        ],
        againPassword: [
          {
            required: true,
            message: '请再次输入新密码',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  created() {
    this.userInfo = this.$store.state.userInfo
  },
  methods: {
    updateUser() {
      this.userInfo.password = null
      updateUserInfo(this.userInfo).then(res => {
        Message.success('修改成功')
        this.$store.commit('setUserInfo', this.userInfo)
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let param = {}
          param.id = this.userInfo.id
          param.password = this.ruleForm.password
          updateUserInfo(param).then(res => {
            Message.success('修改成功')
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style scoped>
.nick-name {
  padding-bottom: 20px;
  width: 30%;
  display: flex;
}

.head-img {
  padding-top: 20px;
  border-bottom: 1px solid #dcdcdc;
}
</style>
