<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-24 20:23:12
 * @LastEditTime: 2022-11-17 10:40:47
-->
<template>
  <div class="profile-friends">
    <div class="profil-friends-search">
      <input
        type="text"
        placeholder="请输入好友用户名或备注……"
        v-model="keyword"
      />
      <input type="submit" value="搜索" @click="searchFriends" />
    </div>
    <div class="profile-friends-list">
      <div
        class="profile-friends-list-item"
        v-for="item in friends"
        :key="item.id"
      >
        <img :src="item.friend.avatar" alt="" />
        <div>
          <p>{{ item.remake }}</p>
          <p>{{ item.friend.nickname }}</p>
        </div>
        <router-link :to="getTo(item)" tag="button">消息</router-link>
        <button @click="openRemake(item.id)">备注</button>
        <button @click="toDelFriend(item.id)">删除</button>
      </div>
    </div>
    <div class="profile-friends-remake" v-if="remakeIsShow">
      <input type="text" placeholder="请填写好友备注……" v-model="remake" />
      <div class="profile-friends-remake-button">
        <button @click="closeRemake">取消</button
        ><button @click="toUpdateRemake">确定</button>
      </div>
    </div>
  </div>
</template>

<script>
//网络请求
import { getFriendsByUId, delFriendById, updateRemake } from "api/friend";

export default {
  data() {
    return {
      friends: [],
      keyword: "",
      remake: "",
      remakeIsShow: false,
      id: null,
    };
  },
  created() {
    getFriendsByUId(JSON.parse(sessionStorage.getItem("user")).id, " ").then(
      (res) => {
        if (res.status == 200) this.friends = res.data;
      }
    );
  },
  methods: {
    getTo(item) {
      return `/chat/${item.friend.id}/${item.friend.nickname}/${item.remake}`;
    },
    toDelFriend(id) {
      delFriendById(id).then((res) => {
        if (res.status == 200) this.$router.go(0);
      });
    },
    searchFriends() {
      if (!this.keyword) return;
      getFriendsByUId(
        JSON.parse(sessionStorage.getItem("user")).id,
        this.keyword
      ).then((res) => {
        if (res.status == 200) this.friends = res.data;
        else if (res.status == 201) this.friends = [];
        this.keyword = "";
      });
    },
    openRemake(id) {
      this.remakeIsShow = true;
      this.id = id;
    },
    closeRemake() {
      this.remakeIsShow = false;
      this.id = null;
    },
    toUpdateRemake() {
      if (!this.id) return;
      if (!this.remake) return;
      updateRemake(this.id, this.remake).then(res => {
        if (res.status == 200) this.$router.go(0);
        else this.remake = "修改失败！";
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.profile-friends {
  padding: 1.25rem 2rem;
  height: 100%;
  position: relative;
  .profil-friends-search {
    margin: 0 0 1.5rem;
    height: auto;
    display: flex;
    input {
      font-size: 1.125rem;
      height: 2.5rem;
      border: 0.2rem solid #000;

      &[type="text"] {
        padding: 0 0 0 0.625rem;
      }
      &[type="submit"] {
        color: #fff;
        background: #000;
        flex: 30%;
      }
    }
  }

  .profile-friends-list {
    height: calc(100% - 1.5rem - 2.5rem);
    overflow-y: scroll;

    &::-webkit-scrollbar {
      width: 0; // 删除滚动条
    }
    .profile-friends-list-item {
      margin: 0 0 0.9375rem;
      padding: 0.3125rem 0.625rem;
      height: auto;
      border: 0.2rem solid #000;
      border-radius: 0.1875rem;
      display: flex;
      align-items: center;

      img {
        margin: 0 0.4rem 0 0;
        width: 3rem;
        height: 3rem;
      }

      div {
        padding: 0.3125rem 0;
        p {
          text-overflow: ellipsis;
          -webkit-line-clamp: 1;
          margin: 0 0 0.3125rem;
          display: -webkit-box;
          -webkit-box-orient: vertical;
          overflow: hidden;

          &:first-child {
            font-weight: bold;
          }
          &:last-child {
            font-size: 0.9rem;
          }
        }
      }

      button {
        color: #fff;
        margin: 0 0 0 0.625rem;
        padding: 0.3125rem 0.625rem;
        height: 2.5rem;
        background: #000;
        flex: 20%;
      }
    }
  }
  .profile-friends-remake {
    padding: 0.625rem;
    width: 50%;
    height: auto;
    background: #fff;
    border: 0.1875rem solid #000;
    border-radius: 0.625rem;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    input {
      padding: 0.3125rem 0 0.3125rem 0.3125rem;
      border: #000 0.1rem solid;
      border-radius: 0.3125rem;
    }

    .profile-friends-remake-button {
      margin: 0.625rem 0 0 0;
      display: flex;

      button {
        color: #fff;
        background: #000;

        &:first-child {
          color: #000;
          margin: 0 0.625rem 0 0;
          background: #fff;
        }
      }
    }
  }
}

@media screen and (max-width: 760px) {
  .profile-friends {
    .profile-friends-list {
      height: calc(100% - 7rem);
      .profile-friends-list-item {
        button {
          font-size: 0.75rem;
          flex: 100%;
        }
      }
    }
  }
}
</style>