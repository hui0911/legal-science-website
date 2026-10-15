<template>
  <div>
    <BasicHeader :activeIndex="6"></BasicHeader>
    <div class="mc mt20">
      <el-breadcrumb separator="/" class="mb20">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>个人中心</el-breadcrumb-item>
      </el-breadcrumb>
      <el-card class="mb20">
        <el-tabs v-model="activeName">
          <el-tab-pane label="个人信息" name="first">
            <UserInfo></UserInfo>
          </el-tab-pane>
          <el-tab-pane label="留言记录" name="sec">
            <el-table :data="tableData"
                      stripe
                      ref="multipleTable"
                      style="width: 100%"
            >
              <el-table-column prop="name"
                               align="center"
                               label="律师名称"
              >
              </el-table-column>
              <el-table-column prop="avatar"
                               align="center"
                               label="律师头像"
              >
                <template slot-scope="scope">
                  <ImgPreview :src="scope.row.avatar"></ImgPreview>
                </template>
              </el-table-column>
              <el-table-column prop="content"
                               align="center"
                               label="留言内容"
              >
              </el-table-column>
              <el-table-column prop="answer"
                               align="center"
                               label="律师回复"
              >
              </el-table-column>
              <el-table-column prop="createTime"
                               align="center"
                               label="留言时间"
              >
              </el-table-column>
              <el-table-column prop="updateTime"
                               align="center"
                               label="回复时间"
              >
              </el-table-column>

              <el-table-column fixed="right"
                               align="center"
                               label="记录操作"
              >
                <template slot-scope="scope">
                  <el-button type="text"
                             size="small"
                             @click="delClick(scope.row)"
                  >删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
            <pagination v-show="total>0"
                        :total="total"
                        :page.sync="pageParam.current"
                        :limit.sync="pageParam.pageSize"
                        @pagination="getTableDataList"
            />
          </el-tab-pane>
          <el-tab-pane label="预约记录" name="three">
            <el-table :data="tableData1"
                      stripe
                      ref="multipleTable"
                      style="width: 100%">
              <el-table-column prop="name"
                               align="center"
                               label="律师名称">
              </el-table-column>
              <el-table-column prop="avatar"
                               align="center"
                               label="律师头像">
                <template slot-scope="scope">
                  <ImgPreview :src="scope.row.avatar"></ImgPreview>
                </template>
              </el-table-column>
              <el-table-column prop="subscribeTime"
                               align="center"
                               label="预约时间">
              </el-table-column>
              <el-table-column prop="status"
                               align="center"
                               label="审核状态">
                <template slot-scope="scope">
                  <span v-if="scope.row.status == '0'">审核中</span>
                  <span v-if="scope.row.status == '1'">审核通过</span>
                  <span v-if="scope.row.status == '2'">拒绝</span>
                  <span v-if="scope.row.status == '3'">已完成</span>
                </template>
              </el-table-column>
              <el-table-column prop="createTime"
                               align="center"
                               label="创建时间">
              </el-table-column>

              <el-table-column fixed="right"
                               align="center"
                               label="记录操作">
                <template slot-scope="scope">
                  <el-button type="text" v-if="scope.row.status == 0 || scope.row.status == 1"
                             size="small"
                             @click="delClick1(scope.row)">取消预约
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
            <pagination v-show="total1>0"
                        :total="total1"
                        :page.sync="pageParam1.current"
                        :limit.sync="pageParam1.pageSize"
                        @pagination="getTableDataList1"/>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </div>
    <Footer fixed></Footer>
    <el-backtop></el-backtop>
  </div>
</template>

<script>
import UserInfo from '@/components/UserInfo'
import {Message} from "element-ui";
import {delMessage, delSubscribes, messagePage, subscribesPage} from "@/api/index";

export default {
  name: "index",
  components: {
    UserInfo
  },
  data() {
    return {
      activeName: 'first',
      total: 0,
      total1: 0,
      pageParam: {
        current: 1,
        pageSize: 5,
        userId: this.$store.state.userInfo.id
      },
      tableData: [],
      pageParam1: {
        current: 1,
        pageSize: 5,
        userId: this.$store.state.userInfo.id
      },
      tableData1: []
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      this.getTableDataList()
      this.getTableDataList1()
    },
    getTableDataList() {
      messagePage(this.pageParam).then(res => {
        this.total = res.data.total
        this.tableData = res.data.records
      })
    },
    delClick(row) {
      delMessage(row.id).then(res => {
        this.getTableDataList()
      })
    },
    delClick1(row) {
      delSubscribes(row.id).then(res => {
        this.getTableDataList1()
      })
    },
    getTableDataList1() {
      subscribesPage(this.pageParam1).then(res => {
        this.total1 = res.data.total
        this.tableData1 = res.data.records
      })
    }
  }
}
</script>

<style scoped>

</style>
