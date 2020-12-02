<template>
  <el-main class="myblog">
    <el-page-header @back="goBack" content="我的所有博客"></el-page-header>
    <div class="empty" v-if="isEmpty">
      <h2>你还没有发布过内容哦！</h2>
    </div>
    <div class="blog-content" v-else>
      <div class="artic" v-for="(blog,index) in blogs" :key="index">
        <div class="blogtitle">
          <router-link :to="{
            path:'/bloginfo',
            query:{
            blogid:blog.id,
          }
          }">
            <el-link :underline="false"><h2>{{blog.title}}</h2></el-link>

          </router-link>

        </div>
        <div class="blogcontent">
          <router-link :to="{
          path:'/bloginfo',
          query:{
            blogid:blog.id,
          }
          }">
            <p>
              <el-link :underline="false">{{blog.summaryText.substr(0,100)}}<span v-if="blog.summaryText.length>=100">......</span></el-link>
            </p>
          </router-link>
        </div>

        <div class="editicon">
          <el-button-group>
            <el-button icon="el-icon-edit" @click="editblog(blog)"></el-button>
            <el-button icon="el-icon-delete" @click="deleteblog(blog.id)"></el-button>
          </el-button-group>
        </div>
      </div>


    </div>
    <div class="pagination">
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

    <el-dialog title="编辑博客" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="博客标题" :label-width="formLabelWidth">
          <el-input v-model="form.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="文章类型" :label-width="formLabelWidth">
          <el-cascader v-model="form.blogtype" placeholder="请选择文章类型" :options="options"
                       :props="props"
                       :collapse-tags="true"
                       :show-all-levels="false"></el-cascader>
        </el-form-item>
        <el-form-item label="内容" :label-width="formLabelWidth">

          <quill-editor
            ref="myQuillEditor"
            v-model="form.desc"
            class="myQuillEditor"
            :options="editorOption">
          </quill-editor>

        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateblog()">确 定</el-button>
      </div>
    </el-dialog>
  </el-main>
</template>

<script>
  import {quillEditor} from 'vue-quill-editor'
  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'
  import 'quill/dist/quill.bubble.css'


  export default {
    name: "MyBlog",
    components: {
      quillEditor
    },
    inject: ['reload'],
    data() {
      return {
        upblogid: '',
        form: {
          title: '',
          region: '',
          desc: '',
          blogtype: ''
        },
        editorOption: {
          placeholder: '请输入内容',
          // editorOption里是放图片上传配置参数用的，例如：
          // action:  '/api/product/richtext_img_upload.do',  // 必填参数 图片上传地址
          // methods: 'post',  // 必填参数 图片上传方式
          // token: '',  // 可选参数 如果需要token验证，假设你的token有存放在sessionStorage
          // name: 'upload_file',  // 必填参数 文件的参数名
          // size: 500,  // 可选参数   图片大小，单位为Kb, 1M = 1024Kb
          // accept: 'multipart/form-data, image/png, image/gif, image/jpeg, image/bmp, image/x-icon,image/jpg'  // 可选 可上传的图片格式
        },
        options: [
          {
            label: 'Spring',
            children: [{
              value: 'Spring',
              label: 'Spring框架',
            },
              {
                value: 'SpringMvc',
                label: 'SpringMvc',
              }],
          },
          {
            value: 'Mybatis',
            label: 'Mybatis',
          },
          {
            value: 'other',
            label: '其他',
            children: [{
              value: '测试',
              label: '测试',
            },
              {
                value: '笔记',
                label: '笔记',
              }],
          },
        ],
        props: {
          expandTrigger: 'hover',
        },
        blogs: '',
        isEmpty: true,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        pageSize: 4,
        rows: 1,
        currentPage: 1
      }
    },
    created() {

      this.getblogbyname(sessionStorage.getItem("USER_SESSION"), this.currentPage, this.pageSize);
    },
    methods: {
      goBack() {
        this.$router.go(-1);
      },
      handleSizeChange: function (size) {
        this.pageSize = size;
        //console.log(this.pageSize);  //每页下拉显示数据
        this.getblogbyname(sessionStorage.getItem("USER_SESSION"), this.currentPage, this.pageSize);
      },

      handleCurrentChange: function (currentPage) {
        this.currentPage = currentPage;
        //console.log(this.currentPage);  //点击第几页
        this.getblogbyname(sessionStorage.getItem("USER_SESSION"), this.currentPage, this.pageSize);

      },

      getblogbyname(name, currentPage, pageSize) {

        let param = new URLSearchParams();
        param.append("username", name)
        param.append("currentPage", currentPage)
        param.append("pageSize", pageSize)

        this.$axios({
          method: "post",
          url: "/blog/getblogbyname",
          data: param
        }).then(result => {
          if (result.data.res) {
            this.isEmpty = false;
            this.blogs = result.data.blogs;
            this.rows = result.data.rows;
          }
        }).catch(error => {
          console.log(error)
        })

      },

      editblog(blog) {
        let desc=blog.content
        this.dialogFormVisible = true;
        this.form.title = blog.title;
        this.form.desc = desc;
        this.form.blogtype = blog.blogType.typeName;
        this.upblogid = blog.id;

      },

      deleteblog(blogid) {
        this.$confirm('此操作将永久删除该条博客, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          let param = new URLSearchParams();
          param.append("id", blogid)
          this.$axios({
            method: "post",
            url: "/blog/deleteblogbyid",
            data: param
          }).then(result => {
            if (result.data.res) {
              this.$message({
                type: 'success',
                message: '删除成功!',
                center: true
              });
              this.reload();
            } else {
              this.$message({
                type: 'error',
                message: '删除失败!',
                center: true
              });
            }
          }).catch(error => {
            console.log(error)
          })

        }).catch(() => {

        });
      },

      updateblog() {
        let param = new URLSearchParams();
        param.append("id", this.upblogid)
        param.append("content", this.form.desc)
        param.append("title", this.form.title)
        param.append("blogtype", this.form.blogtype[this.form.blogtype.length - 1])
        this.$axios({
          method: 'post',
          url: "/blog/updateblogbyid",
          data: param
          /*{
            "id": this.upblogid,
            "content": this.form.desc,
            "title": this.form.title
          }*/
        }).then(result => {
          if (result.data.res) {
            this.dialogFormVisible = false;
            this.$message({
              type: 'success',
              message: '修改成功!',
              center: true
            });
            this.reload();

          } else {
            this.$message({
              type: 'error',
              message: '修改失败!',
              center: true
            });
          }
        }).catch(error => {
          console.log(error);
        })
      }
    },

  }
</script>

<style scoped>
  .myblog {
    background: #ffffff;
    width: 100%;

  }

  .myblog .empty {
    position: absolute;
    width: 400px;
    height: 100px;
    top: 50%;
    left: 50%;
    margin-top: -50px;
    margin-left: -200px;
  }

  .myblog .empty h2 {
    color: #909399;
    font-style: oblique;
  }

  .myblog .blog-content {
    margin-top: 20px;
  }

  .myblog .blog-content .artic {

    margin-bottom: 10px;

    height: 128px;
    border-bottom: 1px solid gray;

  }


  .myblog .blog-content .artic .blogtitle {
    margin: 30px 20px 10px 0px;
    width: 400px;
  }

  .myblog .blog-content .artic .blogtitle h2 {
    margin: 0;
    font-family: 宋体;
    color: #303133;
    font-size: 20px;

    font-weight: 600;
  }

  .myblog .blog-content .artic .blogcontent {

    margin-left: 20px;
    width: 800px;
  }

  .myblog .blog-content .artic .blogcontent p {
    color: #909399;
    text-indent: 2em;
    font-size: 14px;
    font-style: oblique;
    font-weight: 500;
  }

  .myblog .blog-content .artic .editicon {
    width: 150px;
    position: relative;
    left: 90%;
    bottom: 45%;


  }

   .pagination {
    text-align: right;


  }
</style>
