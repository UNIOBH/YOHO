<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-09 18:58:40
 * @LastEditTime: 2022-11-03 14:24:53
-->
<template>
  <div class="login">
    <div class="login-header">
      <img src="~assets/imgs/logo.png" alt="" />
      <h1>Welcome to join YoHo!!!</h1>
    </div>
    <div class="login-main">
      <div class="login-main-left">
        <img src="~assets/imgs/login.webp" alt="" />
      </div>
      <div class="login-main-right">
        <div class="username">
          <label for="login-username">
            <svg
              t="1665318503018"
              class="icon"
              viewBox="0 0 1024 1024"
              version="1.1"
              xmlns="http://www.w3.org/2000/svg"
              p-id="2969"
              width="16"
              height="16"
            >
              <path
                d="M409.6 128c113.152 0 204.8 93.056 204.8 208S522.752 544 409.6 544c-113.152 0-204.8-93.056-204.8-208S296.448 128 409.6 128z m0 98.816c-59.392 0-107.52 48.896-107.52 109.184s48.128 109.184 107.52 109.184c59.392 0 107.52-48.896 107.52-109.184S468.992 226.816 409.6 226.816z m0 376.64c136.704 0 409.6 68.224 409.6 203.712V960H0v-152.832C0 671.68 272.896 603.52 409.6 603.52z m0 91.328c-152.064 0-312.32 75.968-312.32 109.248v57.152h624.64v-57.152c0-33.28-160.256-109.248-312.32-109.248zM887.488 960v-168c0-115.456-133.12-197.248-276.288-214.464 24.832-3.008 74.368 0 94.592 0 136.704 0 318.208 78.976 318.208 214.464V960h-136.512zM576 501.12A191.872 191.872 0 0 0 702.08 320 191.872 191.872 0 0 0 576 138.88c19.712-7.04 40.96-10.88 63.04-10.88 104.448 0 188.992 85.888 188.992 192 0 106.048-84.544 192-188.992 192A186.24 186.24 0 0 1 576 501.12z"
                fill="#2c2c2c"
                p-id="2970"
              ></path>
            </svg>
          </label>
          <input
            type="text"
            id="login-username"
            placeholder="用户名"
            v-model="user.username"
          />
        </div>
        <div class="password">
          <label for="login-password">
            <svg
              t="1665318635786"
              class="icon"
              viewBox="0 0 1024 1024"
              version="1.1"
              xmlns="http://www.w3.org/2000/svg"
              p-id="4047"
              width="16"
              height="16"
            >
              <path
                d="M512 64c153.173333 0 277.333333 124.16 277.333333 277.333333v128a128 128 0 0 1 128 128v234.666667a128 128 0 0 1-128 128H234.666667a128 128 0 0 1-128-128V597.333333a128 128 0 0 1 128-128v-128c0-153.173333 124.16-277.333333 277.333333-277.333333z m277.333333 469.333333H234.666667a64 64 0 0 0-63.893334 60.245334L170.666667 597.333333v234.666667a64 64 0 0 0 60.245333 63.893333L234.666667 896h554.666666a64 64 0 0 0 63.893334-60.245333L853.333333 832V597.333333a64 64 0 0 0-60.245333-63.893333L789.333333 533.333333z m-243.2 106.666667c4.693333 0 8.533333 3.84 8.533334 8.533333v110.933334a8.533333 8.533333 0 0 1-8.533334 8.533333h-46.933333a8.533333 8.533333 0 0 1-8.533333-8.533333v-110.933334c0-4.693333 3.84-8.533333 8.533333-8.533333h46.933333zM512 128c-115.84 0-210.090667 92.309333-213.248 207.36L298.666667 341.333333v128h426.666666v-128c0-115.84-92.309333-210.090667-207.36-213.248L512 128z"
                fill="#333333"
                p-id="4048"
              ></path>
            </svg>
          </label>
          <input
            type="password"
            id="login-password"
            placeholder="密码"
            v-model="user.password"
          />
        </div>
        <div class="login-code">
          <div class="code">
            <label for="login-code-input">
              <svg
                t="1665384502949"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="5596"
                width="200"
                height="200"
              >
                <path
                  d="M511.296 671.808a48.256 48.256 0 0 1-24.32-7.104l-383.04-261.12a45.248 45.248 0 0 1-18.56-37.12c0-15.744 7.104-27.136 19.968-35.712l383.104-238.272a43.968 43.968 0 0 1 45.696 0l383.104 239.744a40.64 40.64 0 0 1 19.968 35.648 42.624 42.624 0 0 1-18.56 35.648l-383.04 261.12a48.256 48.256 0 0 1-24.32 7.168zM206.848 369.28l305.92 208.384L818.56 369.28 511.296 178.112 206.848 369.28z"
                  fill="#2c2c2c"
                  p-id="5597"
                ></path>
                <path
                  d="M511.296 938.688a48.256 48.256 0 0 1-24.32-7.168l-383.04-262.528a42.624 42.624 0 0 1 1.408-71.36l383.104-239.744a43.968 43.968 0 0 1 45.696 0l383.104 239.744a40.704 40.704 0 0 1 19.968 35.648 42.624 42.624 0 0 1-18.56 35.712l-383.04 261.12a38.848 38.848 0 0 1-24.32 8.576zM206.848 636.16l305.92 208.32L818.56 636.16 511.296 444.928 206.848 636.16z"
                  fill="#2c2c2c"
                  p-id="5598"
                ></path>
                <path
                  d="M938.688 367.872h-85.76v266.88h85.76v-266.88zM171.072 367.872h-85.76v266.88h85.76v-266.88zM554.176 140.992h-85.76v266.88h85.76v-266.88zM554.176 623.36h-85.76v266.816h85.76v-266.88z"
                  fill="#2c2c2c"
                  p-id="5599"
                ></path>
              </svg>
            </label>
            <input
              type="text"
              id="login-code-input"
              placeholder="验证码"
              v-model="verificationCode.str"
            />
          </div>
          <div class="login-code-right">
            <div class="login-code-img">
              <!-- 验证码 start -->
              <verification-code
                :w="verificationCode.w"
                :h="verificationCode.h"
                ref="code"
              />
              <!-- 验证码 end -->
            </div>
            <svg
              t="1665384824357"
              class="icon"
              viewBox="0 0 1024 1024"
              version="1.1"
              xmlns="http://www.w3.org/2000/svg"
              p-id="6689"
              width="200"
              height="200"
              @click="changeCode"
            >
              <path
                d="M768 576c0 141.12-114.752 256-256 256s-256-114.88-256-256 114.752-256 256-256l75.488 0.864-37.248 37.248 90.496 90.496L833.12 256l-192.384-192-90.496 90.496L588 192 512 192C300 192 128 363.872 128 576s172 384 384 384 384-171.872 384-384L768 576z"
                p-id="6690"
                fill="#2c2c2c"
              ></path>
            </svg>
          </div>
        </div>
        <div class="login-submit">
          <input type="submit" value="提 交" @click="toLogin" />
          <div class="a">
            <router-link to="/forget">忘记密码?</router-link>
            <router-link to="/register">注册</router-link>
          </div>
        </div>
      </div>
    </div>
    <div class="login-tip" v-show="tipIsShow">
      {{ tipMessage }} <button @click="tipIsShow = false">确定</button>
    </div>
  </div>
</template>

<script>
// 子组件
import VerificationCode from "components/common/VerificationCode";

// 网络请求
import { login } from "api/user.js";
export default {
  name: "Login",
  components: {
    VerificationCode,
  },
  data() {
    return {
      user: {
        username: null,
        password: null,
      },
      verificationCode: {
        str: null,
        w: 189,
        h: 46,
      },
      tipMessage: "", // 提示信息
      tipIsShow: false,
    };
  },
  methods: {
    /**
     * 登录
     */
    toLogin() {
      if (!this.user.username) {
        this.showTip("请填写用户名!");
        return;
      } else if (!this.user.password) {
        this.showTip("请填写密码!");
        return;
      } else if (!this.verificationCode.str) {
        this.showTip("请填写验证码!");
        return;
      }
      if (this.verificationCode.str != this.$refs.code.code) {
        this.showTip("验证码错误!");
        this.verificationCode.str = ""; // 清空验证码
        this.changeCode(); // 刷新验证码
      } else {
        login(this.user).then((res) => {
          if (res.status == 200) {
            // 登录成功
            sessionStorage.setItem("user", JSON.stringify(res.data)); // 将后端返回的token存入sessionStorage中
            // 判断用户类型
            if (res.data.type == "user") this.$router.push("/recommend");
            // 跳转界面
            else this.$router.push("/admin");
          } else {
            // 登录失败
            this.showTip(res.message);
            this.changeCode();
            this.user.username = "";
            this.user.password = "";
            this.verificationCode.str = "";
          }
        });
      }
    },
    /**
     * 改变验证码
     */
    changeCode() {
      this.$refs.code.getCode();
      this.$refs.code.drawCode();
    },
    /**
     * 显示提示信息
     * @param {String} tipMsg 提示信息
     */
    showTip(tipMsg) {
      this.tipMessage = tipMsg;
      this.tipIsShow = true;
    },
  },
};
</script>

<style lang="scss">
.login {
  text-align: center;
  padding: 20px 30px;
  width: 60%;
  height: auto; // 让子元素决定父元素的高度
  border: 4px solid rgb(0, 0, 0);
  border-radius: 2.625rem;
  box-shadow: 7px 6px 7px rgba(0, 0, 0, 0.19);
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  .login-header {
    h1 {
      text-align: center;
      font-size: 3rem;
    }
  }

  .login-main-right {
    .username,
    .password,
    .code {
      position: relative;

      label {
        width: 1.875rem;
        position: absolute;
        top: 50%;
        left: 0.625rem;
        transform: translateY(-50%);
      }
    }

    input {
      font-size: 0.9375rem;
      font-weight: bold;
      margin: 1.25rem 0;
      padding-left: 3.375rem;
      width: 100%;
      height: 2.5rem;
      border: 2px solid rgb(0, 0, 0);
      border-radius: 0.625rem;

      // 修改input里placeholder属性的样式
      &::-webkit-input-placeholder {
        color: #000;
      }
    }

    .login-code {
      .login-code-right {
        display: flex;
        align-items: center;
        svg {
          width: 2rem;

          &:hover {
            cursor: pointer;
          }
        }
      }
    }

    .login-submit {
      input {
        padding: 0;
        background: #fff;
      }

      a {
        font-size: 0.7rem;
        font-weight: bold;
      }

      .a {
        display: flex;
      }
    }
  }

  .login-tip {
    font: {
      size: 1.125rem;
    }
    padding: 1.25rem;
    width: auto;
    height: auto;
    background: #fff;
    border: 0.1875rem #000 solid;
    border-radius: 0.9375rem;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    button {
      color: #fff;
      margin: 0.625rem 0 0 0;
      background: #000;
      border-radius: 0.9375rem;
    }
  }
}

@media screen and (min-width: 760px) {
  @import "assets/scss/login/pc";
}
@media screen and (max-width: 760px) {
  @import "assets/scss/login/mobile";
}
</style>