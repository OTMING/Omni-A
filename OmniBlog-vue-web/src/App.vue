<template>

  <div id="app">

    <el-container style="display: flex;flex-direction: column;min-height: 100%">
      <app-header v-if="isFlash"></app-header>


        <router-view v-if="isShow"></router-view>

      <app-footer></app-footer>
    </el-container>

  </div>
</template>

<style>


</style>
<style scoped>


  #app {
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;


  }

</style>
<script>
  import AppHeader from './components/Header';
  import AppFooter from './components/Footer';

  export default {


    name: 'App',
    components: {
      AppHeader,
      AppFooter
    },
    provide() {
      return {
        reload: this.reload,
        reflash: this.reflash
      }
    },
    data() {
      return {
        isShow: true,
        isFlash: true,
        minHeight: 0
      }
    },
    methods: {
      reload() {
        this.isShow = false
        this.$nextTick(function () {
          this.isShow = true
        })
      },
      reflash() {
        this.isFlash = false
        this.$nextTick(function () {
          this.isFlash = true
        })
      }
    }

  }
</script>



