<template>
  <div class="right bg-light">
    <slot></slot>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.right {
  width: 1130px;
  height: 688px;
  float: right;
  margin-top: 5%;
  margin-right: 5%;
  border-radius: 10px;
  /* background-color: rgb(248, 249, 250); */
  /* background-color: #f8f9fa; */
  opacity: 0.8;
}
</style>