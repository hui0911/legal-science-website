<template>
  <div class="empty-container">
    <el-empty :description="title"
              :image="imgUrl"></el-empty>
  </div>
</template>

<script>
export default {
  name: "index",
  props: {
    title: {
      type: String,
      default: '暂无内容'
    },
    imgUrl: {
      type: String,
      default: 'https://azhou-image.oss-cn-hangzhou.aliyuncs.com/code-project/pc-image/no.png'
    },
  }
}
</script>

<style scoped>
.empty-container{
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
