<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-11 11:17:38
 * @LastEditTime: 2022-10-20 14:47:13
-->
<template>
  <!--
    canvas标签的器宽高不要在css中设置，会被浏览拉伸，从而设置的不是自己想要的宽高
    正确方法：
      1. 直接在canvas标签里设置
      2. 使用js设置
  -->
  <canvas class="code" :width="w" :height="h"></canvas>
</template>

<script>
const codes = [
  "q",
  "w",
  "e",
  "r",
  "t",
  "y",
  "u",
  "i",
  "o",
  "p",
  "a",
  "s",
  "d",
  "f",
  "g",
  "h",
  "j",
  "k",
  "l",
  "z",
  "x",
  "c",
  "v",
  "b",
  "n",
  "m",
  1,
  2,
  3,
  4,
  5,
  6,
  7,
  8,
  9,
  0,
];

export default {
  name: "VerificationCode",
  data() {
    return {
      code: "",
      cxt: null, // 画笔对象
    };
  },
  props: {
    w: {
      type: Number,
      require: true,
    },
    h: {
      type: Number,
      require: true,
    },
  },
  created() {
    this.getCode();
  },
  mounted() {
    this.cxt = this.$el.getContext("2d");
    this.cxt.fillStyle = "#000"; // 设置画笔的颜色
    this.cxt.lineWidth = 2; // 设置画笔粗细
    this.cxt.strokeRect(0, 0, this.w, this.h); // 绘制边框
    this.drawCode();
  },
  methods: {
    /**
     * 生成验证码
     */
    getCode() {
      this.code = ""; // 清空验证码
      for (let i = 0; i < 6; i++) {
        this.code += codes[Math.floor(Math.random() * codes.length)];
      }
    },
    /**
     * 绘制验证码
     */
    drawCode() {
      // 清空画布
      this.cxt.clearRect(
        1,
        1,
        this.$el.offsetWidth - 2,
        this.$el.offsetHeight - 2
      );
      this.cxt.font = "25px Arial";
      // 绘制文本
      for (let i = 0; i < this.code.length; i++) {
        this.cxt.fillText(this.code[i], (i + 1) * 25, 30);
      }
      // 绘制干扰项
      for (let i = 0; i < 130; i++) {
        this.cxt.beginPath();
        this.cxt.arc(
          Math.random() * this.$el.offsetWidth,
          Math.random() * this.$el.offsetHeight,
          1,
          0,
          Math.PI * 2,
          true
        );
        this.cxt.closePath();
        this.cxt.fill();
      }
    },
  },
};
</script>

<style lang="scss" scoped>
</style>