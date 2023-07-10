<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-31 15:52:34
 * @LastEditTime: 2022-11-10 16:02:23
-->
<template>
  <div class="page">
    <span @click="changeCurrent(current - 1)">&lt;</span>
    <span
      v-for="item in total"
      :key="item"
      :class="isActive(item)"
      @click="changeCurrent(item)"
      >{{ item }}</span
    >
    <span @click="changeCurrent(current + 1)">&gt;</span>
  </div>
</template>

<script>
export default {
  data() {
    return {
      current: 1, // 当前页，默认第一页
    };
  },
  props: {
    total: Number, // 总页数
  },
  methods: {
    isActive(index) {
      return { active: this.current == index };
    },
    /**
     * 切换当前页数
     * @param {Number} index 需要切换到的页数
     */
    changeCurrent(index) {
      if (index <= 0) return;
      if (index > this.total) return;
      this.current = index;
      this.$emit("changeCurrent", this.current);
    },
  },
};
</script>

<style lang="scss" scoped>
.page {
  text-align: center;
  height: auto;

  span {
    margin: 0 5px 0 0;
    padding: 0 5px;
    border: 2px solid #000;
    cursor: default;

    &.active {
      color: #fff;
      background: #000;
    }
  }
}
</style>