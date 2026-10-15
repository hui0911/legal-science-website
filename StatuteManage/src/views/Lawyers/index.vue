<template>
  <div>
    <el-card v-if="!isLaw" shadow="hover" style="margin-bottom: 20px">
      <div class="flex-warp">
        <div class="flex-warp-item">
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

        <div class="flex-warp-item">
          <div class="flex-warp-item-box">
            <el-button
              type="success"
              size="small"
              icon="el-icon-plus"
              @click="add"
            >添加
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
          prop="id"
          align="center"
          label="律师编号"
        />
        <el-table-column
          prop="name"
          align="center"
          label="律师名称"
        />
        <el-table-column
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
          prop="email"
          align="center"
          label="邮箱"
        />
        <el-table-column
          prop="level"
          align="center"
          label="律师等级"
        >
          <template slot-scope="scope">
            <span v-if="scope.row.level == '0'">助理律师</span>
            <span v-if="scope.row.level == '1'">四级律师</span>
            <span v-if="scope.row.level == '2'">三级律师</span>
            <span v-if="scope.row.level == '3'">二级律师</span>
            <span v-if="scope.row.level == '4'">一级律师</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="info"
          align="center"
          label="简历"
        >
          <template slot-scope="{row}">
            <el-popover
              placement="right"
              width="400"
              trigger="click"
            >
              <span>{{ row.info }}</span>
              <el-button slot="reference" size="mini">点击预览</el-button>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
          prop="certificate"
          align="center"
          width="80"
          label="证书"
        >
          <template slot-scope="scope">
            <ImgPreview :src="scope.row.certificate" />
          </template>
        </el-table-column>
        <el-table-column
          prop="age"
          align="center"
          label="年龄"
        />
        <el-table-column
          prop="sex"
          align="center"
          label="性别"
        >
          <template slot-scope="scope">
            <span v-if="scope.row.sex == '0'">男</span>
            <span v-if="scope.row.sex == '1'">女</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="status"
          align="center"
          label="状态"
        >
          <template slot-scope="scope">
            <span v-if="scope.row.status == '0'">审核中</span>
            <span v-if="scope.row.status == '1'">审核通过</span>
            <span v-if="scope.row.status == '2'">审核不通过</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="createTime"
          align="center"
          width="180"
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
              @click="updateClick(scope.row)"
            >编辑
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
        v-show="total>0 && !isLaw"
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
          label="律师名称"
          prop="name"
        >
          <el-input v-model="formData.name" />
        </el-form-item>
        <el-form-item
          label="律师头像"
          prop="avatar"
        >
          <uploadImg v-model="formData.avatar" />
        </el-form-item>
        <el-form-item
          label="邮箱"
          prop="email"
        >
          <el-input v-model="formData.email" :disabled="isLaw" />
        </el-form-item>
        <el-form-item
          v-if="!isLaw"
          label="律师等级"
          prop="level"
        >
          <el-radio-group v-model="formData.level">
            <el-radio :label="0">助理律师</el-radio>
            <el-radio :label="1">四级律师</el-radio>
            <el-radio :label="2">三级律师</el-radio>
            <el-radio :label="3">二级律师</el-radio>
            <el-radio :label="4">一级律师</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          label="简历"
          prop="info"
        >
          <el-input v-model="formData.info" type="textarea" :autosize="{ minRows: 8, maxRows: 8}" />
        </el-form-item>
        <el-form-item
          label="证书"
          prop="certificate"
        >
          <uploadImg v-model="formData.certificate" />
        </el-form-item>
        <el-form-item
          label="年龄"
          prop="age"
        >
          <el-input-number v-model="formData.age" :max="100" />
        </el-form-item>
        <el-form-item
          label="性别"
          prop="sex"
        >
          <el-radio-group v-model="formData.sex">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          label="登录密码"
          prop="password"
        >
          <el-input v-model="formData.password" />
        </el-form-item>
        <el-form-item
          v-if="!isLaw"
          label="状态"
          prop="status"
        >
          <el-radio-group v-model="formData.status">
            <el-radio :label="0">审核中</el-radio>
            <el-radio :label="1">审核通过</el-radio>
            <el-radio :label="2">审核不通过</el-radio>
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
import { getList, insert, update, get, del } from '@/api/Lawyers/index'
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
        name: [
          { required: true, message: '请输入律师名称', trigger: 'blur' }
        ],
        avatar: [
          { required: true, message: '请上传律师头像', trigger: 'change' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
        level: [
          { required: true, message: '请选择律师等级', trigger: 'change' }
        ],
        info: [
          { required: true, message: '请输入简历', trigger: 'blur' }
        ],
        certificate: [
          { required: true, message: '请上传证书', trigger: 'change' }
        ],
        age: [
          { required: true, message: '请输入年龄', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        status: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ]
      }
    }
  },
  mounted() {
    this.isLaw = localStorage.getItem('statuteType') == 1
    this.getTableDataList()
  },
  methods: {
    getTableDataList() {
      if (this.isLaw) {
        this.listQueryParam.id = localStorage.getItem('statuteId')
      }
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
    }
  }
}
</script>
