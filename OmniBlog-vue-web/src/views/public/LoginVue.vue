<template>
  <el-main class="main">


    <div class="login">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" class="demo-ruleForm"
               style="width: 220px; margin: 110px 250px;">
        <h1>登录</h1>
        <el-form-item label="账号" prop="username">
          <el-input type="text" v-model="ruleForm.username" autocomplete="off" :clearable="true"
                    style="width: 220px;"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass" style="margin-top: -15px;">
          <el-input type="password" v-model="ruleForm.pass" autocomplete="off" :show-password="true"
                    style="width: 220px;"></el-input>
        </el-form-item>
        <div class="toregist">
          <router-link to="/regist">
            <el-link :underline="false">没有账号？去注册</el-link>
          </router-link>
        </div>
        <div class="button">
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </div>
      </el-form>
    </div>

  </el-main>
</template>


<script>
  export default {
    name: "LoginVue",
    inject: ['reflash'],
    data() {
      var validateUserName = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入账号'));
        }
        callback();

      };

      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        }
        callback();
      };

      return {
        ruleForm: {
          username: '',
          pass: '',

        },
        rules: {
          username: [
            {validator: validateUserName, trigger: 'blur'}
          ],
          pass: [
            {validator: validatePass, trigger: 'blur'}
          ]
        }
      };
    },
    created() {
      if (this.$route.query.msg != null) {
        this.getMessage(this.$route.query.msg);
      }
    },
    methods: {
      submitForm(formName) {

        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios({
              method: "get",
              url: "/user/loginvue",
              params: {
                "username": this.ruleForm.username,
                "password": this.ruleForm.pass,
              }
            }).then(
              response => {
                if (response.data.msg) {


                  sessionStorage.setItem("USER_SESSION", response.data.user.username);
                  this.$router.replace({
                    name: "home",

                  });
                  this.reflash();
                  this.$message({
                    showClose: true,
                    message: '登录成功！欢迎您，' + response.data.user.username,
                    type: "success",
                    center: true
                  });

                } else {

                  this.$message({
                    showClose: true,
                    message: '用户名或密码错误',
                    type: "error",
                    center: true
                  });
                }

                //console.log(response.data);
              }
            ).catch(error => {
              console.log(error)
            })
          } else {
            this.$message({
              showClose: true,
              message: '请输入用户名和密码',
              type: "warning",
              center: true
            });
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      getMessage(msg) {
        this.$message({
          showClose: true,
          message: msg,
          type: "warning",
          center: true
        });

      }
    }
  }
</script>


<style scoped>
  .login {
    overflow: inherit;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -250px;
    margin-left: -350px;
    width: 700px;

    background: #ffffff;
    border-radius: 4px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }

  .toregist {
    text-align: right;
    margin: -8px 8px 35px 0;
    font-size: 4px;
  }

  .button {
    margin-top: 10px;
    text-align: center;
  }
</style>
