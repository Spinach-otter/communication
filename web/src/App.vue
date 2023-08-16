<template>
  <router-view />
  <NavBar v-if="!$route.meta.hideComponent"></NavBar>
</template>

<script>
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap";
import NavBar from "@/components/NavBar.vue";

export default {
  components: {
    NavBar,
  },
};
</script>


<style>
body {
  background-size: cover;
  background-image: url("@/assets/images/background.png");
}
</style>
