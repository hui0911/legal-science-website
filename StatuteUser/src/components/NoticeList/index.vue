<template>
  <div>
    <div v-for="(item,index) in dataList" :key="index" class="ac-container">
      <div class="ac-img">
        <img :src="item[imgKey]"/>
      </div>
      <div class="ac-next">
        <div class="ac-title" @click="itemClick(item)">{{ item[titleKey] }}</div>
        <div style="flex-grow: 1"></div>
        <div class="ac-time">{{ item.createTime }} <i class="el-icon-view"></i> {{item.seeNum}}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "index",
  props: {
    dataList: {
      type: Array,
      default: () => []
    },
    imgKey: {
      type: String,
      default: 'bannerImg'
    },
    titleKey: {
      type: String,
      default: 'title'
    }
  },
  methods: {
    itemClick(e) {
      this.$emit("itemClick", e)
    }
  }
}
</script>

<style scoped>
.ac-container {
  display: flex;
  padding: 14px 8px;
  align-items: center;
}

.ac-img {
  margin-right: 10px;
}

.ac-img img {
  width: 80px;
  height: 60px;
  border-radius: 4px;
  transition: all 0.3s;
}

.ac-img img:hover {
  transform: scale(1.1);
}

.ac-next {
  height: 60px;
  display: flex;
  flex-direction: column;
  cursor: pointer;
}

.ac-title {
  font-weight: bold;
}

.ac-title:hover {
  color: #55aaff;
}

.ac-time {
  font-size: 14px;
  color: #999;
}
</style>
