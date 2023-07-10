<!--
 * @Descripttion: 好友申请
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-11-02 15:00:32
 * @LastEditTime: 2022-11-10 10:49:03
-->
<template>
  <ul class="profile-apply">
    <li v-for="item in applys" :key="item.id">
      <img :src="item.friend.avatar" alt="" />
      <div class="profile-apply-msg">
        <p>{{ item.friend.nickname }}</p>
        <p>验证信息：{{ item.verification }}</p>
      </div>
      <div class="profile-apply-button">
        <button @click="toUpdateState(item.id, 2)">拒绝</button>
        <button @click="toUpdateState(item.id, 1)">同意</button>
      </div>
    </li>
  </ul>
</template>

<script>
// 网络请求
import { getFriendsInVerificationByUId, updateStateById } from "api/friend";

export default {
  data() {
    return {
      applys: [],
    };
  },
  created() {
    getFriendsInVerificationByUId(
      JSON.parse(sessionStorage.getItem("user")).id
    ).then((res) => {
      if (res.status == 200) this.applys = res.data;
    });
  },
  methods: {
    toUpdateState(id, state) {
      updateStateById(id, state).then(res => {
        if (res.status == 200) this.$router.go(0);
      });
    },
  },
};
</script>

<style lang="scss" scoped>
ul.profile-apply {
  padding: 1.25rem;
  height: 100%;
  overflow-y: scroll;

  &::-webkit-scrollbar {
    width: 0;
  }

  li {
    margin: 0.625rem 0 0 0;
    padding: 0.625rem;
    height: auto;
    border: #000 0.1875rem solid;
    border-radius: 0.625rem;
    display: flex;
    align-items: center;

    img {
      width: 3.125rem;
      height: 3.125rem;
    }

    .profile-apply-msg {
      margin: 0 0.625rem;
    }

    .profile-apply-button {
      display: flex;
      align-items: center;
      flex: 80%;

      button {
        color: #fff;
        padding: 0.625rem 0;
        background: #000;
        &:first-child {
          color: #000;
          margin: 0 0.3125rem 0 0;
          background: #fff;
        }
      }
    }
  }
}
</style>