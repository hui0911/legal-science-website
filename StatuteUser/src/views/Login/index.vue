<template>
  <div class="login-container">
    <div class="login-box" v-if="showLogin" :class="isCenter?'is-center' :'is-right'">
      <div class="title">{{ sysTitle }}</div>
      <div style="margin-top: 40px">
        <el-form label-position="top" label-width="80px" ref="loginForm" :model="loginForm" :rules="loginRule">
          <el-form-item prop="account" label="账号">
            <el-input v-model="loginForm.account"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="密码">
            <el-input v-model="loginForm.password" show-password></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div style="margin-top: 40px">
        <el-button style="width: 100%" type="primary" @click="loginClick('loginForm')">登 录</el-button>
      </div>
      <div style="margin-top: 20px" v-if="showRegister">
        <div class="register-link" @click="showLoginClick('loginForm')">没有账号？去注册~</div>
      </div>
    </div>
    <div class="reg-box" v-else :class="isCenter?'is-center' :'is-right'">
      <div class="title">{{ sysTitle }}</div>
      <div style="margin-top: 40px">
        <el-form label-position="top" label-width="80px" ref="registerForm" :model="registerForm" :rules="registerRule">
          <el-form-item prop="account" label="账号">
            <el-input v-model="registerForm.account"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="密码">
            <el-input v-model="registerForm.password" show-password></el-input>
          </el-form-item>
          <el-form-item prop="againPassword" label="确认密码">
            <el-input v-model="registerForm.againPassword" show-password></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div style="margin-top: 40px">
        <el-button style="width: 100%" type="success" @click="registerClick('registerForm')">注 册</el-button>
      </div>
      <div style="margin-top: 20px" v-if="showRegister">
        <div class="register-link" @click="showLoginClick('registerForm')">已有账号？去登录~</div>
      </div>
    </div>
  </div>
</template>

<script>
import {Message} from "element-ui";
import {login, register} from "../../api/index";

export default {
  name: "index",
  props: {
    // 是否展示注册
    showRegister: {
      type: Boolean,
      default: true
    },
    // 登录注册框框是否居中显示
    isCenter: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      // 系统标题
      sysTitle: '法律科普网站',
      // 登录注册两个表单切换开关
      showLogin: true,
      loginForm: {
        account: '',
        password: ''
      },
      loginRule: {
        account: [
          {required: true, message: '请输入账号！', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码！', trigger: 'blur'}
        ]
      },
      registerForm: {
        account: '',
        password: '',
        againPassword: ''
      },
      registerRule: {
        account: [
          {required: true, message: '请输入账号！', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码！', trigger: 'blur'}
        ],
        againPassword: [
          {required: true, message: '请再次输入密码！', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    showLoginClick(formName) {
      this.$refs[formName].resetFields();
      this.showLogin = !this.showLogin
    },
    loginClick(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.loginForm)
          // 发送请求登录操作
          login(this.loginForm).then(res => {
            Message.success("登录成功！")
            this.$store.commit("setIsLogin", true)
            this.$store.commit("setUserInfo", res.data)
            this.$router.push("/")
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      })
    },
    registerClick(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.registerForm.password != this.registerForm.againPassword) {
            Message.warning("两次密码输入不一致~")
            return
          }
          // 发送请求注册操作
          register(this.registerForm).then(res => {
            Message.success("注册成功！")
            this.showLogin = true
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      })
    }
  }
}
</script>

<style scoped>

.login-container {
  height: 100vh;
  width: 100vw;
  color: black;
  position: relative;
  background: url("../../assets/acbg.png") no-repeat;
  background-size: 100%;
}

.login-box {
  width: 340px;
  padding: 0 50px;
  height: 550px;
  position: absolute;
  top: 50%;
  right: 50%;
  margin-right: -170px;
  margin-top: -270px;
  background-color: rgba(252, 250, 250, 0.8);
  border-radius: 20px;
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);
}

.reg-box {
  width: 340px;
  padding: 0 50px;
  height: 700px;
  position: absolute;
  top: 50%;
  right: 50%;
  margin-right: -170px;
  margin-top: -350px;
  background-color: rgba(252, 250, 250, 0.8);
  border-radius: 20px;
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);
}

.title {
  width: 100%;
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  margin-top: 80px;
}

.register-link {
  font-size: 14px;
  float: right;
  cursor: pointer;
}

.register-link:hover {
  color: #5af;
  text-decoration: underline;
}


::v-deep .el-form-item__label {
  padding: 0 0 5px;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  font-size: 16px;
}
</style>
