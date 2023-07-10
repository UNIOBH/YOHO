<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-29 17:02:21
 * @LastEditTime: 2022-11-11 15:48:30
-->
<template>
  <div class="admin-user">
    <div class="admin-user-top">
      <div>
        <!-- 用户id start -->
        <input
          type="text"
          placeholder="请输入用户id……"
          class="admin-user-top-id"
          v-model.number="uId"
        />
        <!-- 用户id end -->
        <!-- 查询关键字 start -->
        <input
          type="text"
          placeholder="根据输入的关键字查询……"
          class="admin-user-top-keyword"
          v-model="keyword"
        />
        <!-- 查询关键字 end -->
      </div>
      <div>
        <!-- 学校 start -->
        <select v-model="sId">
          <option value="0" disabled>——请选择——</option>
          <option :key="item.name" :value="item.id" v-for="item in sList">
            {{ item.name }}
          </option>
        </select>
        <!-- 学校 end -->
        <!-- 性别 start -->
        <div>
          <span>性别：</span>
          <div>
            <input type="radio" v-model="sex" value="1" />男
            <input type="radio" v-model="sex" value="0" />女
            <input type="radio" v-model="sex" value="2" />All
          </div>
        </div>
        <!-- 性别 end -->
        <input type="submit" value="查 询" @click="search(1)" />
      </div>
    </div>
    <main>
      <table cellspacing="0">
        <thead>
          <tr>
            <th>用户id</th>
            <th>用户名</th>
            <th>用户昵称</th>
            <th>用户性别</th>
            <th>用户邮箱</th>
            <th>注册时间</th>
            <th>更新时间</th>
            <th>所在学校</th>
            <th>是否已注销</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环 18 start -->
          <tr v-for="item in userList" :key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.uname }}</td>
            <td>{{ item.unickname }}</td>
            <td>{{ item.sex ? "男" : "女" }}</td>
            <td>{{ item.email }}</td>
            <td>{{ getTime(item.registerTime) }}</td>
            <td>{{ getTime(item.lastTime) }}</td>
            <td>{{ item.sname }}</td>
            <td>{{ item.udel ? "已注销" : "未注销"}}</td>
          </tr>
          <!-- 循环 end -->
        </tbody>
      </table>
      <div class="admin-main-page">
        <page :total="pages" @changeCurrent="search" />
      </div>
    </main>
  </div>
</template>

<script>
// 组件
import Page from "components/common/Page.vue";

// 网络请求
import { getAll } from "api/school";
import { getUserPage } from "api/user";

// utils
import { intercept } from "utils/time";

export default {
  data() {
    return {
      sId: 0,
      sList: [],
      userList: [],
      uId: "",
      keyword: "",
      sex: 2,
      current: 1, // 当前页数
      pages: 0,
    };
  },
  components: {
    Page,
  },
  methods: {
    search(current) {
      this.current = current;
      getUserPage(
        this.current,
        18,
        this.uId == "" ? 0 : this.uId,
        this.sId,
        this.keyword == "" ? " " : this.keyword,
        this.sex
      ).then((res) => {
        if (res.status == 200) {
          this.userList = res.data.list;
          this.pages = res.data.pages;
        } else if (res.status == 201) {
          this.userList = [];
          this.pages = 0;
        }
      });
    },
    getTime(time) {
      return intercept(time);
    },
  },
  created() {
    getAll().then((res) => {
      if (res.status == 200) this.sList = res.data;
    });
    this.search(1);
  },
};
</script>

<style lang="scss" scoped>
.admin-user {
  padding: 10px 20px;
  height: 100%;
  .admin-user-top {
    padding: 0 0 20px 0;
    height: auto;
    border-bottom: 0.25rem solid #000;

    & > div {
      display: flex;
      align-items: center;

      input,
      select {
        padding: 5px 0 5px 10px;
        border: #000 solid 3px;
        border-radius: 5px;
        &.admin-user-top-id {
          margin: 0 10px 0 0;
        }
      }

      &:last-child {
        margin: 15px 0 0 0;

        & input:first-child {
          margin: 0 10px 0 0;
        }

        & > div {
          display: flex;
          align-items: center;

          &:first-of-type {
            span {
              flex: 120%;
            }
          }

          span {
            text-align: right;
          }

          div {
            display: flex;
            align-items: center;

            input {
              margin: 0 3px 0 5px;
              width: 20px;
              height: 20px;

              &:last-child {
                margin: 0 5px;
              }
            }
          }
        }

        input[type="submit"] {
          color: #fff;
          background: #000;
          flex: 30%;
        }
      }
    }
  }

  main {
    height: 625px;
    position: relative;

    table {
      text-align: center;
      margin: 10px 0 0 0;
      height: auto;
      border: #000 solid 3px;

      thead {
        height: 30px;
        tr {
          th {
            color: #fff;
            white-space: nowrap;
            padding: 5px 10px;
            background: #000;
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
            padding: 5px 10px;
            height: 30px;
            border-top: #000 solid 3px;
            border-right: #000 solid 3px;

            &:last-child {
              border-right: 0;
            }
          }
        }
      }
    }

    .admin-main-page {
      width: 100%;
      height: auto;
      position: absolute;
      bottom: 8px;
      left: 0;
      right: 0;
    }
  }
}
</style>