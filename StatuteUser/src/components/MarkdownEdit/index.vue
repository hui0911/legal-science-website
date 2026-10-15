<template>
  <div>
    <mavon-editor ref="md"
                  class="mt15"
                  style="height:700px"
                  v-model="content"
                  :ishljs="true"
                  @imgAdd="imgAdd"
                  @imgDel="imgDel"
    />
  </div>
</template>

<script>

import { uploadFile } from '@/api/upload'

export default {
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  watch: {
    value: {
      handler(val) {
        this.content = val
      },
      deep: true,
      immediate: true
    },
    content: {
      handler(val) {
        this.$emit('input', val)
      },
      deep: true,
      immediate: true
    }
  },
  data() {
    return {
      content: '',
      imgFile: []
    }
  },
  methods: {
    imgAdd(pos, $file) {
      let formdata = new FormData()
      this.imgFile[pos] = $file
      formdata.append('file', $file)
      // 第一步.将图片上传到服务器.
      uploadFile(formdata).then(res => {
        let url = res.data
        let name = $file.name
        if (name.includes('-')) {
          name = name.replace(/-/g, '')
        }
        let content = this.content
        // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)  这里是必须要有的
        if (content.includes(name)) {
          let oStr = `(${pos})`
          let nStr = `(${url})`
          let index = content.indexOf(oStr)
          let str = content.replace(oStr, '')
          let insertStr = (soure, start, newStr) => {
            return soure.slice(0, start) + newStr + soure.slice(start)
          }
          this.content = insertStr(str, index, nStr)
        }
      }).catch(err => {
        console.log(err)
      })
    },
    imgDel(pos) {
      delete this.imgFile[pos]
    }
  }

}

</script>

<style scoped>

</style>
