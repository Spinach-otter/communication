<template>
  <div class="right">
    <div class="find">
      <div class="classname">申请记录</div>
    </div>

    <div class="list">
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">同学</th>
            <th scope="col">账号</th>

            <th scope="col">性别</th>
            <th scope="col">联系</th>
            <th scope="col">操作</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
          <tr v-for="userinfo in userlist" :key="userinfo.username">
            <td>{{ userinfo.fullname }}</td>
            <td>{{ userinfo.username }}</td>
            <td>{{ userinfo.sex === 0 ? "女" : "男" }}</td>
            <td>{{ userinfo.phonenumber }}</td>
            <td>
              <div
                class="btn-group"
                role="group"
                aria-label="Basic outlined example"
              >
                <button
                  @click="yes(userinfo)"
                  type="button"
                  class="btn btn-outline-primary"
                >
                  同意
                </button>

                <button
                  @click="no(userinfo)"
                  type="button"
                  class="btn btn-outline-primary"
                >
                  拒绝
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>


<script>
import $ from "jquery";
import { useStore } from "vuex";
import { ref } from "vue";

export default {
  //test
  setup() {
    const store = useStore();
    let userlist = ref([]);
    let roleid = ref("");

    $.ajax({
      //得到权限信息
      url: "http://localhost:8080/user/role/",
      type: "get",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        roleid.value = resp;
        console.log(resp);
      },
    });

    const refresh_user = () => {
      $.ajax({
        url: "http://localhost:8080/apply/list/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          userlist.value = resp;
        },
      });
    };
    refresh_user();

    const yes = (userinfo) => {
      $.ajax({
        url: "http://localhost:8080/apply/yes/",
        type: "post",
        data: {
          username: userinfo.username,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            //console.log(userinfo.username);
            console.log(resp);
            refresh_user();
          }
        },
      });
    };

    const no = (userinfo) => {
      $.ajax({
        url: "http://localhost:8080/apply/no/",
        type: "post",
        data: {
          username: userinfo.username,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            console.log(resp);
            refresh_user();
          }
        },
      });
    };

    return {
      roleid,
      userlist,
      refresh_user,
      yes,
      no,
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
.find {
  width: 100%;
  height: 8%;
  background-color: white;
  border-radius: 10px;
  margin-top: 2%;
}
.list {
  /* display: flex; */
  width: 100%;
  /* height: 80%; */
  border-radius: 10px;
  background-color: white;
  /* margin-top: 10%; */
  overflow: hidden;
  margin-top: 3%;
}
.table {
  margin-left: 1%;
}
.mix {
  display: flex;
  height: 100%;
}
.select {
  width: 10%;
}
.form-select {
  height: 100%;
}
.put {
  width: 70%;
}
.send {
  width: 20%;
}
.radio-container {
  display: flex; /* 使用 Flex 布局 */
}
.error-message {
  color: red;
}
.classname {
  font-size: 20px;
  margin-left: 45%;
}
</style>