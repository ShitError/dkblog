<template>
  <v-navigation-drawer
      app
      v-model="drawer"
      width="250"
      disable-resize-watcher
      right
      overlay-opacity="0.8"
  >
    <!-- 博主介绍 -->
    <div class="blogger-info">
      <v-avatar size="110" style="margin-bottom:0.5rem">
          <img :src="this.$store.state.blogInfo.websiteConfig.websiteAvatar" />
      </v-avatar>
    </div>
    <!-- 博客信息 -->
    <div class="blog-info-wrapper">
      <div class="blog-info-data">
        <router-link to="/archives">
          <div style="font-size: 0.875rem">文章</div>
          <div style="font-size: 1.125rem;">
            {{ this.$store.state.blogInfo.articleCount }}
          </div>
        </router-link>
      </div>
      <div class="blog-info-data">
        <router-link to="/categories">
          <div style="font-size: 0.875rem">分类</div>
          <div style="font-size: 1.125rem">
            {{ this.$store.state.blogInfo.categoryCount }}
          </div>
        </router-link>
      </div>
      <div class="blog-info-data">
        <router-link to="/tags">
          <div style="font-size: 0.875rem">标签</div>
          <div style="font-size: 1.125rem">
            {{ this.$store.state.blogInfo.tagCount }}
          </div>
        </router-link>
      </div>
    </div>
    <hr />
    <!-- 页面导航 -->
    <div class="menu-container">
      <div class="menus-item">
        <router-link to="/">
          <i class="iconfont icon-home1" /> 首页
        </router-link>
      </div>
      <div class="menus-item">
        <router-link to="/archives">
          <i style="color: cornflowerblue;" class="iconfont icon-faxian" /> 归档
        </router-link>
      </div>
      <div class="menus-item">
        <router-link to="/categories">
          <i style="color: mediumpurple;" class="iconfont icon-fenlei" /> 分类
        </router-link>
      </div>
      <div class="menus-item">
        <router-link to="/tags">
          <i style="color: lightpink;" class="iconfont icon-biaoqian-" /> 标签
        </router-link>
      </div>
      <div class="menus-item">
        <router-link to="/links">
          <i class="iconfont icon-link" /> 友链
        </router-link>
      </div>
      <div class="menus-item">
        <router-link to="/about">
          <i class="iconfont icon-navigation" /> 关于
        </router-link>
      </div>
      <div class="menus-item">
        <router-link to="/message">
          <i class="iconfont icon-pinglun" /> 留言
        </router-link>
      </div>
      <div class="menus-item" v-if="!this.$store.state.avatar">
        <a @click="openLogin"><i class="iconfont icon-denglu" /> 登录 </a>
      </div>
      <div v-else>
        <div class="menus-item">
          <router-link to="/user">
            <i class="iconfont icon-icon_gerenzhongxin_x" /> 个人中心
          </router-link>
        </div>
        <div class="menus-item">
          <a @click="logout"><i class="iconfont icon-tuichu" /> 退出</a>
        </div>
      </div>
    </div>
  </v-navigation-drawer>
</template>

<style scoped>
.blogger-info {
  padding: 26px 30px 0;
  text-align: center;
}
.blog-info-wrapper {
  display: flex;
  align-items: center;
  padding: 12px 10px 0;
}
.blog-info-data {
  flex: 1;
  line-height: 2;
  text-align: center;
}
hr {
  border: 2px dotted #e3d2fd;
  margin: 20px 0;
}
.menu-container {
  padding: 0 10px 40px;
  animation: 0.8s ease 0s 1 normal none running sidebarItem;
}
.menus-item a {
  padding: 6px 30px;
  display: block;
  line-height: 2;
}
.menus-item i {
  margin-right: 2rem;
}
@keyframes sidebarItem {
  0% {
    transform: translateX(200px);
  }
  100% {
    transform: translateX(0);
  }
}
</style>

<script>
export default {
  computed: {
    drawer: {
      set(value) {
        this.$store.state.drawer = value;
      },
      get() {
        return this.$store.state.drawer;
      }
    },
    isLogin() {
      return this.$store.state.userId;
    }
  },
  methods: {
    openLogin() {
      this.$store.state.loginFlag = true;
    },
    logout() {
      //如果在个人中心则跳回上一页
      if (this.$route.path == "/user") {
        this.$router.go(-1);
      }
      this.axios.get("/api/logout").then(({ data }) => {
        if (data.flag) {
          this.$store.commit("logout");
          this.$toast({ type: "success", message: "注销成功" });
        } else {
          this.$toast({ type: "error", message: data.message });
        }
      });
    }
  }
};
</script>
