<template>
  <div class="Login">
    <div class="box">
      <p class="table">Login</p>
      <br />
      <input v-model="username" type="text" id="username" placeholder="账号" />
      <input
        v-model="password"
        type="password"
        id="password"
        placeholder="密码"
      />
      <div class="error-message">{{ error_message }}</div>
      <!-- <br /> -->
      <button type="submit" class="reset-button go" @click="login">GO</button>
      <a href="/register/" class="go">Register</a>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { ref } from "vue";
import router from "@/router/index";

export default {
  setup() {
    const store = useStore();
    let username = ref("");
    let password = ref("");
    let error_message = ref("");

    const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
      store.commit("updateToken", jwt_token);
      store.dispatch("getinfo", {
        success() {
          router.push({ name: "home" });
        },
        error() {},
      });
    } else {
    }

    const login = () => {
      error_message.value = "";
      store.dispatch("login", {
        username: username.value,
        password: password.value,
        success() {
          store.dispatch("getinfo", {
            success() {
              router.push({ name: "home" });
            },
          });
          router.push({ name: "home" });
        },
        error() {
          error_message.value = "用户名或密码错误";
        },
      });
    };

    return {
      username,
      password,
      error_message,
      login,
    };
  },
};
</script>


<style scoped>
body {
  height: 100vh;
}
.Login {
  width: 550px;
  height: 450px;
  display: flex;
  border-radius: 15px;
  justify-content: center;
  align-items: center;
  margin-left: 30%;
  margin-top: 150px;
  background: linear-gradient(
    to right bottom,
    rgba(255, 255, 255, 0.7),
    rgba(255, 255, 255, 0.5),
    rgba(255, 255, 255, 0.4)
  );
  /* 使背景模糊化 */
  backdrop-filter: blur(10px);
  box-shadow: 0 0 20px #a29bfe;
}
::v-deep .table {
  font: 900 40px "";
  text-align: center;
  letter-spacing: 5px;
  color: #3d3d3d;
}
::v-deep .box {
  overflow: hidden;
}

::v-deep .box input {
  width: 100%;
  margin-bottom: 20px;
  outline: none;
  border: 0;
  padding: 10px;
  border-bottom: 3px solid rgb(150, 150, 240);
  background-color: transparent;
  font: 900 16px "";
}
::v-deep .reset-button {
  padding: 0;
  border: none;
  background: none;
  cursor: pointer;
  /* 还可以添加其他重置样式 */
}

::v-deep .go {
  width: 400px;
  height: auto; /* 将固定高度改为自动高度 */
  padding: 8px 12px; /* 调整内边距 */
  text-align: center;
  display: block;
  font: 900 20px "";
  border-radius: 10px;
  margin-top: 20px;
  color: #fff;
  letter-spacing: 3px;
  background-image: linear-gradient(to left, #fd79a8, #a29bfe);
  text-decoration: none;
}

::v-deep .error-message {
  color: red;
}
</style>