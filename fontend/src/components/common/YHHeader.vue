<!--
 * @Descripttion: 用户头像和用户名未渲染
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-06 12:53:50
 * @LastEditTime: 2022-11-17 10:21:42
-->
<template>
  <header>
    <div class="header-logo">
      <img src="~assets/imgs/logo.png" alt="" />
    </div>
    <div class="header-right-pc">
      <ul class="header-nav">
        <li>
          <router-link to="/home" :class="isUnderline('/home')"
            >首页</router-link
          >
        </li>
        <li>
          <router-link to="/recommend" :class="isUnderline('/recommend')"
            >推荐</router-link
          >
        </li>
        <li>
          <router-link to="/trade" :class="isUnderline('/trade')"
            >二手市场</router-link
          >
        </li>
        <li>
          <router-link to="/forum" :class="isUnderline('/forum')"
            >论坛</router-link
          >
        </li>
      </ul>
      <div class="header-user">
        <div class="header-user-show" v-if="user">
          <div class="header-user-avatar">
            <img :src="user.avatar" alt="" />
          </div>
          <span>{{ user.nickname }}</span>
        </div>
        <div v-else class="header-user-no">
          <router-link to="/login">去登录</router-link>
          <router-link to="/register">去注册</router-link>
        </div>
        <ul class="header-user-hide" v-if="user">
          <li class="header-user-message">
            <router-link to="/profile">个人中心</router-link>
          </li>
          <router-link to="/profile/friends" tag="li" class="header-user-friend"
            >我的好友</router-link
          >
          <li class="header-user-logout" @click="logout">退出登录</li>
          <li class="header-user-logoff" @click="toDelUser">注销账户</li>
        </ul>
      </div>
    </div>
    <div class="header-right-mobile">
      <ul
        class="header-mobile-icon"
        @click="moblieNavIsShow = !moblieNavIsShow"
      >
        <li></li>
        <li></li>
        <li></li>
      </ul>
      <transition name="fade">
        <ul class="header-mobile-nav" v-show="moblieNavIsShow">
          <li>
            <router-link to="/home">首页</router-link>
          </li>
          <li>
            <router-link to="/recommend">推荐</router-link>
          </li>
          <li>
            <router-link to="/trade">二手市场</router-link>
          </li>
          <li>
            <router-link to="/forum">论坛</router-link>
          </li>
          <li>
            <router-link to="/profile">我的</router-link>
          </li>
          <li>
            <a href="#" @click="logout">退出登录</a>
          </li>
          <li>
            <a href="#" @click="toDelUser">注销</a>
          </li>
        </ul>
      </transition>
    </div>
  </header>
</template>

<script>
// 网络请求
import { delUser } from "api/user";

export default {
  data() {
    return {
      moblieNavIsShow: false,
      user: {}, // 用户信息
    };
  },
  methods: {
    /**
     * 判断当前页面是否需要加上underline类名
     * @param {String} path 当前界面的path
     */
    isUnderline(path) {
      return this.$route.path.includes(path) ? "underline" : "no-underline";
    },
    toDelUser() {
      delUser(this.user.id).then((res) => {
        if (res.status == 200) this.logout();
      });
    },
    logout() {
      sessionStorage.removeItem("user");
      this.$router.push("/login");
    },
  },
  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
  },
};
</script>

<style lang="scss">
header {
  padding: 0 1.875rem;
  width: 100%;
  height: 3.125rem;
  border: 0.3125rem solid #000;
  display: flex;
  align-items: center;

  .header-logo {
    width: auto;
    position: relative;

    img {
      position: absolute;
      top: 50%;
      transform: translateY(-50%);
    }
  }
  .header-right-pc,
  .header-right-mobile {
    width: auto;
  }
}

@media screen and (min-width: 760px) {
  @import "assets/scss/header/pc";
}
@media screen and (max-width: 760px) {
  @import "assets/scss/header/mobile";
}
</style>