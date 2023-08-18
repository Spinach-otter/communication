<template>
  <div class="right">
    <div class="find">
      <div class="mix input-group mb-3">
        <div class="select">
          <select class="form-select" v-model="op">
            <option value="1">姓名</option>
            <option value="2">账号</option>
            <option value="3">班级</option>
          </select>
        </div>
        <input
          type="text"
          class="form-control put"
          v-model="filter"
          placeholder="请查询校友关键词"
        />
        <button
          type="button"
          class="btn btn-outline-primary send"
          @click="refresh_user"
        >
          查询
        </button>
      </div>
    </div>

    <div class="list">
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">校友</th>
            <th scope="col">班级</th>
            <th scope="col">账号</th>

            <th scope="col">性别</th>
            <th scope="col">联系</th>
            <th scope="col">操作</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
          <tr v-for="userinfo in userlist" :key="userinfo.username">
            <td>{{ userinfo.fullname }}</td>
            <td>{{ userinfo.classname }}</td>
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
                  data-bs-toggle="modal"
                  :data-bs-target="'#update-user-btn-' + userinfo.username"
                  v-if="roleid === 3"
                  type="button"
                  class="btn btn-outline-primary"
                >
                  修改
                </button>
                <!-- Modal -->
                <div
                  class="modal fade"
                  :id="'update-user-btn-' + userinfo.username"
                  tabindex="-1"
                >
                  <div class="modal-dialog modal-xl">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title">修改信息</h5>
                        <button
                          type="button"
                          class="btn-close"
                          data-bs-dismiss="modal"
                          aria-label="Close"
                        ></button>
                      </div>
                      <div class="modal-body">
                        <div class="mb-3">
                          <label for="update-btn-full" class="form-label"
                            >姓名</label
                          >
                          <input
                            v-model="userupdate.fullname"
                            type="text"
                            class="form-control"
                            id="update-btn-full"
                            placeholder="请输入姓名"
                          />
                          <label for="update-btn-name" class="form-label"
                            >账号</label
                          >
                          <input
                            v-model="userupdate.newname"
                            type="text"
                            class="form-control"
                            id="update-btn-name"
                            placeholder="请输入账号"
                          />
                          <label for="update-btn-phone" class="form-label"
                            >联系</label
                          >
                          <input
                            v-model="userupdate.phone"
                            type="text"
                            class="form-control"
                            id="update-btn-phone"
                            placeholder="请输入联系方式"
                          />
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
                              <label
                                class="form-check-label"
                                for="radio1"
                              ></label>
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
                              <label
                                class="form-check-label"
                                for="radio2"
                              ></label>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="modal-footer">
                        <div class="error-message">
                          {{ userupdate.error_message }}
                        </div>
                        <button
                          type="button"
                          class="btn btn-primary"
                          @click="update_info(userinfo)"
                        >
                          修改
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
                  @click="user_del(userinfo)"
                  v-if="roleid === 3"
                  type="button"
                  class="btn btn-outline-primary"
                >
                  删除
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

export default {
  //test
  setup() {
    const store = useStore();
    let userlist = ref([]);
    let roleid = ref("");
    let op = ref("1");
    let filter = ref("");

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

    const refresh_user = () => {
      $.ajax({
        url: "http://localhost:8080/user/getlist/",
        type: "get",
        data: {
          op: op.value,
          filter: filter.value,
        },
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

    const user_del = (userinfo) => {
      $.ajax({
        url: "http://localhost:8080/user/del/",
        type: "post",
        data: {
          username: userinfo.username,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            refresh_user();
          }
        },
      });
    };

    const userupdate = reactive({
      newname: "",
      fullname: "",
      phone: "",
      sex: "",
      error_message: "",
    });

    //定义函数
    const update_info = (userinfo) => {
      userupdate.error_message = "";

      $.ajax({
        url: "http://localhost:8080/update/info/",
        type: "post",
        data: {
          username: userinfo.username,
          newname: userupdate.newname,
          fullname: userupdate.fullname,
          phone: userupdate.phone,
          sex: userupdate.sex,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },

        success(resp) {
          if (resp.error_message === "success") {
            Modal.getInstance("#update-user-btn-" + userinfo.username).hide();
            alert("修改成功");
            refresh_user();
          } else {
            userupdate.error_message = resp.error_message;
          }
        },
      });
    };

    return {
      roleid,
      userlist,
      op,
      filter,
      msgadd,
      refresh_user,
      msg_add,
      user_del,
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
</style>