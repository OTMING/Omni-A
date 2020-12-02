<template>

  <el-main class="main">

    <div class="regist">

      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm"  class="demo-ruleForm" style="width: 220px; margin: 130px 250px;">
        <el-form-item label="账号" prop="username">
          <el-input type="text" v-model="ruleForm.username"  :clearable="true" style="width: 220px;"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass" style="margin-top: -15px;">
          <el-input :show-password="true" type="password" v-model="ruleForm.pass"  style="width: 220px;"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass" style="margin-top: -15px;">
          <el-input :show-password="true" type="password" v-model="ruleForm.checkPass"  style="width: 220px;"></el-input>
        </el-form-item>

        <div class="tologin">
          <router-link to="/login"><el-link :underline="false">已有账号？去登录</el-link> </router-link>
        </div>

        <div class="button">
          <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>

        </div>


      </el-form>
    </div>
  </el-main>
</template>

<script>
  export default {
    name: "RegistVue",
    data() {

      let self = this;

      var validateUserName = (rule, value, callback) => {

        if (value === '') {
          callback(new Error('请输入账号'));

        }else{
          self.checkName(value);
          if (this.isName){
            callback(new Error('用户已存在，换一个名字吧'));

          }
        }
        callback();




      };


      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          username:'',
          pass: '',
          checkPass: '',
        },
        rules: {
          username: [
            { validator: validateUserName, trigger: 'blur' }
          ],
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],

        },
        isName: false,

      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let param = new URLSearchParams();
            param.append("username",  this.ruleForm.username)
            param.append("password", this.ruleForm.pass)
            this.$axios({
              method: "post",
              url: "/user/registvue",
              data:param

            }).then(response =>{
                if (response.data.res){
                  this.$message({
                    showClose: true,
                    message: '注册成功，请登录',
                    type: 'success',
                    center:true
                  });
                  this.$router.replace({
                    name:"loginvue"
                  })
                }else{
                  alert("注册失败")
                }
            }).catch(error=>{
              console.log(error)
            })
          } else {
            this.$message({
              showClose: true,
              message: '请检查填写内容是否有误',
              type: 'error',
              center: true
            });
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      checkName(name){
        this.$axios({
          methods: "get",
          url:"/user/checkname",
          params:{
            "username": name,
          }
        }).then(response=>{

          this.isName = response.data.res;

        }).catch(error=>{
          console.log(error)
        })
      }

    }
  }
</script>

<style scoped>
  .regist{
    overflow: inherit;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -300px;
    margin-left: -350px;
    width: 700px;

    background: #ffffff;
    border-radius: 4px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
  .tologin{
    text-align: right;
    margin: -8px 8px 35px 0;
    font-size: 4px;
  }
  .button{
    margin-top: 10px;
    text-align: center;
  }
</style>
