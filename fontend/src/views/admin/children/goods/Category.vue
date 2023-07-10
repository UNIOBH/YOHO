<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-31 21:10:45
 * @LastEditTime: 2022-11-17 13:39:31
-->
<template>
  <div class="admin-goods-category">
    <div class="admin-goods-category-search">
      <div class="admin-goods-category-search-id">
        <label for="admin-goods-category-search-id">类别id：</label>
        <input
          type="text"
          id="admin-goods-category-search-id"
          placeholder="请输入类别id……"
          v-model="cId"
        />
      </div>
      <div class="admin-goods-category-search-keyword">
        <label for="admin-goods-category-search-keyword">类别名称：</label>
        <input
          type="text"
          id="admin-goods-category-search-keyword"
          placeholder="请输入类别名称："
          v-model="cName"
        />
      </div>
      <input type="submit" value="查 询" />
    </div>
    <div class="admin-goods-category-show">
      <table cellspacing="0">
        <thead>
          <tr>
            <th>id</th>
            <th>名称</th>
            <th>是否已删除</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in categoryList" :key="item.cid">
            <td>{{ item.cid }}</td>
            <td>{{ item.cname }}</td>
            <td>{{ item.cdel ? "是" : "否" }}</td>
            <td>
              <button @click="editor(item.cid, item.cname)">编辑</button>
              <button @click="showDel(item.cid)">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
      <!-- 点击删除按钮时显示 -->
      <div class="amdin-goods-category-show-del" v-show="changeIsShow">
        <h4>将该类别的商品移入哪个类别？</h4>
        <div class="amdin-goods-category-show-del-select">
          <label for="amdin-goods-category-show-del-select">请选择类别：</label>
          <select id="amdin-goods-category-show-del-select" v-model="changeCId">
            <option value="0" disabled>——请选择——</option>
            <option
              :value="item.id"
              v-for="item in cList"
              :key="item.id"
              v-if="delCId != item.id"
            >
              {{ item.name }}
            </option>
          </select>
        </div>
        <div class="amdin-goods-category-show-del-button">
          <button @click="changeIsShow = false">取 消</button>
          <button @click="del">确 定</button>
        </div>
      </div>
      <page
        :total="page"
        class="admin-goods-category-page"
        @changeCurrent="toGetGoodsPage"
      />
    </div>
    <div class="admin-goods-category-bottom">
      <div class="admin-goods-category-bottom-alter">
        <div class="admin-goods-category-bottom-alter-old">
          <label for="admin-goods-category-bottom-alter-old">旧名称：</label>
          <input
            type="text"
            id="admin-goods-category-bottom-alter-old"
            disabled
            v-model="oldName"
          />
        </div>
        <div class="admin-goods-category-bottom-alter-new">
          <label for="admin-goods-category-bottom-alter-new">新名称：</label>
          <input
            type="text"
            id="admin-goods-category-bottom-alter-new"
            placeholder="请输入新名称……"
            v-model="newName"
          />
        </div>
        <input type="submit" value="修 改" @click="toUpdateName" />
      </div>
      <div class="admin-goods-category-bottom-add">
        <div>
          <label for="admin-goods-category-bottom-add-name">类别名称：</label>
          <input
            type="text"
            id="admin-goods-category-bottom-add-name"
            placeholder="请输入类别名称……"
            v-model="addCName"
          />
        </div>
        <input type="submit" value="添 加" @click="toAddCategory" />
      </div>
    </div>
  </div>
</template>

<script>
// 组件
import Page from "components/common/Page.vue";

// 网络请求
import { updateCategoryByIds } from "api/goods";
import {
  addCategory,
  getCategoryPage,
  delCategory,
  getAll,
  updateNameById,
} from "api/goods_category";

export default {
  data() {
    return {
      addCName: "",
      changeIsShow: false,
      current: 1, // 当前页数
      cId: 0,
      cName: "",
      categoryList: [],
      cList: [],
      changeCId: 0, // 删除类别后选择把该类别的商品移到哪个类别的id
      delCId: null, // 已被删除的类别id
      page: 0, // 页数
      oldName: "",
      newName: "",
      updateId: null,
    };
  },
  components: {
    Page,
  },
  methods: {
    toAddCategory() {
      if (!this.addCName) return;
      addCategory(this.addCName).then((res) => {
        if (res.status == 200) this.$router.go(0);
      });
    },
    toGetGoodsPage(current) {
      this.current = current;
      getCategoryPage(
        this.current,
        9,
        this.cName == "" ? " " : this.cName,
        this.cId
      ).then((res) => {
        if (res.status == 200) {
          this.categoryList = res.data.list;
          this.page = res.data.pages;
        } else if (res.status == 201) {
          this.categoryList = [];
          this.page = 0;
        }
      });
    },
    toDelCategory(id) {
      return delCategory(id).then((res) => {
        if (res.status == 200) console.log("删除成功");
      });
    },
    async del() {
      await this.toDelCategory(this.delCId);
      updateCategoryByIds(this.delCId, this.changeCId).then((res) => {
        if (res.status == 200) this.$router.go(0);
      });
    },
    showDel(id) {
      this.changeIsShow = true;
      this.delCId = id;
      getAll().then((res) => {
        if (res.status == 200) this.cList = res.data;
      });
    },
    editor(id, name) {
      this.updateId = id;
      this.oldName = name;
    },
    toUpdateName() {
      if (!this.newName || !this.updateId) return;
      updateNameById(this.updateId, this.newName).then((res) => {
        if (res.status == 200) this.$router.go(0);
      });
    },
  },
  created() {
    this.toGetGoodsPage(1);
  },
};
</script>

<style lang="scss" scoped>
.admin-goods-category {
  .admin-goods-category-search {
    padding: 20px 20px 0;
    height: auto;
    display: flex;
    align-items: center;

    div {
      margin: 0 10px 0 0;
      display: flex;
      align-items: center;

      label {
        width: 120px;
      }

      input {
        padding: 3px 0 3px 5px;
        border: 2px solid #000;
        outline: none;
      }
    }

    input[type="submit"] {
      color: #fff;
      padding: 3px 10px;
      width: auto;
      height: auto;
      background: #000;
    }
  }

  .admin-goods-category-show {
    padding: 10px 20px 0;
    height: 558px;
    position: relative;

    table {
      text-align: center;
      height: auto;
      border: 2px solid #000;

      thead {
        tr {
          color: #fff;
          background: #000;
          th {
            white-space: nowrap;
            padding: 5px 10px;
            height: auto;
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
            padding: 3px 5px;
            border-right: #000 solid 3px;
            border-top: #000 solid 3px;

            &:last-child {
              border-right: none;
            }

            button {
              color: #fff;
              width: auto;
              height: auto;
              background: #000;
            }
          }
        }
      }
    }

    .amdin-goods-category-show-del {
      padding: 10px 20px;
      width: auto;
      height: auto;
      background: #fff;
      border: 5px solid #000;
      border-radius: 10px;
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);

      .amdin-goods-category-show-del-select {
        margin: 15px 0;
        display: flex;
        align-items: center;

        label {
          font-size: 14px;
          flex: 70%;
        }

        select {
          border: 2px solid #000;
        }
      }

      .amdin-goods-category-show-del-button {
        display: flex;
        align-items: center;

        button {
          padding: 3px 40px;
          width: auto;
          height: auto;

          &:first-child {
            color: #000;
            margin: 0 17px 0 0;
            background: #fff;
          }

          &:last-child {
            color: #fff;
            background: #000;
          }
        }
      }
    }

    .admin-goods-category-page {
      position: absolute;
      left: 0;
      right: 0;
      bottom: 10px;
    }
  }
  .admin-goods-category-bottom {
    padding: 20px 30px 0;
    height: 140px;
    border-top: #000 3px solid;

    .admin-goods-category-bottom-alter {
      height: auto;
      display: flex;
      align-items: center;

      div {
        margin: 0 20px 0 0;
        height: auto;
        display: flex;
        align-items: center;
        label {
          flex: 15%;
        }

        input {
          font-size: 16px;
          padding: 5px 0 5px 5px;
          border: 2px solid #000;
          outline: none;
        }
      }

      input[type="submit"] {
        color: #fff;
        padding: 3px 25px;
        width: auto;
        height: auto;
        background: #000;
      }
    }

    .admin-goods-category-bottom-add {
      margin: 30px 0 0 0;
      height: auto;
      display: flex;
      align-content: center;
      div {
        margin: 0 80px 0 0;
        display: flex;
        align-items: center;
        label {
          flex: 10%;
        }

        input {
          padding: 5px 0 5px 5px;
          border: 2px solid #000;
        }
      }

      input[type="submit"] {
        color: #fff;
        padding: 5px 20px;
        width: auto;
        height: auto;
        background: #000;
      }
    }
  }
}
</style>