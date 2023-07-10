<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-24 20:24:02
 * @LastEditTime: 2022-11-10 13:29:11
-->
<template>
  <div class="profile-message">
    <div class="profile-message-item" v-for="item in chatList" :key="item.id">
      <img :src="item.to.avatar" />
      <div>
        <p>
          {{ item.to.nickname }} {{ item.remake == "null" ? "" : item.rameke }}
        </p>
        <p>{{ item.content }}</p>
      </div>
      <span>{{ getTime(item.time) }}</span>
    </div>
  </div>
</template>

<script>
// 网络请求
import { getNewestChat } from "api/chat";

// utils
import { format } from "utils/time";

export default {
  data() {
    return {
      chatList: [],
    };
  },
  created() {
    getNewestChat(JSON.parse(sessionStorage.getItem("user")).id).then((res) => {
      if (res.status == 200) this.chatList = res.data;
    });
  },
  methods: {
    getTime(time) {
      return format(time);
    },
  },
};
</script>

<style lang="scss" scoped>
div.profile-message {
  padding: 1.5rem 2.5rem;
  overflow-y: scroll;

  &::-webkit-scrollbar {
    width: 0; // 删除滚动条
  }

  .profile-message-item {
    margin: 0 0 0.9375rem;
    padding: 0.3125rem 0.625rem;
    height: auto;
    border: 0.2rem solid #000;
    border-radius: 0.2rem;
    display: flex;
    align-items: center;

    img {
      margin: 0 0.4rem 0 0;
      width: 3rem;
      height: 3rem;
    }
    div {
      p {
        text-overflow: ellipsis;
        -webkit-line-clamp: 1;
        margin: 0 0 0.3125rem;
        height: auto;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        overflow: hidden;

        &:last-child {
          font-size: 0.85rem;
        }
      }
    }
    span {
      color: rgb(71, 71, 71);
      font-size: 0.75rem;
      text-align: right;
      height: auto;
      flex: 40%;
    }
  }
}
</style>