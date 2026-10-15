<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6" class="mb10" v-for="(item,index) in dataList" :key="index">
        <el-card>
          <img :src="item[imgKey]" @click="itemClick(item)">
          <div>
            <div style="font-size: 18px;font-weight: bold;cursor: pointer" class="mb5 title text1"
                 @click="itemClick(item)"> {{
                item[titleKey]
              }}
            </div>
            <div class="text2 mb5 mt5" style="font-size: 15px;color: #666">
            </div>
            <div class="footer-container">
              <div style="font-size: 14px;color: #999">{{ item[timeKey] }}</div>
              <div class="flex1"></div>
              <div style="display: flex;align-items: center">
                <span style="font-size: 14px;color: #999"><i class="el-icon-view"></i> {{ item.seeNum }}</span>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
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
      default: "cover"
    },
    titleKey: {
      type: String,
      default: "title"
    },
    timeKey: {
      type: String,
      default: "createTime"
    }
  },
  methods: {
    itemClick(item) {
      this.$emit("itemClick", item)
    },
  }
}
</script>

<style scoped>
img {
  width: 100%;
  height: 200px;
  border-radius: 5px;
  transition: all 0.3s;
  cursor: pointer;
}

img:hover {
  transform: scale(1.05);
}

.footer-container {
  display: flex;
  align-items: center;
}
</style>
