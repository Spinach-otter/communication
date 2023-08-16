// 下拉菜单模版
<button
            class="btn btn-toggle align-items-center rounded collapsed"
            data-bs-toggle="collapse"
            data-bs-target="#dashboard-collapse"
            aria-expanded="false"
          >
            <svg class="bi me-2" width="16" height="16">
              <use xlink:href="#speedometer2" />
            </svg>
            Dashboard
          </button>
          <div class="collapse" id="dashboard-collapse">
            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <li><a href="#" class="link-dark rounded">Overview</a></li>
              <li><a href="#" class="link-dark rounded">Weekly</a></li>
              <li><a href="#" class="link-dark rounded">Monthly</a></li>
              <li><a href="#" class="link-dark rounded">Annually</a></li>
            </ul>
          </div>


// UserView原先模版
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
