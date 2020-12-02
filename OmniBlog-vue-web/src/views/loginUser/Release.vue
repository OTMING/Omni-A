<template>
  <el-main class="content">
    <el-form ref="form" :model="form" label-width="80px" :rules="rules" class="release-from">
      <el-form-item label="标题" prop="title">
        <el-input v-model="form.title" maxlength="55" placeholder="请输入标题"></el-input>
      </el-form-item>
      <el-form-item label="文章类型" prop="type">
        <el-cascader v-model="form.blogtype" placeholder="请选择文章类型" :options="options"
                     :props="props"
                     :collapse-tags="true"
                     :show-all-levels="false"></el-cascader>
      </el-form-item>

      <el-form-item label="内容" prop="content">

        <quill-editor
          ref="myQuillEditor"
          v-model="form.desc"
          class="myQuillEditor"
          :options="editorOption">
        </quill-editor>

      </el-form-item>

      <!--<el-form-item label="内容">
        <el-input type="textarea" v-model="form.desc" rows="16"></el-input>
      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">立即发布</el-button>
        <el-button @click="goBack()">取消</el-button>
      </el-form-item>
    </el-form>
  </el-main>
</template>

<script>
  import {quillEditor} from 'vue-quill-editor'
  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'
  import 'quill/dist/quill.bubble.css'

  export default {
    name: "Release",
    components: {
      quillEditor
    },
    data() {
      var validateTitle = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('标题不能为空'));
        } else if (value.length <= 5) {
          callback(new Error('标题需五个字符及以上'));
        }
        callback();

      };

      var validateType = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请选择文章类型'));
        }
        callback();
      };
      var validateContent = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入文章内容'));
        }
        callback();
      };
      return {
        form: {
          title: '',
          region: '',
          desc: '',
          blogtype: '',

        },

        rules: {
          title: [
            {validator: validateTitle, trigger: 'blur'}
          ],
          type: [
            {validator: validateType, trigger: 'blur'}
          ],
          content: [
            {validator: validateContent, trigger: 'blur'}
          ]
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
              value: 'spring',
              label: 'Spring框架',
            },
              {
                value: 'springmvc',
                label: 'SpringMvc',
              }],
          },
          {
            value: 'mybatis',
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
        }
      }
    },
    methods: {
      goBack() {
        this.$router.go(-1);
      },
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let param = new URLSearchParams();
            param.append("title", this.form.title)
            param.append("content", this.form.desc)
            param.append("blogtype", this.form.blogtype[this.form.blogtype.length - 1])
            param.append("owner", sessionStorage.getItem("USER_SESSION"))
            this.$axios({
              method: 'post',
              url: "/blog/releasevue",
              data: param
              /*{
            title: this.form.title,
            content: this.form.desc,
            owner: sessionStorage.getItem("USER_SESSION")
          }*/,
            }).then(response => {if (response.data.res) {
                this.$router.replace({name: "home"});
                this.$message({
                  showClose: true,
                  message: '发布成功',
                  type: 'success',
                  center: true
                });
              } else {
                this.$message({
                  showClose: true,
                  message: '发布失败',
                  type: "error",
                  center: true
                });
              }
            }).catch(erro => {
              console.log(erro)
            })
          }else {
            this.$message({
              showClose: true,
              message: '请输入对应信息',
              type: "warning",
              center: true
            });
            return false;
          }
        })
      }
    }
  }
</script>
<style>
  .quill-editor {
    height: 400px;
  }

  .ql-container.ql-snow {
    height: 300px;
  }
</style>
<style scoped>
  .content {

    width: 100%;
    height: 600px;
    background: #fff;
  }

  .content .release-from {
    margin: 50px 50px;

  }

</style>
