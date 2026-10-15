<template>
  <div>
    <el-card style="margin-top: 10px">
      <div style="width: 100%;display: flex;justify-content: center" class="mb10">
        <div style="width: 50%">
          <el-input :placeholder="label" v-model="keyword" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="searchClick"></el-button>
          </el-input>
        </div>
      </div>
      <div class="type-list">
        <strong class="first-title">类别</strong>
        <div :class="current == index ? 'type-active' : ''" class="type-item" v-for="(item,index) in allTypeList"
             :key="index"
             @click="typeClick(index,item)">{{ item.typeName }}
        </div>
      </div>
      <div class="type-list">
        <strong class="first-title">已选择</strong>
        <div class="tag-item"> {{ currentType.typeName }}</div>
        <el-tag class="tag-item" size="small" closable type="success" effect="plain"
                v-for="(item,index) in selectTagList"
                :key="index"
                @close="tagClose(index,item)">{{ item.value }}
        </el-tag>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'index',
  props: {
    typeList: {
      type: Array,
      default: () => []
    },
    tagList: {
      type: Array,
      default: () => []
    },
    label: {
      type: String,
      default: '请输入资讯关键字'
    }
  },
  watch: {
    typeList: {
      deep: true,
      immediate: true,
      handler(newVal) {
        let first = {
          id: 0,
          typeName: '全部'
        }
        let arr = [first]
        arr.push.apply(arr, newVal)
        this.currentType = first
        this.allTypeList = arr
      }
    }
  },
  data() {
    return {
      keyword: '',
      current: 0,
      currentType: {},
      allTypeList: [],
      selectTagList: []
    }
  },
  methods: {
    searchClick() {
      this.$emit('searchClick', this.keyword)
    },
    typeClick(index, item) {
      this.currentType = item
      this.current = index
      this.$emit('typeClick', item)
    },
    tagClick(item) {
      let objItem = this.selectTagList.find(tag => {
        return tag.value == item.value
      })
      if (!objItem) {
        this.selectTagList.push(item)
        this.$emit('tagClick', this.selectTagList)
      }
    },
    tagClose(index, item) {
      this.selectTagList.splice(index, 1)
      this.$emit('tagClick', this.selectTagList)
    }
  }
}
</script>

<style scoped>
.type-list {
  height: 40px;
  line-height: 40px;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  font-size: 12px;
  color: #999;
  cursor: pointer;
}

.first-title {
  width: 40px;
  color: black;
  font-weight: bold;
  margin-right: 20px;
}

.type-item {
  margin-right: 20px;
}

.type-active {
  color: #5c81e3;
}

.tag-item {
  margin-right: 20px;
}
</style>
