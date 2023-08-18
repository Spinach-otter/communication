<template>
  <div class="right">
    <div class="bg">
      <div class="nav-setting">
        <div class="set-normal">
          <router-link :to="{ name: 'settings' }" class="link"
            >信息</router-link
          >
        </div>
        <div class="set-pwd">
          <router-link :to="{ name: 'setting_pwd' }" class="link"
            >密码</router-link
          >
        </div>
      </div>
      <div class="put">
        <div class="container mt-3">
          <div class="mb-3">
            <label for="pwd" class="form-label">新密码:</label>
            <input
              v-model="userupdate.pwd"
              type="password"
              class="form-control"
              id="pwd"
              placeholder="请输入新密码"
              name="pswd"
            />
          </div>
          <div class="mb-3">
            <label for="newpwd" class="form-label">确认密码:</label>
            <input
              v-model="userupdate.newpwd"
              type="password"
              class="form-control"
              id="newpwd"
              placeholder="请确认密码"
              name="newpwd"
            />
          </div>
          <br />
          <div class="error_msg">{{ userupdate.error_message }}</div>
          <button
            @click="update_pwd"
            type="submit"
            class="btn btn-dark form-control"
          >
            Submit
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
import $ from "jquery";
import { useStore } from "vuex";
import router from "@/router/index";

export default {
  setup() {
    const store = useStore();
    // 定义一个userupdate对象
    const userupdate = reactive({
      pwd: "",
      newpwd: "",
      error_message: "",
    });

    //定义函数
    const update_pwd = () => {
      userupdate.error_message = "";
      $.ajax({
        url: "http://localhost:8080/setting/pwd/",
        type: "post",
        data: {
          pwd: userupdate.pwd,
          newpwd: userupdate.newpwd,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            (userupdate.pwd = ""), (userupdate.newpwd = "");
            store.dispatch("logout"), router.push({ name: "home" });
          } else {
            userupdate.error_message = resp.error_message;
          }
        },
      });
    };

    return {
      userupdate,
      update_pwd,
    };
  },
};
</script>

<style scoped>
.right {
  width: 1130px;
  height: 688px;
  float: right;
  margin-top: 5%;
  margin-right: 5%;
  border-radius: 10px;
  background-color: rgba(248, 249, 250, 0.8);
  /* background-color: #f8f9fa; */
  /* opacity: 0.8; */
}
.bg {
  width: 60%;
  height: 80%;
  margin-top: 5%;
  margin-left: 20%;
  background-color: white;
  /* background-color: rgb(252, 157, 154); */
  border-radius: 20px;
  opacity: 1;
}
.nav-setting {
  display: flex;
  margin-left: 38%;
  margin-top: 5%;
  width: 30%;
  height: 8%;
  background-color: rgb(87, 96, 105);
  border-radius: 10px;
}
.nav-setting .set-normal {
  display: flex;
  justify-content: center;
  align-items: center; /* 垂直居中 */
  width: 50%;
  height: 100%;
  background-color: rgb(173, 195, 192);
  border-radius: 10px;
}
.nav-setting .set-pwd {
  display: flex;
  justify-content: center;
  align-items: center; /* 垂直居中 */
  width: 50%;
  height: 100%;
  /* float: right; */
}
.link {
  color: black;
  font-size: 18px;
  text-decoration: none;
}

.put {
  margin-top: 8%;
}
.error_msg {
  color: red;
}
</style>