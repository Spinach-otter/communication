<template>
  <div class="right">
    <div class="head">
      <p class="txt">收件箱</p>
    </div>
    <div class="msg-list">
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">发件人</th>
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
import { ref } from "vue";

export default {
  setup() {
    const store = useStore();
    let msglist = ref([]);
    $.ajax({
      url: "http://localhost:8080/msg/getreceive/",
      type: "get",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        msglist.value = resp;
      },
    });
    return {
      msglist,
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
.head {
  background-color: white;
  width: 100%;
  height: 8%;
  margin-bottom: 3%;
  border-radius: 10px;
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
}
.txt {
  display: flex;
  font-size: 30px;
  font-family: cursive;
}
</style>