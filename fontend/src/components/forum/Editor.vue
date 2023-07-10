<!--
 * @Descripttion: 
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-24 20:41:54
 * @LastEditTime: 2022-11-11 11:20:56
-->
<template>
  <div class="editor">
    <quillEditor :options="options" v-model="content" />
    <button class="release" @click="toRelease">发布</button>
  </div>
</template>

<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import { quillEditor, Quill } from "vue-quill-editor";
import { ImageExtend, QuillWatch } from "quill-image-extend-module";
import { ImageDrop } from "quill-image-drop-module";

Quill.register("modules/ImageExtend", ImageExtend);
Quill.register("modules/imageDrop", ImageDrop);

const barOption = [
  ["bold", "italic", "underline", "strike"], // 加粗 斜体 下划线 删除线 -----['bold', 'italic', 'underline', 'strike']
  [{ header: 1 }, { header: 2 }], // 1、2 级标题-----[{ header: 1 }, { header: 2 }]
  [{ list: "ordered" }, { list: "bullet" }], // 有序、无序列表-----[{ list: 'ordered' }, { list: 'bullet' }]
  [{ script: "sub" }, { script: "super" }], // 上标/下标-----[{ script: 'sub' }, { script: 'super' }]
  [{ indent: "-1" }, { indent: "+1" }], // 缩进-----[{ indent: '-1' }, { indent: '+1' }]
  [{ size: [] }], // 字体大小-----[{ size: ['small', false, 'large', 'huge'] }]
  [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题-----[{ header: [1, 2, 3, 4, 5, 6, false] }]
  [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色-----[{ color: [] }, { background: [] }]
  [{ font: [] }], // 字体种类-----[{ font: [] }]
  [{ align: [] }], // 对齐方式-----[{ align: [] }]
  ["clean"], // 清除文本格式-----['clean']
  ["link", "image"], // 链接、图片、视频-----['link', 'image', 'video']
];

// 网络请求
// import { }

export default {
  props: {
    pContent: String,
  },
  components: {
    quillEditor,
  },
  data() {
    return {
      options: {
        modules: {
          // 工具栏配置
          toolbar: {
            container: barOption,
            handlers: {
              image: function () {
                QuillWatch.emit(this.quill.id);
              },
            },
          },
          ImageExtend: {
            loading: true,
            name: "file", // 图片参数名
            action: "/api/invitation/img", // 服务器地址
            response(res) {
              console.log(res);
              return res.data;
            },
            // 上传成功触发的回调函数
            success() {
              console.log("上传成功！");
            },
            // 上传失败触发的回调函数
            error() {
              console.log("上传失败");
            },
            accept: "image/png, image/webp, image/jepg, image/jpg, image/gif", // 设置文件类型
            headers: (xhr) => {
              console.log(xhr);
              xhr.setRequestHeader(
                "uId",
                JSON.parse(sessionStorage.getItem("user")).id
              );
            },
          },
          imageDrop: false,
        },
        placeholder: "请输入正文……",
      },
      content: "",
    };
  },
  methods: {
    toRelease() {
      if (!this.content) return;
      this.$emit("release", this.content);
    },
  },
  mounted() {
    if (this.pContent) this.content = this.pContent;
  },
};
</script>

<style lang="scss">
.editor {
  width: 100%;
  height: 62.5rem;
  position: relative;
  .quill-editor {
    display: flex;
    flex-direction: column;
    div.ql-toolbar {
      height: auto;
      display: flex;
      flex-wrap: wrap;
      align-content: flex-start;
      span.ql-formats {
        width: auto;
        height: auto;
        .ql-picker-options {
          width: auto;
          height: auto;
        }
      }
    }
    .ql-container {
      height: 100%;
      overflow-y: scroll;

      .ql-editor {
        height: 100%;
        &::-webkit-scrollbar {
          width: 0;
        }
      }

      ol,
      ul,
      li,
      p {
        font-size: 1.25rem;
        height: auto;
      }
      h1,
      h2,
      h3,
      h4,
      h5,
      h6 {
        height: auto;
      }
      img {
        margin: 0 auto;
        width: auto;
        height: auto;
        display: block;
      }
    }
  }

  button.release {
    color: #fff;
    font-size: 1.25rem;
    padding: 0.5rem 0.625rem;
    width: 5rem;
    height: auto;
    background: #000;
    position: absolute;
    right: 0;
    bottom: 0;
  }
}

.editor {
  line-height: normal !important;
}
.ql-snow .ql-tooltip[data-mode="link"]::before {
  content: "请输入链接地址:";
}
.ql-snow .ql-tooltip.ql-editing a.ql-action::after {
  border-right: 0px;
  content: "保存";
  padding-right: 0px;
}

.ql-snow .ql-tooltip[data-mode="video"]::before {
  content: "请输入视频地址:";
}

.ql-snow .ql-picker.ql-size .ql-picker-label::before,
.ql-snow .ql-picker.ql-size .ql-picker-item::before {
  content: "14px";
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="small"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="small"]::before {
  content: "10px";
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="large"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="large"]::before {
  content: "18px";
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="huge"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="huge"]::before {
  content: "32px";
}

.ql-snow .ql-picker.ql-header .ql-picker-label::before,
.ql-snow .ql-picker.ql-header .ql-picker-item::before {
  content: "文本";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
  content: "标题1";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
  content: "标题2";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
  content: "标题3";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
  content: "标题4";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
  content: "标题5";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
  content: "标题6";
}

.ql-snow .ql-picker.ql-font .ql-picker-label::before,
.ql-snow .ql-picker.ql-font .ql-picker-item::before {
  content: "标准字体";
}
.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="serif"]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="serif"]::before {
  content: "衬线字体";
}
.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="monospace"]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="monospace"]::before {
  content: "等宽字体";
}
</style>