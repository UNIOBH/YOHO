<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-11-01 14:27:50
 * @LastEditTime: 2022-11-16 15:49:34
-->
<template>
  <div class="admin-modify-modify">
    <div class="admin-modify-top">
      <div class="admin-modify-top-uid">
        <label for="admin-modify-top-uid">用户id：</label>
        <input
          type="text"
          placeholder="请输入用户id……"
          id="admin-modify-top-uid"
          v-model.number="uId"
        />
      </div>
      <div class="admin-modify-top-keyword">
        <label for="admin-modify-top-keyword">搜索关键字：</label>
        <input
          type="text"
          placeholder="请输入搜索关键字……"
          id="admin-modify-top-keyword"
          v-model="keyword"
        />
      </div>
      <div class="admin-modify-top-recommend">
        <span>是否为推荐：</span>
        <input
          type="radio"
          id="admin-modify-top-recommend-all"
          value="2"
          v-model="isRecommend"
        /><label for="admin-modify-top-recommend-all">All</label>
        <input
          type="radio"
          id="admin-modify-top-recommend-yes"
          value="1"
          v-model="isRecommend"
        /><label for="admin-modify-top-recommend-yes">是</label>
        <input
          type="radio"
          id="admin-modify-top-recommend-no"
          value="0"
          v-model="isRecommend"
        /><label for="admin-modify-top-recommend-no">否</label>
      </div>
      <input type="submit" value="删除" @click="toDel" />
      <input type="submit" value="设为推荐" @click="toSetRecommend(null, 1)" />
      <input type="submit" value="设不推荐" @click="toSetRecommend(null, 0)" />
    </div>
    <main>
      <table cellspacing="0">
        <thead>
          <tr>
            <th>选择</th>
            <th>帖子id</th>
            <th>标题</th>
            <th>创建时间</th>
            <th>阅读量</th>
            <th>点赞量</th>
            <th>更新时间</th>
            <th>是否为推荐</th>
            <th>用户id</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in invitataionList" :key="item.id">
            <td>
              <input type="checkbox" v-model="selectIds" :value="item.id" />
            </td>
            <td>{{ item.id }}</td>
            <td>{{ item.title }}</td>
            <td>{{ getTime(item.createTime) }}</td>
            <td>{{ item.readCount }}</td>
            <td>{{ item.likeCount }}</td>
            <td>{{ getTime(item.lastTime) }}</td>
            <td>{{ item.recommend ? "是" : "否" }}</td>
            <td>{{ item.user.id }}</td>
            <td>
              <router-link :to="getTo(item.id)" tag="button">查看详情</router-link>
              <button
                v-if="!item.recommend"
                @click="toSetRecommend(item.id, 1)"
              >
                设为推荐
              </button>
              <button v-else @click="toSetRecommend(item.id, 0)">
                设不推荐
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <page
        :total="totalPage"
        class="admin-modify-page"
        @changeCurrent="toSearchInvitation"
      />
    </main>
  </div>
</template>

<script>
// 组件
import Page from "@/components/common/Page.vue";

// 网络请求
import { adminSearchInvitation, del, setRecommed } from "api/invitation";

// utils
import { intercept } from "utils/time";

export default {
  components: {
    Page,
  },
  data() {
    return {
      uId: "",
      keyword: "",
      isRecommend: 2,
      invitataionList: [],
      current: 1,
      totalPage: 0,
      selectIds: [],
    };
  },
  created() {
    this.toSearchInvitation(1);
  },
  methods: {
    toSearchInvitation(current) {
      this.current = current;
      adminSearchInvitation(
        this.current,
        18,
        this.isRecommend,
        this.keyword == "" ? " " : this.keyword,
        this.uId == "" ? 0 : this.uId
      ).then((res) => {
        if (res.status == 200) {
          this.invitataionList = res.data.list;
          this.totalPage = res.data.total;
        } else if (res.status == 201) {
          this.invitataionList = [];
          this.totalPage = 0;
        }
      });
    },
    getTime(time) {
      return intercept(time);
    },
    toDel() {
      if (this.selectIds.length != 0 && this.selectIds != null) {
        del(this.selectIds).then((res) => {
          if (res.status == 200) this.$router.go(0);
        });
      }
    },
    toSetRecommend(ids, recommend) {
      // 批量
      if (ids == null && this.selectIds != null && this.selectIds.length != 0) {
        setRecommed(this.ids, recommend).then((res) => {
          if (res.status == 200) this.$router.go(0);
        });
      } else {
        // 单次
        setRecommed([ids], recommend).then((res) => {
          if (res.status == 200) this.$router.go(0);
        });
      }
    },
    getTo(iId) {
      return `/InvitationDetail/${iId}`;
    },
  },
  watch: {
    uId() {
      this.toSearchInvitation(1);
    },
    keyword() {
      this.toSearchInvitation(1);
    },
    isRecommend() {
      this.toSearchInvitation(1);
    },
  },
};
</script>

<style lang="scss" scoped>
.admin-modify-modify {
  .admin-modify-top {
    padding: 20px;
    height: auto;
    border-bottom: 3px solid #000;
    display: flex;
    align-items: center;

    div {
      margin: 0 20px 0 0;
      display: flex;
      align-items: center;

      &.admin-modify-top-keyword,
      &.admin-modify-top-uid {
        label {
          flex: 65%;
        }
        input {
          padding: 3px 0 3px 5px;
          border: 2px solid #000;
        }
      }

      &.admin-modify-top-recommend {
        span {
          flex: 363%;
        }
        input {
          height: 20px;
        }
      }
    }

    input[type="submit"] {
      color: #fff;
      margin: 0 5px 0 0;
      padding: 5px 30px;
      width: auto;
      height: auto;
      background: #000;
    }
  }

  main {
    padding: 30px 20px 0;
    height: calc(100% - 85px);
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

    .admin-modify-page {
      position: absolute;
      left: 0;
      right: 0;
      bottom: 10px;
    }
  }
}
</style>