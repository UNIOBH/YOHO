<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-28 15:50:15
 * @LastEditTime: 2022-12-07 13:37:02
-->
<template>
  <div class="goods-detail">
    <YHHeader v-if="!admin" />
    <div class="goods-detail-border">
      <main>
        <div class="goods-detail-top">
          <h2>{{ goods.name }}</h2>
          <span>/{{ cName }}/</span>
        </div>
        <div class="goods-detail-user">
          <img :src="goods.user.avatar" alt="" />
          <span v-cloak>{{ goods.user.nickname }}</span>
          <router-link v-if="uId == goods.user.id" tag="button" :to="to"
            >编辑</router-link
          >
          <button v-if="uId == goods.user.id" @click="delIsShow = true">
            下架
          </button>
          <button v-if="uId == goods.user.id" @click="sellIsShow = true">
            卖出
          </button>
          <button
            v-if="uId != goods.user.id && !isFriend"
            @click="changeFriendIsShow(true)"
          >
            ＋好友
          </button>
        </div>
        <div class="goods-detail-msg">
          <span>￥{{ goods.price }}</span>
          <span>{{ goods.hits }}人浏览</span>
        </div>
        <div class="goods-detail-content">
          <div class="goods-detail-text">
            {{ goods.details }}
          </div>
          <div class="goods-detail-img">
            <img v-for="i in goods.pictures" :key="i.id" :src="i.img" />
          </div>
        </div>
        <div class="goods-detail-time">
          <p>发布于 {{ getTime(goods.releaseTime) }}</p>
          <p>编辑于{{ getTime(goods.lastTime) }}</p>
        </div>
      </main>
    </div>
    <div class="goods-detail-friend" v-show="friendIsShow">
      <textarea placeholder="请填写验证信息……" v-model="msg"></textarea>
      <div class="goods-detail-friend-button">
        <button @click="changeFriendIsShow(false)">取消</button>
        <button @click="toAddFriend">确认</button>
      </div>
    </div>
    <div class="goods-detail-tip" v-show="tipIsShow">
      <p>{{ tipMsg }}</p>
      <button @click="tipIsShow = false">确认</button>
    </div>
    <div class="goods-detail-del" v-show="delIsShow">
      <h2>确定下架吗？</h2>
      <div class="goods-detail-del-button">
        <button @click="delIsShow = false">取消</button>
        <button @click="toDel">确认</button>
      </div>
    </div>
    <div class="goods-detail-sell" v-show="sellIsShow">
      <h4>请输入卖出商品的价格：</h4>
      <input type="text" v-model.number="sellPrice" />
      <div class="goods-detail-sell-button">
        <button @click="sellIsShow = false">取消</button
        ><button @click="toSellGoods">确认</button>
      </div>
    </div>
  </div>
</template>

<script>
// 组件
import YHHeader from "@/components/common/YHHeader.vue";

// 网络请求
import {
  getGoodsById,
  addGoodsHits,
  getCategoryById,
  setState,
  sellGoods,
} from "api/goods.js";
import { addFriend, isFriend } from "api/friend.js";

// utils
import { intercept } from "utils/time.js";

let timer;

export default {
  data() {
    return {
      goods: {},
      uId: null, // 用户id
      msg: "", // 验证信息
      friendIsShow: false,
      tipIsShow: false,
      tipMsg: "",
      cName: "",
      admin: null,
      delIsShow: false,
      sellPrice: "",
      sellIsShow: false,
      isFriend: true,
      to: "",
    };
  },
  components: {
    YHHeader,
  },
  methods: {
    /**
     * 修改friendIsShow
     * @param {Boolean} f
     */
    changeFriendIsShow(f) {
      this.msg = "";
      this.friendIsShow = f;
    },
    /**
     * 添加好友
     */
    toAddFriend() {
      if (!this.msg) {
        this.tipMsg = "请填写验证信息！";
        this.tipIsShow = true;
        return;
      }
      addFriend({
        uId: this.uId,
        fUId: this.goods.user.id,
        msg: this.msg,
      }).then((res) => {
        if (res.status == 200) {
          this.changeFriendIsShow(false);
          this.tipMsg = "发送申请成功！";
          this.tipIsShow = true;
        } else {
          this.msg = "";
          this.tipMsg = "发送申请失败！";
          this.tipIsShow = true;
        }
      });
    },
    /**
     * 根据商品id查询商品详情
     */
    getGoodsById() {
      return getGoodsById(this.$route.params.gId).then((res) => {
        if (res.status == 200) {
          this.goods = res.data;
          this.to = `/ReleaseGoods/${res.data.id}`;
        }
      });
    },
    /**
     * 根据商品id查询商品所在类别名称
     */
    getNameByGId() {
      getCategoryById(this.goods.id).then((res) => {
        if (res.status == 200) this.cName = res.data.name;
      });
    },
    async init() {
      await this.getGoodsById();
      this.getNameByGId();
      this.toIsFriend(this.goods.user.id);
    },
    getTime(str) {
      return intercept(str);
    },
    /**
     * 下架商品
     */
    toDel() {
      setState([this.goods.id], 3).then((res) => {
        if (res.status == 200) this.$router.push("/trade");
      });
    },
    toSellGoods() {
      if (this.sellPrice == "" || this.sellPrice == 0) return;
      sellGoods(this.goods.id, this.sellPrice).then((res) => {
        if (res.status == 200) this.$router.push("/trade");
      });
    },
    toIsFriend(fId) {
      isFriend(this.uId, fId).then((res) => {
        this.isFriend = res.data;
        console.log(res);
      });
    },
  },
  created() {
    const user = JSON.parse(sessionStorage.getItem("user"));
    if (user && user.type == "user") this.uId = user.id;
    else if (user) this.admin = user;
    console.log(user);
    console.log(this.user, this.admin);
    this.init();
  },
  mounted() {
    let t = 0;
    timer = setInterval(() => {
      if (t >= 10) {
        clearInterval(timer);
        addGoodsHits(this.goods.id).then((res) => {
          console.log("增加成功");
        });
      }
      t++;
    }, 1000);
  },
  destroyed() {
    clearInterval(timer);
  },
};
</script>

<style lang="scss" scoped>
.goods-detail {
  position: relative;
  .goods-detail-border {
    min-height: 100%;
    height: auto;
    background: url("~assets/imgs/goodsdetailsbg.png");
    border: 0.3125rem solid #000;
    border-top: none;

    main {
      margin: 0 auto;
      padding: 1.875rem 0 1.25rem;
      width: 60%;
      height: auto;
      .goods-detail-top {
        display: flex;
        align-items: center;

        span {
          text-align: right;
        }
      }

      .goods-detail-user {
        margin: 0.625rem 0;
        height: auto;
        display: flex;
        align-items: center;

        img {
          margin: 0 0.3125rem 0 0;
          width: 3rem;
          height: 3rem;
          border-radius: 50%;
          flex: 1;
        }

        span {
          height: auto;
        }

        button {
          color: #fff;
          font-size: 1rem;
          margin: 0 0 0 0.625rem;
          padding: 0.625rem;
          height: auto;
          background: #000;
          flex: 30%;
        }
      }

      .goods-detail-msg {
        margin: 0 0 1.25rem 0;
        display: flex;

        span {
          &:last-child {
            text-align: right;
          }
        }
      }

      .goods-detail-content {
        height: auto;
        .goods-detail-text {
          font-size: 1.125rem;
          word-break: break-all;
          line-height: 1.5625rem;
        }
        .goods-detail-img {
          height: auto;

          img {
            margin: 0.625rem auto 0;
            max-width: 100%;
            width: auto;
            height: auto;
            display: block;
          }
        }
      }

      .goods-detail-time {
        font-size: 0.875rem;
        margin: 0.625rem 0 0 0;
        display: flex;
        align-items: center;

        p {
          &:last-child {
            text-align: right;
          }
        }
      }
    }
  }

  .goods-detail-friend,
  .goods-detail-tip,
  .goods-detail-del,
  .goods-detail-sell {
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
    textarea {
      padding: 0.3125rem;
      border: 0.125rem #000 solid;
      resize: none;
      outline: none;
    }

    .goods-detail-friend-button,
    .goods-detail-del-button,
    .goods-detail-sell-button {
      margin: 0.625rem 0 0 0;
      display: flex;

      button {
        color: #fff;
        background: #000;
        border: 0.125rem solid #000;

        &:first-child {
          margin: 0 0.625rem 0 0;
        }
      }
    }
  }
}

@media screen and (max-width: 1105px) {
  .goods-detail {
    .goods-detail-border {
      main {
        width: 85%;
        .goods-detail-user {
          button {
            flex: 49%;
            &:last-child {
              flex: 70%;
            }
          }
        }
      }
    }
  }
}
</style>