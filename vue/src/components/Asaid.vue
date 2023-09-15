<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        padding-left="0px"
        :default-active="path"
        router
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
    >
      <el-sub-menu index="1" v-if="user.role===1">
        <template #title>
          <el-icon><location /></el-icon>
          <span>系统管理</span>
        </template>
          <el-menu-item index="/user">用户管理</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="/book">Book管理</el-menu-item>
      <el-menu-item index="/news">news管理</el-menu-item>
<!--      <el-menu-item >-->
<!--        <el-icon><icon-menu /></el-icon>-->
<!--        <span>数据管理</span>-->
<!--      </el-menu-item>-->
      <el-menu-item index="3" disabled>
        <el-icon><document /></el-icon>
        <span>three</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import request from "../utils/request";

export default {
  name: "Asaid",
  data(){
    return{
      user:{},
      path:this.$router.path
    }
  },
  created(){
    let userStr= sessionStorage.getItem("user")||"{}"
    this.user = JSON.parse(userStr)

    request.get("/user/"+this.user.id).then(res =>{
      if(res.code === '0'){
        this.user = res.date
      }
    })
  }
}
</script>

<style scoped>

</style>