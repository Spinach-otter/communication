<template>
  <div class="register">
    <div class="box">
      <p class="table">Register</p>
      <br />
      <div class="input-container">
        <input
          v-model="username"
          id="username"
          type="text"
          placeholder="账号"
        />
        <input
          v-model="password"
          id="password"
          type="password"
          placeholder="密码"
        />
        <input
          v-model="confirmedPassword"
          id="confirmedPassword"
          type="password"
          placeholder="确认密码"
        />
      </div>
      <div class="error-message">{{ error_message }}</div>
      <button type="submit" class="reset-button go" @click="register">
        Register
      </button>
      <!-- <a href="/" class="go">GO</a> -->
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import router from "@/router/index";
import $ from "jquery";

export default {
  setup() {
    let username = ref("");
    let password = ref("");
    let confirmedPassword = ref("");
    let error_message = ref("");

    const register = () => {
      $.ajax({
        url: "http://localhost:8080/user/account/register/",
        type: "post",
        data: {
          username: username.value,
          password: password.value,
          confirmedPassword: confirmedPassword.value,
        },
        success(resp) {
          if (resp.error_message === "success") {
            router.push({ name: "login" });
          } else {
            error_message.value = resp.error_message;
          }
        },
      });
    };

    return {
      username,
      password,
      confirmedPassword,
      error_message,
      register,
    };
  },
};
</script>


<style scoped>
body {
  height: 100vh;
}
.register {
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
  width: 400px;
  margin-bottom: 20px;
  outline: none;
  border: 0;
  padding: 10px;
  border-bottom: 3px solid rgb(150, 150, 240);
  background-color: transparent;
  font: 900 16px "";
}

.input-container {
  display: flex;
  flex-direction: column;
  align-items: center;
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