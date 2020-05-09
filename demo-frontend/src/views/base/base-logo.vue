<template>
  <div class="system-logo">
    <img v-if="collapse" src="img/system-logo.png">
    <span v-else class="title-container">
      <span class="title">PC端通用项目框架</span>
      <h6 class="sub-title">PC Universal Framework Of Project</h6>
    </span>
  </div>
</template>

<script>
export default {
  name: 'BaseLogo',
  props: {
    collapse: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style lang="scss" scoped>
.system-logo {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  .title-container {
    color: #f8f8f8;
    .title {
      letter-spacing: 3px;
      font-size: 25px;
      font-weight: bold;
      word-break: keep-all;
    }
    .sub-title {
      margin: 0;
      font-size: 12px;
      text-align: center;
      letter-spacing: 1px;
      font-weight: normal;
    }
  }
}
</style>
