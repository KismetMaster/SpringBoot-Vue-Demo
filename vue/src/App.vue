<template>
  <div>
    <router-view />
<!--  &lt;!&ndash;    头部&ndash;&gt;-->
<!--  <Header :user="user"/>-->

<!--  &lt;!&ndash;    主体&ndash;&gt;-->
<!--  <div style="display: flex">-->
<!--    &lt;!&ndash;      侧边栏&ndash;&gt;-->
<!--    <Asaid />-->
<!--    &lt;!&ndash;      内容区域&ndash;&gt;-->
<!--    <router-view style="flex: 1" />-->
<!--  </div>-->
  </div>
</template>

<script>
  // import Header from "@/components/Header";
  // import Asaid from "@/components/Asaid";

  export default {
    name:"App",
    //name:"layout",
    // components:{
    //   Header,
    //   Asaid
    // }
  }
//解决网页弹出resizeobserver loop limit exceeded
  const debounce = (fn, delay) => {
    let timer = null;
    return function () {
      let context = this;
      let args = arguments;
      clearTimeout(timer);
      timer = setTimeout(function () {
        fn.apply(context, args);
      }, delay);
    }
  }

  const _ResizeObserver = window.ResizeObserver;
  window.ResizeObserver = class ResizeObserver extends _ResizeObserver{
    constructor(callback) {
      callback = debounce(callback, 16);
      super(callback);
    }
  }
</script>

<style scoped>
@import url("//unpkg.com/element-ui@2.15.6/lib/theme-chalk/index.css");
</style>

