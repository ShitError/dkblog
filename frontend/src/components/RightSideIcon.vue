<template>
  <div class="rightside">
     <i class="iconfont rightside-icon icon-sousuo" @click="openSearch"/>
     <i :class="'iconfont rightside-icon ' + icon" @click="check" />
     <i @click="backTop" class="iconfont rightside-icon icon-fanhuidingbu" />
  </div>
</template>

<script>
export default {
  mounted() {
    window.addEventListener("scroll", this.scrollToTop);
  },
  destroyed() {
    window.removeEventListener("scroll", this.scrollToTop);
  },
  data: function() {
    return {
      icon: "icon-yueliang"
    };
  },
  methods: {
    // 回到顶部方法
    backTop() {
      window.scrollTo({
        behavior: "smooth",
        top: 0
      });
    },
    openSearch() {
      this.$store.state.searchFlag = true;
    },
    scrollToTop() {
      const that = this;
      let scrollTop =
          window.pageYOffset ||
          document.documentElement.scrollTop ||
          document.body.scrollTop;
      that.scrollTop = scrollTop;
    },
    check() {
      const flag = this.icon == "icon-yueliang";
      this.icon = flag ? "icon-taiyang" : "icon-yueliang";
      this.$vuetify.theme.dark = !this.$vuetify.theme.dark;
    }
  }
};
</script>

<style scoped>
.rightside {
  z-index: 4;
  position: fixed;
  right: -45px;
  bottom: 85px;
  transition: all 0.5s;
  transform: translateX(-48px);
}
.rightside-icon
{
  display: block;
  margin-bottom: 2px;
  width: 30px;
  height: 30px;
  background-color: rgba(0,0,0,0);
  text-align: center;
  font-size: 16px;
  line-height: 30px;
  cursor: pointer;
}
.rightside-icon:hover{
  background-color: cornflowerblue;
}

</style>
