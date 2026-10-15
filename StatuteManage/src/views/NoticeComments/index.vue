<template>
  <div>
    <el-card shadow="hover" style="margin-bottom: 20px">
      <div class="flex-warp">
        <div class="flex-warp-item">
          <div class="flex-warp-item-box">
            <el-input
              v-model="listQueryParam.commentInfo"
              placeholder="请输入评价内容"
              size="small"
              suffix-icon="el-icon-search"
            />
          </div>
        </div>
        <div class="flex-warp-item">
          <div class="flex-warp-item-box">
            <el-input
              v-model="listQueryParam.title"
              placeholder="请输入资讯标题"
              size="small"
              suffix-icon="el-icon-search"
            />
          </div>
        </div>
        <div class="flex-warp-item">
          <div class="flex-warp-item-box">
            <el-input
              v-model="listQueryParam.nickName"
              placeholder="请输入姓名"
              size="small"
              suffix-icon="el-icon-search"
            />
          </div>
        </div>
        <div class="flex-warp-item">
          <div class="flex-warp-item-box">
            <el-button
              size="small"
              icon="el-icon-search"
              @click="search"
            >搜索 </el-button>
          </div>
        </div>

        <div class="flex-warp-item">
          <div class="flex-warp-item-box">
            <el-button
              type="primary"
              size="small"
              icon="el-icon-refresh-right"
              @click="refresh"
            >刷新 </el-button>
          </div>
        </div>

      </div>
    </el-card>
    <el-card
      shadow="hover"
      class="mt15"
    >
      <el-table
        ref="multipleTable"
        :data="tableData"
        stripe
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >

        <el-table-column
          prop="title"
          align="center"
          label="资讯标题"
        />
        <el-table-column
          prop="bannerImg"
          align="center"
          width="80"
          label="资讯图片"
        >
          <template slot-scope="scope">
            <ImgPreview :src="scope.row.bannerImg" />
          </template>
        </el-table-column>
        <el-table-column
          prop="nickName"
          align="center"
          label="姓名"
        />
        <el-table-column
          prop="headImg"
          align="center"
          width="80"
          label="头像"
        >
          <template slot-scope="scope">
            <ImgPreview :src="scope.row.headImg" />
          </template>
        </el-table-column>

        <el-table-column
          prop="commentInfo"
          align="center"
          label="评价内容"
        />
        <el-table-column
          prop="createTime"
          align="center"
          label="创建时间"
        />

        <el-table-column
          fixed="right"
          align="center"
          label="记录操作"
        >
          <template slot-scope="scope">

            <el-button
              type="text"
              size="small"
              @click="delClick(scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="listQueryParam.current"
        :limit.sync="listQueryParam.pageSize"
        @pagination="getTableDataList"
      />
    </el-card>
    <!-- 添加或修改对话框 -->
    <el-dialog
      :title="dialogTitle"
      :visible.sync="addOrUpdateDialogVisible"
      width="60%"
      :before-close="handleClose"
    >
      <el-form
        ref="formData"
        :model="formData"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item
          label="评价内容"
          prop="commentInfo"
        >
          <el-input v-model="formData.commentInfo" />
        </el-form-item>
        <el-form-item
          label="评论用户"
          prop="userId"
        >
          <el-input v-model="formData.userId" />
        </el-form-item>
        <el-form-item
          label="所属资讯"
          prop="noticeId"
        >
          <el-input v-model="formData.noticeId" />
        </el-form-item>
        <el-form-item
          label="回复人编号"
          prop="toUserId"
        >
          <el-input v-model="formData.toUserId" />
        </el-form-item>
        <el-form-item
          label="父级评论编号"
          prop="parentId"
        >
          <el-input v-model="formData.parentId" />
        </el-form-item>
      </el-form>
      <span
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="addOrUpdateDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="submitForm('formData')"
        >确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getList, insert, update, get, del } from '@/api/NoticeComments/index'
import Pagination from '@/components/Pagination'
import { tableUtil } from '@/mixins/table'
import { Message } from 'element-ui'
import uploadImg from '@/components/ImageUpload'
import ImgPreview from '@/components/ImgPreview'
import MarkdownEdit from '@/components/MarkdownEdit'
import 'github-markdown-css'
export default {
  components: {
    Pagination, uploadImg, ImgPreview, MarkdownEdit
  },
  mixins: [tableUtil],
  data() {
    return {
      listQueryParam: {
        current: 1,
        pageSize: 5
      },
      formData: {},
      rules: {
        commentInfo: [
          { required: true, message: '请输入评价内容', trigger: 'blur' }
        ],
        userId: [
          { required: true, message: '请输入评论用户', trigger: 'blur' }
        ],
        noticeId: [
          { required: true, message: '请输入所属资讯', trigger: 'blur' }
        ],
        toUserId: [
          { required: true, message: '请输入回复人编号', trigger: 'blur' }
        ],
        parentId: [
          { required: true, message: '请输入父级评论编号', trigger: 'blur' }
        ]
      }}
  },
  mounted() {
    this.getTableDataList()
  },
  methods: {
    getTableDataList() {
      getList(this.listQueryParam).then((res) => {
        this.total = res.data.total
        this.tableData = res.data.records
      })
    },
    updateClick(row) {
      this.dialogTitle = '修改'
      get(row.id).then((res) => {
        this.formData = res.data
        this.addOrUpdateDialogVisible = true
      })
    },
    delByIds(ids) {
      del(ids).then((res) => {
        Message.success('删除成功')
        this.getTableDataList()
      })
    },
    saveData() {
      if (this.formData.id) {
        update(this.formData).then((res) => {
          Message.success('修改成功')
          this.addOrUpdateDialogVisible = false
          this.getTableDataList()
        })
      } else {
        insert(this.formData).then((res) => {
          Message.success('添加成功')
          this.addOrUpdateDialogVisible = false
          this.getTableDataList()
        })
      }
    } }
}
</script>
