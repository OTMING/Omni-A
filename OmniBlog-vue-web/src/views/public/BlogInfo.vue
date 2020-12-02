<template>
  <el-main class="main">

    <el-page-header @back="goBack" content="博客详情页面"></el-page-header>
    <div class="bloginfo">

      <div class="allinfo">
        <div class="blogtitle">
          <p>{{blogtitle}}</p>
        </div>
        <div class="blogowner">
          <p>{{blogowner}} &nbsp;&nbsp;发布于&nbsp;&nbsp;{{blogdate}}</p>
        </div>
        <el-divider></el-divider>
        <div class="ql-snow blogcontent ">
          <div class="ql-editor"  v-html="blogcontent"></div>
        </div>
      </div>


    </div>
  </el-main>
</template>

<script>
  export default {
    name: "BlogInfo",

    data() {
      return {
        blogtitle: '',
        blogcontent: '',
        blogdate: '',
        blogowner: ''

      }
    },

    created() {
      this.getinfo();

    },
    methods: {
      goBack() {
        this.$router.go(-1);
      },
      getinfo() {
        var blogid = this.$route.query.blogid;

        let param = new URLSearchParams();
        param.append("id", blogid)

        this.$axios({
          method: "post",
          url: "/blog/getblogbyid",
          data: param
        }).then(result => {
          let blog = result.data.blog[0]
          this.blogtitle = blog.title;
          this.blogcontent = blog.content;
          this.blogdate = blog.cdate;
          this.blogowner = blog.user.username;

        }).catch(error => {
          console.log(error);
        })
      }
    }
  }
</script>

<style scoped>

  .bloginfo {
    margin: 20px auto;
    border-radius: 4px;

    width: 85%;


  }

  .bloginfo .allinfo {
    margin: 80px 90px;

  }

  .bloginfo .allinfo .blogtitle {
    margin-top: 50px;

  }

  .bloginfo .allinfo .blogtitle p {
    text-align: center;
    font-size: 18px;
    color: #303133;
  }

  .bloginfo .allinfo .blogowner {
    margin-bottom: -20px;
  }

  .bloginfo .allinfo .blogowner p {
    color: #909399;
    text-align: right;
    font-size: 13px;
    font-style: oblique;
    white-space: nowrap;
  }

  .bloginfo .allinfo .blogcontent{

  }

</style>



