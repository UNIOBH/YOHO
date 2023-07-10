/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-05 20:42:03
 * @LastEditTime: 2022-10-26 09:55:44
 */
const webpack = require("webpack");

module.exports = {
  configureWebpack: {
    resolve: {
      alias: {
        assets: "@/assets",
        components: "@/components",
        views: "@/views",
        api: "@/api",
        utils: "@/utils",
      },
    },
    plugins: [
      new webpack.ProvidePlugin({
        "window.Quill": "quill/dist/quill.js",
        Quill: "quill/dist/quill.js",
      }),
    ],
  },
  devServer: {
    // 代理跨域
    proxy: {
      // 拦截以 /api 开头的接口
      "/api": {
        target: "http://localhost:8081",
        pathRewrite: {
          "^/api": "/", // 使用 /api 代替 target 里的地址
        },
      },
    },
  },
};
