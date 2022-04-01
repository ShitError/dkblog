<template>
  <v-footer app absolute v-if="!this.isMessage">
    <div class="footer-wrap">
      <div>
        ©{{ blogInfo.websiteConfig.websiteCreateTime | year }} -
        {{ new Date().getFullYear() }} By
        {{ blogInfo.websiteConfig.websiteAuthor }}
      </div>
      <a href="https://beian.miit.gov.cn/" target="_blank">
        {{ blogInfo.websiteConfig.websiteRecordNo }}
      </a>
    </div>
  </v-footer>
</template>

<script>
export default {
  computed: {
    isMessage() {
      return this.$route.path == "/message";
    },
    blogInfo() {
      return this.$store.state.blogInfo;
    }
  }
};
</script>

<style scoped>
.footer-wrap {
  height: 100%;
  width: 100%;
  padding: 40px 20px;
  font-size: 14px;
  text-align: center;
  animation: gradientBG 15s ease infinite;

}
.footer-wrap a {
  color: gray !important;
}

</style>
