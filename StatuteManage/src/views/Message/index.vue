<template>
  <div>
    <el-card shadow="hover" style="margin-bottom: 20px">
      <div class="flex-warp">
        <div class="flex-warp-item">
          <div class="flex-warp-item-box">
            <el-input
              v-if="!isLaw"
              v-model="listQueryParam.name"
              placeholder="请输入律师名称"
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
            >搜索
            </el-button>
          </div>
        </div>

        <div class="flex-warp-item">
          <div class="flex-warp-item-box">
            <el-button
              type="primary"
              size="small"
              icon="el-icon-refresh-right"
              @click="refresh"
            >刷新
            </el-button>
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
          prop="nickName"
          align="center"
          label="用户姓名"
        />
        <el-table-column
          prop="headImg"
          width="80"
          align="center"
          label="用户头像"
        >
          <template slot-scope="scope">
            <ImgPreview :src="scope.row.headImg" />
          </template>
        </el-table-column>
        <el-table-column
          prop="content"
          align="center"
          label="留言内容"
        />
        <el-table-column
          prop="answer"
          align="center"
          label="回复内容"
        />
        <el-table-column
          prop="createTime"
          align="center"
          label="留言时间"
        />
        <el-table-column
          prop="updateTime"
          align="center"
          label="回复时间"
        />
        <el-table-column
          v-if="!isLaw"
          prop="name"
          align="center"
          label="律师名称"
        />
        <el-table-column
          v-if="!isLaw"
          prop="avatar"
          align="center"
          width="80"
          label="律师头像"
        >
          <template slot-scope="scope">
            <ImgPreview :src="scope.row.avatar" />
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          align="center"
          label="记录操作"
        >
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="updateClick(scope.row)"
            >回复
            </el-button>
            <el-button
              v-if="!isLaw"
              type="text"
              size="small"
              @click="delClick(scope.row)"
            >删除
            </el-button>
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
      width="40%"
      :before-close="handleClose"
    >
      <el-form
        ref="formData"
        :model="formData"
        :rules="rules"
        label-width="100px"
      >

        <el-form-item
          label="留言内容"
          prop="content"
        >
          <el-input v-model="formData.content" disabled type="textarea" :autosize="{ minRows: 8, maxRows: 8}" />
        </el-form-item>
        <el-form-item
          label="回复内容"
          prop="answer"
        >
          <el-input v-model="formData.answer" type="textarea" :autosize="{ minRows: 8, maxRows: 8}" />
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
import { getList, insert, update, get, del, getUsersDate, getLawyersDate } from '@/api/Message/index'
import Pagination from '@/components/Pagination'
import { tableUtil } from '@/mixins/table'
import { Message } from 'element-ui'
import uploadImg from '@/components/ImageUpload'
import ImgPreview from '@/components/ImgPreview'

export default {
  components: {
    Pagination, uploadImg, ImgPreview
  },
  mixins: [tableUtil],
  data() {
    return {
      isLaw: false,
      listQueryParam: {
        current: 1,
        pageSize: 5
      },
      formData: {},
      rules: {
        userId: [
          { required: true, message: '请选择留言用户', trigger: 'change' }
        ],
        lawyerId: [
          { required: true, message: '请选择律师编号', trigger: 'change' }
        ],
        content: [
          { required: true, message: '请输入留言内容', trigger: 'blur' }
        ],
        answer: [
          { required: true, message: '请输入回复内容', trigger: 'blur' }
        ]
      },
      userIdOptions: [],
      lawyerIdOptions: []
    }
  },
  mounted() {
    this.isLaw = localStorage.getItem('statuteType') == 1
    this.getTableDataList()
    this.getUsersDateList()
    this.getLawyersDateList()
  },
  methods: {
    getTableDataList() {
      if (this.isLaw) {
        this.listQueryParam.lawyerId = localStorage.getItem('statuteId')
      }
      getList(this.listQueryParam).then((res) => {
        this.total = res.data.total
        this.tableData = res.data.records
      })
    },
    updateClick(row) {
      this.dialogTitle = '回复'
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
    },
    getUsersDateList() {
      getUsersDate().then(res => {
        this.userIdOptions = res.data
      })
    },
    getLawyersDateList() {
      getLawyersDate().then(res => {
        this.lawyerIdOptions = res.data
      })
    }
  }
}
</script>
