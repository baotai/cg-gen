<template>
  <body id="poster">
    <el-form label-position="left" label-width="0px" class="login-container">
      <h3 class="login_title">登录平台</h3>
      <el-form-item>
        <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号" />
      </el-form-item>
      <el-form-item>
        <el-input v-model="loginForm.password" type="password" autocomplete="off" placeholder="密码" />
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%; background: #505458; border: none" @click="handleLogin">提交</el-button>
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
export default {
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    handleLogin() {
      this.$store.dispatch('user/login', this.loginForm).then(() => {
        this.$router.replace({ path: '/' })
      })
    }
  }
}
</script>

<style scoped>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 145px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  #poster {
    background:url('../assets/eva.jpg') no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
</style>
