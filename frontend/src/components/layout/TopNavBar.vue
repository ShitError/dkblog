<template>
  <v-app-bar app :class="navClass" hide-on-scroll flat height="60">
    <!-- 手机端导航栏 -->
    <div class="d-md-none nav-mobile-container">
      <div style="font-size:18px;font-weight:bold">
        <router-link to="/">
          {{ blogInfo.websiteConfig.websiteName }}
        </router-link>
      </div>
      <div style="margin-left:auto">
        <a @click="openDrawer" style="margin-left:10px;font-size:20px">
          <i class="iconfont icon-hanbaobao" />
        </a>
      </div>
    </div>
    <!-- 电脑导航栏 -->
    <div class="d-md-block d-none nav-container">
      <div class="float-left blog-title">
        <router-link to="/">
          <div v-if="blogInfo.websiteConfig">
            {{ blogInfo.websiteConfig.websiteName }}
          </div>
        </router-link>
      </div>
      <div class="float-right nav-title">
        <div class="menus-item">
          <router-link class="menu-btn" to="/">
            <i class="iconfont icon-home1" /> 首页
          </router-link>
        </div>
        <div class="menus-item">
          <a class="menu-btn">
            <i class="iconfont icon-layers" /> 归档
            <i class="iconfont icon-xiangxia-2 expand" />
          </a>
          <ul class="menus-submenu">
            <li>
              <router-link to="/archives">
                <i class="iconfont icon-faxian" /> 时间
              </router-link>
            </li>
            <li>
              <router-link to="/categories">
                <i class="iconfont icon-fenlei" /> 分类
              </router-link>
            </li>
            <li>
              <router-link to="/tags">
                <i class="iconfont icon-biaoqian-" /> 标签
              </router-link>
            </li>
          </ul>
        </div>
        <div class="menus-item">
          <router-link class="menu-btn" to="/links">
            <i class="iconfont icon-link" /> 友链
          </router-link>
        </div>
        <div class="menus-item">
          <router-link class="menu-btn" to="/about">
            <i class="iconfont icon-navigation" /> 关于
          </router-link>
        </div>
        <div class="menus-item">
          <router-link class="menu-btn" to="/message">
            <i class="iconfont icon-pinglun" /> 留言
          </router-link>
        </div>
        <div class="float-right menus-item">
          <a
            class="menu-btn"
            v-if="!this.$store.state.avatar"
            @click="openLogin"
          >
            <i class="iconfont icon-denglu" /> 登录
          </a>
          <template v-else>
            <img
              class="user-avatar"
              :src="this.$store.state.avatar"
              height="30"
              width="30"
              alt=""
            />
            <ul class="menus-submenu">
              <li>
                <router-link to="/user">
                  <i class="iconfont icon-icon_gerenzhongxin_x" /> 个人中心
                </router-link>
              </li>
              <li>
                <a @click="logout"><i class="iconfont icon-tuichu" /> 退出</a>
              </li>
            </ul>
          </template>
        </div>
      </div>
    </div>
  </v-app-bar>
</template>

<script>
export default {
  mounted() {
    window.addEventListener("scroll", this.scroll);
  },
  data: function() {
    return {
      navClass: "nav"
    };
  },
  methods: {
    scroll() {
      const that = this;
      that.scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      if (that.scrollTop > 60) {
        that.navClass = "nav-fixed";
      } else {
        that.navClass = "nav";
      }
    },
    openDrawer() {
      this.$store.state.drawer = true;
    },
    openLogin() {
      this.$store.state.loginFlag = true;
    },
    logout() {
      //如果在个人中心则跳回上一页
      if (this.$route.path === "/user") {
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
  },
  computed: {
    avatar() {
      return this.$store.state.avatar;
    },
    // eslint-disable-next-line vue/no-dupe-keys
    blogInfo() {
      return this.$store.state.blogInfo;
    }
  }
};
</script>

<style scoped>
a {
  text-decoration: none;
}

i {
  margin-right: 4px;
}
ul {
  list-style: none;
  padding: 0;
}

.nav a {
  color: lightslategrey !important;
}

.nav-fixed .menus-item a,
.nav-fixed .blog-title a {
  text-shadow: none;
  color: lightgrey;
}
.nav-container {
  font-size: 16px;
  width: 100%;
  height: 100%;
}
.nav-mobile-container {
  width: 100%;
  display: flex;
  align-items: center;
}
.blog-title,
.nav-title {
  display: flex;
  align-items: center;
  height: 100%;
}
.blog-title a {
  font-size: 18px;
  font-weight: bold;
}
.menus-item {
  position: relative;
  display: inline-block;
  margin: 0 0 0 0.875rem;
}
.menus-item a {
  transition: all 0.2s;
}
.menus-item > a:hover {
  color: #409dfe !important;
}
.nav-fixed .menu-btn:hover {
  color: #409dfe !important;
}
.menu-btn:hover:after {
  width: max-content;
}
.user-avatar {
  cursor: pointer;
}
.menus-item:last-child {
  flex-direction: row-reverse !important;
}
.menus-item:hover .menus-submenu {
  display: block;
}
.menus-submenu {
  position: absolute;
  display: none;
  right: 0;
  width: max-content;
  margin-top: 8px;
  background-color: #fff;
  animation: submenu 0.3s 0.1s ease both;
}
.menus-submenu:before {
  position: absolute;
  top: -8px;
  left: 0;
  width: 100%;
  height: 20px;
  content: "";
}
.menus-submenu a {
  line-height: 2;
  color: #4c4948 !important;
  text-shadow: none;
  display: block;
  padding: 6px 14px;
}
.menus-submenu a:hover {
  background: #4ab1f4;
}
@keyframes submenu {
  0% {
    opacity: 0;
    filter: alpha(opacity=0);
    transform: translateY(0);
  }
  100% {
    opacity: 1;
    filter: none;
    transform: translateY(0);
  }
}
</style>
