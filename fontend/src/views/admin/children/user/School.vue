<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-31 15:01:12
 * @LastEditTime: 2022-11-10 16:19:09
-->
<template>
  <div class="admin-user-school">
    <div class="admin-user-school-top">
      <div class="admin-user-school-province">
        <label for="admin-user-school-province">省份：</label>
        <select id="admin-user-school-province" v-model="pId">
          <option selected disabled value="0">——选择省份——</option>
          <option
            :value="item.id"
            v-for="item in provinceList"
            :key="item.name"
          >
            {{ item.name }}
          </option>
        </select>
      </div>
      <div class="admin-user-school-city">
        <label for="admin-user-school-city">城市：</label>
        <select id="admin-user-school-city" v-model="cId">
          <option value="0" selected disabled>——选择城市——</option>
          <option :value="item.id" v-for="item in cityList" :key="item.name">
            {{ item.name }}
          </option>
        </select>
      </div>
      <div class="admin-user-school-input">
        <label for="admin-user-school-input">学校：</label>
        <input
          type="text"
          id="admin-user-school-input"
          placeholder="请输入学校名称……"
          v-model="sName"
        />
      </div>
      <input type="submit" value="添 加" @click="toAddSchool" />
    </div>
    <div class="admin-user-school-middle">
      <table cellspacing="0">
        <thead>
          <tr>
            <th>学校id</th>
            <th>省份</th>
            <th>城市</th>
            <th>学校名称</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环 start -->
          <tr v-for="item in schoolList" :key="item.sname">
            <td>{{ item.id }}</td>
            <td>{{ item.pname }}</td>
            <td>{{ item.cname }}</td>
            <td>{{ item.sname }}</td>
          </tr>
          <!-- 循环 end -->
        </tbody>
      </table>
      <page
        :total="totalPage"
        class="admin-user-school-middle-page"
        @changeCurrent="toGetSchoolByPage"
      />
    </div>
    <div class="admin-user-school-bottom">
      <h4>用户申请</h4>
      <table cellspacing="0">
        <thead>
          <tr>
            <th>省份</th>
            <th>城市</th>
            <th>学校</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in applyList" :key="item.name">
            <td>{{ item.province.name }}</td>
            <td>{{ item.city.name }}</td>
            <td>{{ item.name }}</td>
            <td>
              <button @click="toSetState(item.city.id, item.id, item.name, 1)">
                同意
              </button>
              <button @click="toSetState(item.city.id, item.id, item.name, 0)">
                拒绝
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <page
        :total="applyTotalPage"
        class="admin-user-school-bottom-page"
        @changeCurrent="toGetApply"
      />
    </div>
  </div>
</template>

<script>
// 组件
import Page from "components/common/Page.vue";

// 网络请求
import { getApply, setState } from "api/apply";
import { getAll } from "api/province";
import { getCityByProvinceId } from "api/city";
import { addSchool, getSchoolByPage } from "api/school";

export default {
  data() {
    return {
      current: 1,
      applyList: [],
      totalPage: 0,
      provinceList: [],
      pId: 0, // 省份id
      cityList: [],
      cId: 0, // 城市id
      sName: "", // 学校名称
      aCurrent: 1,
      applyTotalPage: 0,
      schoolList: [],
    };
  },
  components: {
    Page,
  },
  created() {
    getAll().then((res) => {
      if (res.status == 200) this.provinceList = res.data;
    });
    this.toGetSchoolByPage(1);
    this.toGetApply(1);
  },
  methods: {
    /**
     * 修改用户申请状态
     * @param {Number} cId
     * @param {Number} id
     * @param {String} name
     * @param {Number} state
     */
    toSetState(cId, id, name, state) {
      setState(cId, id, name, state).then((res) => {
        if (res.status == 200) this.$router.go(0);
      });
    },
    toAddSchool() {
      if (this.pId == 0 || this.cId == 0 || this.sName == "") return;
      addSchool(this.cId, this.sName).then((res) => {
        if (res.status == 200) this.$router.go(0);
      });
    },
    toGetSchoolByPage(current) {
      this.current = current;
      getSchoolByPage(this.current, 8).then((res) => {
        if (res.status == 200) {
          this.schoolList = res.data.list;
          this.totalPage = res.data.total;
        } else if (res.status == 201) {
          this.schoolList = [];
          this.totalPage = 0;
        }
      });
    },
    toGetApply(current) {
      this.aCurrent = current;
      getApply(this.aCurrent, 5).then((res) => {
        if (res.status == 200) {
          this.applyList = res.data.list;
          this.applyTotalPage = res.data.total;
        }
      });
    },
  },
  watch: {
    pId(newValue) {
      getCityByProvinceId(newValue).then((res) => {
        if (res.status == 200) this.cityList = res.data;
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.admin-user-school {
  padding: 20px 0 0 0;
  .admin-user-school-top {
    padding: 0 0 20px 0;
    height: auto;
    border-bottom: 4px solid #000;
    display: flex;
    align-items: center;

    div {
      margin: 0 10px 0 0;
      height: auto;
      display: flex;
      align-items: center;

      label {
        flex: 30%;
      }
    }

    input,
    select {
      padding: 5px 0 5px 5px;
      height: auto;
      border: #000 2px solid;
      outline: none;

      &[type="submit"] {
        color: #fff;
        background: #000;
      }
    }
  }

  .admin-user-school-middle {
    margin: 10px 0;
    padding: 0 20px;
    height: 340px;
    position: relative;

    table {
      text-align: center;
      height: auto;
      border: #000 solid 3px;

      thead {
        height: 30px;
        tr {
          th {
            color: #fff;
            white-space: nowrap;
            padding: 0 5px;
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
            padding: 0 5px;
            height: 30px;
            border-top: #000 solid 3px;
            border-right: #000 solid 3px;

            &:last-child {
              border-right: none;
            }

            button {
              color: #fff;
              height: auto;
              background: #000;
            }
          }
        }
      }
    }

    .admin-user-school-middle-page {
      position: absolute;
      left: 0;
      right: 0;
      bottom: 0;
    }
  }

  .admin-user-school-bottom {
    margin: 20px 0 0 0;
    height: 305px;
    position: relative;

    h4 {
      color: #fff;
      padding: 5px 0 5px 3px;
      height: auto;
      background: #000;
    }

    table {
      text-align: center;
      margin: 10px auto;
      width: 1250px;
      height: auto;
      border: #000 solid 3px;
      thead {
        tr {
          height: auto;
          th {
            color: #fff;
            white-space: nowrap;
            padding: 0 5px;
            background: #000;
            border-right: #fff solid 3px;

            &:last-child {
              border-right: 0;
            }
          }
        }
      }

      tbody {
        tr {
          td {
            white-space: nowrap;
            padding: 0 5px;
            border-right: #000 3px solid;
            border-top: #000 3px solid;

            &:last-child {
              border-right: 0;
            }
            button {
              color: #fff;
              margin: 0 5px 0 0;
              width: auto;
              height: auto;
              background: #000;
            }
          }
        }
      }
    }

    .admin-user-school-bottom-page {
      position: absolute;
      left: 0;
      right: 0;
      bottom: 10px;
    }
  }
}
</style>