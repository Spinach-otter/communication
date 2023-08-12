<template>
  <div class="container">
    <div class="left-component">
      <NavBar />
    </div>
    <div class="right-component">
      <ContentField>class</ContentField>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import ContentField from "@/components/ContentField.vue";

export default {
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
