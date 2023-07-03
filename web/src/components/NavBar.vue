<template>
  <div
    style="width: 10%"
    class="offcanvas offcanvas-start show"
    data-bs-backdrop="false"
    tabindex="-1"
    id="offcanvas"
    aria-labelledby="offcanvasLabel"
  >
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="offcanvasLabel">DHUer</h5>
    </div>
    <div class="offcanvas-body">
      <nav class="nav flex-column">
        <a class="nav-link" href="/user/">账号管理</a>
        <a class="nav-link" href="/class/">班级管理</a>
        <a class="nav-link" href="/text/">留言管理</a>
        <a class="nav-link">退出登录</a>
      </nav>
    </div>
  </div>
</template>

<script>
export default {
  setup() {},
};
</script>

<style scoped>
</style>