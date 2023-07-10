<!--
 * @Descripttion: 修改用户信息
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-24 20:22:06
 * @LastEditTime: 2022-11-17 09:34:37
-->
<template>
  <div class="profile-alter">
    <main>
      <div class="profile-alter-avatar">
        <img :src="avatar" alt="" />
        <br />
        <label for="profile-alter-avatar">修改头像</label>
        <input type="file" id="profile-alter-avatar" @change="toUpdateAvatar" />
      </div>
      <div class="profile-alter-nickname">
        <label for="profile-alter-nickname">用户昵称：</label>
        <input
          type="text"
          id="profile-alter-nickname"
          v-model="user.nickname"
        />
      </div>
      <div class="profile-alter-two">
        <div class="profile-alter-username">
          <label for="profile-alter-username">用户名：</label>
          <input
            type="text"
            id="profile-alter-username"
            v-model="user.username"
          />
        </div>
        <div class="profile-alter-sex">
          <span>性别：</span>
          <input
            type="radio"
            id="profile-alter-sex-male"
            v-model="user.sex"
            value="1"
          />
          <label for="profile-alter-sex-male">男</label>
          <input
            type="radio"
            id="profile-alter-sex-female"
            v-model="user.sex"
            value="0"
          />
          <label for="profile-alter-sex-female">女</label>
        </div>
      </div>
      <div class="profile-alter-email">
        <label for="profile-alter-email">邮箱：</label>
        <input type="text" disabled id="profile-alter-email" v-model="email" />
      </div>
      <div class="profile-alter-button">
        <button @click="clear">清空</button>
        <button @click="toUpdate">确认修改</button>
      </div>
    </main>
  </div>
</template>

<script>
// 网络请求
import { updateUser, getUser, updateAvatar } from "api/user";

export default {
  data() {
    return {
      user: {
        id: null,
        nickname: "",
        username: "",
        sex: 0,
      },
      email: "",
      avatar: "",
    };
  },
  created() {
    this.user.id = JSON.parse(sessionStorage.getItem("user")).id;
    getUser(this.user.id).then((res) => {
      if (res.status == 200) {
        this.user.nickname = res.data.nickname;
        this.user.username = res.data.name;
        this.user.sex = "女" == res.data.sex ? 0 : 1;
        this.email = res.data.email;
        this.avatar = res.data.avatar;
      }
    });
  },
  methods: {
    clear() {
      this.user.nickname = "";
      this.user.username = "";
    },
    toUpdate() {
      if (!this.user.nickname) return;
      if (!this.user.username) return;
      updateUser(this.user).then(res => {
        if (res.status == 200) this.$router.go(0);
      });
    },
    toUpdateAvatar(e) {
      const type = e.target.files[0].type;
      if (
        type != "image/webp" &&
        type != "image/jpg" &&
        type != "image/png" &&
        type != "image/jpeg" &&
        type != "image/gif"
      )
        return;
      let avatar = new FormData();
      avatar.append("avatar", e.target.files[0]);
      updateAvatar(avatar, JSON.parse(sessionStorage.getItem("user")).id).then(
        (res) => {
          if (res.status == 200) this.$router.go(0);
        }
      );
    },
  },
};
</script>

<style lang="scss" scoped>
.profile-alter {
  main {
    margin: 0 auto;
    padding: 6.25rem 0 0 0;
    width: 80%;

    div {
      margin: 1.25rem 0 0 0;
      height: auto;
      position: relative;

      label {
        height: auto;
        position: absolute;
        top: 50%;
        left: 0.3125rem;
        transform: translateY(-50%);
      }

      input {
        font: {
          size: 1rem;
          weight: bold;
        }
        padding: 0.3125rem 0 0.3125rem 5rem;
        border: #000 0.1875rem solid;
      }

      &.profile-alter-avatar {
        text-align: center;
        margin: 0 0 3rem 0;
        height: auto;
        img {
          margin: 0 0 0.625rem 0;
          width: 6.25rem;
          height: 6.25rem;
          border: 0.1875rem solid #000;
          border-radius: 50%;
        }

        label {
          color: #fff;
          padding: 0.5rem 1.25rem;
          width: 30%;
          background: #000;
          position: static;
        }

        input {
          display: none;
        }
      }

      &.profile-alter-two,
      &.profile-alter-button {
        display: flex;
        align-items: center;

        div {
          &.profile-alter-sex {
            margin: 0;
            padding: 0.3125rem 0 0.3125rem 0.3125rem;
            border: #000 0.1875rem solid;
            display: flex;
            align-items: center;

            input {
              height: 1rem;
            }

            label {
              position: static;
              transform: translate(0);
            }
          }

          &:first-child {
            margin: 0 0.625rem 0 0;
            input {
              padding: 0.3125rem 0 0.3125rem 3.9rem;
            }
          }
        }

        input {
          padding: 0.3125rem 0 0.3125rem 0.3125rem;
        }

        button {
          font: {
            size: 1rem;
          }
          padding: 0.3125rem 0;
          border: #000 0.1875rem solid;

          &:nth-child(2) {
            margin: 0 0 0 0.625rem;
            background: #fff;
          }

          &:first-of-type {
            color: #fff;
            background: #000;
          }
        }
      }

      &.profile-alter-email {
        input {
          padding: 0.3125rem 0 0.3125rem 3rem;
        }
      }
    }
  }
}

@media screen and (max-width: 760px) {
  .profile-alter {
    main {
      div {
        &.profile-alter-two,
        &.profile-alter-four,
        &.profile-alter-button {
          div {
            &.profile-alter-sex {
              span {
                flex: 195%;
              }
            }
          }
        }
      }
    }
  }
}
</style>