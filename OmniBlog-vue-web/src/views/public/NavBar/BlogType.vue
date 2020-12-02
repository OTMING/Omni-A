<template>
  <el-main class="main">
    <div class="content">
      <div class="imglogo">

        <el-image :src="imgsrc"></el-image>
      </div>


      <div class="allblogs">
        <div class="artic" v-for="(list,index) in blogs" :key="index">


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
          <div class="blog-content">
            <router-link :to="{
          path:'/bloginfo',
          query:{
            blogid:list.id,
          }
          }">
              <p>
                <el-link :underline="false">{{list.summaryText.substr(0,80)}}<span v-if="list.summaryText.length>=80">......</span></el-link>
              </p>
            </router-link>
          </div>
          <div class="blog-owner">
            <div class="blogtype"><span>{{list.blogtype}}</span></div>
            <span><i class="el-icon-user"></i>作者： {{list.user.username}}   &nbsp; &nbsp;&nbsp;<i
              class="el-icon-circle-check"></i>创建时间：{{list.cdate}}</span>
            <span></span>
          </div>
          <el-divider></el-divider>
        </div>

      </div>


    </div>
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
  </el-main>



</template>

<script>
  export default {
    name: "BlogType",

    data() {

      return {
        imgsrc:'',
        type: '',
        pageTotal: 1,
        pageSize: 4,
        rows: 1,
        currentPage: 1,
        blogs: [],
      }

    },
    created() {
      // document.querySelector('body').setAttribute('style', 'background-color:#250054')
      this.getData(this.currentPage, this.pageSize);
    },
    watch: {
      '$route' (to, from) {
        this.getData(this.currentPage, this.pageSize)
      }
    },
    methods: {

      handleSizeChange: function (size) {
        this.pageSize = size;
        //console.log(this.pageSize);  //每页下拉显示数据
        this.getData(this.currentPage, this.pageSize);
      },
      handleCurrentChange: function (currentPage) {
        this.currentPage = currentPage;
        //console.log(this.currentPage);  //点击第几页
        this.getData(this.currentPage, this.pageSize);
        window.scroll(0, 390);

      },

      getData(currentPage, pageSize) {
        let type = this.$route.params.btype;
        this.imgsrc='/static/'+type+'.GIF';
        let param = new URLSearchParams();
        param.append("blogtype", type)
        param.append("currentPage", currentPage)
        param.append("pageSize", pageSize)
        this.$axios.post('/blog/getblogbytype', param).then(result => {
          if (result.data.res) {
            this.blogs = result.data.blogs;
            this.rows = result.data.rows;
          }
        })

      },
    },

    destroyed() {
      // 当组件销毁之后触发

      //document.querySelector('body').setAttribute('style', 'background-color:#fff')
    }
  }

</script>

<style scoped>

  .content {
    overflow: hidden;
  }

  .content .imglogo {
    display: inline-block;
    width: 500px;
    height: 300px;
    float: right;
  }

  .content .allblogs {
    display: inline-block;
    width: 700px;
    float: left;

  }

  .artic {

    display: inline-block;
    overflow: hidden;
    padding: 0 12px;
    width: 660px;

  }


  .artic .content-tit {
    width: auto;
    margin-top: 5px;
    margin-left: 25px;
  }


  .artic .content-tit span {
    font-family: "Microsoft YaHei";
    color: #303133;
    font-size: 20px;
    letter-spacing: 1px;
    font-weight: 600;
  }

  .artic .content-tit :hover {
    color: #df7e65;;


  }

  .artic .blog-content {
    height: 42px;
    margin: 7px auto;
    width: 600px;
  }

  .artic .blog-content p {
    font-family: "Microsoft YaHei";
    display: inline;
    text-indent: 2em;
    font-size: 16px;
    line-height: 26px;
  }

  .artic .blog-owner {
    height: min-content;
    width: 400px;
    position: relative;
    bottom: 0px;
    right: -37%;
    text-align: right;
  }

  .artic .blog-owner .blogtype {
    width: 70px;
    display: inline-block;
    border-radius: 25px;
    text-align: center;
    background: rgba(245, 241, 241, 0.78);

  }

  .artic .blog-owner span {
    color: #909399;
    font-size: 13px;
    font-style: oblique;
    white-space: nowrap;
  }

  .number {
    position: fixed;
    bottom: 80px;
    right: 450px;
  }

</style>

