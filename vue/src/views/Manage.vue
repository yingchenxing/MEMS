<template>
  <el-container style="min-height: 100vh;">
    <el-aside :width="sideWidth+'px'"
              style="background-color: rgb(238, 241, 246);box-shadow: 2px 0 6px rgb(0,21,41,0.35)">
      <Aside :isCollapse="isCollapse"/>
    </el-aside>

    <el-container>

      <el-header style="border-bottom: 1px solid #cccccc">
        <Header :collapse-btn-class="collapseBtnClass" :collapse="isCollapse"/>
      </el-header>

      <el-main>
        <!--        表示当前页面的子路由会在router-view中展示-->
        <router-view/>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
// @ is an alias to /src
import Aside from "@/components/Aside";
import Header from "@/components/Header";
export default {
  name: 'HomeView',
  components: {
    Header,
    Aside,
  },
  data() {
    return {
      msg: "hello,world",
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
    };
  },
  component: [
    Aside,Header
  ],
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {
        this.sideWidth = 64;
        this.collapseBtnClass = 'el-icon-s-unfold';
      } else {
        this.sideWidth = 200;
        this.collapseBtnClass = 'el-icon-s-fold';
      }
    },
  }
}
</script>

<style>
.headerBg {
  background-color: #eee !important;
}
</style>

<style scoped>
</style>
