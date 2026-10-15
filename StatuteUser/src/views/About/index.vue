<!-- <template>
  <div>
    <BasicHeader :activeIndex="5"></BasicHeader>
    <div class="mc mt20">
      <el-breadcrumb separator="/" class="mb20">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>关于我们</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="mc mt20 mb20">
      <div>
        <img src="../../assets/ls.png" style="width: 100%"/>
        <el-card style="margin: 20px 0">
          <div slot="header">所在地址</div>
          <div id="amapcontainer" style="width: 100%; height: 620px;"/>
        </el-card>
        <img src="../../assets/ls1.png" style="width: 100%"/>
      </div>
    </div>
    <Footer ></Footer>
  </div>
</template>

<script>
import AMapLoader from "@amap/amap-jsapi-loader"


export default {
  name: "index",
  data() {
    return {
      marker: null,//标记对象
      loaded: false,
      infoWindow: null,//弹窗对象
      adress: '', // 根据位置查找经纬度
      map: null,
      auto: null,
      geocoders: null,
      markers: [], // 标记点 数组
      lnglat: [], // 当前位置的经纬度
      mapForm: {
        resizeEnable: true,
        viewMode: '3D', // 使用3D视图
        zoomEnable: true, // 地图是否可缩放，默认值为true
        dragEnable: true, // 地图是否可通过鼠标拖拽平移，默认为true
        doubleClickZoom: true, // 地图是否可通过双击鼠标放大地图，默认为true
        zoom: 13, // 初始化地图级别
        center: [120.230349, 30.228173], // 初始化中心点坐标 山东济南
        // mapStyle: "amap://styles/darkblue", // 设置颜色底层
        placeSearch: null // 搜索关键字
      }
    }
  },
  created() {
    this.initMap()
  },
  methods: {
    initMap() {
      AMapLoader.load({
        key: '8acc1d21b602266b6104cbd3d4a7eee3', // 申请好的Web端开发者Key，首次调用 load 时必填
        version: '2.0', // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: ['AMap.Scale', 'AMap.ToolBar', 'AMap.ControlBar', 'AMap.Geocoder', 'AMap.Marker',
          'AMap.CitySearch', 'AMap.Geolocation', 'AMap.AutoComplete', 'AMap.InfoWindow', 'AMap.PlaceSearch'] // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      }).then((AMap) => {
        // 获取到作为地图容器的DOM元素，创建地图实例
        this.map = new AMap.Map('amapcontainer', // 设置地图容器id
          this.mapForm
        )
        this.addMarker()
      }).catch(e => {
        console.log(e)
      })
    },
    addMarker() {
      this.marker = new window.AMap.Marker({//新增一个标记点
        position: [120.230349, 30.228173],
      });
      this.marker.setMap(this.map); //放在map这个对象里面
      //顺便新增一个点击标记点出来的窗口
      this.infoWindow = new window.AMap.InfoWindow({//新增一个window窗口
        content: "奥体中心", // 添加你想要显示的文字描述
        offset: new window.AMap.Pixel(0, -30), // 设置信息窗体偏移量，使其显示在标记点正上方
      });

      // 点击标记点时显示信息窗体
      this.marker.on("click", () => {
        this.infoWindow.open(this.map, this.marker.getPosition());
      });
    },
  }
}
</script>

<style scoped>

</style> -->
