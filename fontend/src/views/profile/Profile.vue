<!--
 * @Descripttion: 个人信息
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-08 15:32:56
 * @LastEditTime: 2022-11-02 15:01:11
-->
<template>
  <div class="profile">
    <YHHeader />
    <div class="profile-border">
      <main>
        <ul class="profile-nav">
          <router-link to="/profile/information" tag="li">我的信息</router-link>
          <router-link to="/profile/alter" tag="li">修改信息</router-link>
          <router-link to="/profile/password" tag="li">修改密码</router-link>
          <router-link to="/profile/friends" tag="li">我的好友</router-link>
          <router-link to="/profile/apply" tag="li">好友申请</router-link>
          <router-link to="/profile/message" tag="li">我的消息</router-link>
          <router-link to="/profile/like" tag="li">我的喜欢</router-link>
          <router-link to="/profile/goods" tag="li">我的商品</router-link>
          <router-link to="/profile/posts" tag="li">我的帖子</router-link>
        </ul>
        <router-view />
      </main>
    </div>
  </div>
</template>

<script>
// 组件
import YHHeader from "@/components/common/YHHeader.vue";
import YHFooter from "@/components/common/YHFooter.vue";

export default {
  components: {
    YHHeader,
    YHFooter,
  },
};
</script>

<style lang="scss" scoped>
.profile {
  .profile-border {
    padding: 4rem 0 0;
    border-left: 0.3125rem solid #000;
    border-right: 0.3125rem solid #000;
    border-bottom: 0.3125rem solid #000;
  }
  main {
    margin: 0 auto;
    width: 85%;
    height: 95%;
    border: 0.3125rem solid #000;
    display: flex;
    ul.profile-nav {
      height: auto;
      li {
        text-align: center;
        padding: 0.625rem 0;
        height: auto;
        border-bottom: 0.2rem solid #000;
        cursor: default;

        &:hover {
          background: rgb(231, 231, 231);
        }

        &.router-link-active {
          color: #fff;
          background: #000;
        }
      }
    }
  }
}

@media screen and (min-width: 760px) {
  @import "assets/scss/profile/pc";
}

@media screen and (max-width: 760px) {
  @import "assets/scss/profile/mobile";
}
</style>