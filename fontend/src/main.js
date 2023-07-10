/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-01 11:25:05
 * @LastEditTime: 2022-10-26 18:43:54
 */
import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import { VueMasonryPlugin } from "vue-masonry"; // 流式布局插件

Vue.use(VueMasonryPlugin);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: function (h) {
    return h(App);
  },
}).$mount("#app");
