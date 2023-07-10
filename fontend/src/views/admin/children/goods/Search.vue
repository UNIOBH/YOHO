<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-29 17:02:58
 * @LastEditTime: 2022-11-10 16:01:42
-->
<template>
  <div class="admin-goods-search">
    <div class="admin-goods-search-top">
      <div class="admin-goods-search-top-id">
        <label for="admin-goods-search-top-id">用户id:</label>
        <input
          type="text"
          id="admin-goods-search-top-id"
          placeholder="请输入用户id"
          v-model.number="uId"
        />
      </div>
      <div class="admin-goods-search-top-category">
        <label for="admin-goods-search-top-category">商品类别:</label>
        <select v-model="cId" id="admin-goods-search-top-category">
          <option selected value="0">All</option>
          <option v-for="item in categoryList" :key="item.id" :value="item.id">
            {{ item.name }}
          </option>
        </select>
      </div>
      <div class="admin-goods-search-top-state">
        <label for="admin-goods-search-top-state">商品状态:</label>
        <select v-model="gState" id="admin-goods-search-top-state">
          <option selected value="0">All</option>
          <option value="1">已发布</option>
          <option value="2">已出售</option>
          <option value="3">已下架</option>
        </select>
      </div>
      <div class="amdin-goods-search-top-recommend">
        <span>是否为推荐商品：</span>
        <input type="radio" v-model="isRecommend" value="2" />All
        <input type="radio" v-model="isRecommend" value="1" />是
        <input type="radio" v-model="isRecommend" value="0" />否
      </div>
      <input type="submit" value="查 询" @click="toSearchGoods(1)" />
    </div>
    <main>
      <table cellspacing="0">
        <thead>
          <tr>
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
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in goodsList" :key="item.id">
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
          </tr>
        </tbody>
      </table>
      <!-- 页面切换未测试 -->
      <page
        @changeCurrent="toSearchGoods"
        :total="totalPage"
        class="amdin-goods-search-page"
      />
    </main>
  </div>
</template>

<script>
// 组件
import Page from "@/components/common/Page.vue";

// 网络请求
import { getAll } from "api/goods_category";
import { adminSearchGoods } from "api/goods";

// utils
import { intercept } from "utils/time";

export default {
  data() {
    return {
      uId: "",
      categoryList: [],
      cId: 0,
      gState: 0,
      isRecommend: 2,
      totalPage: 0,
      current: 1,
      goodsList: [],
    };
  },
  components: {
    Page,
  },
  methods: {
    toSearchGoods(current) {
      this.current = current;
      adminSearchGoods(
        this.current,
        18,
        this.isRecommend,
        this.cId,
        this.gState,
        this.uId == "" ? 0 : this.uId
      ).then((res) => {
        if (res.status == 200) {
          this.totalPage = res.data.total;
          this.goodsList = res.data.list;
        } else if (res.status == 201) {
          this.totalPage = 0;
          this.goodsList = [];
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
  },
  created() {
    getAll().then((res) => {
      if (res.status == 200) this.categoryList = res.data;
    });
    this.toSearchGoods(1);
  },
};
</script>

<style lang="scss" scoped>
.admin-goods-search {
  .admin-goods-search-top {
    padding: 10px 20px;
    height: auto;
    border-bottom: 5px #000 solid;
    display: flex;
    align-items: center;

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

      &.admin-goods-search-top-id {
        label {
          width: 65px;
        }
        input {
          padding: 3px 0 3px 5px;
          width: 200px;
          border: #000 2px solid;
        }
      }

      &.admin-goods-search-top-category,
      &.admin-goods-search-top-state {
        label {
          width: 125px;
        }

        select {
          padding: 3px 0 3px 5px;
          border: #000 2px solid;
        }
      }

      &.amdin-goods-search-top-recommend {
        span {
          flex: 80%;
        }

        input {
          width: 40px;
        }
      }
    }

    input[type="submit"] {
      color: #fff;
      padding: 5px 15px;
      width: auto;
      background: #000;
    }
  }

  main {
    padding: 20px 30px 0;
    height: calc(100% - 51px);
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
            padding: 0 10px;
            height: 30px;
            border-right: #000 solid 3px;
            border-top: #000 solid 3px;

            &:last-child {
              border-right: none;
            }
          }
        }
      }
    }

    .amdin-goods-search-page {
      position: absolute;
      left: 0;
      right: 0;
      bottom: 20px;
    }
  }
}
</style>