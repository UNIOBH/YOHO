<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-29 17:04:14
 * @LastEditTime: 2022-11-10 16:05:51
-->
<template>
  <div class="admin-invitation">
    <div class="admin-invitation-top">
      <div class="admin-invitation-top-uid">
        <label for="admin-invitation-top-uid">用户id：</label>
        <input
          type="text"
          placeholder="请输入搜索用户id……"
          id="admin-invitation-top-uid"
          v-model.number="uId"
        />
      </div>
      <div class="admin-invitation-top-keyword">
        <label for="admin-invitation-top-keyword">搜索关键字：</label>
        <input
          type="text"
          placeholder="请输入搜索关键字……"
          id="admin-invitation-top-keyword"
          v-model="keyword"
        />
      </div>
      <div class="admin-invitation-top-recommend">
        <span>是否为推荐：</span>
        <input
          type="radio"
          id="admin-invitation-top-recommend-all"
          value="2"
          v-model="isRecommend"
        /><label for="admin-invitation-top-recommend-all">All</label>
        <input
          type="radio"
          id="admin-invitation-top-recommend-yes"
          v-model="isRecommend"
          value="1"
        /><label for="admin-invitation-top-recommend-yes">是</label>
        <input
          type="radio"
          id="admin-invitation-top-recommend-no"
          v-model="isRecommend"
          value="0"
        /><label for="admin-invitation-top-recommend-no">否</label>
      </div>
      <input type="submit" value="查 询" @click="toSearchInvitation(1)" />
    </div>
    <main>
      <table cellspacing="0">
        <thead>
          <tr>
            <th>帖子id</th>
            <th>标题</th>
            <th>创建时间</th>
            <th>阅读量</th>
            <th>点赞量</th>
            <th>更新时间</th>
            <th>是否为推荐</th>
            <th>用户id</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in invitataionList" :key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.title }}</td>
            <td>{{ getTime(item.createTime) }}</td>
            <td>{{ item.readCount }}</td>
            <td>{{ item.likeCount }}</td>
            <td>{{ getTime(item.lastTime) }}</td>
            <td>{{ item.recommend ? "是" : "否" }}</td>
            <td>{{ item.user.id }}</td>
          </tr>
        </tbody>
      </table>
      <page
        :total="totalPage"
        class="admin-invitation-page"
        @changeCurrent="toSearchInvitation"
      />
    </main>
  </div>
</template>

<script>
// 组件
import Page from "@/components/common/Page.vue";

// 网络请求
import { adminSearchInvitation } from "api/invitation";

// utils
import { intercept } from "utils/time";

export default {
  data() {
    return {
      uId: "",
      keyword: "",
      isRecommend: 2,
      invitataionList: [],
      current: 1,
      totalPage: 0,
    };
  },
  components: {
    Page,
  },
  created() {
    this.toSearchInvitation(1);
  },
  methods: {
    getTime(time) {
      return intercept(time);
    },
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
  },
};
</script>

<style lang="scss" scoped>
.admin-invitation {
  .admin-invitation-top {
    padding: 20px;
    height: auto;
    border-bottom: 3px solid #000;
    display: flex;
    align-items: center;

    div {
      margin: 0 20px 0 0;
      display: flex;
      align-items: center;

      &.admin-invitation-top-keyword,
      &.admin-invitation-top-uid {
        label {
          flex: 50%;
        }
        input {
          padding: 3px 0 3px 5px;
          border: 2px solid #000;
        }
      }

      &.admin-invitation-top-recommend {
        span {
          flex: 250%;
        }
        input {
          height: 20px;
        }
      }
    }

    input[type="submit"] {
      color: #fff;
      padding: 5px 30px;
      width: auto;
      height: auto;
      background: #000;
    }
  }

  main {
    padding: 20px 20px 0;
    height: calc(100% - 74px);
    position: relative;
    table {
      text-align: center;
      height: auto;
      border: 3px solid #000;

      thead {
        height: 30px;
        tr {
          th {
            color: #fff;
            white-space: nowrap;
            padding: 5px 10px;
            height: auto;
            background: #000;
            border-right: 3px solid #fff;

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
            padding: 5px 10px;
            border-right: 3px solid #000;
            border-top: 3px solid #000;

            &:last-child {
              border-right: none;
            }
          }
        }
      }
    }

    .admin-invitation-page {
      position: absolute;
      left: 0;
      right: 0;
      bottom: 10px;
    }
  }
}
</style>