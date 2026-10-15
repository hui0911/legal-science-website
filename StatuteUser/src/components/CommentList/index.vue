<template>
  <div>
    <el-card>
      <h2 style="margin-bottom: 20px">评论</h2>
      <div style="display: flex">
        <img :src="defaultUserImg" class="user-img"/>
        <el-input type="textarea" placeholder="期待您的发言" maxlength="100" show-word-limit v-model="info"
                  :autosize="{ minRows: 5, maxRows: 5 }"></el-input>

      </div>
      <div style="margin-top: 20px;">
        <el-button type="success" size="small" style="float: right" @click="submitInfo">提交</el-button>
      </div>
      <div>
        <div style="margin-top: 20px;font-size: 16px">{{ dataList.length }} 评论</div>
        <el-divider></el-divider>
      </div>
      <div>
        <div v-for="(item,index) in dataList" :key="index" style="border-bottom: 1px solid #e2e2e2">
          <div class="comment-header">
            <img :src="item[headerImgKey] ? item[headerImgKey] : defaultUserImg" class="user-img"/>
            <span class="comment-title">{{ item[nickNameKey] }}</span>
            <div style="margin:  0 auto"></div>
            <div class="btn huifu" @click="sendInfo(item,item.id)"
                 v-if=" item[fromUserKey] != id"><i
              class="el-icon-receiving"
            ></i>
              回复
            </div>
            <div class="btn del" @click="delInfo(item)"
                 v-if=" item[fromUserKey] == id"><i
              class="el-icon-delete"></i> 删除
            </div>
          </div>
          <div class="comment-content">
            <div style="margin-bottom: 10px">
              {{ item[contentKey] }}
            </div>
            <div>
              <div v-for="(chi,index1) in item[childrenListKey]" :key="index1">
                <div class="comment-header">
                  <img :src="chi[headerImgKey] ? chi[headerImgKey] : defaultUserImg" class="user-img"/>
                  <span class="comment-title">
                <strong> {{ chi[nickNameKey] }}</strong> 回复  {{ chi[nickNameToKey] }}
              </span>
                  <div style="margin:  0 auto"></div>
                  <div class="btn huifu" @click="sendInfo(chi,chi.parentId)"
                       v-if=" chi[fromUserKey] != id"><i
                    class="el-icon-receiving"
                  ></i>
                    回复
                  </div>
                  <div class="btn del" @click="delInfo(chi)"
                       v-if="chi[fromUserKey] == id"><i
                    class="el-icon-delete"></i> 删除
                  </div>

                </div>
                <div class="comment-content">
                  {{ chi[contentKey] }}
                </div>
                <div class="comment-footer">
                  <div style="color: #999999">回复时间：{{ chi[timeKey] }}</div>
                </div>
              </div>
            </div>
          </div>
          <div class="comment-footer">
            <div style="color: #999999">评论时间： {{ item[timeKey] }}</div>
          </div>
        </div>
      </div>
    </el-card>
    <el-dialog
      title="回复"
      :visible.sync="commentDia"
      width="30%">
      <el-input type="textarea" placeholder="期待您的回复" maxlength="100" show-word-limit v-model="nextInfo"
                :autosize="{ minRows: 5, maxRows: 5 }"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDia">取 消</el-button>
        <el-button type="primary" @click="saveInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: 'index',
  props: {
    // 评论数据
    dataList: {
      type: Array,
      default: () => []
    },
    // 子评论数据key
    childrenListKey: {
      type: String,
      default: 'childrenList'
    },
    // 发起人ID字段key
    fromUserKey: {
      type: String,
      default: 'fromUserId'
    },
    // 发起人头像key
    headerImgKey: {
      type: String,
      default: 'headerImg'
    },
    // 发起人昵称key
    nickNameKey: {
      type: String,
      default: 'nickName'
    },
    // 被评论人昵称的key
    nickNameToKey: {
      type: String,
      default: 'nickNameTo'
    },
    // 评论内容key
    contentKey: {
      type: String,
      default: 'content'
    },
    // 时间key
    timeKey: {
      type: String,
      default: 'createTime'
    },
  },
  data() {
    return {
      info: '',
      nextInfo: '',
      id: 0,
      commentDia: false,
      currentComment: {},
      parentId: 0,
      defaultUserImg: 'https://code-grade-server-prd.oss-cn-shenzhen.aliyuncs.com/2023-05-08/648dd276-c058-47c3-8af3-5bf276a91723_default_user.png'
    }
  },
  mounted() {
    this.id = this.$store.state.userInfo.id
  },
  methods: {
    submitInfo() {
      this.$emit('submitInfo', this.info)
      this.info = ''
    },
    closeDia() {
      this.commentDia = false
      this.nextInfo = ''
    },
    sendInfo(item, parentId) {
      this.commentDia = true
      this.parentId = parentId
      this.currentComment = item
    },
    delInfo(item) {
      this.$emit('delInfo', item)
    },
    sendError(item) {
      this.$emit('sendError', item)
    },
    saveInfo() {
      let param = {}
      param.toUserId = this.currentComment.userId
      param.commentInfo = this.nextInfo
      param.parentId = this.parentId
      this.$emit('saveInfo', param)
      this.nextInfo = ""
      this.commentDia = false
    },
  }
}
</script>

<style scoped>
.user-img {
  height: 40px;
  width: 40px;
  border-radius: 50%;
  margin-right: 10px
}

.comment-header {
  display: flex;
  height: 50px;
  align-items: center;
  font-size: 14px;
}

.comment-title {
  font-size: 14px;
}

.comment-content {
  margin-left: 50px;
  font-size: 14px;
  color: #999999;
}

.comment-footer {
  display: flex;
  font-size: 14px;
  margin: 10px 0;
  margin-left: 50px;
}

.huifu {
  margin-right: 20px;
}

.del {
  color: #e74949;
}

.btn {
  cursor: pointer;
}
</style>
