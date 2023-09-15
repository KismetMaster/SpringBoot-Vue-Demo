<template>
  <div style="width: 100%;height: 100vh;background: darkslateblue;overflow: hidden">
    denglu
    <div style="width: 400px; margin: 100px auto">
     <div style="coLor: #cccccc; font-size: 30px; text-align: center; padding: 30px 0">欢迎注册</div>
    <el-form  ref="form" :model="form" size="normal" >
<!--      ref="form":aria-modal="form" size="normal"-->
      <el-form-item prop="username">
        <el-input prefix-icon="User" v-model="form.username">user</el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input prefix-icon="lock" v-model="form.password" show-password>password</el-input>
      </el-form-item>
      <el-form-item>
        <el-input prefix-icon="lock" v-model="form.confirm" show-password>password</el-input>
      </el-form-item>
      <el-form-item>
        <el-button style="width: 100%" type="primary" @click="register">sign</el-button>
      </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "../utils/request";  //必须加这个
export default {
  name: "Register",
  // components: {
  //   ValidCode,
  // },
  data() {
    return {
      vedioCanPlay: false,
      fixStyle: '',
      form: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
      },
      validCode: ''

    }
  },
  // mounted() {
  //   sessionStorage.removeItem("user")
  //
  //   window.onresize = () => {
  //     const windowWidth = document.body.clientWidth
  //     const windowHeight = document.body.clientHeight
  //     const windowAspectRatio = windowHeight / windowWidth
  //     let videoWidth
  //     let videoHeight
  //     if (windowAspectRatio < 0.5625) {
  //       videoWidth = windowWidth
  //       videoHeight = videoWidth * 0.5625
  //       this.fixStyle = {
  //         height: windowWidth * 0.5625 + 'px',
  //         width: windowWidth + 'px',
  //         'margin-bottom': (windowHeight - videoHeight) / 2 + 'px',
  //         'margin-left': 'initial'
  //       }
  //     } else {
  //       videoHeight = windowHeight
  //       videoWidth = videoHeight / 0.5625
  //       this.fixStyle = {
  //         height: windowHeight + 'px',
  //         width: windowHeight / 0.5625 + 'px',
  //         'margin-left': (windowWidth - videoWidth) / 2 + 'px',
  //         'margin-bottom': 'initial'
  //       }
  //     }
  //   }
  //   window.onresize()
  // },
  methods: {
    register(){
      if(this.form.password!==this.form.confirm){
        this.$message({
          type: "success",
          message: "密码不一样"
        })
        return
      }
      request.post("/user/register", this.form).then(res =>{
        if (res.code === '0'){
          this.$message({
            type: "success",
            message: "注册成功"
          })
          this.$router.push("/login")  //登录成功之后进行页面的跳转，跳转到主页
        }else{
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    }
    // login () {
    //       request.post("/api/user/login", this.form).then(res => {
    //         if (res.code === '0') {
    //           this.$message({
    //             type: "success",
    //             message: "登录成功"
    //           })
    //           //sessionStorage.setItem("user", JSON.stringify(res.data))  // 缓存用户信息
    //
    //           // 登录成功的时候更新当前路由
    //           //activeRouter()
    //           this.$router.push("/")  //登录成功之后进行页面的跳转，跳转到主页
    //
    //         } else {
    //           this.$message({
    //             type: "error",
    //             message: res.msg
    //           })
    //         }
    //       })
    //     }
      }
}
</script>