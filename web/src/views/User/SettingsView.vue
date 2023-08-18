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
          <div class="mb-3 mt-3">
            <label for="username" class="form-label">用户名:</label>
            <input
              v-model="userupdate.username"
              type="text"
              class="form-control"
              id="username"
              placeholder="输入用户名"
              name="username"
            />
          </div>
          <div class="mb-3">
            <label for="major" class="form-label">专业:</label>
            <input
              v-model="userupdate.major"
              type="text"
              class="form-control"
              id="major"
              placeholder="输入专业"
              name="major"
            />
          </div>
          <div class="mb-3 mt-3">
            <label for="name" class="form-label">姓名:</label>
            <input
              v-model="userupdate.fullname"
              type="text"
              class="form-control"
              id="name"
              placeholder="输入姓名"
              name="name"
            />
          </div>
          <div class="radio-container">
            性别:&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="form-check">
              <input
                v-model="userupdate.sex"
                type="radio"
                class="form-check-input"
                id="radio1"
                name="optradio"
                value="1"
                checked
              />男
              <label class="form-check-label" for="radio1"></label>
            </div>
            <div class="form-check">
              <input
                v-model="userupdate.sex"
                type="radio"
                class="form-check-input"
                id="radio2"
                name="optradio"
                value="0"
              />女
              <label class="form-check-label" for="radio2"></label>
            </div>
          </div>
          <div class="mb-3 mt-3">
            <label for="tel" class="form-label">联系方式:</label>
            <input
              v-model="userupdate.phone"
              type="text"
              class="form-control"
              id="tel"
              placeholder="输入联系方式"
              name="tel"
            />
          </div>
          <br />
          <div class="error_msg">{{ userupdate.error_message }}</div>
          <button
            @click="update_info"
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

export default {
  setup() {
    const store = useStore();
    // 定义一个userupdate对象
    const userupdate = reactive({
      username: "",
      major: "",
      fullname: "",
      phone: "",
      sex: "",
      error_message: "",
    });

    //定义函数
    const update_info = () => {
      userupdate.error_message = "";
      $.ajax({
        url: "http://localhost:8080/setting/info/",
        type: "post",
        data: {
          username: userupdate.username,
          major: userupdate.major,
          fullname: userupdate.fullname,
          phone: userupdate.phone,
          sex: userupdate.sex,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            (userupdate.username = ""),
              (userupdate.major = ""),
              (userupdate.fullname = ""),
              (userupdate.phone = ""),
              (userupdate.sex = "");
          } else {
            userupdate.error_message = resp.error_message;
          }
        },
      });
    };

    return {
      userupdate,
      update_info,
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
  margin-top: 5%;
}

.radio-container {
  display: flex; /* 使用 Flex 布局 */
}

.form-check {
  display: inline-block;
  margin-right: 10px; /* 可以根据需要调整间距 */
}

.error_msg {
  color: red;
}
</style>