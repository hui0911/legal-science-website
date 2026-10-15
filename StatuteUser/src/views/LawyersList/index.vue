<template>
  <div>
    <BasicHeader :activeIndex="3"></BasicHeader>
    <div class="mc mt20">
      <el-breadcrumb separator="/" class="mb20">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>律师事务所</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="mc mt20">
      <div style="text-align: center;font-family: 微软雅黑;font-size: 28px;font-weight: bold">选择专业律师，帮您争取更大权益</div>
      <el-row :gutter="20" class="mt20">
        <el-col :span="6" style="margin-bottom: 10px" v-for="(item,index) in dataList" :key="index">
          <el-card>
            <img :src="item.avatar" style="width: 100%;height: 300px"/>
            <div style="text-align: center;font-weight: bold;font-size: 20px;margin-bottom: 10px">{{ item.name }}</div>
            <div class="text3" style="height: 60px;margin-bottom: 20px;font-size: 14px;color: #999">
              简介：{{ item.info }}
            </div>
            <div style="text-align: right">
              <el-button type="text" @click="toDetail(item)">立刻联系</el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <pagination v-show="total>0"
                  :total="total"
                  :pageSizes="[8, 16, 32, 64]"
                  :page.sync="pageParam.current"
                  :limit.sync="pageParam.pageSize"
                  @pagination="getTableDataList"></pagination>
    </div>

    <div class="ai-btn" @click="dialogVisible = true">
      <img src="../../assets/zixun.png"/>
      <div style="margin-top: 10px">法律咨询小助手</div>
    </div>
    <Footer></Footer>
    <el-backtop></el-backtop>
    <el-dialog
      title="法律咨询小助手" :close-on-click-modal="false"
      :visible.sync="dialogVisible"
      width="40%">
      <div style="margin: 10px 0">本助手回复仅提供参考不具备法律效应，如有疑问请咨询相关律师</div>
      <div ref="scrollableDiv"
           style="height: 300px;border-radius: 3px;border: 1px solid #e2e2e2;padding: 20px;background-color: #e2e2e2;overflow: auto"
      >
        <div class="message-container left-message" v-for="(item,index) in messageList" :key="index"
             :class="item.position == 'right' ? 'left-message' : 'right-message'"
        >
          <img class="message-img"
               :src="item.position == 'right' ? 'https://code-grade-server-prd.oss-cn-shenzhen.aliyuncs.com/2024-05-17/9090b3da-9f57-429f-8e9d-225d549d1782_robot.png'
                  : 'https://code-grade-server-prd.oss-cn-shenzhen.aliyuncs.com/2023-05-08/648dd276-c058-47c3-8af3-5bf276a91723_default_user.png'"
          />
          <div class="message-info">
            {{ item.content }}
          </div>
        </div>
      </div>
      <div style="margin-top: 10px">
        <div style="margin: 4px 0">您的问题：</div>
        <el-input type="textarea" v-model="message"></el-input>
        <div style="margin-top: 10px">
          <el-button size="mini" @click="saveMessageClick" type="success" :icon="loading ? 'el-icon-loading' : ''">
            {{ !loading ? '发送' : 'ai回复中...' }}
          </el-button>
        </div>
        <div style="margin: 10px 0">相关问题：</div>
        <div v-for="(item,index) in questionList" :key="index" style="padding: 4px 4px;
        margin-bottom: 10px;border: 1px solid #e2e2e2;width: auto;border-radius: 5px;cursor: pointer"
             @click="questionClick(index)">
          {{ index + 1 }}：{{ item }}
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">关闭</el-button>
  </span>
    </el-dialog>


  </div>
</template>

<script>
import {lawyersPage, query} from "@/api/index";

export default {
  name: "index",
  data() {
    return {
      total: 0,
      loading: false,
      pageParam: {
        current: 1,
        pageSize: 8,
        title: '',
        status: 1
      },
      dataList: [],
      message: "",
      dialogVisible: false,
      messageList: [],
      questionList: ["邻居把电车用电梯推上楼犯法吗？", "老板扣押员工工资，怎么求助？", "小区消防通道经常被占用怎么投诉？"]
    }
  },
  created() {
    this.init()
  },
  methods: {
    scrollToBottom() {
      const scrollableDiv = this.$refs.scrollableDiv;
      scrollableDiv.scrollTop = scrollableDiv.scrollHeight;
    },
    init() {
      this.getTableDataList()
    },
    questionClick(index) {
      this.message = this.questionList[index]
      this.saveMessageClick();
    },
    saveMessageClick() {
      let param = {}
      param.content = this.message
      param.position = 'left'
      this.loading = true
      this.message = ""
      this.messageList.push(param)
      this.$nextTick(() => {
        this.scrollToBottom()
      })
      query(param).then(res => {
        console.log(res)
        let param1 = {}
        param1.content = res.data
        param1.position = 'right'
        this.messageList.push(param1)
        this.$nextTick(() => {
          this.scrollToBottom()
        })
      }).finally(() => {
        this.loading = false
      })
    },
    getTableDataList() {
      lawyersPage(this.pageParam).then(res => {
        this.total = res.data.total
        this.dataList = res.data.records
      })
    },
    toDetail(item) {
      this.$router.push("/lawyerDetail/" + item.id)
    }
  }
}
</script>

<style scoped>
.ai-btn {
  padding: 10px;
  border: 1px solid #e2e2e2;
  position: fixed;
  right: 50px;
  display: flex;
  background-color: white;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  bottom: 20%;
  color: #999;
  font-size: 14px;
  border-radius: 10px;
  cursor: pointer;
}

.message-container {
  display: flex;
  align-items: center;
  margin-bottom: 10px;

}

.message-img {
  height: 40px;
  width: 40px;
  border-radius: 50%;
}

.message-info {
  display: flex;
  align-items: center;
  border-radius: 10px;
  padding: 5px 10px;
  min-height: 30px;
  color: black;
  font-weight: bold;
}

.left-message img {
  margin-right: 10px;
}

.left-message div {
  background-color: white;
}

.right-message {
  flex-direction: row-reverse;

}

.right-message img {
  margin-left: 10px;
}

.right-message div {
  background-color: #4cd964;
}

</style>
