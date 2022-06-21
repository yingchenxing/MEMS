<template xmlns:span="http://java.sun.com/xml/ns/javaee">
  <div style="font-size:12px;line-height: 60px;display: flex">
    <div style="flex: 1;">
      <span :class="collapseBtnClass" style="cursor: pointer;font-size: 18px" @click="collapse"></span>

      <el-breadcrumb separator="/" style="display: inline-block;margin-left: 10px">
        <el-breadcrumb-item :to="'/'">Home</el-breadcrumb-item>
        <el-breadcrumb-item> {{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <el-dropdown style="width: 200px;cursor: pointer;text-align: right">
      <div>
        <span class="el-dropdown-link">
           user.username </span>
        <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      </div>
      <el-dropdown-menu slot="dropdown" style="width: 100px;text-align:center">
        <router-link to="/profile" style="text-decoration: none;">
          <el-dropdown-item style="font-size:14px;padding:5px 0">
            <i class="el-icon-s-custom"></i>
            Profile
          </el-dropdown-item>
        </router-link>
        <span style="text-decoration: none" @click="logOut">
          <el-dropdown-item style="font-size:14px;padding:5px 0">
              Log out
          </el-dropdown-item>
        </span>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    user: Object,
  },
  computed: {
    currentPathName() {
       return this.$store.state.currentPathName;
    }
  },
  // created() {
  //   console.log(this.user)
  // },
  data() {
    return {
      // user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods: {
    collapse() {
      this.$emit("asideCollapse")
    },
    logOut() {
      this.$store.commit("logout")
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("Successfully log out.")
    }
  }
}
</script>

<style scoped>

</style>