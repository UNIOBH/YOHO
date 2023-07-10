<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-18 15:11:21
 * @LastEditTime: 2022-11-17 15:37:19
-->
<template>
  <div class="recommend">
    <YHHeader />
    <div class="recommend-border">
      <ul v-masonry item-selector=".li" transition-duration="0.5s" gutter="10">
        <li
          v-masonry-tile
          class="li"
          v-for="item in list"
          @click="toDetail(item)"
        >
          <!-- 表示是商品 -->
          <img :src="item.pictures[0].img" v-if="item.name" />
          <h2 v-else>{{ item.title }}</h2>
          <p v-if="item.describe">{{ item.describe }}</p>
          <p class="hidden-img" v-else v-html="item.details"></p>
        </li>
      </ul>
    </div>
    <YHFooter />
  </div>
</template>

<script>
// 组件
import YHHeader from "components/common/YHHeader";
import YHFooter from "components/common/YHFooter";

// 网络请求
import { getRecommendGoods } from "api/goods.js";
import { getRecommendInvitation } from "api/invitation.js";

export default {
  data() {
    return {
      current: 1, // 当前页数
      list: [],
    };
  },
  components: {
    YHHeader,
    YHFooter,
  },
  created() {
    getRecommendGoods(
      this.current,
      15,
      JSON.parse(sessionStorage.getItem("user")).sId
    ).then((res) => {
      if (res.status == 200) this.list.push(...res.data.list);
    });
    getRecommendInvitation(
      this.current,
      15,
      JSON.parse(sessionStorage.getItem("user")).sId
    ).then((res) => {
      if (res.status == 200) this.list.push(...res.data.list);
    });
  },
  methods: {
    /**
     * 跳转详情页
     * @param {Object} item
     */
    toDetail(item) {
      if (item.name) this.$router.push(`/GoodsDetail/${item.id}`);
      else this.$router.push(`/InvitationDetail/${item.id}`);
    },
  },
};
</script>

<style lang="scss" scoped>
.recommend {
  .recommend-border {
    padding: 0 0 3.125rem 0;
    height: auto;
    border-left: 0.3125rem solid #000;
    border-right: 0.3125rem solid #000;

    ul {
      margin: 0 auto;
      padding: 1.875rem 0 0;
      width: 85%;
      position: relative;
      height: auto;
      li {
        margin: 0.625rem 0 0;
        padding: 1.25rem 0.625rem;
        width: 24%;
        height: auto;
        border: 0.25rem solid #000;
        overflow: hidden;

        img {
          margin: 0 auto;
          width: 12.5rem;
          height: 12.5rem;
          display: block;
        }
        h2 {
          text-overflow: ellipsis;
          word-break: break-all;
          -webkit-line-clamp: 1;
          height: auto;
          display: -webkit-box;
          -webkit-box-orient: vertical;
          overflow: hidden;
        }

        p {
          text-overflow: ellipsis;
          word-break: break-all;
          -webkit-line-clamp: 3;
          margin: 0.625rem 0 0;
          height: auto;
          display: -webkit-box;
          -webkit-box-orient: vertical;
          overflow: hidden;

          &.hidden-img {
            img {
              display: none;
            }
          }
        }
      }
    }
  }
}

@media screen and (max-width: 760px) {
  .recommend {
    .recommend-border {
      ul {
        li {
          width: 45%;
        }
      }
    }
  }
}
</style>