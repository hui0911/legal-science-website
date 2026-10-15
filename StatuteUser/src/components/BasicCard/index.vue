<template>
  <div class="card-container">
    <div class="title-container">
      <div class="title">{{ title }}</div>
      <div class="flex1"></div>
      <div>
        <slot name="left"/>
      </div>
    </div>
    <div>
      <div class="top-img-container" v-if="src">
        <img :src="src" class="top-img" />
      </div>
      <slot/>
    </div>
  </div>
</template>

<script>
export default {
  name: "index",
  props: {
    title: {
      type: String,
      default: '标题哦'
    },
    src: {
      type: String,
      default: ''
    }
  }
}
</script>

<style scoped>


.title-container {
  color: #484848;
  font-size: 14px;
  line-height: 40px;
  font-width: bold;
  border-bottom: 1px solid #eee;;
  margin-bottom: 15px;
  display: flex;
}

.title {
  line-height: 38px;
  width: fit-content;
  width: -webkit-fit-content;
  width: -moz-fit-content;
  border-bottom: 2px solid #484848
}

.top-img {
  width: 100%;
  cursor: pointer;
  opacity: 0.8;
  border-radius: 8px;
  transition: all 0.3s;
  overflow: hidden;
}

.top-img-container {
  width: 100%;
  border-radius: 8px;
  overflow: hidden;
  margin-bottom: 15px;

}

.top-img:hover {
  transform: scale(1.1);
  opacity: 1;
}

</style>
