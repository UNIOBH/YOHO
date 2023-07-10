<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-27 16:31:20
 * @LastEditTime: 2022-12-07 15:24:05
-->
<template>
  <div class="chat">
    <main>
      <div class="chat-top">
        <span
          >{{ $route.params.nickname }}
          {{ $route.params.remake == "null" ? "" : $route.params.remake }}</span
        >
        <svg
          t="1666860637322"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="8572"
          width="32"
          height="32"
          @click="close"
        >
          <path
            d="M853.728 755.424q0 22.848-16 38.848l-77.728 77.728q-16 16-38.848 16t-38.848-16l-168-168-168 168q-16 16-38.848 16t-38.848-16l-77.728-77.728q-16-16-16-38.848t16-38.848l168-168-168-168q-16-16-16-38.848t16-38.848l77.728-77.728q16-16 38.848-16t38.848 16l168 168 168-168q16-16 38.848-16t38.848 16l77.728 77.728q16 16 16 38.848t-16 38.848l-168 168 168 168q16 16 16 38.848z"
            p-id="8573"
            fill="#ffffff"
          ></path>
        </svg>
      </div>
      <div class="chat-show">
        <div
          v-for="item in records"
          :key="item.id"
          :class="getClass(item.from.id)"
        >
          <img
            v-if="item.from.id != uId"
            :src="item.from.avatar"
            alt=""
            class="chat-show-avatar"
          />
          <span v-if="item.type == 'TEXT'">{{ item.content }}</span>
          <img v-else :src="item.content" alt="" class="chat-main-img" />
          <img
            v-if="item.from.id == uId"
            :src="item.from.avatar"
            alt=""
            class="chat-show-avatar"
          />
        </div>
      </div>
      <div class="chat-input">
        <textarea
          v-model="content"
          autofocus
          placeholder="请输入内容……"
        ></textarea>
        <div class="chat-input-tool">
          <button class="chat-input-tool-send" @click="sendMsg">发 送</button>
          <button class="chat-input-tool-clean" @click="content = ''">
            清 空
          </button>
        </div>
      </div>
    </main>
    <img src="~assets/imgs/chat.png" alt="" class="chat-img" />
  </div>
</template>

<script>
// 网络请求
import { getChatRecord } from "api/chat";

export default {
  data() {
    return {
      uId: null,
      records: [],
      content: "",
      ws: null,
      // avatar: "",
    };
  },
  created() {
    if (typeof WebSocket == "undefined") this.close();
    this.uId = JSON.parse(sessionStorage.getItem("user")).id;
    // this.avatar = JSON.parse(sessionStorage.getItem("user")).avatar;
    getChatRecord(this.uId, this.$route.params.toId).then((res) => {
      if (res.status == 200) this.records = res.data;
    });
    this.ws = new WebSocket(`ws://localhost:8081/chat/${this.uId}`);
    // 后台向前台发送信息时的回调
    this.ws.onmessage = (me) => {
      const data = JSON.parse(me.data);
      this.records.push(data);
      if (data.type == 0) this.$router.go(0);
    };
    this.ws.onclose = () => {
      this.ws.close();
    };
  },
  methods: {
    getClass(id) {
      return id == this.uId ? "chat-main-right" : "chat-main-left";
    },
    close() {
      this.$router.push("/profile/friends");
    },
    sendMsg() {
      if (!this.content) return;
      this.ws.send(
        JSON.stringify({
          toId: this.$route.params.toId,
          content: this.content,
          type: 1,
        })
      );
      this.$router.go(0);
    },
    // sendImg(e) {},
  },
  destroyed() {
    if (this.ws) this.ws.close();
  },
};
</script>

<style lang="scss" scoped>
.chat {
  padding: 1.875rem;
  height: 100%;
  border: 0.3125rem solid #000;
  display: flex;

  main {
    height: 100%;
    border: 0.3125rem solid #000;
    border-top: none;
    flex: auto;
    .chat-top {
      color: #fff;
      text-align: center;
      padding: 0.3125rem;
      height: auto;
      background: #000;
      display: flex;
      align-items: center;

      svg {
        width: 1.875rem;
        height: 1.875rem;
      }
    }
    .chat-show {
      padding: 0.625rem 0.3125rem 0;
      width: 100%;
      height: 65%;
      border-bottom: #000 solid 0.3125rem;
      overflow-y: scroll;

      &::-webkit-scrollbar {
        width: 0;
      }

      div {
        margin: 0 0 0.6rem 0;
        height: auto;
        img {
          &.chat-show-avatar {
            width: 2.5rem;
            height: 2.5rem;
            border: 0.0625rem solid #000;
            border-radius: 50%;
            vertical-align: top;
          }

          &.chat-main-img {
            padding: 0.3125rem;
            width: 6.25rem;
            height: 6.25rem;
            border: 0.18rem solid #000;
            border-radius: 0.3125rem;
          }
        }
        span {
          text-align: left;
          font-size: 0.9rem;
          word-break: break-all;
          padding: 0.3125rem;
          max-width: 65%;
          width: auto;
          height: auto;
          border: 0.18rem solid #000;
          border-radius: 0.3125rem;
          display: inline-block;
        }

        &.chat-main-left {
          img {
            margin: 0 0.3125rem 0 0;
          }
        }

        &.chat-main-right {
          text-align: right;
          img {
            margin: 0 0 0 0.3125rem;
          }
        }
      }
    }
    .chat-input {
      padding: 0.3125rem;
      height: calc(100% - 65% - 2.51rem);

      textarea {
        font-size: 1rem;
        height: 80%;
        border: none;
        outline: none;
        resize: none;

        &::-webkit-scrollbar {
          width: 0;
        }
      }

      .chat-input-tool {
        height: auto;
        button {
          font-weight: bold;
          margin: 0 0.625rem 0 0;
          width: 5rem;
          height: 2rem;
          border: #000 solid 0.15rem;
          border-radius: 0.3125rem;

          &.chat-input-tool-send {
            color: #fff;
            background: #000;
          }

          &.chat-input-tool-clean {
            color: #000;
            background: #fff;
          }
        }

        label,
        svg {
          width: 1.875rem;
          height: 1.875rem;
          vertical-align: middle;
        }

        input {
          display: none;
        }
      }
    }
  }
  img.chat-img {
    width: 35rem;
    flex: 1;
  }
}

@media screen and (max-width: 760px) {
  .chat {
    img.chat-img {
      display: none;
    }
  }
}
</style>