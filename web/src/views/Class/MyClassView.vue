<template>
  <div class="right">
    <div class="find">
      <div class="classname">{{ class_name }}</div>
      <hr />
      <div class="classinfo">{{ class_info }}</div>
    </div>

    <div class="list">
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">同学</th>
            <th scope="col">账号</th>

            <th scope="col">性别</th>
            <th scope="col">联系</th>
            <th scope="col">
              <button
                @click="apply"
                v-if="roleid !== 1"
                type="button"
                class="btn btn-outline-primary"
              >
                申请记录
              </button>
            </th>
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
                  data-bs-toggle="modal"
                  :data-bs-target="'#add-msg-btn-' + userinfo.username"
                  type="button"
                  class="btn btn-outline-primary"
                >
                  留言
                </button>

                <!-- Modal -->
                <div
                  class="modal fade"
                  :id="'add-msg-btn-' + userinfo.username"
                  tabindex="-1"
                >
                  <div class="modal-dialog modal-xl">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title">留言</h5>
                        <button
                          type="button"
                          class="btn-close"
                          data-bs-dismiss="modal"
                          aria-label="Close"
                        ></button>
                      </div>
                      <div class="modal-body">
                        <div class="mb-3">
                          <textarea
                            v-model="msgadd.messagecontent"
                            class="form-control"
                            id="add-msg-messagecontent"
                            rows="3"
                            placeholder="请输入留言"
                          ></textarea>
                        </div>
                      </div>
                      <div class="modal-footer">
                        <div class="error-message">
                          {{ msgadd.error_message }}
                        </div>
                        <button
                          type="button"
                          class="btn btn-primary"
                          @click="msg_add(userinfo)"
                        >
                          发送
                        </button>
                        <button
                          type="button"
                          class="btn btn-secondary"
                          data-bs-dismiss="modal"
                        >
                          取消
                        </button>
                      </div>
                    </div>
                  </div>
                </div>

                <button
                  @click="user_kick(userinfo)"
                  v-if="roleid !== 1"
                  type="button"
                  class="btn btn-outline-primary"
                >
                  踢出
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
import { ref, reactive } from "vue";
import { Modal } from "bootstrap/dist/js/bootstrap";
import router from "@/router/index";

export default {
  //test
  setup() {
    const store = useStore();
    let userlist = ref([]);
    let roleid = ref("");
    let class_name = ref("");
    let class_info = ref("");

    const msgadd = reactive({
      messagecontent: "",
      error_message: "",
    });

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
    $.ajax({
      //得到class信息
      url: "http://localhost:8080/myclass/info/",
      type: "get",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        class_name.value = resp.classname;
        class_info.value = resp.classinfo;
        console.log(resp);
      },
    });

    const refresh_user = () => {
      $.ajax({
        url: "http://localhost:8080/myclass/userlist/",
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

    const msg_add = (userinfo) => {
      msgadd.error_message = "";
      $.ajax({
        url: "http://localhost:8080/add/msg/",
        type: "post",
        data: {
          username: userinfo.username,
          messagecontent: msgadd.messagecontent,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            msgadd.messagecontent = "";
            Modal.getInstance("#add-msg-btn-" + userinfo.username).hide();
            alert("发送成功");
            //refresh_bots();
          } else {
            msgadd.error_message = resp.error_message;
          }
        },
      });
    };

    const user_kick = (userinfo) => {
      $.ajax({
        url: "http://localhost:8080/user/kick/",
        type: "post",
        data: {
          username: userinfo.username,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(userinfo.username);
          if (resp.error_message === "success") {
            alert("踢出成功");
            refresh_user();
          }
        },
      });
    };

    const apply = () => {
      router.push({ name: "my_class_apply" });
    };

    return {
      roleid,
      class_name,
      class_info,
      userlist,
      msgadd,
      refresh_user,
      msg_add,
      user_kick,
      apply,
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
  height: 20%;
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
.classinfo {
  margin-left: 5%;
}
</style>