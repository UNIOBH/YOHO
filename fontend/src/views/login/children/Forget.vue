<!--
 * @Descripttion: 忘记密码页面
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-11-01 19:45:03
 * @LastEditTime: 2022-11-16 17:05:16
-->
<template>
  <div class="forget-password">
    <main>
      <div class="forget-password-code" v-if="codeIsShow">
        <label for="forget-password-email">请填写邮箱地址：</label>
        <input type="text" id="forget-password-email" v-model="email" />
        <input type="text" placeholder="请填写收到的验证码……" v-model="code" />
        <button @click="sendEmail">发送验证码至邮箱</button>
        <button @click="next">下一步</button>
      </div>
      <div class="forget-password-reset" v-if="resetIsShow">
        <label for="forget-password-reset-new">请输入新密码：</label>
        <input
          type="password"
          id="forget-password-reset-new"
          v-model="newPassword"
        />
        <label for="forget-password-reset-new-confirm"
          >请再次确认新密码：</label
        >
        <input
          type="password"
          id="forget-password-reset-new-confirm"
          v-model="confirmPassword"
        />
        <button @click="alterPassword">修改密码</button>
      </div>
      <div class="forget-password-msg" v-if="msgIsShow.my">
        <div class="forget-password-msg-success" v-if="msgIsShow.succ">
          <h3>修改成功！</h3>
          <p>
            还有<span>{{ count }}</span
            >秒跳转至登录页面
          </p>
          <router-link to="/login">现在跳转</router-link>
        </div>
        <div class="forget-password-msg-fail" v-if="msgIsShow.err">
          <h3>修改失败！</h3>
          <div class="forget-password-msg-fail-button">
            <button @click="anewAlter">重新修改</button>
            <router-link tag="button" to="/login">去登录</router-link>
            <router-link tag="button" to="/register">去注册</router-link>
          </div>
        </div>
      </div>
      <div class="forget-password-tip" v-if="tipIsShow">
        <p>{{ tipMsg }}</p>
        <button @click="tipIsShow = false">确定</button>
      </div>
    </main>
  </div>
</template>

<script>
// 网络请求
import { checkEmail, sendForgetPassword, updatePassword } from "api/user.js";

// utils
import { verifyPassword } from "utils/regular";

let timer;

export default {
  data() {
    return {
      tipMsg: "",
      tipIsShow: false,
      codeIsShow: true,
      resetIsShow: false,
      msgIsShow: {
        my: false,
        err: false,
        succ: false,
      },
      email: "",
      newPassword: "",
      confirmPassword: "",
      code: "",
      correctCode: "",
      count: 5,
    };
  },
  methods: {
    sendEmail() {
      if (!this.email) {
        this.showTip("请填写邮箱！");
        return;
      }
      checkEmail(this.email).then((res) => {
        if (res.status == 202) {
          sendForgetPassword([this.email]).then((r) => {
            if (r.status == 200) this.correctCode = r.data.code;
          });
        } else this.showTip("该邮箱不存在！");
      });
    },
    showTip(msg) {
      this.tipIsShow = true;
      this.tipMsg = msg;
    },
    next() {
      if (!this.email) {
        this.showTip("请填写邮箱！");
        return;
      }
      if (!this.code) {
        this.showTip("请填写验证码！");
        return;
      }
      if (this.code != this.correctCode) {
        this.showTip("验证码不正确！");
        return;
      }
      this.codeIsShow = false;
      this.resetIsShow = true;
    },
    alterPassword() {
      if (!this.newPassword) {
        this.showTip("请填写新密码！");
        return;
      }
      if (!this.confirmPassword) {
        this.showTip("请填写确认新密码！");
        return;
      }
      if (!verifyPassword(this.newPassword)) {
        this.showTip(
          "密码必须由4-16位大写字母、小写字母、数字或下划线或-组成！"
        );
        return;
      }
      if (this.newPassword != this.confirmPassword) {
        this.showTip("两次密码不一致！");
        return;
      }
      updatePassword(this.email, this.newPassword).then((res) => {
        if (res.status == 200) {
          this.resetIsShow = false;
          this.msgIsShow.my = true;
          this.msgIsShow.succ = true;
          timer = setInterval(() => {
            this.count--;
            if (this.count <= 0) {
              clearInterval(timer);
              this.$router.push("/login");
            }
          }, 1000);
        } else {
          this.msgIsShow.my = true;
          this.msgIsShow.err = true;
        }
      });
    },
    anewAlter() {
      this.codeIsShow = true;
      this.resetIsShow = false;
      this.msgIsShow.my = false;
      this.msgIsShow.err = false;
      this.msgIsShow.succ = false;
      this.email = "";
      this.newPassword = "";
      this.confirmPassword = "";
      this.code = "";
      this.correctCode = "";
      this.count = 5;
    },
  },
  destroyed() {
    clearInterval(timer);
  },
};
</script>

<style lang="scss" scoped>
.forget-password {
  border: 0.3125rem solid #000;

  main {
    margin: 0 auto;
    padding: 6.25rem 0 0 0;
    width: 50%;
    height: auto;
    position: relative;

    .forget-password-code {
      height: auto;
    }

    .forget-password-reset {
      height: auto;
    }

    .forget-password-msg {
      margin: 12.5rem auto;
      padding: 1.25rem;
      width: 90%;
      height: auto;
      border: #000 solid 0.1875rem;
      border-radius: 0.625rem;

      p {
        margin: 0.625rem 0;
      }

      a {
        font: {
          size: 0.875rem;
        }
      }

      .forget-password-msg-fail {
        .forget-password-msg-fail-button {
          margin: 0.625rem 0 0 0;
          display: flex;

          button {
            margin: 0 0.3125rem 0 0;

            &:last-child {
              margin: 0;
            }
          }
        }
      }
    }

    input,
    button {
      font: {
        size: 1rem;
        weight: bold;
      }
      margin: 0.625rem 0;
      padding: 0.3125rem 0;
      border: #000 solid 0.1875rem;
    }

    input {
      padding: 0.3125rem 0 0.3125rem 0.3125rem;
    }

    button {
      background: #fff;
    }

    .forget-password-tip {
      padding: 0.625rem 1.25rem;
      width: auto;
      height: auto;
      background: #fff;
      border: 0.3125rem solid #000;
      border-radius: 0.625rem;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);

      p {
        font: {
          weight: bold;
        }
      }

      button {
        color: #fff;
        height: auto;
        background: #000;
      }
    }
  }
}

@media screen and (max-width: 760px) {
  .forget-password {
    main {
      width: 80%;
    }
  }
}
</style>