<template>
  <el-header
    style=" width: 100%;padding: 0px;display: block; background-color:transparent;height: 128px;flex: 0 0 auto">


    <!--<nav class="top-header">
      <div class="top-container">
        <div class="tor" v-if="username!=null">
          你好呀！
          <el-dropdown
            trigger="click"
            placement="bottom"
            :show-timeout="0"
            :hide-on-click="false"
          >
            <el-link type="info">
              <span class="el-dropdown-link">
                {{username}}<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>
              </span>
            </el-link>

            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-user-solid">
                <router-link to="/myblog">
                  <el-link :underline="false">我的博客</el-link>

                </router-link>
              </el-dropdown-item>
              <el-dropdown-item icon="el-icon-switch-button">
                <el-popconfirm
                  confirmButtonText='确定'
                  cancelButtonText='不用了'
                  icon="el-icon-info"
                  iconColor="red"
                  title="确定要注销吗？"
                  placement="right"
                  @onConfirm="zhuxiao()"
                >
                  <el-button slot="reference" type="text" style="color:gray;">注销</el-button>
                </el-popconfirm>

              </el-dropdown-item>

            </el-dropdown-menu>
          </el-dropdown>


          <router-link to="/">

          </router-link>
        </div>
        <div class="tor" v-else>
          <router-link to="/login">
            <el-link :underline="false" type="primary" style="font-size: larger; margin-right: 10px;">登录</el-link>
          </router-link>
          <router-link to="/regist">
            <el-link :underline="false" type="primary" style="font-size: larger;">注册</el-link>
          </router-link>

        </div>

        <div class="topinfo">

          <el-link icon="el-icon-s-custom">联系作者</el-link>
          <el-divider direction="vertical"></el-divider>
          <el-link :underline="false" icon="el-icon-info" type="info">关于本站</el-link>

        </div>
      </div>

    </nav>-->

    <el-row class="menu-container">


      <el-col :xs="{span: 12, offset: 0}" :sm="{span: 12, offset: 0}" :md="{span: 10, offset: 2}">
        <div class="logsite">
          <el-image src="static/home/log.gif"
          ></el-image>
        </div>
        <div class="logtitle hidden-sm-and-down">
          <span>OMNIBLOG</span>
        </div>
      </el-col>

      <el-col :xs="{span: 12, offset: 0}" :sm="{span: 12, offset: 0}" :md="{span: 12, pull: 2}">
        <div class="loginbar">
          <el-divider direction="vertical"></el-divider>
          <div class="tor" v-if="username!=null">
            <div class="loghead">
              <el-avatar size="small" icon="el-icon-user-solid"></el-avatar>
            </div>
            <el-dropdown
              trigger="click"
              placement="bottom"
              :show-timeout="0"
              :hide-on-click="false"
            >
              <el-link type="info">
                <span class="el-dropdown-link">
                  {{username}}<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>
                </span>
              </el-link>

              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item icon="el-icon-user-solid">
                  <router-link to="/myblog">
                    <el-link :underline="false">我的博客</el-link>

                  </router-link>
                </el-dropdown-item>
                <el-dropdown-item icon="el-icon-switch-button">
                  <el-popconfirm
                    confirmButtonText='确定'
                    cancelButtonText='不用了'
                    icon="el-icon-info"
                    iconColor="red"
                    title="确定要注销吗？"
                    placement="right"
                    @onConfirm="zhuxiao()"
                  >
                    <el-button slot="reference" type="text" style="color:gray;">注销</el-button>
                  </el-popconfirm>

                </el-dropdown-item>

              </el-dropdown-menu>
            </el-dropdown>


          </div>
          <div class="tor" v-else>
            <router-link to="/login">
              <el-link :underline="false" type="primary" style="font-size: larger; margin-right: 10px;">登录</el-link>
            </router-link>
            <router-link to="/regist">
              <el-link :underline="false" type="primary" style="font-size: larger;">注册</el-link>
            </router-link>

          </div>

        </div>

        <div class="menubar hidden-sm-and-down">
          <el-menu

            default-active="/home"
            router
            class="el-menu-demo" mode="horizontal"
            style="border-bottom: none;"
            background-color="#250054"
            text-color="#3f9dfe"
            active-text-color="#20ec8a">
            <el-menu-item index="/home">
              首页
            </el-menu-item>
            <el-submenu index="2">
              <template slot="title">Spring</template>
              <el-menu-item index="/type/Spring">Spring框架</el-menu-item>
              <el-menu-item index="/type/SpringMvc">Spring Mvc</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">Mybatis</template>
              <el-menu-item index="/type/Mybatis">Mybatis</el-menu-item>

            </el-submenu>
            <el-submenu index="4">
              <template slot="title">其他</template>
              <el-menu-item index="/type/测试">测试</el-menu-item>
              <el-menu-item index="/type/笔记">笔记</el-menu-item>
            </el-submenu>
          </el-menu>
        </div>
      </el-col>







    </el-row>


  </el-header>
</template>

<script>
  export default {
    name: "Header",
    inject: ['reflash'],
    data() {
      return {
        username: sessionStorage.getItem("USER_SESSION"),
        activeIndex: '1',
        isCollapse: true,
        logsrc: "http://localhost:8081/img/log.GIF"
      };
    },


    methods: {
      handleOpen(key, keyPath) {

      },
      handleClose(key, keyPath) {

      },

      zhuxiao() {
        sessionStorage.removeItem("USER_SESSION");
        this.$router.replace({
          name: "home",
        });
        this.reflash();
      },
      handleSelect(key, keyPath) {

      },
    }

  }
</script>
<style>
  .el-menu--horizontal .el-menu-item {
    height: 78px;
    line-height: 78px;
  }

  .el-menu--horizontal .el-submenu .el-submenu__title {
    height: 78px;
    line-height: 78px;
  }

  .el-menu--horizontal .el-menu-item:hover {

    background: rgba(184, 190, 219, 0.37) !important;
  }

  .el-menu--horizontal .el-submenu .el-submenu__title:hover {
    background: rgba(184, 190, 219, 0.37) !important;

  }
</style>
<style scoped>


  .header-main {
    background: #250054;
    position: fixed;
    top: 0;
    width: 100%;
    height: 78px;
    z-index: 999;
  }


  .top-header .top-container .tor {

    float: left;
  }


  .menu-container {

    height: 80px;
    background: #250054;
    margin: 0 auto;
    overflow: visible;
  }

  .menu-container .menubar {
    font-size: 1.5rem;

    height: 80px;
    float: right;
    overflow-y: hidden;
  }

  .menu-container .logsite {
    display: inline-block;
    border-radius: 50%;
    width: 130px;
    height: 94px;
    overflow: hidden;
    float: left;
  }


  .menu-container .logtitle {
    margin-left: 10px;
    display: inline-block;
    width: 100px;
    height: 78px;
    line-height: 78px;
  }

  .menu-container .logtitle span {
    background: linear-gradient(to right, #c22362, #21ef8b);
    -webkit-background-clip: text;
    color: transparent;
    font-size: 1.5rem;
    font-family: Quicksand, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
    font-weight: 700;
  }

  .menu-container .loginbar {
    padding: 0 10px 0 7px;

    height: 78px;
    line-height: 78px;
    float: right;
    font-size: 0.75rem;

  }

  .menu-container .loginbar .tor {
    margin-left: 5px;
    display: inline-block;
    text-align: center;
  }

  .menu-container .loginbar .tor span {
    color: #21ef8b;
  }

  .menu-container .loginbar .tor .loghead {
    display: inline-block;
    width: 45px;
    height: 28px;
    float: left;
    margin-top: 10px;
  }
</style>
