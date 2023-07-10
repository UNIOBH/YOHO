<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-29 14:39:37
 * @LastEditTime: 2022-11-11 15:03:04
-->
<template>
  <div class="admin">
    <div class="admin-top-bar">
      <span>管理员用户名</span>
      <router-link tag="span" to="/admin/register">注册</router-link>
      <span @click="logout">退出登录</span>
      <span>YoHo——管理员系统</span>
    </div>
    <main>
      <ul class="admin-side-bar">
        <li @click.self="userIsShow = !userIsShow">
          用户管理
          <ul v-show="userIsShow">
            <router-link tag="li" to="/admin/user">——查询用户</router-link>
            <router-link tag="li" to="/admin/school">——添加学校</router-link>
          </ul>
        </li>
        <li @click.self="goodsIsShow = !goodsIsShow">
          商品管理
          <ul v-show="goodsIsShow">
            <router-link tag="li" to="/admin/search">——查询商品</router-link>
            <router-link tag="li" to="/admin/alter">——修改商品</router-link>
            <router-link tag="li" to="/admin/category">——商品类别</router-link>
          </ul>
        </li>
        <li @click.self="invitationIsShow = !invitationIsShow">
          帖子管理
          <ul v-show="invitationIsShow">
            <router-link to="/admin/invitation" tag="li"
              >——查询帖子</router-link
            >
            <router-link to="/admin/modify" tag="li">——修改帖子</router-link>
          </ul>
        </li>
      </ul>
      <router-view />
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userIsShow: true,
      goodsIsShow: false,
      invitationIsShow: false,
    };
  },
  methods: {
    logout() {
      sessionStorage.removeItem("user");
      this.$router.push("/login");
    }
  }
};
</script>

<style lang="scss" scoped>
.admin {
  height: 100vh;
  border: 0.3125rem solid #000;
  .admin-top-bar {
    color: #fff;
    font-size: 0.875rem;
    padding: 0.5rem;
    height: 2rem;
    background: #000;
    display: flex;
    span {
      &:last-child {
        text-align: right;
      }
    }
  }

  main {
    height: calc(100% - 2rem);
    display: flex;

    ul.admin-side-bar {
      text-align: center;
      max-width: 12.5rem;
      height: auto;
      border-right: 0.3125rem solid #000;

      & > li {
        padding: 0.625rem 0 0 0;
        height: auto;
        border-bottom: 0.3125rem solid #000;

        ul {
          margin: 10px 0 0 0;
          li {
            font-size: 14px;
            text-align: right;
            padding: 10px 10px 10px 0;
            height: auto;
            border-top: #000 solid 0.3125rem;
            &.router-link-active {
              color: #fff;
              background: #000;
            }
          }
        }
      }
    }
  }
}
</style>