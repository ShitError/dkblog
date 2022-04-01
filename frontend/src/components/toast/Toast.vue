<template>
  <v-snackbar v-model="show" top :timeout="2000" :color="color">
    <i :class="icon" style="margin-right: 10px;" />{{ message }}
  </v-snackbar>
</template>

<script>
export default {
  data: function() {
    return {
      show: false,
      message: "",
      type: "normal",
      color: "#ba2f7b",
      icon: ""
    };
  },
  watch: {
    type(value) {
      switch (value) {
        case "error":
          this.color = "#F56C6C";
          this.icon = "iconfont icon-cuowuguanbiquxiao";
          break;
        case "success":
          this.color = "#52C41A";
          this.icon = "iconfont icon-chenggong";
          break;
        case "warnning":
          this.color = "#F57C00";
          this.icon = "iconfont icon-chenggong";
      }
    }
  }
};
</script>
