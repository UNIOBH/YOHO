<!--
 * @Descripttion: 论坛
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-08 15:31:38
 * @LastEditTime: 2022-11-17 15:29:29
-->
<template>
  <div class="forum">
    <YHHeader />
    <div class="forum-boder">
      <main class="forum-main">
        <aside class="forum-search">
          <input type="text" placeholder="请输入搜索内容……" v-model="keyword" />
          <input type="submit" value="搜 索" @click="toGetInvitation" />
          <router-link tag="button" to="/ReleaseInvitation/0"
            >发布帖子</router-link
          >
        </aside>
        <article class="forum-information">
          <Invitation
            v-for="item in invitationList"
            :key="item.id"
            :invitation="item"
          />
        </article>
      </main>
    </div>
    <YHFooter />
  </div>
</template>

<script>
// 组件
import YHHeader from "@/components/common/YHHeader.vue";
import YHFooter from "@/components/common/YHFooter.vue";
import Invitation from "@/components/forum/Invitation.vue";

// 网络请求
import { getInvitation } from "api/invitation.js";

export default {
  data() {
    return {
      pages: 0, // 总页数
      current: 1, // 当前页数
      keyword: "", // 查询关键字
      invitationList: [], // 帖子列表
    };
  },
  components: {
    YHHeader,
    YHFooter,
    Invitation,
  },
  methods: {
    toGetInvitation() {
      getInvitation(
        this.current,
        5,
        JSON.parse(sessionStorage.getItem("user")).id,
        this.keyword == "" ? " " : this.keyword
      ).then((res) => {
        if (res.status == 200) {
          this.invitationList.splice(this.invitationList.length, 0, ...res.data.list)
          this.pages = res.data.total;
        } else if (res.status == 201) {
          this.invitationList = [];
          this.pages = 0;
        }
      });
    },
    loadData() {
      const scrollTop =
        document.documentElement.scrollTop || document.body.scrollTop; // 被卷去的高度
      const clientHeight = document.documentElement.clientHeight; // 浏览器可视高度（content + padding）
      const scrollHeight = document.documentElement.scrollHeight; // 浏览器高度
      if (scrollTop + clientHeight + 1 >= scrollHeight) {
        this.current++;
        if (this.current <= this.pages) this.toGetInvitation();
      }
    },
  },
  created() {
    this.toGetInvitation();
  },
  mounted() {
    window.addEventListener("scroll", this.loadData);
  },
};
</script>

<style lang="scss">
.forum {
  .forum-boder {
    padding: 2.3rem 0 0;
    min-height: 35rem;
    height: auto;
    border-left: 0.3125rem solid #000;
    border-right: 0.3125rem solid #000;
    .forum-main {
      margin: 0 auto;
      padding: 0 0 5rem 0;
      width: 85%;
      height: auto;
      .forum-search {
        height: 1.875rem;
        display: flex;
        input {
          font-size: 1rem;
          border: 3px solid #000;
          &[type="text"] {
            padding: 0 0 0 0.3125rem;
            border-right: none;
            outline: none;
          }
          &[type="submit"] {
            color: #fff;
            background: #000;
            border-left: none;
            flex: 15%;
          }
        }
        button {
          color: #fff;
          margin: 0 0 0 1.2rem;
          background: #000;
          flex: 30%;
        }
      }

      .forum-information {
        .invitation {
          margin: 1.25rem 0 0;
        }
      }
    }
  }
}
</style>