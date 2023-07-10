<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-24 20:19:44
 * @LastEditTime: 2022-11-16 17:30:49
-->
<template>
  <div class="profile-forget-passwrod">
    <main>
      <div class="profile-forget-passwrod-old">
        <label for="profile-forget-passwrod-old">请输入旧密码:</label>
        <input
          type="password"
          id="profile-forget-passwrod-old"
          v-model="oldPassword"
        />
      </div>
      <div class="profile-forget-passwrod-new">
        <label for="profile-forget-passwrod-new">请输入新密码：</label>
        <input
          type="password"
          id="profile-forget-passwrod-new"
          v-model="newPassword"
        />
      </div>
      <div class="profile-forget-passwrod-new-confirm">
        <label for="profile-forget-passwrod-new-confirm"
          >请再次输入新密码：</label
        >
        <input
          type="password"
          id="profile-forget-passwrod-new-confirm"
          v-model="confirmPassword"
        />
      </div>
      <input type="submit" value="确认修改" @click="updatePassword" />
    </main>
    <div class="profile-forget-passwrod-tip" v-if="tipIsShow">
      {{ tipMsg }}
      <button @click="tipIsShow = false">确定</button>
    </div>
  </div>
</template>

<script>
// 网络请求
import { updatePswById } from "api/user";

// utills
import { verifyPassword } from "utils/regular";

export default {
  data() {
    return {
      oldPassword: "",
      newPassword: "",
      confirmPassword: "",
      tipIsShow: false,
      tipMsg: "",
    };
  },
  methods: {
    showTip(msg) {
      this.tipIsShow = true;
      this.tipMsg = msg;
    },
    updatePassword() {
      if (!this.oldPassword) {
        this.showTip("请填写旧密码！");
        return;
      }
      if (!this.newPassword) {
        this.showTip("请填写新密码！");
        return;
      }
      if (!this.confirmPassword) {
        this.showTip("请填写第二次确认密码！");
        return;
      }
      if (!verifyPassword(this.oldPassword)) {
        this.showTip(
          "密码必须由4-16位大写字母、小写字母、数字或下划线或-组成！"
        );
        return;
      }
      if (this.newPassword != this.confirmPassword) {
        this.showTip("两次密码不一致！");
        return;
      }
      updatePswById(
        JSON.parse(sessionStorage.getItem("user")).id,
        this.oldPassword,
        this.newPassword
      ).then((res) => {
        if (res.status == 200) {
          this.showTip("修改成功！");
          this.oldPassword = "";
          this.newPassword = "";
          this.confirmPassword = "";
        } else this.showTip("修改失败");
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.profile-forget-passwrod {
  main {
    margin: 0 auto;
    padding: 6.25rem 0 0 0;
    width: 80%;
    position: relative;

    div {
      height: auto;
      label {
        height: auto;
      }

      button {
        color: #fff;
        padding: 0.3125rem 0 0.3125rem 0.3125rem;
        height: auto;
        border: 0.1875rem solid #000;
        background: #000;
      }

      &.profile-forget-passwrod-code {
        display: flex;
        align-items: center;

        input {
          margin: 0 0.625rem 0 0;
        }
      }
    }

    input {
      font: {
        size: 1rem;
        weight: bold;
      }
      margin: 0.6rem 0 1.25rem 0;
      height: auto;
      padding: 0.3125rem 0 0.3125rem 0.3125rem;
      border: 0.1875rem solid #000;

      &[type="submit"] {
        color: #fff;
        margin: 1.25rem 0 0 0;
        background: #000;
      }
    }
  }
  .profile-forget-passwrod-tip {
    padding: 1.25rem;
    width: auto;
    height: auto;
    background: #fff;
    border: 0.3125rem solid #000;
    border-radius: 0.625rem;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    button {
      color: #fff;
      margin: 0.625rem 0 0 0;
      padding: 0.3125rem 0;
      background: #000;
    }
  }
}
</style>