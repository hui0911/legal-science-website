<template>
  <div>
    <div class="footer-container" :class="fixed ? 'footer-fixed' :''">
      <div class="mc">
        <div class="top">
          <div>法律科普网站声明</div>
        </div>
        <div class="bottom">
          <div>1、本站为法律科普网站-V1.0版，旨在为大家提供法律普及分享服务平台。</div>
          <div>2、若本站共享的资源侵犯了您的利益，请联系站长，本站将在24小时内处理。邮箱：<span style="color: #5af">66666666@qq.com</span></div>
          <div>Copyright (C) 2026 法律科普网站 All Rights Reserved.</div>
        </div>
      </div>
    </div>
    <div v-if="fixed" style="height: 136px;width: 100%"></div>
  </div>
</template>

<script>
export default {
  name: 'index',
  props: {
    fixed: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style scoped>
.footer-container {
  clear: both;
  width: 100%;
  background-color: #222;
}

.footer-fixed {
  position: fixed;
  left: 0;
  bottom: 0;
}

.top {
  width: 100%;
  height: 40px;
  line-height: 40px;
  border-bottom: 1px solid #393939;
  display: flex;
  align-items: center;
}

.top div {
  margin-right: 22px;
  color: white;
  cursor: pointer;
  font-size: 14px;
}

.bottom {
  margin-top: 10px;
  font-size: 12px;
  color: #888c8e;
  padding-bottom: 20px;
}

.bottom div {
  height: 25px;
  line-height: 25px;
  cursor: pointer;
}
</style>
