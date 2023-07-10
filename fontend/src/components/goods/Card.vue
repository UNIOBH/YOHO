<!--
 * @Descripttion: 点击事件ing
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-08 16:51:03
 * @LastEditTime: 2022-11-08 18:58:56
-->
<template>
  <div class="card" @click="getGoods">
    <div class="card-img">
      <ul class="card-time">
        <li class="card-time-year">{{ getYear }}</li>
        <li class="card-time-month">
          <b>{{ getMonth }}</b
          >月
        </li>
        <li class="card-time-day">
          <b>{{ getDay }}</b
          >日
        </li>
      </ul>
      <img :src="getImg" alt="商品图片" />
      <span class="card-publisher">{{ publisher }}</span>
    </div>
    <p class="card-describe">{{ describe }}</p>
    <hr />
    <div class="card-msg">
      <span class="card-price">￥{{ price }}</span>
      <span class="card-hits">
        <svg
          t="1666428683633"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="11987"
          width="16"
          height="16"
        >
          <path
            d="M512.004096 170.667008c-307.160064 0-455.114752 298.305536-455.114752 312.88832 0 14.599168 144.003072 312.889344 455.114752 312.889344 311.105536 0 455.10656-298.2912 455.10656-312.889344 0-14.582784-147.954688-312.88832-455.10656-312.88832z m0 483.555328c-94.2592 0-170.67008-76.41088-170.67008-170.669056 0-94.2592 76.41088-170.663936 170.67008-170.663936 94.251008 0 170.662912 76.40576 170.662912 170.663936 0 94.257152-76.411904 170.669056-170.662912 170.669056z m0-256c-47.136768 0-85.337088 38.20544-85.337088 85.32992 0 47.13472 38.20032 85.33504 85.337088 85.33504 47.123456 0 85.32992-38.20032 85.32992-85.33504 0-47.12448-38.206464-85.32992-85.32992-85.32992z"
            fill="#323233"
            p-id="11988"
          ></path>
        </svg>
        {{ hits }}
      </span>
    </div>
    <div class="card-title">{{ title }}</div>
  </div>
</template>

<script>
// 网络请求
import { getGoodsById } from "api/goods.js";

export default {
  name: "Card",
  data() {
    return {};
  },
  props: {
    cId: {
      // 商品id
      type: Number,
      required: true,
    },
    // 显示的图片
    img: {
      type: String,
    },
    // 文字描述
    describe: {
      type: String,
      required: true,
    },
    // 发布者
    publisher: {
      type: String,
      require: true,
    },
    // 标题
    title: {
      type: String,
      require: true,
    },
    // 时间
    time: {
      type: String,
      require: true,
    },
    // 价格
    price: {
      type: Number,
      require: true,
    },
    // 点击量
    hits: {
      type: Number,
      require: true,
    },
  },
  computed: {
    getImg() {
      if (!this.img) return require("assets/imgs/暂无图片.png");
      return this.img;
    },
    getYear() {
      return this.time.substr(0, 4);
    },
    getMonth() {
      return this.time.substr(5, 2);
    },
    getDay() {
      return this.time.substr(8, 2);
    },
  },
  methods: {
    getGoods() {
      getGoodsById(this.cId).then((res) => {
        if (res.status == 200) {
          const id = res.data.id;
          this.$router.push(`/GoodsDetail/${id}`);
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.card {
  padding: 20px;
  width: 300px;
  height: 400px;
  border: 3px solid #000;
  border-radius: 15px;
  transition: all 0.3s;

  &:hover {
    transform: scale(1.01);
  }

  .card-img {
    height: 50%;
    position: relative;

    ul.card-time {
      padding: 3px;
      width: 55px;
      height: 100px;
      background: #fff;
      border: 3px solid #000;
      border-radius: 10px;
      position: absolute;
      top: 20px;
      transform: translateX(-80%);

      li {
        text-align: center;
        height: auto;
        display: inline-block;
        &.card-time-year {
          font-size: 8px;

          &::after {
            content: "";
            margin: 7px auto;
            width: 90%;
            height: 2px;
            display: block;
            background: #000;
          }
        }

        &.card-time-month,
        &.card-time-day {
          font-size: 12px;

          b {
            font-size: 16px;
            margin: 0 2px 0 0;
          }
        }
        &.card-time-day {
          margin: 5px 0 0 0;
        }
      }
    }

    img {
      border-radius: 0.5rem;
    }

    span.card-publisher {
      color: rgb(236, 236, 236);
      text-align: center;
      font-size: 10px;
      letter-spacing: 2px; // 字间距
      padding: 3px 5px;
      width: auto;
      height: auto;
      background: rgba(0, 0, 0, 0.5);
      border-radius: 3px;
      position: absolute;
      left: 0;
      bottom: -2px;
    }
  }

  p.card-describe {
    font-size: 18px;
    font-weight: 600;
    text-overflow: ellipsis; // 超出的部分用省略号代替
    letter-spacing: 2px;
    -webkit-line-clamp: 3;
    word-break: break-all; // 文字换行
    line-height: 30px;
    margin: 20px 0;
    height: auto;
    display: -webkit-box; // 弹性伸缩盒
    -webkit-box-orient: vertical; //从上到下垂直排列子元素
    overflow: hidden;
  }

  hr {
    height: 3px;
    background: #000;
  }

  .card-msg {
    font-size: 14px;
    margin: 5px 0;
    height: auto;
    display: flex;

    .card-hits {
      text-align: right;

      svg {
        width: 15px;
        vertical-align: middle;
      }
    }
  }

  .card-title {
    text-overflow: ellipsis;
    font-size: 16px;
    font-weight: 600;
    letter-spacing: 1px;
    -webkit-line-clamp: 1;
    word-break: normal;
    height: auto;
    display: -webkit-box; // 弹性伸缩盒
    -webkit-box-orient: vertical; //从上到下垂直排列子元素
    overflow: hidden;
  }
}
</style>