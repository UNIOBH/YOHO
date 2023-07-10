<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-29 17:05:03
 * @LastEditTime: 2022-11-11 15:20:33
-->
<template>
  <div class="admin-register">
    <main>
      <h3>管理员注册</h3>
      <div class="admin-register-username">
        <label for="admin-register-username">用户名：</label>
        <input
          type="text"
          id="admin-register-username"
          placeholder="请输入用户名……"
          v-model="username"
        />
      </div>
      <div class="admin-register-password">
        <label for="admin-register-password">密码：</label>
        <input
          type="password"
          id="admin-register-password"
          placeholder="请输入密码……"
          v-model="password"
        />
      </div>
      <div class="admin-regiter-confirm-password">
        <label for="admin-regiter-confirm-password">确认密码：</label>
        <input
          type="password"
          id="admin-regiter-confirm-password"
          placeholder="请再次输入密码……"
          v-model="confirmPsw"
        />
      </div>
      <input type="submit" value="注 册" @click="toRegister" />
    </main>
  </div>
</template>

<script>
// 网络请求
import { adminRegister } from "api/user";

export default {
  data() {
    return {
      username: "",
      password: "",
      confirmPsw: "",
    };
  },
  methods: {
    toRegister() {
      if (!this.username) return;
      if (!this.password) return;
      if (!this.confirmPsw) return;
      if (this.password != this.confirmPsw) {
        this.confirmPsw = "";
        return;
      }
      adminRegister(this.username, this.password).then((res) => {
        if (res.status == 200) {
          this.username = "";
          this.password = "";
          this.confirmPsw = "";
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.admin-register {
  main {
    margin: 150px auto;
    padding: 30px;
    width: 800px;
    height: auto;
    border: 5px solid #000;
    border-radius: 15px;

    h3 {
      text-align: center;
      margin: 0 0 10px 0;
    }

    div {
      input {
        font-size: 16px;
        font-weight: bold;
        margin: 10px 0 20px;
        padding: 5px 0 5px 5px;
        border: 3px solid #000;
        border-radius: 10px;
      }
    }

    input[type="submit"] {
      color: #fff;
      font-size: 20px;
      margin: 0 auto;
      padding: 5px 20px;
      width: auto;
      height: auto;
      background: #000;
      border-radius: 15px;
      display: block;
    }
  }
}
</style>