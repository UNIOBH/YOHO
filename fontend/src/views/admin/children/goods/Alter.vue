<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-31 21:20:40
 * @LastEditTime: 2022-11-16 15:41:18
-->
<template>
  <div class="admin-goods-alter">
    <div class="admin-goods-alter-top">
      <div class="admin-goods-alter-top-id">
        <label for="admin-goods-alter-top-id">用户id:</label>
        <input
          type="text"
          id="admin-goods-alter-top-id"
          placeholder="请输入用户id"
          v-model.number="uId"
        />
      </div>
      <div class="admin-goods-alter-top-category">
        <label for="admin-goods-alter-top-category">商品类别:</label>
        <select v-model="cId" id="admin-goods-alter-top-category">
          <option selected value="0">All</option>
          <option v-for="item in categoryList" :key="item.id" :value="item.id">
            {{ item.name }}
          </option>
        </select>
      </div>
      <div class="admin-goods-alter-top-state">
        <label for="admin-goods-alter-top-state">商品状态:</label>
        <select id="admin-goods-alter-top-state" v-model="state">
          <option selected value="0">All</option>
          <option value="1">已发布</option>
          <option value="2">已出售</option>
          <option value="3">已下架</option>
        </select>
      </div>
      <div class="amdin-goods-alter-top-recommend">
        <span>是否为推荐商品：</span>
        <input type="radio" v-model="isRecomment" value="2" />All
        <input type="radio" v-model="isRecomment" value="1" />是
        <input type="radio" v-model="isRecomment" value="0" />否
      </div>
      <input type="submit" value="设不推荐" @click="toSetRecomment(null, 0)" />
      <input type="submit" value="设为推荐" @click="toSetRecomment(null, 1)" />
      <input type="submit" value="下架" @click="toSetState(3)" />
    </div>
    <main>
      <table cellspacing="0">
        <thead>
          <tr>
            <th>选择</th>
            <th>商品id</th>
            <th>用户id</th>
            <th>商品名称</th>
            <th>商品价格</th>
            <th>商品状态</th>
            <th>商品类别</th>
            <th>点击量</th>
            <th>发布时间</th>
            <th>是否为推荐</th>
            <th>成交价格</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in goodsList" :key="item.id">
            <td><input type="checkbox" v-model="ids" :value="item.id" /></td>
            <td>{{ item.id }}</td>
            <td>{{ item.user.id }}</td>
            <td>{{ item.name }}</td>
            <td>￥{{ item.price }}</td>
            <td>{{ getStateName(item.state) }}</td>
            <td>{{ item.category.name }}</td>
            <td>{{ item.hits }}</td>
            <td>{{ getTime(item.releaseTime) }}</td>
            <td>{{ item.isRecommend ? "是" : "否" }}</td>
            <td>{{ item.finalPrice ? "￥" + item.finalPrice : "" }}</td>
            <td>
              <router-link :to="getTo(item.id)" tag="button"
                >查看详情</router-link
              >
              <button
                v-if="!item.isRecommend"
                @click="toSetRecomment(item.id, 1)"
              >
                设为推荐
              </button>
              <button v-else @click="toSetRecomment(item.id, 0)">
                设不推荐
              </button>
              <button @click="lookImg(item.id)">查看图片</button>
            </td>
          </tr>
        </tbody>
      </table>
      <page
        :total="totalPage"
        class="admin-goods-alter-page"
        @changeCurrent="toSearchGoods"
      />
    </main>
    <div class="admin-goods-alter-imgs" v-show="imgsIsShow">
      <h3 v-if="imgList.length == 0">暂无图片~</h3>
      <ul v-else>
        <li v-for="item in imgList" :key="item.id">
          <img :src="item.img" alt="" />
          <button @click="violation(item.id)">违规</button>
        </li>
      </ul>
      <button @click="closeImgs">确定</button>
    </div>
  </div>
</template>

<script>
// 组件
import Page from "@/components/common/Page.vue";

// 网络请求
import { getAll } from "api/goods_category";
import { adminSearchGoods, setRecomment, setState } from "api/goods";
import { getImgsByGId, setViolation } from "api/goods_picture";

// utils
import { intercept } from "utils/time";

export default {
  components: {
    Page,
  },
  data() {
    return {
      uId: "",
      categoryList: [],
      state: 0,
      isRecomment: 2,
      cId: 0,
      current: 1,
      goodsList: [],
      totalPage: 0,
      ids: [],
      imgsIsShow: false,
      imgList: [],
    };
  },
  created() {
    getAll().then((res) => {
      if (res.status == 200) this.categoryList = res.data;
    });
    this.toSearchGoods(1);
  },
  methods: {
    toSearchGoods(current) {
      this.current = current;
      adminSearchGoods(
        this.current,
        7,
        this.isRecomment,
        this.cId,
        this.state,
        this.uId == "" ? 0 : this.uId
      ).then((res) => {
        if (res.status == 200) {
          this.goodsList = res.data.list;
          this.totalPage = res.data.total;
        } else if (res.status == 201) {
          this.goodsList = [];
          this.totalPage = 0;
        }
      });
    },
    getStateName(state) {
      if (state == 1) return "已发布";
      if (state == 2) return "已出售";
      if (state == 3) return "下架";
    },
    getTime(time) {
      return intercept(time);
    },
    toSetRecomment(id, recommend) {
      // 批量
      if (id == null) {
        setRecomment(this.ids, recommend).then((res) => {
          if (res.status == 200) this.$router.go(0);
        });
      } else {
        // 单次
        setRecomment([id], recommend).then((res) => {
          if (res.status == 200) this.$router.go(0);
        });
      }
    },
    toSetState(state) {
      if (this.ids.length != 0 && this.ids != null) {
        setState(this.ids, state).then((res) => {
          if (res.status == 200) this.$router.go(0);
        });
      }
    },
    lookImg(gId) {
      this.imgsIsShow = true;
      getImgsByGId(gId).then((res) => {
        if (res.status == 200) this.imgList = res.data;
      });
    },
    closeImgs() {
      this.imgsIsShow = false;
      this.imgList = [];
    },
    /**
     * 违规图片
     * @param {Number} id 图片id
     */
    violation(id) {
      setViolation(id).then((res) => {
        if (res.status == 200) this.$router.go(0);
      });
    },
    getTo(gId) {
      return `/GoodsDetail/${gId}`;
    },
  },
  watch: {
    uId() {
      this.toSearchGoods(1);
    },
    state() {
      this.toSearchGoods(1);
    },
    isRecomment() {
      this.toSearchGoods(1);
    },
    cId() {
      this.toSearchGoods(1);
    },
  },
};
</script>

<style lang="scss" scoped>
.admin-goods-alter {
  .admin-goods-alter-top {
    padding: 10px 20px;
    height: auto;
    border-bottom: 5px #000 solid;
    display: flex;
    align-items: center;
    position: relative;

    div {
      margin: 0 10px 0 0;
      height: auto;
      display: flex;
      align-items: center;
      label {
        height: auto;
      }

      input {
        height: auto;
        outline: none;
      }

      &.admin-goods-alter-top-id {
        flex: 65%;
        label {
          width: 65px;
        }
        input {
          padding: 3px 0 3px 5px;
          width: 140px;
          border: #000 2px solid;
        }
      }

      &.admin-goods-alter-top-category,
      &.admin-goods-alter-top-state {
        flex: 65%;
        label {
          width: 125px;
        }

        select {
          padding: 3px 0 3px 5px;
          border: #000 2px solid;
        }
      }

      &.amdin-goods-alter-top-recommend {
        span {
          flex: 355%;
        }

        input {
          height: 15px;
        }
      }
    }

    input[type="submit"] {
      color: #fff;
      margin: 0 5px 0 0;
      padding: 5px 15px;
      width: auto;
      background: #000;
      border-radius: 10px;
    }
  }

  main {
    padding: 10px 20px 0;
    height: calc(100% - 56px);
    position: relative;
    table {
      text-align: center;
      height: auto;
      border: #000 solid 3px;

      thead {
        color: #fff;
        background: #000;

        tr {
          height: 30px;
          th {
            font-size: 14px;
            white-space: nowrap;
            padding: 0 10px;
            height: 30px;
            border-right: #fff solid 3px;

            &:first-child {
              width: 20px;
            }

            &:last-child {
              border-right: none;
            }
          }
        }
      }

      tbody {
        tr {
          td {
            white-space: nowrap;
            padding: 5px 10px 0;
            height: 30px;
            border-right: #000 solid 3px;
            border-top: #000 solid 3px;
            &:first-child {
              width: 20px;

              input {
                width: 15px;
              }
            }

            &:last-child {
              border-right: none;
            }

            button {
              color: #fff;
              margin: 0 0 5px 0;
              width: auto;
              height: auto;
              background: #000;
              display: block;
            }
          }
        }
      }
    }
    .admin-goods-alter-page {
      position: absolute;
      left: 0;
      right: 0;
      bottom: 10px;
    }
  }

  .admin-goods-alter-imgs {
    padding: 1.25rem;
    width: auto;
    height: auto;
    background: #fff;
    border: #000 0.1875rem solid;
    border-radius: 0.9375rem;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    ul {
      margin: 0 0 0.625rem 0;
      display: flex;

      li {
        margin: 0 0 0 0.625rem;
        display: flex;
        flex-direction: column;

        &:first-child {
          margin: 0;
        }

        img {
          margin: 0 0 5px 0;
          width: 100px;
          height: 100px;
        }

        button {
          color: #000;
          background: #fff;
        }
      }
    }

    button {
      color: #fff;
      background: #000;
    }
  }
}
</style>