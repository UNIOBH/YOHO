<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-29 13:24:05
 * @LastEditTime: 2022-11-17 14:52:00
-->
<template>
  <div class="release-invitation">
    <main>
      <div class="release-invitation-title">
        <label for="release-invitation-title">帖子标题：</label>
        <input type="text" id="release-invitation-title" v-model="title" />
      </div>
      <editor
        @release="releaseInvitation"
        :pContent="pContent"
        v-if="pContent || isShow"
      />
    </main>
  </div>
</template>

<script>
// 组件
import Editor from "components/forum/Editor.vue";

// 网络请求
import {
  addInvitation,
  getRoughInvitation,
  updateContentById,
} from "api/invitation";

export default {
  name: "ReleaseInvitation",
  components: {
    Editor,
  },
  data() {
    return {
      title: "",
      pContent: null,
      iId: null,
      isShow: false,
    };
  },
  methods: {
    releaseInvitation(content) {
      if (!this.title) return;
      if (this.iId != 0) {
        // 修改
        updateContentById(this.iId, this.title, content).then((res) => {
          if (res.status == 200)
            this.$router.push(`/InvitationDetail/${this.iId}`);
        });
      } else {
        console.log("发布");
        // 发布
        addInvitation(
          this.title,
          content,
          JSON.parse(sessionStorage.getItem("user")).id
        ).then((res) => {
          if (res.status == 200) this.$router.push("/forum");
        });
      }
    },
  },
  created() {
    // 修改
    this.iId = this.$route.params.iId;
    if (this.iId) {
      getRoughInvitation(this.iId).then((res) => {
        if (res.status == 200) {
          this.title = res.data.title;
          this.pContent = res.data.details;
        } else this.isShow = true;
      });
    }
  },
};
</script>

<style lang="scss" scoped>
.release-invitation {
  min-height: 100%;
  width: 100%;
  height: auto;
  border: 0.3125rem solid #000;
  main {
    margin: 0 auto;
    width: 80%;
    height: 100%;

    .release-invitation-title {
      padding: 0.9375rem 0;
      height: auto;

      label {
        font: {
          size: 1.25rem;
        }
      }

      input {
        margin: 0.5rem 0 0 0;
        padding: 0.3125rem 0 0.3125rem 0.3125rem;
        border: 0.1875rem #000 solid;
        border-radius: 0.3125rem;
      }
    }
  }
}

@media screen and (max-width: 760px) {
  .release-invitation {
    main {
      width: 100%;
    }
  }
}
</style>