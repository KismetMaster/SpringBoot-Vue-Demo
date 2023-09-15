<template>
  <div>
    <!--    头部-->
    <Header :user="user"/>

    <!--    主体-->
    <div style="display: flex">
      <!--      侧边栏-->
      <Asaid />
      <!--      内容区域-->
      <router-view style="flex: 1" @userInfo="refreshUser"/>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Asaid from "@/components/Asaid";

export default {
  name: "Layout",
  components: {
    Header,
    Asaid
  },
}
</script>

<style scoped>

</style>