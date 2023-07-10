/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-01 11:25:05
 * @LastEditTime: 2022-11-17 10:21:54
 */
import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "",
    redirect: "/home",
  },
  {
    path: "/home",
    component: () => import("views/home/Home"),
  },
  {
    path: "/login",
    component: () => import("views/login/Login"),
  },
  {
    path: "/forget",
    component: () => import("views/login/children/Forget"),
  },
  {
    path: "/forum",
    component: () => import("views/forum/Forum"),
  },
  {
    path: "/profile",
    component: () => import("views/profile/Profile"),
    children: [
      {
        path: "/profile",
        redirect: "/profile/information",
      },
      {
        path: "information",
        component: () => import("views/profile/children/Information"),
      },
      {
        path: "password",
        component: () => import("views/profile/children/Password"),
      },
      {
        path: "message",
        component: () => import("views/profile/children/Message"),
      },
      {
        path: "friends",
        component: () => import("views/profile/children/Friends"),
      },
      {
        path: "apply",
        component: () => import("views/profile/children/Apply"),
      },
      {
        path: "alter",
        component: () => import("views/profile/children/Alter"),
      },
      {
        path: "like",
        component: () => import("views/profile/children/Like"),
      },
      {
        path: "goods",
        component: () => import("views/profile/children/Goods"),
      },
      {
        path: "posts",
        component: () => import("views/profile/children/Posts"),
      },
    ],
  },
  {
    path: "/trade",
    component: () => import("views/trade/Trade"),
  },
  {
    path: "/register",
    component: () => import("views/register/Register"),
  },
  {
    path: "/recommend",
    component: () => import("views/recommend/Recommend"),
  },
  {
    path: "/chat/:toId/:nickname/:remake",
    component: () => import("views/chat/Chat"),
  },
  {
    path: "/InvitationDetail/:iId",
    component: () => import("views/forum/children/InvitationDetail"),
  },
  {
    path: "/GoodsDetail/:gId",
    component: () => import("views/trade/children/GoodsDetail"),
  },
  {
    path: "/ReleaseGoods/:gId",
    component: () => import("views/trade/children/Release"),
  },
  {
    path: "/ReleaseInvitation/:iId",
    component: () => import("views/forum/children/Release"),
  },
  {
    path: "/admin",
    component: () => import("views/admin/Admin"),
    children: [
      {
        path: "/admin",
        redirect: "/admin/user",
      },
      {
        path: "user",
        component: () => import("views/admin/children/user/User"),
      },
      {
        path: "school",
        component: () => import("views/admin/children/user/School"),
      },
      {
        path: "search",
        component: () => import("views/admin/children/goods/Search"),
      },
      {
        path: "alter",
        component: () => import("views/admin/children/goods/Alter"),
      },
      {
        path: "category",
        component: () => import("views/admin/children/goods/Category"),
      },
      {
        path: "invitation",
        component: () => import("views/admin/children/invitation/Invitation"),
      },
      {
        path: "modify",
        component: () => import("views/admin/children/invitation/Modify"),
      },
      {
        path: "register",
        component: () => import("views/admin/children/profile/Register"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  const user = JSON.parse(sessionStorage.getItem("user"));
  if (
    (to.path == "/forum" ||
      to.path.includes("/profile") ||
      to.path == "/trade" ||
      to.path == "/recommend" ||
      to.path.includes("/ReleaseGoods") ||
      to.path.includes("/ReleaseInvitation") ||
      to.path.includes("/chat")) &&
    (to.path != "/login" || to.path != "/register")
  ) {
    try {
      const token = user.token;
      if (token) next();
      else next({ path: "/login" });
    } catch (error) {
      next({ path: "/login" });
    }
  } else if (
    to.path.includes("/admin") &&
    (to.path != "/login" || to.path != "/register")
  ) {
    if (!user) next({ path: "/login" });
    if (user.type == "user") next({ path: "/login" });
    else if (user.type == "admin") next();
  } else if (
    (to.path.includes("/InvitationDetail") ||
      to.path.includes("/GoodsDetail")) &&
    (to.path != "/login" || to.path != "/register")
  ) {
    if (user) next();
    else next({ path: "/login" });
  } else next();
});

export default router;
