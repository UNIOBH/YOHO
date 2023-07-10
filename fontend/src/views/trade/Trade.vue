<!--
 * @Descripttion: 跳蚤市场
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-08 15:31:08
 * @LastEditTime: 2022-12-12 15:49:02
-->
<template>
  <div class="trade">
    <YHHeader />
    <div class="trade-border">
      <div class="trade-container">
        <aside class="trade-search">
          <select v-model="cId">
            <option value="0" selected>全部</option>
            <option
              v-for="item in goodsCategoryList"
              :value="item.id"
              :key="item.name"
            >
              {{ item.name }}
            </option>
          </select>
          <input
            type="text"
            class="search-input"
            placeholder="输入你想要搜索的内容吧……"
            v-model="keyword"
          />
          <input type="submit" value="搜索" @click="toSerachGoods" />
          <div class="goods-search">
            <router-link tag="button" to="/ReleaseGoods/0"
              >发布商品</router-link
            >
          </div>
        </aside>
        <section class="trade-category">
          <ul>
            <li>分类：</li>
            <li @click="searchByCId(0)" value="0">全部</li>
            <li
              v-for="item in goodsCategoryList"
              :key="item.name"
              @click="searchByCId(item.id)"
            >
              {{ item.name }}
            </li>
          </ul>
        </section>
        <main class="trade-main">
          <card
            v-for="item in goodsList"
            :key="item.id"
            :c-id="item.id"
            :img="item.pictures == null ? '' : item.pictures[0].img"
            :describe="item.details"
            :publisher="item.user.nickname"
            :title="item.name"
            :time="item.releaseTime"
            :price="item.price"
            :hits="item.hits"
          />
        </main>
      </div>
    </div>
    <YHFooter />
  </div>
</template>

<script>
// 组件
import YHHeader from "components/common/YHHeader";
import YHFooter from "components/common/YHFooter.vue";
import Card from "components/goods/Card.vue";

// 网络请求
import { getGoodsByPage, searchGoods } from "api/goods.js";
import { getAll } from "api/goods_category.js";

export default {
  name: "Trade",
  components: {
    YHHeader,
    YHFooter,
    Card,
  },
  data() {
    return {
      pages: 0, // 总页数
      current: 1, // 当前页数
      cId: 0, // 商品分类id
      keyword: "", // 搜索关键字
      goodsList: [], // 展示商品列表
      goodsCategoryList: [], // 商品分类列表
    };
  },
  created() {
    this.getGoodsPage();
    getAll().then((res) => {
      if (res.status == 200) this.goodsCategoryList = res.data;
    });
  },
  mounted() {
    window.addEventListener("scroll", this.loadData);
  },
  methods: {
    /**
     * 获取goods
     */
    getGoodsPage() {
      getGoodsByPage(
        this.current,
        12,
        JSON.parse(sessionStorage.getItem("user")).sId
      ).then((res) => {
        if (res.status == 200) {
          this.goodsList.splice(this.goodsList.length, 0, ...res.data.list);
          this.pages = res.data.total;
        }
      });
    },
    /**
     * 加载goods数据
     */
    loadData() {
      const scrollTop =
        document.documentElement.scrollTop || document.body.scrollTop; // 被卷去的高度
      const clientHeight = document.documentElement.clientHeight; // 浏览器可视高度（content + padding）
      const scrollHeight = document.documentElement.scrollHeight; // 浏览器高度
      if (scrollTop + clientHeight + 1 >= scrollHeight) {
        console.log("=============================================");
        this.current++;
        if (this.current <= this.pages) this.getGoodsPage();
      }
    },
    /**
     * 搜索商品
     */
    toSerachGoods() {
      this.current = 1;
      searchGoods(
        this.current,
        12,
        JSON.parse(sessionStorage.getItem("user")).sId,
        this.cId,
        this.keyword
      ).then((res) => {
        if (res.status == 200) this.goodsList = res.data.list;
        else if (res.status == 201) this.goodsList = [];
      });
    },
    searchByCId(id) {
      this.cId = id;
      this.toSerachGoods();
    },
  },
};
</script>

<style lang="scss" scoped>
@mixin mobile {
  .trade-search {
    font-size: 0.5rem;
    select {
      margin: 0 0.3125rem 0 0;
      flex: 40%;
    }
    input[type="submit"] {
      flex: 15%;
    }

    .goods-search {
      margin: 0 0 0 0.625rem;
      flex: 40%;

      button {
        color: #fff;
        padding: 0.05rem 0;
        background: #000;
      }
    }
  }
  .trade-category {
    display: none;
  }
}
@mixin pc {
  .trade-search {
    select {
      display: none;
    }
    input {
      &[type="submit"] {
        flex: 15%;
      }
    }
    .goods-search {
      margin: 0 0 0 0.625rem;
      flex: 15%;

      button {
        color: #fff;
        padding: 0.3125rem 0;
        background: #000;
      }
    }
  }
  .trade-category {
    margin: 0 0 1.25rem 0;
    display: block;

    ul {
      display: flex;
      flex-wrap: wrap;
      li {
        margin: 0 0.5rem;
        padding: 0 0.625rem 0 0;
        width: auto;
        position: relative;
        cursor: default;

        &:nth-child(n + 2):after {
          content: "";
          width: 2px;
          height: 100%;
          background: #000;
          position: absolute;
          right: 0;
        }
      }
    }
  }
}

.trade {
  .trade-border {
    padding: 1.875rem 0;
    min-height: 100%;
    height: auto;
    border-left: 0.3125rem solid #000;
    border-right: 0.3125rem solid #000;
    .trade-container {
      margin: 0 auto;
      width: 85%;
      height: auto;

      .trade-search {
        margin: 0 0 1.25rem 0;
        display: flex;

        input,
        select {
          height: 1.875rem;
          border: 3px solid #000;
          outline: none;
          &.search-input {
            padding: 0 0 0 0.5rem;
            border-right: none;
            border-radius: 0.3125rem 0 0 0.3125rem;
            flex: auto;
          }
          &[type="submit"] {
            color: #fff;
            background: #000;
            border-left: none;
            border-radius: 0 0.3125rem 0.3125rem 0;
          }
        }
        // .goods-search {
        //   text-align: right;
        //   font-size: 0.875rem;
        //   height: 100%;
        //   input {
        //     margin: 0 0.5rem 0 0;
        //     width: 1.2rem;
        //     vertical-align: middle;
        //   }
        // }
      }

      .trade-main {
        width: 100%;
        height: auto;
        display: grid;
        grid-gap: 1.875rem;
        justify-content: space-evenly;
        div.card {
          width: 300px;
          height: 400px;
        }
      }
    }
  }
}

@media screen and (min-width: 300px) {
  .trade-container {
    @include mobile;
    .trade-main {
      grid-template-columns: auto;
    }
  }
}

@media screen and (min-width: 700px) {
  .trade-container {
    @include mobile;
    .trade-main {
      grid-template-columns: auto auto;
    }
  }
}

@media screen and (min-width: 1200px) {
  .trade-container {
    @include pc;
    .trade-main {
      grid-template-columns: auto auto auto;
    }
  }
}

@media screen and (min-width: 1500px) {
  .trade-container {
    @include pc;
    .trade-main {
      grid-template-columns: auto auto auto auto;
    }
  }
}
</style>