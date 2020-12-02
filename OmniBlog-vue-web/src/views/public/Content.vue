<template>


  <el-main>
    <!--<el-menu

      default-active="/home"
      router
      class="el-menu-demo" mode="vertical"
      style="border-bottom: none;"
      background-color="#250054"
      text-color="#3f9dfe"
      active-text-color="#20ec8a">
      <el-menu-item index="/home">
        首页
      </el-menu-item>
      <el-submenu index="2">
        <template slot="title">Spring</template>
        <el-menu-item index="/spring">Spring框架</el-menu-item>
        <el-menu-item index="/springmvc">Spring Mvc</el-menu-item>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">Mybatis</template>
        <el-menu-item index="/mybatis">Mybatis</el-menu-item>

      </el-submenu>
      <el-submenu index="4">
        <template slot="title">其他</template>
        <el-menu-item index="/test">测试</el-menu-item>
        <el-menu-item index="/note">笔记</el-menu-item>
      </el-submenu>
    </el-menu>-->
    <div id="content" class="content">


      <el-backtop :bottom=100 :right=70></el-backtop>

      <div class="con">
        <el-row :gutter="10" class="publicnews">
          <el-col class=" hidden-sm-and-down"  :md="{span: 8, offset: 2}">
            <div class="imgcar">

              <el-carousel height="280px" direction="vertical">
                <el-carousel-item v-for="(img,index) in images" :key="index">

                  <router-link :to="{
                path:'/bloginfo',
                query:{
                  blogid:index+1,
                }
                }">
                    <el-link :underline="false">
                      <div class="carimg">
                        <el-image :src="img" fit="fill"
                                  style="width: 680px; height: 280px"></el-image>

                      </div>
                    </el-link>
                  </router-link>
                </el-carousel-item>
              </el-carousel>
            </div>
          </el-col>
          <el-col :xs="{span: 24, offset: 0}" :sm="{span: 24, offset: 0}" :md="{span: 10, offset: 2}">
            <div class="news">
              <el-collapse accordion>
                <el-collapse-item title="重要通知" name="1">
                  <div>
                    <i class="el-icon-apple"></i>
                    项目已部署成功
                  </div>
                </el-collapse-item>
                <el-collapse-item title="项目进展" name="2">
                  <div style="margin-left: 30px;">正在完善。。。</div>
                  <div style="width: 200px;margin-top: 15px;margin-left: 40px;">
                    <el-progress :text-inside="true" :stroke-width="20" :percentage="50" status="warning"></el-progress>
                  </div>
                </el-collapse-item>
                <el-collapse-item title="调节网页背景颜色" name="3">
                  <div>

                    <el-color-picker v-model="bgcolor" @change="trunBgcolor(bgcolor)"></el-color-picker>
                  </div>
                </el-collapse-item>
                <div class="gorelease">
                  <el-tooltip content="登录后才能发布哦" placement="bottom" effect="light">
                    <el-button type="primary" round @click="toRelease()">我要发布</el-button>
                  </el-tooltip>
                </div>
              </el-collapse>

            </div>

          </el-col>


        </el-row>
        <el-divider></el-divider>
      </div>

      <el-row>

        <el-col :xs="{span: 24, offset: 0}"  :md="{span:16,push:4}"
                class="artic" v-for="(list,index) in blogs" :key="index">

          <div class="blogtype"><span>{{list.blogType.typeName}}</span></div>

          <div class="content-tit">
            <router-link :to="{
          path:'/bloginfo',
          query:{
            blogid:list.id,
          }
          }">
              <el-link :underline="false">
                <span>{{list.title}}</span>
              </el-link>


            </router-link>

          </div>
          <div class="blog-content" style=" -webkit-box-orient: vertical;">
            <router-link :to="{
          path:'/bloginfo',
          query:{
            blogid:list.id,
          }
          }">
              <p>
                <el-link :underline="false">{{list.summaryText}}<span v-if="list.summaryText.length===150">......
            </span></el-link>
              </p>
            </router-link>
          </div>
          <div class="blog-owner">

            <span><i class="el-icon-user"></i>作者： {{list.user.username}}   &nbsp; &nbsp;&nbsp;<i
              class="el-icon-circle-check"></i>创建时间：{{list.cdate}}</span>
            <span></span>
          </div>

        </el-col>


      </el-row>


      <div class="number">
        <el-pagination
          :hide-on-single-page="true"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          background
          layout="prev, pager, next"
          :page-size="pageSize"
          :total="rows">
        </el-pagination>
      </div>
    </div>


  </el-main>


</template>


<script>

  import AppHeader from '@/components/Header'

  export default {
    name: "Content",

    components: {
      AppHeader
    },
    computed: {},


    data() {


      return {
        isFlash: true,
        bgcolor: '#fff',
        images: (function () {
          // 导入图片模块
          let fileArr = [];
          for (let i = 0; i < 3; i++) {
            fileArr[i] = 'static/home/' + (i + 1) + '.jpg';
          }
          return fileArr;
        })(),
        blogs: [],
        pageTotal: 1,
        pageSize: 6,
        rows: 1,
        currentPage: 1,
        isShowHot: false

      }
    },


    created() {


      this.showTable(this.currentPage, this.pageSize);


    },

    methods: {
      handleSizeChange: function (size) {
        this.pageSize = size;
        //console.log(this.pageSize);  //每页下拉显示数据
        this.showTable(this.currentPage, this.pageSize);
      },
      handleCurrentChange: function (currentPage) {
        this.currentPage = currentPage;
        //console.log(this.currentPage);  //点击第几页
        this.showTable(this.currentPage, this.pageSize);
        window.scroll(0, 390);

      },
      showTable(currentPage, pageSize) {
        let param = new URLSearchParams();
        param.append("currentPage", currentPage)
        param.append("pageSize", pageSize)

        this.$axios({
          method: "post",
          url: "/home/page",
          data: param
        }).then(response => {
          this.blogs = response.data.blogs;
          this.rows = response.data.rows;
        }).catch(error => {
          console.log(error)
        })


      },

      toRelease() {

        this.$router.push('release')
        /*this.$axios({
          method: "post",
          url: "/intercept/torelease",
          dataType: 'JSONP'
        }).then(result => {
          if (result.data) {
            this.$router.replace({
              name: "release",

            });
          } else {
            this.$router.replace({
              name: "loginvue",

            });
          }
        }).catch(error => {
          console.log(error)
        })*/
      },

      trunBgcolor(color) {

        document.querySelector('body').setAttribute('style', 'background-color:' + color)

      }


    }


  }

</script>


<style scoped>
  .con{
    min-height: 310px;
    margin-bottom: 40px;
  }

  .con .imgcar {
    width: 600px;
    display: inline-block;

  }

  .con .carimg {
    width: 100%;
    height: 200px;
    overflow: visible;
  }


  .con .news {
    width: 100%;
    display: inline-block;
    float: right;

  }

  .con .gorelease {
    margin-top: 20px;
    width: 100%;
    position: relative;
    display: inline-block;
    bottom: -93px;
    right: 20px;
    text-align: right;
  }

  .artic {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

    overflow: hidden;
    margin: 10px 5px;
    border-radius: 8px;
    padding: 4px;
  }


  .artic .content-tit {
    width: 100%;
    margin-top: 15px;
    margin-left: 25px;
  }


  .artic .content-tit span {
    color: #303133;
    font-size: 20px;
    letter-spacing: 1px;
    font-weight: 600;
  }

  .artic .content-tit :hover {
    color: #df7e65;;


  }

  .number {
    text-align: right;
    margin: 20px;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  .side-toolbar {
    position: fixed;
    width: 50px;
    right: 200px;
    bottom: 140px;
  }

  .artic .blog-content {
    margin: 10px 5px;
    max-width: 1000px;
    -webkit-line-clamp: 2; /* 这里是超出几行省略 */

  }

  .artic .blog-content p {


    text-indent: 2em;
    font-size: 16px;
    line-height: 26px;
    word-break: break-all;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }

  .artic .blog-owner {
    height: min-content;
    width: 100%;
    text-align: right;
    margin-right: 20px;
  }

  .artic  .blogtype {
    float: right;
    width: 85px;
    display: inline-block;
    border-radius: 10px 0 0 10px;
    text-align: center;
    background: rgba(111, 199, 101, 0.78);
    margin-right: -2px;
    margin-top: 4px;
  }

  .artic .blog-owner span {
    color: #909399;
    font-size: 13px;
    font-style: oblique;
    white-space: nowrap;
  }
</style>
