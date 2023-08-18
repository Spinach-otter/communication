<template>
  <div class="right">
    <div class="title-send">
      <div class="text">
        <textarea
          v-model="msgadd.messagecontent"
          class="form-control"
          id="add-msg-messagecontent"
          rows="3"
          placeholder="请输入留言"
        ></textarea>
      </div>
      <div class="err-msg">{{ msgadd.error_message }}</div>
      <div class="user">
        <div class="uid">
          <input
            v-model="msgadd.username"
            type="text"
            class="form-control"
            placeholder="输入收件人账号"
          />
        </div>

        <div class="put">
          <button
            @click="msg_add"
            type="button"
            class="btn btn-outline-primary"
          >
            发送
          </button>
        </div>
      </div>
    </div>
    <div class="msg-list">
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">收件人</th>
            <th scope="col">发送内容</th>
            <th scope="col">发送时间</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
          <tr v-for="msg in msglist" :key="msg.messageid">
            <td>{{ msg.recipientname }}</td>
            <td>{{ msg.messagecontent }}</td>
            <td>{{ msg.timestamp }}</td>
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

export default {
  setup() {
    const store = useStore();
    let msglist = ref([]);

    const refresh_list = () => {
      $.ajax({
        url: "http://localhost:8080/msg/getlist/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          msglist.value = resp;
        },
      });
    };
    refresh_list();

    const msgadd = reactive({
      username: "",
      messagecontent: "",
      error_message: "",
    });
    const msg_add = () => {
      msgadd.error_message = "";
      $.ajax({
        url: "http://localhost:8080/add/msg/",
        type: "post",
        data: {
          username: msgadd.username,
          messagecontent: msgadd.messagecontent,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            msgadd.messagecontent = "";
            msgadd.username = "";
            alert("发送成功");
            refresh_list();
          } else {
            msgadd.messagecontent = "";
            msgadd.username = "";
            alert("用户不存在");
          }
        },
      });
    };

    return {
      msglist,
      msgadd,
      msg_add,
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
.title-send {
  background-color: white;
  width: 100%;
  height: 20%;
  border-radius: 10px;
  /* margin-left: 3%; */
  margin-bottom: 3%;
}
.user {
  border-radius: 10px;

  height: 30%;
  display: flex;
}
.uid {
  display: flex;
  width: 80%;
  height: 100%;
  /* padding: 0; */
}
.put {
  width: 20%;
  display: flex;
  flex: right;
}
.btn {
  width: 100%;
}
.msg-list {
  border-radius: 10px;
  overflow: hidden;
}
.table {
  border-radius: 10px;
}
.err-msg {
  color: red;
}
</style>

