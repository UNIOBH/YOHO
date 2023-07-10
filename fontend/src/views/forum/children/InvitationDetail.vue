<!-- 
 * @Descripttion: 帖子详情
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-25 16:35:53
 * @LastEditTime: 2022-12-07 14:35:34
-->
<template>
  <div class="invitation-detail">
    <YHHeader v-if="!admin" />
    <div class="invitation-detail-border">
      <main>
        <h2>{{ invitation.title }}</h2>
        <div class="invitation-detail-user">
          <img :src="invitation.user.avatar" />
          <span>{{ invitation.user.nickname }}</span>
          <router-link :to="to" tag="button" v-if="invitation.user.id == uId"
            >编辑</router-link
          >
          <button @click="toDel" v-if="invitation.user.id == uId">删除</button>
          <button
            v-if="invitation.user.id != uId && !admin"
            @click="hideIsShow = true"
          >
            添加好友
          </button>
        </div>
        <!-- 内容 start -->
        <article v-html="invitation.details" class="ql-editor"></article>
        <!-- 内容 end -->
        <aside>
          <div>
            编辑于<span>{{ getTime(invitation.lastTime) }}</span>
          </div>
          <div>
            <svg
              t="1666872300497"
              class="icon"
              viewBox="0 0 1024 1024"
              version="1.1"
              p-id="10739"
              width="32"
              height="32"
              @click="toAddLike"
            >
              <path
                d="M526.464 159.573333C572.586667 113.472 631.893333 85.333333 704 85.333333c155.221333 0 277.333333 126.869333 277.333333 305.408 0 107.093333-52.693333 196.138667-150.016 292.202667-20.949333 20.693333-84.970667 79.317333-87.850666 82.090667-9.066667 8.618667-18.837333 17.557333-29.290667 26.773333a1554.282667 1554.282667 0 0 1-41.301333 35.114667c-29.866667 24.533333-61.76 49.237333-93.632 72.917333a2607.744 2607.744 0 0 1-42.688 31.061333 42.666667 42.666667 0 0 1-49.109334 0 2607.744 2607.744 0 0 1-42.709333-31.04 2444.010667 2444.010667 0 0 1-93.610667-72.938666 1554.282667 1554.282667 0 0 1-41.301333-35.114667c-10.453333-9.216-20.224-18.154667-29.290667-26.773333-2.88-2.773333-66.901333-61.418667-87.850666-82.090667C95.381333 586.88 42.666667 497.856 42.666667 390.741333 42.666667 212.181333 164.778667 85.333333 320 85.333333c72.106667 0 131.413333 28.117333 177.536 74.24 5.184 5.184 10.005333 10.368 14.464 15.488 4.48-5.12 9.28-10.304 14.464-15.466666z m1.898667 671.786667A2360.746667 2360.746667 0 0 0 618.666667 761.002667a1469.802667 1469.802667 0 0 0 39.04-33.173334c9.664-8.533333 18.645333-16.746667 26.88-24.576 3.989333-3.797333 67.2-61.717333 86.784-81.024C853.952 540.672 896 469.674667 896 390.741333 896 258.624 811.349333 170.666667 704 170.666667c-47.765333 0-86.144 18.197333-117.205333 49.258666a202.325333 202.325333 0 0 0-27.946667 35.029334 129.728 129.728 0 0 0-7.872 13.909333c-15.04 33.749333-62.933333 33.749333-77.952 0a129.728 129.728 0 0 0-7.850667-13.909333 202.325333 202.325333 0 0 0-27.968-35.029334C406.144 188.864 367.765333 170.666667 320 170.666667c-107.349333 0-192 87.957333-192 220.074666 0 78.933333 42.026667 149.930667 124.629333 231.466667 19.562667 19.328 82.794667 77.248 86.784 81.045333 8.234667 7.829333 17.216 16.042667 26.88 24.576 12.074667 10.666667 25.130667 21.76 39.04 33.173334a2360.746667 2360.746667 0 0 0 106.666667 82.410666c5.205333-3.818667 10.666667-7.829333 16.362667-12.053333z"
                fill="#000000"
                p-id="10740"
              ></path>
            </svg>
            <span>{{ invitation.like }}</span>
          </div>
          <div>
            <svg
              width="25.000000"
              height="25.000000"
              viewBox="0 0 25 25"
              fill="none"
            >
              <g clip-path="url(#clip4_14)">
                <path
                  d="M12.5146 24.2187C12.0752 24.2187 11.6309 24.1943 11.1841 24.1454C6.2207 23.6059 1.49902 18.9672 0.876465 14.0185C0.41748 10.3686 1.6333 6.79437 4.21387 4.21381C6.79199 1.63324 10.3687 0.414978 14.0186 0.876404C18.96 1.49896 23.5962 6.21332 24.1455 11.1694C24.4409 13.8427 23.8574 16.433 22.4585 18.6816C22.7539 19.7582 23.5815 22.6122 23.5815 22.6122C23.6597 22.8857 23.584 23.1811 23.3838 23.3813C23.1836 23.5815 22.8882 23.6596 22.6147 23.579C22.6147 23.579 19.7534 22.7514 18.6963 22.4487C16.8237 23.6181 14.7119 24.2187 12.5146 24.2187ZM12.5073 2.34369C9.80957 2.34369 7.24365 3.39349 5.31738 5.31732C3.08105 7.55365 2.02637 10.6542 2.42676 13.8232C2.95654 18.0346 7.12891 22.1337 11.355 22.5927C13.7427 22.8539 16.0498 22.3071 18.0322 21.018C18.291 20.8495 18.6157 20.8032 18.9209 20.8886C19.3921 21.0229 20.6763 21.394 21.687 21.687C21.394 20.6689 21.0229 19.3701 20.8911 18.8793C20.813 18.5839 20.8643 18.269 21.0327 18.0126C22.3145 16.0351 22.854 13.728 22.5903 11.3427C22.1265 7.12152 18.0273 2.95648 13.8232 2.4267C13.3838 2.37054 12.9419 2.34369 12.5073 2.34369Z"
                  fill-rule="evenodd"
                  fill="#000000"
                />
                <path
                  d="M8.20312 13.2812C7.55615 13.2812 7.03125 12.7563 7.03125 12.1094C7.03125 11.4624 7.55615 10.9375 8.20312 10.9375C8.8501 10.9375 9.375 11.4624 9.375 12.1094C9.375 12.7563 8.84766 13.2812 8.20312 13.2812ZM12.8906 13.2812C12.2437 13.2812 11.7188 12.7563 11.7188 12.1094C11.7188 11.4624 12.2437 10.9375 12.8906 10.9375C13.5376 10.9375 14.0625 11.4624 14.0625 12.1094C14.0625 12.7563 13.5352 13.2812 12.8906 13.2812ZM17.5781 13.2812C16.9312 13.2812 16.4062 12.7563 16.4062 12.1094C16.4062 11.4624 16.9312 10.9375 17.5781 10.9375C18.2251 10.9375 18.75 11.4624 18.75 12.1094C18.75 12.7563 18.2227 13.2812 17.5781 13.2812Z"
                  fill-rule="evenodd"
                  fill="#000000"
                />
              </g>
              <defs>
                <clipPath id="clip4_14">
                  <rect width="25.000000" height="25.000000" fill="white" />
                </clipPath>
              </defs>
            </svg>
            <span>{{ invitation.ctotal }}</span>
          </div>
          <div>
            <svg
              t="1666872432996"
              class="icon"
              viewBox="0 0 1024 1024"
              version="1.1"
              p-id="11839"
              width="32"
              height="32"
            >
              <path
                d="M934.464 517.44c-14.08 0-29.696-6.4-39.04-20.864-120.32-177.92-253.056-272.448-384.192-272.448-131.2 0-262.4 94.592-382.72 272.448-14.016 22.4-43.712 27.264-65.536 12.8-21.888-14.4-26.56-44.8-12.544-67.264C192.64 233.792 347.2 128 511.232 128c163.968 0 320.128 105.792 460.736 314.112 14.08 22.4 9.344 51.2-12.544 67.264a42.432 42.432 0 0 1-24.96 8.064z"
                fill="#000000"
                p-id="11840"
              ></path>
              <path
                d="M511.232 810.688c-164.032 0-318.656-105.792-460.8-314.112a49.664 49.664 0 0 1 12.544-67.328 46.848 46.848 0 0 1 65.6 12.8c120.256 177.92 252.992 272.448 384.192 272.448 131.2 0 262.4-94.528 384.192-272.384 14.08-22.4 43.776-27.264 65.6-12.8 21.888 14.4 26.56 44.8 12.544 67.264-143.68 208.32-298.304 314.112-463.872 314.112z"
                fill="#000000"
                p-id="11841"
              ></path>
              <path
                d="M511.232 668.032c-106.24 0-193.664-89.728-193.664-198.72 0-108.928 87.424-198.656 193.664-198.656 106.24 0 193.664 89.728 193.664 198.656 0 108.992-85.888 198.72-193.664 198.72z m0-301.248c-54.656 0-99.968 46.464-99.968 102.528 0 56.128 45.312 102.592 99.968 102.592 54.656 0 99.968-46.464 99.968-102.592 0-56.064-43.776-102.528-99.968-102.528z"
                fill="#000000"
                p-id="11842"
              ></path>
            </svg>
            <span>{{ invitation.iread }}</span>
          </div>
        </aside>
        <div class="invitation-detail-comment">
          <div class="invitation-detail-comment-send" v-if="!admin">
            <img :src="getAvatar" />
            <input
              type="text"
              placeholder="评论千万条，友善第一条"
              v-model="comment"
            />
            <input
              type="submit"
              value="发 布"
              @click="releaseComment(comment, null, null)"
            />
          </div>
          <!-- 评论循环 start -->
          <div
            class="invitation-detail-comment-show"
            v-if="invitation.comments"
          >
            <div
              class="invitation-detail-comment-show-item"
              v-for="(comment, index) in invitation.comments"
              :key="comment.mid"
            >
              <div class="invitation-detail-comment-show-item-parent">
                <div class="invitation-detail-comment-show-item-parent-user">
                  <img :src="comment.user.avatar" />
                  <span>{{ comment.user.nickname }}</span>
                </div>
                <div class="invitation-detail-comment-show-item-parent-main">
                  <p>{{ comment.content }}</p>
                  <ul
                    class="invitation-detail-comment-show-item-parent-main-msg"
                  >
                    <li>{{ getTime(comment.time) }}</li>
                    <li
                      @click="
                        twoComment[index].isShow = !twoComment[index].isShow
                      "
                    >
                      <svg
                        width="14.000000"
                        height="14.000000"
                        viewBox="0 0 14 14"
                        fill="none"
                      >
                        <g clip-path="url(#clip4_55)">
                          <path
                            d="M14 7C14 3.13403 10.8662 0 7 0C3.1344 0 0 3.13403 0 7C0 10.866 3.13409 14 7 14C8.49545 14 9.87796 13.5278 11.0154 12.7289L13.3003 13.3L12.7292 11.0151C13.5776 9.81116 14.0012 8.47278 14 7ZM4.21591 8.00732C3.66763 8.00769 3.39346 7.73376 3.39346 7.18555C3.39346 6.63721 3.66763 6.36328 4.21591 6.36365C4.76343 6.36401 5.0372 6.63794 5.0372 7.18555C5.0372 7.73303 4.76343 8.00696 4.21591 8.00732ZM7.07956 8.00732C6.53125 8.00769 6.25711 7.73376 6.25711 7.18555C6.25711 6.63721 6.53125 6.36328 7.07956 6.36365C7.62708 6.36401 7.90085 6.63794 7.90085 7.18555C7.90085 7.73303 7.62708 8.00696 7.07956 8.00732ZM9.94318 8.00732C9.3949 8.00769 9.12076 7.73376 9.12076 7.18555C9.12076 6.63721 9.3949 6.36328 9.94318 6.36365C10.4907 6.36401 10.7645 6.63794 10.7645 7.18555C10.7645 7.73303 10.4907 8.00696 9.94318 8.00732Z"
                            fill-rule="evenodd"
                            fill="#000000"
                          />
                        </g>
                        <defs>
                          <clipPath id="clip4_55">
                            <rect
                              width="14.000000"
                              height="14.000000"
                              fill="white"
                            />
                          </clipPath>
                        </defs>
                      </svg>
                      <span>回复</span>
                    </li>
                    <li>
                      <svg
                        width="16.000000"
                        height="16.000000"
                        viewBox="0 0 16 16"
                        fill="none"
                        @click="toAddCommentLike(comment.mid)"
                      >
                        <g clip-path="url(#clip4_58)">
                          <path
                            d="M9.71362 6.37537C9.9733 5.21533 10.1546 4.37634 10.258 3.85669C10.5179 2.54834 9.8428 1.33337 8.50464 1.33337C7.29797 1.33337 6.69196 1.93298 6.33463 3.13171C6.12012 4.10132 5.78296 4.88806 5.34879 5.4259C4.86798 6.02295 4.24438 6.33008 3.47797 6.34717L3.13562 6.35449C2.31931 6.37207 1.66663 7.04407 1.66663 7.86719L1.66663 12.8334C1.66663 13.8458 2.4808 14.6667 3.48529 14.6667L9.93097 14.6667C11.2549 14.6667 12.4368 13.8297 12.886 12.5742L14.2249 8.83069C14.5656 7.87817 14.076 6.82751 13.1311 6.48401C12.9319 6.41162 12.7262 6.37537 12.5141 6.37537L9.71362 6.37537Z"
                            fill-rule="evenodd"
                            fill="#030303"
                          />
                        </g>
                        <defs>
                          <clipPath id="clip4_58">
                            <rect
                              width="16.000000"
                              height="16.000000"
                              fill="white"
                            />
                          </clipPath>
                        </defs>
                      </svg>
                      <span>{{ comment.like }}</span>
                    </li>
                  </ul>
                  <!-- 回复框 start -->
                  <div v-show="twoComment[index].isShow" v-if="!admin">
                    <input
                      type="text"
                      placeholder="回复"
                      v-model="twoComment[index].content"
                      
                    />
                    <input
                      type="submit"
                      value="提 交"
                      @click="
                        releaseComment(
                          twoComment[index].content,
                          comment.mid,
                          null
                        )
                      "
                    />
                  </div>
                  <!-- 回复框 end -->
                </div>
                <!-- 循环回复 start -->
                <div
                  class="invitation-detail-comment-show-item-reply"
                  v-for="(i, ii) in comment.children"
                  :key="i.content"
                >
                  <div
                    class="invitation-detail-comment-show-item-reply-user"
                    v-if="i"
                  >
                    <img :src="i.user.avatar" />
                    <span>{{ i.user.nickname }}</span>
                    <svg
                      width="16.000000"
                      height="7.000000"
                      viewBox="0 0 16 7"
                      fill="none"
                      v-if="i.toMId"
                    >
                      <g clip-path="url(#clip4_83)">
                        <path
                          d="M4.51239 0.448364L11.4877 3.5L4.51239 6.55176L4.51239 0.448364Z"
                          fill-rule="evenodd"
                          fill="#000000"
                        />
                      </g>
                      <defs>
                        <clipPath id="clip4_83">
                          <rect
                            width="16.000000"
                            height="7.000000"
                            fill="white"
                          />
                        </clipPath>
                      </defs>
                    </svg>
                    <span v-if="i.toMId">{{ i.toMId.user.nickname }}</span>
                  </div>
                  <div
                    class="invitation-detail-comment-show-item-reply-main"
                    v-if="i"
                  >
                    <p>{{ i.content }}</p>
                    <ul
                      class="invitation-detail-comment-show-item-reply-main-msg"
                    >
                      <li>{{ getTime(i.time) }}</li>
                      <li
                        @click="
                          threeComment[ii].isShow = !threeComment[ii].isShow
                        "
                      >
                        <svg
                          width="14.000000"
                          height="14.000000"
                          viewBox="0 0 14 14"
                          fill="none"
                        >
                          <g clip-path="url(#clip4_55)">
                            <path
                              d="M14 7C14 3.13403 10.8662 0 7 0C3.1344 0 0 3.13403 0 7C0 10.866 3.13409 14 7 14C8.49545 14 9.87796 13.5278 11.0154 12.7289L13.3003 13.3L12.7292 11.0151C13.5776 9.81116 14.0012 8.47278 14 7ZM4.21591 8.00732C3.66763 8.00769 3.39346 7.73376 3.39346 7.18555C3.39346 6.63721 3.66763 6.36328 4.21591 6.36365C4.76343 6.36401 5.0372 6.63794 5.0372 7.18555C5.0372 7.73303 4.76343 8.00696 4.21591 8.00732ZM7.07956 8.00732C6.53125 8.00769 6.25711 7.73376 6.25711 7.18555C6.25711 6.63721 6.53125 6.36328 7.07956 6.36365C7.62708 6.36401 7.90085 6.63794 7.90085 7.18555C7.90085 7.73303 7.62708 8.00696 7.07956 8.00732ZM9.94318 8.00732C9.3949 8.00769 9.12076 7.73376 9.12076 7.18555C9.12076 6.63721 9.3949 6.36328 9.94318 6.36365C10.4907 6.36401 10.7645 6.63794 10.7645 7.18555C10.7645 7.73303 10.4907 8.00696 9.94318 8.00732Z"
                              fill-rule="evenodd"
                              fill="#000000"
                            />
                          </g>
                          <defs>
                            <clipPath id="clip4_55">
                              <rect
                                width="14.000000"
                                height="14.000000"
                                fill="white"
                              />
                            </clipPath>
                          </defs>
                        </svg>
                        <span>回复</span>
                      </li>
                      <li>
                        <svg
                          width="16.000000"
                          height="16.000000"
                          viewBox="0 0 16 16"
                          fill="none"
                        >
                          <g clip-path="url(#clip4_58)">
                            <path
                              d="M9.71362 6.37537C9.9733 5.21533 10.1546 4.37634 10.258 3.85669C10.5179 2.54834 9.8428 1.33337 8.50464 1.33337C7.29797 1.33337 6.69196 1.93298 6.33463 3.13171C6.12012 4.10132 5.78296 4.88806 5.34879 5.4259C4.86798 6.02295 4.24438 6.33008 3.47797 6.34717L3.13562 6.35449C2.31931 6.37207 1.66663 7.04407 1.66663 7.86719L1.66663 12.8334C1.66663 13.8458 2.4808 14.6667 3.48529 14.6667L9.93097 14.6667C11.2549 14.6667 12.4368 13.8297 12.886 12.5742L14.2249 8.83069C14.5656 7.87817 14.076 6.82751 13.1311 6.48401C12.9319 6.41162 12.7262 6.37537 12.5141 6.37537L9.71362 6.37537Z"
                              fill-rule="evenodd"
                              fill="#030303"
                            />
                          </g>
                          <defs>
                            <clipPath id="clip4_58">
                              <rect
                                width="16.000000"
                                height="16.000000"
                                fill="white"
                              />
                            </clipPath>
                          </defs>
                        </svg>
                        <span>{{ i.like }}</span>
                      </li>
                    </ul>
                    <!-- 回复框 (切换) start -->
                    <div v-show="threeComment[ii].isShow" v-if="!admin">
                      <input
                        type="text"
                        placeholder="回复"
                        v-model="threeComment[ii].content"
                      />
                      <input
                        type="submit"
                        value="提 交"
                        @click="
                          releaseComment(threeComment[ii].content, i.pid, i.mid)
                        "
                      />
                    </div>
                    <!-- 回复框 end -->
                  </div>
                </div>
                <!-- 循环回复 end -->
              </div>
            </div>
          </div>
          <div class="invitation-detail-comment-show" v-else>
            <h3>暂无评论，快来发布第一条吧！</h3>
          </div>
        </div>
        <div class="invitation-detail-hide" v-show="hideIsShow">
          <textarea placeholder="请输入验证信息……" v-model="hideMsg"></textarea>
          <div class="invitation-detal-hide-button">
            <button @click="makeHideHide">取消</button
            ><button @click="toAddFriend">确定</button>
          </div>
        </div>
      </main>
    </div>
    <YHFooter />
  </div>
</template>

<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";

// 组件
import YHHeader from "components/common/YHHeader.vue";
import YHFooter from "components/common/YHFooter.vue";

// 网络请求
import {
  getInvitationById,
  del,
  addReadCount,
  addLike,
} from "api/invitation.js";
import { addComment, addCommentLike } from "api/comment";
import { addFriend } from "api/friend";

// utils
import { intercept } from "utils/time";

let timer;

export default {
  data() {
    return {
      uId: null,
      invitation: {}, // 帖子详情
      comment: "", // 评论内容
      twoComment: [],
      threeComment: [],
      to: "",
      admin: null,
      hideIsShow: false,
      hideMsg: "",
    };
  },
  components: {
    YHHeader,
    YHFooter,
  },
  created() {
    const user = JSON.parse(sessionStorage.getItem("user"));
    if (user && user.type == "user") this.uId = user.id;
    else if (user) this.admin = user;
    getInvitationById(this.$route.params.iId).then((res) => {
      if (res.status == 200) {
        console.log(res);
        this.invitation = res.data;
        this.to = `/ReleaseInvitation/${this.invitation.iid}`;
        for (let i = 0; i < res.data.comments.length; i++) {
          this.twoComment.push({ isShow: false, content: "" });
          for (let j = 0; j < res.data.comments[i].children.length; j++)
            this.threeComment.push({ isShow: false, content: "" });
        }
      }
    });
  },
  methods: {
    getTime(str) {
      return intercept(str);
    },
    /**
     * 发布评论
     * @param {Number} iId 帖子id
     * @param {String} content 评论内容
     * @param {Number} pId 父评论id
     * @param {Number} toMId 回复评论id
     */
    releaseComment(content, pId, toMId) {
      if (!content) return;
      console.log(content);
      addComment({
        iId: this.invitation.iid,
        uId: this.uId,
        content,
        pId,
        toMId,
      }).then((res) => {
        if (res.status == 200) this.$router.go(0);
      });
    },
    toDel() {
      del([this.invitation.iid]).then((res) => {
        if (res.status == 200) this.$router.push("/forum");
      });
    },
    toAddLike() {
      console.log(this.uId, parseInt(this.$route.params.iId));
      addLike(parseInt(this.$route.params.iId), this.uId).then((res) => {
        if (res.status == 200) this.$router.go(0);
        else console.log(res);
      });
    },
    toAddCommentLike(cId) {
      addCommentLike(cId, this.uId).then((res) => {
        if (res.status == 200) this.$router.go(0);
      });
    },
    toAddFriend() {
      if (!this.hideMsg) return;
      addFriend({
        uId: this.uId,
        fUId: this.invitation.user.id,
        msg: this.hideMsg,
      }).then((res) => {
        if (res.status == 200) {
          this.hideIsShow = false;
          this.hideMsg = "";
        } else this.hideMsg = "添加失败！";
      });
    },
    makeHideHide() {
      this.hideIsShow = false;
      this.hideMsg = "";
    },
  },
  computed: {
    getAvatar() {
      return JSON.parse(sessionStorage.getItem("user")).avatar;
    },
  },
  mounted() {
    let counter = 0;
    timer = setInterval(() => {
      if (counter >= 10) {
        clearInterval(timer);
        addReadCount(this.invitation.iid);
      }
      counter++;
    }, 1000);
  },
  destroyed() {
    clearInterval(timer);
  },
};
</script>

<style lang="scss" scoped>
.invitation-detail {
  height: auto;
  .invitation-detail-border {
    padding: 2.5rem 0;
    border: 0.3125rem solid #000;
    border-top: none;
    border-bottom: none;

    main {
      margin: 0 auto;
      min-height: 100%;
      width: 60%;
      height: auto;
      position:relative h2 {
        height: auto;
      }

      .invitation-detail-user {
        margin: 0.625rem 0;
        height: auto;
        display: flex;
        align-items: center;

        img {
          margin: 0 0.3125rem 0 0;
          width: 3rem;
          height: 3rem;
          border-radius: 50%;
          flex: 1;
        }

        span {
          height: auto;
        }

        button {
          color: #fff;
          font-size: 1rem;
          margin: 0 0 0 0.625rem;
          padding: 0.625rem;
          height: auto;
          background: #000;
          flex: 30%;
        }
      }

      article {
        p {
          img {
            width: auto !important;
            height: auto !important;
            max-width: 100% !important;
            display: block !important;
          }
        }
      }

      aside {
        font-size: 0.75rem;
        margin: 0.625rem 0;
        display: flex;
        align-items: center;

        div {
          svg {
            margin: 0 0.3125rem 0 0;
            width: 1rem;
            height: 1rem;
          }

          &:nth-child(n + 2) {
            display: flex;
            align-items: center;
          }
        }
      }

      .invitation-detail-comment {
        .invitation-detail-comment-send {
          margin: 0.625rem 0;
          height: auto;
          display: flex;
          align-items: center;
          img {
            margin: 0 0.3125rem 0 0;
            width: 1.9rem;
            height: 1.9rem;
          }

          input {
            height: 1.9rem;
            outline: none;

            &[type="text"] {
              padding: 0 0 0 0.3125rem;
              border: 0.1875rem solid #000;
            }

            &[type="submit"] {
              color: #fff;
              background: #000;
              flex: 20%;
            }
          }
        }

        .invitation-detail-comment-show {
          padding: 0.625rem;
          border: #000 solid 0.1875rem;
          .invitation-detail-comment-show-item {
            .invitation-detail-comment-show-item-parent {
              .invitation-detail-comment-show-item-parent-user {
                img {
                  margin: 0 0.3125rem 0 0;
                  width: 1.875rem;
                  height: 1.875rem;
                  vertical-align: middle;
                }
              }
              .invitation-detail-comment-show-item-parent-main {
                padding: 0.4rem 0 0.4rem 1.875rem;

                ul.invitation-detail-comment-show-item-parent-main-msg {
                  font-size: 0.75rem;
                  margin: 0.3125rem 0;
                  display: flex;
                  align-items: center;

                  svg {
                    margin: 0 0.2rem 0 0;
                    width: 1rem;
                    height: 1rem;
                  }

                  li {
                    margin: 0 0.625rem 0 0;
                    width: auto;
                    &:nth-child(n + 2) {
                      display: flex;
                      align-items: center;
                    }
                  }
                }

                div {
                  display: flex;

                  input {
                    font-size: 0.9rem;
                    padding: 0.3125rem 0 0.3125rem 0;
                    outline: none;

                    &[type="text"] {
                      padding: 0.3125rem 0 0.3125rem 0.3125rem;
                      border: 0.125rem solid #000;
                    }

                    &[type="submit"] {
                      color: #fff;
                      background: #000;
                      flex: 30%;
                    }
                  }
                }
              }

              .invitation-detail-comment-show-item-reply {
                padding: 0 0 0 1.875rem;

                .invitation-detail-comment-show-item-reply-user {
                  display: flex;
                  align-items: center;
                  img {
                    margin: 0 0.3125rem 0 0;
                    width: 1.875rem;
                    height: 1.875rem;
                  }
                  svg {
                    width: 1.5rem;
                    height: 1.5rem;
                  }
                  span {
                    width: auto;
                  }
                }

                .invitation-detail-comment-show-item-reply-main {
                  padding: 0.4rem 0 0.4rem 1.875rem;

                  ul.invitation-detail-comment-show-item-reply-main-msg {
                    font-size: 0.75rem;
                    margin: 0.3125rem 0;
                    display: flex;
                    align-items: center;

                    svg {
                      margin: 0 0.2rem 0 0;
                      width: 1rem;
                      height: 1rem;
                    }

                    li {
                      margin: 0 0.625rem 0 0;
                      width: auto;
                      &:nth-child(n + 2) {
                        display: flex;
                        align-items: center;
                      }
                    }
                  }

                  div {
                    display: flex;

                    input {
                      font-size: 0.9rem;
                      padding: 0.3125rem 0 0.3125rem 0;
                      outline: none;

                      &[type="text"] {
                        padding: 0.3125rem 0 0.3125rem 0.3125rem;
                        border: 0.125rem solid #000;
                      }

                      &[type="submit"] {
                        color: #fff;
                        background: #000;
                        flex: 30%;
                      }
                    }
                  }
                }
              }

              .invitation-detail-comment-show-item-reply-more {
                font-size: 0.7rem;
                padding: 0 0 0 1.875rem;
                height: auto;
                svg {
                  width: 1rem;
                  height: 1rem;
                  vertical-align: middle;
                }
              }
            }
          }
        }
      }

      .invitation-detail-hide {
        padding: 1.25rem 0.625rem;
        width: auto;
        height: auto;
        background: #fff;
        border: 0.1875rem solid #000;
        border-radius: 0.625rem;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);

        textarea {
          padding: 0.3125rem;
          border: 0.125rem solid #000;
          outline: none;
          resize: none;
        }

        .invitation-detal-hide-button {
          margin: 0.5rem 0 0 0;
          display: flex;
          button {
            color: #000;
            padding: 0.1rem 0;
            background: #fff;

            &:last-child {
              color: #fff;
              margin: 0 0 0 0.3125rem;
              background: #000;
            }
          }
        }
      }
    }
  }
}

@media screen and (max-width: 1105px) {
  .invitation-detail {
    .invitation-detail-border {
      height: auto;
      main {
        width: 85%;
        .invitation-detail-user {
          button {
            flex: 31%;

            &:last-child {
              flex: 57%;
            }
          }
        }
        aside {
          div {
            &:first-child {
              flex: 300%;
            }
          }
        }
      }
    }
  }
}
</style>