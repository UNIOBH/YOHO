<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-27 13:46:53
 * @LastEditTime: 2022-12-07 15:39:07
-->
<template>
  <div class="profile-posts">
    <h3>
      共发布<span>{{ list.length }}条</span>帖子
    </h3>
    <main>
      <!-- 循环 start -->
      <div
        class="profile-posts-item"
        v-for="item in list"
        :key="item.id"
        @dblclick="toDeatail(item.id)"
      >
        <h4>{{ item.title }}</h4>
        <p v-html="item.details"></p>
      </div>
      <!-- 循环 end -->
    </main>
  </div>
</template>

<script>
// 网络请求
import { getInvitatonsByUId } from "api/invitation";
export default {
  data() {
    return {
      list: [],
    };
  },
  created() {
    getInvitatonsByUId(JSON.parse(sessionStorage.getItem("user")).id).then(
      (res) => {
        if (res.status == 200) this.list = res.data;
      }
    );
  },
  methods: {
    toDeatail(id) {
      this.$router.push(`/InvitationDetail/${id}`);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "assets/scss/mixin";
.profile-posts {
  padding: 1.25rem;

  h3 {
    color: rgb(106, 106, 106);
    margin: 0 0 0.625rem 0;
    height: auto;
    span {
      color: #000;
      text-decoration: underline;
      margin: 0.3125rem;
    }
  }

  main {
    height: calc(100% - 0.7rem);
    overflow-y: scroll;

    &::-webkit-scrollbar {
      width: 0;
    }

    .profile-posts-item {
      margin: 0 0 0.625rem 0;
      padding: 0.625rem;
      height: auto;
      border: #000 solid 0.1875rem;
      border-radius: 0.3125rem;
      cursor: pointer;

      h4 {
        @include ellipsis(1);
      }

      p {
        @include ellipsis(1);
        margin: 0.625rem 0 0;

        img {
          display: none;
        }
      }
    }
  }
}

@media screen and (max-width: 760px) {
  .profile-posts {
    main {
      height: calc(100% - 5rem);
    }
  }
}
</style>