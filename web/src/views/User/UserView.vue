<template>
  <div class="container">
    <div class="left-component">
      <NavBar />
    </div>
    <div class="right-component">
      <ContentField>{{ msg }}</ContentField>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import { ref } from "vue";
import NavBar from "@/components/NavBar.vue";
import ContentField from "@/components/ContentField.vue";

export default {
  setup: () => {
    let msg = ref("");
    $.ajax({
      url: "http://localhost:8080/user/all/",
      type: "get",
      success: (resp) => {
        console.log(resp);
        msg.value = resp;
      },
    });
    return {
      msg,
    };
  },
  components: {
    NavBar,
    ContentField,
  },
};
</script>

<style scoped>
.container {
  display: flex;
}

.left-component {
  flex: 1;
  padding: 5px;
}

.right-component {
  flex: 11;
  padding: 5px;
}
</style>
