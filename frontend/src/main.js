import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import animated from "animate.css";
import "./assets/css/common.css";
import "./assets/css/iconfont/iconfont.css";
import "./assets/css/markdown.css";
import "./assets/css/vue-social-share/client.css";
import "highlight.js/styles/atom-one-dark.css";
import "vue-image-swipe/dist/vue-image-swipe.css";
import "@mdi/font/css/materialdesignicons.min.css";
import "nprogress/nprogress.css";
import config from "./assets/js/config";
import Share from "vue-social-share";
import axios from "axios";
import VueAxios from "vue-axios";
import InfiniteLoading from "vue-infinite-loading";
import VueImageSwipe from "vue-image-swipe";
import { vueBaberrage } from "vue-baberrage";
import Toast from "./components/toast/index";
import NProgress from "nprogress";
import dayjs from "dayjs";


Vue.prototype.config = config;
Vue.config.productionTip = false;
Vue.use(animated);
Vue.use(Share);
Vue.use(vueBaberrage);
Vue.use(dayjs);
Vue.use(Toast);
Vue.use(InfiniteLoading);
Vue.use(VueImageSwipe);
Vue.use(NProgress);
Vue.use(VueAxios, axios);

Vue.filter("date", function(value) {
  return dayjs(value).format("YYYY-MM-DD");
});

Vue.filter("year", function(value) {
  return dayjs(value).format("YYYY");
});

Vue.filter("hour", function(value) {
  return dayjs(value).format("HH:mm:ss");
});

Vue.filter("num", function(value) {
  if (value >= 1000) {
    return (value / 1000).toFixed(1) + "k";
  }
  return value;
});

router.beforeEach((to, from, next) => {
  NProgress.start();
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  next();
});

router.afterEach(() => {
  window.scrollTo({
    top: 0,
    behavior: "instant"
  });
  NProgress.done();
});

axios.interceptors.response.use(
    function(response) {
      switch (response.data.code) {
        case 50000:
          Vue.prototype.$toast({ type: "error", message: "系统异常" });
      }
      return response;
    },
    function(error) {
      return Promise.reject(error);
    }
);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
