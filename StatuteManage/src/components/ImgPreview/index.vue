<template>
  <div style="display: flex;align-items: center;flex-direction: row;">
    <el-image
      v-for="url in imgUrl"
      :key="url"
      :src="url"
      lazy
      fit="cover"

      :style="`width:${realWidth};height:${realHeight};margin-right:5px`"
      :preview-src-list="imgUrl"
    >
      <div
        slot="error"
        class="image-slot"
      >
        <i class="el-icon-picture-outline" />
      </div>
    </el-image>
  </div>
</template>

<script>

export default {
  name: 'ImagePreview',
  props: {
    src: {
      type: String
    },
    width: {
      type: [Number, String],
      default: 60
    },
    height: {
      type: [Number, String],
      default: 60
    }
  },
  data() {
    return {
      imgUrl: []
    }
  },
  computed: {

    realWidth() {
      return typeof this.width === 'string' ? this.width : `${this.width}px`
    },
    realHeight() {
      return typeof this.height === 'string' ? this.height : `${this.height}px`
    }
  },
  watch: {
    src: {
      handler(newV) {
        if (!newV) this.imgUrl = null
        const real_src_list = newV.split(',')
        console.log('real_src_list:', real_src_list)
        const srcList = []
        real_src_list.forEach(item => {
          srcList.push(item)
        })
        this.imgUrl = srcList
      },
      deep: true,
      immediate: true
    }
  }
}
</script>

<style lang="scss" scoped>
.el-image {
  border-radius: 5px;
  background-color: #ebeef5;
  box-shadow: 0 0 5px 1px #ccc;

  ::v-deep .el-image__inner {
    transition: all 0.3s;
    cursor: pointer;

    &:hover {
      transform: scale(1.2);
    }
  }

  ::v-deep .image-slot {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    color: #909399;
    font-size: 30px;
  }
}
</style>
