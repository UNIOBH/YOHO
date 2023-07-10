<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-25 14:35:19
 * @LastEditTime: 2022-11-10 19:07:25
-->
<template>
  <div class="invitation" @click="toInvitationDetail(invitation.id)">
    <h2>{{ invitation.title }}</h2>
    <p v-html="invitation.details"></p>
    <ul>
      <!-- 用户 start -->
      <li class="invitation-user">
        <img :src="invitation.user.avatar" alt="用户头像" />
        <span>{{ invitation.user.nickname }}</span>
      </li>
      <!-- 用户 end -->
      <!-- 喜欢数量 start -->
      <li class="invitation-like">
        <svg
          t="1666683531437"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="2511"
          width="32"
          height="32"
        >
          <path
            d="M376.892416 823.40352L129.993728 562.477056c-99.436544-105.086976-97.15712-270.232576 5.144576-372.533248 101.490688-101.489664 266.038272-101.489664 367.52896 0l9.984 9.984 9.996288-9.99424c101.53984-101.540864 266.16832-101.540864 367.70816 0 102.395904 102.393856 104.802304 267.651072 5.433344 372.984832l-341.21728 361.694208a56.88832 56.88832 0 0 1-2.463744 2.4576c-22.91712 21.492736-58.918912 20.33664-80.411648-2.58048l-94.803968-101.086208z"
            fill="#323233"
            p-id="2512"
          ></path>
        </svg>
        <span>{{ invitation.likeCount }}</span>
      </li>
      <!-- 喜欢数量 end -->
      <!-- 评论数量 start -->
      <li class="invitation-comment">
        <svg
          t="1666683665826"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="5818"
          width="32"
          height="32"
        >
          <path
            d="M625.777664 739.554304l-66.443264 99.66592a56.889344 56.889344 0 0 1-15.777792 15.777792c-26.14272 17.42848-61.462528 10.36288-78.891008-15.778816l-66.443264-99.664896H113.77664c-31.418368 0-56.88832-25.469952-56.88832-56.88832v-512c0-31.419392 25.469952-56.889344 56.88832-56.889344H910.22336c31.418368 0 56.88832 25.469952 56.88832 56.889344v512c0 31.418368-25.469952 56.88832-56.88832 56.88832H625.77664zM312.889344 284.443648c-15.710208 0-28.444672 12.734464-28.444672 28.443648 0 15.710208 12.734464 28.444672 28.444672 28.444672h398.221312c15.710208 0 28.444672-12.734464 28.444672-28.444672 0-15.70816-12.734464-28.443648-28.444672-28.443648H312.889344z m0 170.665984c-15.710208 0-28.444672 12.735488-28.444672 28.444672S297.179136 512 312.889344 512h398.221312c15.710208 0 28.444672-12.735488 28.444672-28.444672s-12.734464-28.444672-28.444672-28.444672H312.889344z"
            fill="#000000"
            p-id="5819"
          ></path>
        </svg>
        <span>{{ invitation.totalComment }}</span>
      </li>
      <!-- 评论数量 end -->
      <!-- 发布时间 start -->
      <li class="invitation-time">
        <svg
          t="1666684135140"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="7920"
          width="16"
          height="16"
        >
          <path
            d="M511.999488 103.434767c-225.650273 0-408.565233 182.91496-408.565233 408.565233s182.91496 408.565233 408.565233 408.565233 408.565233-182.91496 408.565233-408.565233S737.649761 103.434767 511.999488 103.434767zM675.540192 676.960029c-10.993378 11.126408-25.447579 16.624632-39.85573 16.624632-14.45113 0-28.857235-5.498224-39.85266-16.624632L483.139184 563.513234c-10.197246-10.196223-16.492626-24.382317-16.535605-39.986713L466.603579 296.367895c0-31.388894 25.224498-56.744375 56.346309-56.744375 31.166837 0 56.389288 25.355481 56.389288 56.744375l0 203.485461 96.202039 96.865141C697.528995 618.839307 697.528995 654.837172 675.540192 676.960029z"
            p-id="7921"
            fill="#000000"
          ></path>
        </svg>
        <span>{{ getTime(invitation.createTime) }}</span>
      </li>
      <!-- 发布时间 end -->
    </ul>
  </div>
</template>

<script>
// utils
import { intercept } from "utils/time";

export default {
  props: {
    invitation: {
      type: Object,
      required: true,
    },
  },
  methods: {
    getTime(time) {
      return intercept(time);
    },
    toInvitationDetail(id) {
      this.$router.push(`/InvitationDetail/${id}`);
    },
  },
};
</script>

<style lang="scss">
.invitation {
  word-break: break-all;
  padding: 1.25rem 1.875rem;
  border: 0.25rem solid #000;

  h2,
  p {
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }

  h2 {
    -webkit-line-clamp: 2;
    letter-spacing: 2px;
  }

  p {
    -webkit-line-clamp: 3;
    margin: 0.625rem 0 0 0 ;

    img {
      display: none;
    }
  }

  ul {
    margin: 1.3rem 0 0 0;
    padding: 0.625rem 0 0 0;
    display: flex;
    border-top: 2px solid #000;

    li {
      display: flex;
      align-items: center;

      img,
      svg {
        margin: 0 0.3125rem 0 0;
        width: 1.25rem;
      }
      img {
        border-radius: 50%;
      }
    }
  }
}
</style>