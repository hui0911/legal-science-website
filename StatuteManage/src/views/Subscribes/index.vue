<template>
  <div>
    <el-card shadow="hover" style="margin-bottom: 20px">
      <div class="flex-warp">
        <div class="flex-warp-item">
          <div class="flex-warp-item-box">
            <el-input
              v-model="listQueryParam.nickName"
              placeholder="请输入用户姓名"
              size="small"
              suffix-icon="el-icon-search"
            />
          </div>
        </div>
        <div v-if="!isLaw" class="flex-warp-item">
          <div class="flex-warp-item-box">
            <el-input
              v-model="listQueryParam.name"
              placeholder="请输入律师名称"
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

        <!--        <div class="flex-warp-item">-->
        <!--          <div class="flex-warp-item-box">-->
        <!--            <el-button type="success"-->
        <!--                       size="small"-->
        <!--                       icon="el-icon-plus"-->
        <!--                       @click="add">添加 </el-button>-->
        <!--          </div>-->
        <!--        </div>-->

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
          align="center"
          width="80"
          label="用户头像"
        >
          <template slot-scope="scope">
            <ImgPreview :src="scope.row.headImg" />
          </template>
        </el-table-column>
        <el-table-column
          prop="subscribeTime"
          align="center"
          label="预约时间"
        />
        <el-table-column
          prop="status"
          align="center"
          label="审核状态"
        >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status == '0'" type="info">审核中</el-tag>
            <el-tag v-if="scope.row.status == '1'" type="success">审核通过</el-tag>
            <el-tag v-if="scope.row.status == '2'" type="danger">拒绝</el-tag>
            <el-tag v-if="scope.row.status == '3'" type="primary">已完成</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="createTime"
          align="center"
          label="创建时间"
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
              v-if="!isLaw"
              type="text"
              size="small"
              @click="updateClick(scope.row)"
            >编辑
            </el-button>
            <el-button
              v-if="scope.row.status == 0"
              type="text"
              size="small"
              @click="updateStatus(scope.row,1)"
            >审核通过
            </el-button>
            <el-button
              v-if="scope.row.status == 2"
              type="text"
              size="small"
              @click="updateStatus(scope.row,2)"
            >拒绝通过
            </el-button>
            <el-button
              v-if="scope.row.status == 1"
              type="text"
              size="small"
              @click="updateStatus(scope.row,3)"
            >已完成
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
          label="预约用户"
          prop="userId"
        >
          <el-select v-model="formData.userId" clearable placeholder="请选择预约用户">
            <el-option
              v-for="item in userIdOptions"
              :key="item.key"
              :label="item.value"
              :value="item.key"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="所属律师"
          prop="lawyerId"
        >
          <el-select v-model="formData.lawyerId" clearable placeholder="请选择所属律师">
            <el-option
              v-for="item in lawyerIdOptions"
              :key="item.key"
              :label="item.value"
              :value="item.key"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="预约时间"
        >
          <el-date-picker
            v-model="formData.subscribeTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择预约时间"
          />
        </el-form-item>
        <el-form-item
          label="审核状态"
          prop="status"
        >
          <el-radio-group v-model="formData.status">
            <el-radio label="0">审核中</el-radio>
            <el-radio label="1">审核通过</el-radio>
            <el-radio label="2">拒绝</el-radio>
            <el-radio label="3">已完成</el-radio>
          </el-radio-group>
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
import { getList, insert, update, get, del, getUsersDate, getLawyersDate } from '@/api/Subscribes/index'
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
          { required: true, message: '请选择预约用户', trigger: 'change' }
        ],
        lawyerId: [
          { required: true, message: '请选择所属律师', trigger: 'change' }
        ],
        status: [
          { required: true, message: '请选择审核状态', trigger: 'change' }
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
    updateStatus(row, status) {
      const param = {}
      param.id = row.id
      param.status = status
      update(param).then((res) => {
        Message.success('操作成功')
        this.getTableDataList()
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
