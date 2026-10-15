<template>
 <div style="width: 100%">
   <div class="obitem-container" v-for="(item,index) in dataList" :key="index">
     <img :src="item.bannerImg">
     <div style="position: relative">
       <div class="title" @click="itemClick(item)">{{item.title}}</div>
       <div class="info">{{item.info}}
       </div>
       <div class="time">{{item.createTime}}
        <i class="el-icon-view"></i> {{item.seeNum}}
       </div>
     </div>
   </div>
 </div>
</template>

<script>
// 长方形资讯类组件
export default {
  name: 'index',
  props: {
    dataList: {
      type: Array,
      default: () => []
    }
  },
  methods: {
    itemClick(item) {
      this.$router.push("/noticeDetail/" + item.id)
    }
  }
}
</script>

<style scoped>
.obitem-container {
  padding: 30px 0;
  border-bottom: 1px solid #eee;
  display: flex;
}

.obitem-container img {
  width: 160px;
  height: 120px;
  margin-right: 20px;
}
.title{
  font-size: 20px;
  font-weight: 700;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  color: #26292b;
  cursor: pointer;
}

.title:hover{
  color: #55aaff;;
}

.info{
  margin-top: 10px;
  color: #869099;
  font-size: 14px;
  line-height: 22px;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  display: -webkit-box;
  -webkit-line-clamp: 2;
}
.time{
  position: absolute;
  bottom: 0;font-size: 14px;
  left: 0;
  color: #869099;
  line-height: 24px;
}
</style>
