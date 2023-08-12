<template>
  <div class="container content-field">
    <div class="card">
      <div class="card-body">
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scope>
.card {
  width: 100%;
  height: 90vh;
  margin: 10px 0;
}
</style>