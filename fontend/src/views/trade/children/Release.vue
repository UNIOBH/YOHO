<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-29 12:21:07
 * @LastEditTime: 2022-11-17 14:00:35
-->
<template>
  <div class="release-goods">
    <main>
      <div class="release-goods-title">
        <div>
          <h3>商品名称：</h3>
          <input
            type="text"
            placeholder="请输入商品标题……"
            v-model="goods.name"
          />
        </div>
        <div>
          <h3>商品价格：</h3>
          <input
            type="text"
            placeholder="请输入商品的价格……"
            v-model.number="goods.price"
          />
        </div>
        <div>
          <h3>商品所在类别：</h3>
          <select v-model="cId">
            <option disabled value="0">——请选择商品类别——</option>
            <option
              v-for="item in categoryList"
              :key="item.id"
              :value="item.id"
            >
              {{ item.name }}
            </option>
          </select>
        </div>
      </div>
      <div class="release-goods-describe">
        <h3>商品描述：</h3>
        <textarea
          placeholder="请输入商品描述……"
          v-model="goods.describe"
        ></textarea>
      </div>
      <div class="release-goods-main-img">
        <h3>商品主图：</h3>
        <label for="release-goods-main-img">
          <img :src="mainImg" alt="" v-show="mainImg != ''" />
        </label>
        <input
          type="file"
          id="release-goods-main-img"
          @change="changeMainImg"
        />
      </div>
      <div class="release-goods-other-img">
        <h3>商品图片：</h3>
        <label for="release-goods-other-img1">
          <img :src="img1" alt="" v-show="img1 != ''" />
        </label>
        <input type="file" id="release-goods-other-img1" @change="changeImg1" />
        <label for="release-goods-other-img2">
          <img :src="img2" alt="" v-show="img2 != ''" />
        </label>
        <input type="file" id="release-goods-other-img2" @change="changeImg2" />
        <label for="release-goods-other-img3">
          <img :src="img3" alt="" v-show="img3 != ''" />
        </label>
        <input type="file" id="release-goods-other-img3" @change="changeImg3" />
      </div>
      <button @click="release">发布</button>
      <div class="release-goods-tip" v-show="tipIsShow">
        {{ tipMsg }} <button @click="tipIsShow = false">确定</button>
      </div>
    </main>
  </div>
</template>

<script>
// 网络请求
import { getAll } from "api/goods_category.js";
import { addGoods, getGoodsById, updateGoods } from "api/goods";
import { uploadMainImg, uploadImgs } from "api/goods_picture";

export default {
  name: "Release",
  data() {
    return {
      categoryList: [],
      mainImg: "",
      img1: "",
      img2: "",
      img3: "",
      gId: null,
      goods: {
        uId: null,
        name: "",
        price: "",
        describe: "",
      },
      cId: 0,
      goodsImg: {
        mainImg: null,
        imgs: [],
      },
      tipIsShow: false,
      tipMsg: "",
      modifyGoods: {},
    };
  },
  created() {
    this.goods.uId = JSON.parse(sessionStorage.getItem("user")).id;
    getAll().then((res) => {
      if (res.status == 200) this.categoryList = res.data;
    });
    if (this.$route.params.gId) {
      // 修改
      getGoodsById(this.$route.params.gId).then((res) => {
        if (res.status == 200) {
          this.goods.name = res.data.name;
          this.goods.price = res.data.price;
          this.cId = res.data.category.id;
          this.goods.describe = res.data.details;
          if (res.data.pictures) {
            if (res.data.pictures[0]) this.mainImg = res.data.pictures[0].img;
            if (res.data.pictures[1]) this.img1 = res.data.pictures[1].img;
            if (res.data.pictures[2]) this.img1 = res.data.pictures[2].img;
            if (res.data.pictures[3]) this.img1 = res.data.pictures[3].img;
          }
        }
      });
    }
  },
  methods: {
    /**
     * 发布商品
     */
    release() {
      if (!this.goods.name) {
        this.showTip("请输入商品名称！");
        return;
      } else if (!this.goods.price) {
        this.showTip("请填写商品价格！");
        return;
      } else if (!this.cId) {
        this.showTip("请选择商品所在类别！");
        return;
      } else if (!this.goods.describe) {
        this.showTip("请商品的描述信息！");
        return;
      }
      // 修改
      if (this.$route.params.gId != 0) {
        console.log(this.$route.params.gId);
        updateGoods(
          this.$route.params.gId,
          this.goods.name,
          this.goods.describe,
          this.cId,
          this.goods.price
        ).then((res) => {
          if (res.status == 200)
            this.$router.push(`/GoodsDetail/${this.$route.params.gId}`);
        });
      } else {
        // 发布
        let imgs = null;
        let mainImgs = null;
        if (!this.goodsImg.mainImg) {
          this.showTip("请上传商品主图！");
          return;
        } else if (this.goodsImg.mainImg) {
          mainImgs = mainImgs == null ? new FormData() : mainImgs;
          mainImgs.append("mainImg", this.goodsImg.mainImg);
        }
        if (this.goodsImg.imgs.length != 0) {
          imgs = imgs == null ? new FormData() : imgs;
          this.goodsImg.imgs.forEach((item) => imgs.append("imgs", item));
        }
        if (mainImgs == null && imgs == null) this.toAddGoods();
        else this.upload(mainImgs, imgs);
      }
    },
    changeMainImg(e) {
      if (this.checkImg(e)) {
        this.mainImg = window.URL.createObjectURL(e.target.files[0]);
        this.goodsImg.mainImg = e.target.files[0];
      }
    },
    changeImg1(e) {
      if (this.checkImg(e)) {
        this.img1 = window.URL.createObjectURL(e.target.files[0]);
        this.goodsImg.imgs[0] = e.target.files[0];
      }
    },
    changeImg2(e) {
      if (this.checkImg(e)) {
        this.img2 = window.URL.createObjectURL(e.target.files[0]);
        this.goodsImg.imgs[1] = e.target.files[0];
      }
    },
    changeImg3(e) {
      if (this.checkImg(e)) {
        this.img3 = window.URL.createObjectURL(e.target.files[0]);
        this.goodsImg.imgs[2] = e.target.files[0];
      }
    },
    showTip(msg) {
      this.tipIsShow = true;
      this.tipMsg = msg;
    },
    /**
     * 检查图片格式
     * @param {Object} e 事件对象
     */
    checkImg(e) {
      const type = e.target.files[0].type;
      if (
        type == "image/webp" ||
        type == "image/jpg" ||
        type == "image/png" ||
        type == "image/jpeg" ||
        type == "image/gif"
      )
        return true;
      else {
        this.showTip("只支持jpg、png、jpeg、gif、webp格式的图片！");
        return false;
      }
    },
    toAddGoods() {
      return addGoods({
        uId: this.goods.uId,
        price: this.goods.price,
        name: this.goods.name,
        describe: this.goods.describe,
        cId: this.cId,
      }).then((res) => {
        if (res.status == 200) this.gId = res.data.id;
      });
    },
    async upload(mainImg, imgs) {
      await this.toAddGoods();
      await this.uploadMainImg(mainImg);
      await this.uploadOtherImg(imgs);
      this.$router.push("/trade");
    },
    // 上传主图
    uploadMainImg(mainImg) {
      if (mainImg != null) {
        return uploadMainImg(mainImg, this.goods.uId, this.gId).then((res) => {
          if (res.status == 200) console.log("---上传成功---");
        });
      }
    },
    // 上传其他图片
    uploadOtherImg(imgs) {
      if (imgs != null) {
        return uploadImgs(imgs, this.goods.uId, this.gId).then((res) => {
          if (res.status == 200) console.log("---上传成功---");
        });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.release-goods {
  width: 100%;
  height: auto;
  border: solid #000 0.3125rem;
  position: relative;

  main {
    margin: 0 auto;
    padding: 1.25rem 0;
    width: 80%;

    h3 {
      margin: 0 0 0.5rem 0;
      height: auto;
    }

    input,
    select {
      font-size: 1.125rem;
      padding: 0 0 0 0.3125rem;
      height: 2rem;
      border: 0.1875rem solid #000;
      outline: none;
    }

    textarea {
      font-size: 1.125rem;
      word-break: break-all;
      padding: 0.3125rem;
      height: 7.5rem;
      border: 0.1875rem solid #000;
      resize: none;

      &::-webkit-scrollbar {
        width: 0;
      }
    }

    div {
      margin: 1.5625rem 0 0 0;
      height: auto;

      &.release-goods-main-img,
      &.release-goods-other-img {
        label {
          margin: 0 1.25rem 0.625rem 0;
          width: 9.375rem;
          height: 9.375rem;
          border: 0.25rem #000 dashed;
          display: inline-block;
          position: relative;

          &::before {
            content: "+";
            font-size: 3.125rem;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
          }
        }

        input {
          display: none;
        }

        img {
          width: calc(9.375rem - 0.5rem);
          height: calc(9.375rem - 0.5rem);
          background: #fff;
          position: absolute;
          left: 0;
          right: 0;
          top: 0;
          bottom: 0;
        }
      }
    }

    button {
      color: #fff;
      font-size: 1.25rem;
      margin: 1rem auto 0;
      padding: 0.625rem;
      width: auto;
      background: #000;
      border-radius: 0.625rem;
      display: block;
    }
  }

  .release-goods-tip {
    padding: 1.25rem;
    width: auto;
    height: auto;
    background: #fff;
    border: #000 solid 0.1875rem;
    border-radius: 0.9375rem;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    button {
      font: {
        size: 0.875rem;
      }
    }
  }
}
</style>