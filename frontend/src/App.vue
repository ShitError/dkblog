<template>
  <v-app id="app">
    <!-- 导航栏 -->
    <TopNavBar></TopNavBar>
    <!-- 侧边导航栏 -->
    <SideNavBar></SideNavBar>
    <!-- 内容 -->
    <v-content>
      <router-view :key="$route.fullPath" />
    </v-content>
    <!-- 页脚 -->
    <Footer></Footer>
    <!-- 返回顶部 -->
    <RightSideIcon></RightSideIcon>
    <!-- 搜索模态框 -->
    <searchModel></searchModel>
    <!-- 登录模态框 -->
    <LoginModel></LoginModel>
    <!-- 注册模态框 -->
    <RegisterModel></RegisterModel>
    <!-- 忘记密码模态框 -->
    <ForgetModel></ForgetModel>
    <!-- 绑定邮箱模态框 -->
    <EmailModel></EmailModel>
    <!-- APlayer音乐播放器-->
    <APlayer></APlayer>
  </v-app>
</template>

<script>
import TopNavBar from "./components/layout/TopNavBar";
import SideNavBar from "./components/layout/SideNavBar";
import Footer from "./components/layout/Footer";
import RightSideIcon from "./components/RightSideIcon";
import searchModel from "./components/model/SearchModel";
import LoginModel from "./components/model/LoginModel";
import RegisterModel from "./components/model/RegisterModel";
import ForgetModel from "./components/model/ForgetModel";
import EmailModel from "./components/model/EmailModel";
import APlayer from "./components/APlayer";
export default {
  created() {
    // 获取博客信息
    this.getBlogInfo();
    // 上传访客信息
    this.axios.post("/api/report");
  },
  components: {
    TopNavBar,
    SideNavBar,
    Footer,
    RightSideIcon,
    searchModel,
    LoginModel,
    RegisterModel,
    ForgetModel,
    EmailModel,
    APlayer,
  },
  methods: {
    getBlogInfo() {
      this.axios.get("/api/").then(({ data }) => {
        this.$store.commit("checkBlogInfo", data.data);
      });
    }
  },
  computed: {
    blogInfo() {
      return this.$store.state.blogInfo;
    },
    isMobile() {
      return navigator.userAgent.match(
        /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i
      );
    }
  }
};
</script>
