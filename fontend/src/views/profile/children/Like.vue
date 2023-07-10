<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-25 14:53:58
 * @LastEditTime: 2022-11-11 20:28:16
-->
<template>
  <div class="profile-like">
    <h3>已给<span>{{list.length}}条</span>帖子点赞</h3>
    <main>
      <!-- 循环 start -->
      <div class="profile-like-item" v-for="item in list" :key="item.id">
        <h4>{{ item.title }}</h4>
        <p v-html="item.details"></p>
      </div>
      <!-- 循环 end -->
    </main>
  </div>
</template>

<script>
// 网络请求
import { getLikeInvitaionByUId } from "api/invitation";

export default {
  data() {
    return {
      list: [],
    };
  },
  created() {
    getLikeInvitaionByUId(JSON.parse(sessionStorage.getItem("user")).id).then(
      (res) => {
        if (res.status == 200) this.list = res.data;
      }
    );
  },
};
</script>

<style lang="scss" scoped>
@import "assets/scss/mixin";
.profile-like {
  padding: 20px;

  h3 {
    color: rgb(108, 108, 108);
    margin: 0 0 0.625rem 0;
    height: auto;
    span {
      color: #000;
      text-decoration: underline;
      margin: 0 0.1875rem;
    }
  }

  main {
    height: calc(100% - 20px);
    overflow-y: scroll;

    &::-webkit-scrollbar {
      width: 0;
    }

    .profile-like-item {
      margin: 0 0 0.625rem 0;
      padding: 0.625rem 1.25rem;
      height: auto;
      border: 0.1875rem solid #000;
      border-radius: 0.625rem;
      cursor: pointer;

      h4 {
        @include ellipsis(1);
      }

      p {
        @include ellipsis(1);
        margin: 0.625rem 0 0 0;

        img {
          display: none;
        }
      }
    }
  }
}

@media screen and (max-width: 760px) {
  .profile-like {
    main {
      height: calc(100% - 50px);
    }
  }
}
</style>