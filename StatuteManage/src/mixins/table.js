import { Message } from 'element-ui'

export const tableUtil = {
  data() {
    return {
      // 分页参数
      total: 0,
      // 表格数据
      tableData: [],
      // 添加修改对话框标题
      dialogTitle: '新增',
      // 添加修改对话框是否显示条件
      addOrUpdateDialogVisible: false,
      // 需要删除的数据ID集合
      delIds: []
    }
  },
  methods: {
    // 表格上方搜索按钮点击事件
    search() {
      this.listQueryParam.current = 1
      this.getTableDataList()
    },
    // 表格上方刷新按钮点击事件
    refresh() {
      const pageSize = this.listQueryParam.size
      this.listQueryParam = {
        current: 1,
        pageSize: pageSize || 5
      }
      this.getTableDataList()
    },
    // md文档内容处理
    handleMd(content) {
      const MardownIt = require('markdown-it')
      var md = new MardownIt()
      return md.render(content)
    },
    // 重置表单数据
    reset() {
      const obj = this.formData
      for (const key in obj) {
        obj[key] = undefined
      }
      this.formData = obj
    },
    // 表格上方添加按钮点击事件
    add() {
      this.reset()
      this.dialogTitle = '新增'
      this.addOrUpdateDialogVisible = true
    },
    // 表格第一列多选框选择回调事件
    handleSelectionChange(row) {
      this.delIds = []
      if (row && row.length > 0) {
        const ids = []
        for (const i in row) {
          ids.push(row[i].id)
        }
        this.delIds = ids
      }
    },
    submitForm(formName) {
      const that = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          that.saveData()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    batchDel() {
      if (this.delIds.length > 0) {
        this.$confirm('删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            this.delByIds(this.delIds)
          })
          .catch(() => {
            this.$refs.multipleTable.clearSelection()
          })
      } else {
        Message.warning('请先选择需要删除的记录!')
      }
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then((_) => {
          done()
        })
        .catch((_) => {
        })
    },
    delClick(row) {
      const ids = [row.id]
      this.delByIds(ids)
    }
  }
}
