<template>
  <div>
    <BasicHeader :activeIndex="1"></BasicHeader>
    <div class="mc mt20">
      <el-breadcrumb separator="/" class="mb20">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>法律资讯</el-breadcrumb-item>
      </el-breadcrumb>
      <tag-type :typeList="typeList" @searchClick="searchClick" @typeClick="typeClick"></tag-type>
    </div>
    <div class="mc" v-loading="loading">
      <text-line :text="typeName"></text-line>
      <el-card style="margin-bottom: 10px">
        <OblongItemList :dataList="noticeList"></OblongItemList>
      </el-card>
      <pagination v-show="total>0"
                  :total="total"
                  :page.sync="pageParam.current"
                  :limit.sync="pageParam.pageSize"
                  @pagination="getTableDataList"></pagination>
    </div>
    <Footer></Footer>
    <el-backtop></el-backtop>
  </div>
</template>

<script>
import TagType from '@/components/TagType/index'
import TextLine from '@/components/TextLine'
import OblongItemList from '@/components/OblongItemList'
import {getAllType, getNoticeByType} from "@/api/index";

export default {
  name: "index",
  components: {
    TagType, TextLine, OblongItemList
  },
  data() {
    return {
      typeName: '全部',
      typeList: [],
      loading: false,
      total: 0,
      pageParam: {
        current: 1,
        pageSize: 5,
        title: ''
      },
      noticeList: []
    }
  },
  created() {
    this.init()
  },
  methods: {
    itemClick(item) {
      this.$router.push("/detail/" + item.id)
    },
    searchClick(key) {
      this.pageParam.title = key
      this.pageParam.current = 1
      this.getTableDataList()
    },
    init() {
      getAllType({}).then(res => {
        this.typeList = res.data
      })
      this.getTableDataList()
    },
    typeClick(item) {
      this.typeName = item.typeName
      this.pageParam.typeId = item.id == 0 ? null : item.id
      this.pageParam.current = 1
      this.getTableDataList()
    },
    getTableDataList() {
      this.loading = true
      getNoticeByType(this.pageParam).then(res => {
        this.total = res.data.total
        this.noticeList = res.data.records
      }).finally(() => {
        this.loading = false
      })
    }
  }
}
</script>

<style scoped>

</style>
