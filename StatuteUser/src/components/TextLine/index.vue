<template>
  <div class="text-container">{{ text }}</div>
</template>

<script>
export default {
  name: "index",
  props: {
    text: {
      type: String,
      default: '分割线'
    }
  }
}
</script>

<style scoped>

.text-container {
  height: 60px;
  width: 100%;
  line-height: 60px;
  font-size: 22px;
  font-weight: bold;
}
</style>
