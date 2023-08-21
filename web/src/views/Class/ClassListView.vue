<template>
  <div class="right">
    <div class="find">
      <div class="mix input-group mb-3">
        <input
          type="text"
          class="form-control put"
          v-model="filter"
          placeholder="请查询班级名关键词"
        />
        <button
          type="button"
          class="btn btn-outline-primary send"
          @click="refresh_class"
        >
          查询
        </button>
      </div>
    </div>

    <div class="list">
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">班级名称</th>
            <th scope="col">班级信息</th>

            <th scope="col">
              <button
                data-bs-toggle="modal"
                data-bs-target="#add-class-btn-"
                type="button"
                class="btn btn-outline-primary"
              >
                创建班级
              </button>

              <!-- Modal -->
              <div class="modal fade" id="add-class-btn-" tabindex="-1">
                <div class="modal-dialog modal-xl">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title">创建班级</h5>
                      <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                      ></button>
                    </div>
                    <div class="modal-body">
                      <div class="mb-3">
                        <input
                          v-model="classadd.classname"
                          type="text"
                          class="form-control"
                          id="update-btn-full"
                          placeholder="请输入班级名"
                        />
                        <textarea
                          v-model="classadd.classinfo"
                          class="form-control"
                          id="add-msg-classinfo"
                          rows="3"
                          placeholder="请输入班级简介"
                        ></textarea>
                      </div>
                    </div>
                    <div class="modal-footer">
                      <div class="error-message">
                        {{ classadd.error_message }}
                      </div>
                      <button
                        type="button"
                        class="btn btn-primary"
                        @click="class_add(classofinfo)"
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
            </th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
          <tr v-for="classofinfo in classlist" :key="classofinfo.classname">
            <td>{{ classofinfo.classname }}</td>
            <td>{{ classofinfo.classname }}</td>
            <td>
              <div
                class="btn-group"
                role="group"
                aria-label="Basic outlined example"
              >
                <button
                  @click="join(classofinfo)"
                  type="button"
                  class="btn btn-outline-primary"
                >
                  申请加入
                </button>

                <button
                  @click="class_del(classofinfo)"
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
    let classlist = ref([]);
    let roleid = ref("");
    let filter = ref("");

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

    const refresh_class = () => {
      $.ajax({
        url: "http://localhost:8080/class/getlist/",
        type: "get",
        data: {
          filter: filter.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          classlist.value = resp;
        },
      });
    };
    refresh_class();

    const classadd = reactive({
      classname: "",
      classinfo: "",
      error_message: "",
    });
    const class_add = () => {
      classadd.error_message = "";
      $.ajax({
        url: "http://localhost:8080/class/add/",
        type: "post",
        data: {
          classname: classadd.classname,
          classinfo: classadd.classinfo,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            (classadd.classname = ""), (classadd.classinfo = "");
            Modal.getInstance("#add-class-btn-").hide();
            //alert("发送成功");
            //refresh_bots();
            refresh_class();
          } else {
            classadd.error_message = resp.error_message;
          }
        },
      });
    };

    const class_del = (classofinfo) => {
      $.ajax({
        url: "http://localhost:8080/class/del/",
        type: "post",
        data: {
          classname: classofinfo.classname,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            refresh_class();
            alert("删除成功");
          }
        },
      });
    };

    const join = (classofinfo) => {
      $.ajax({
        url: "http://localhost:8080/class/join/",
        type: "post",
        data: {
          classname: classofinfo.classname,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            refresh_class();
            alert("已申请，等待管理员通过");
          } else {
            alert(resp.error_message);
          }
        },
      });
    };

    return {
      roleid,
      classlist,
      filter,
      classadd,
      refresh_class,
      class_add,
      class_del,
      join,
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