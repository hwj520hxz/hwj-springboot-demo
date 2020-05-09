<template>
  <div class="base-breadcrumb">
    <i
      :class="['hamburger',collapse ? 'el-icon-s-unfold': 'el-icon-s-fold']"
      @click="$emit('update:collapse', !collapse)"
    />
    <el-breadcrumb>
      <transition-group name="breadcrumb">
        <el-breadcrumb-item v-for="item in ($route.meta.menu || {}).siteMap" :key="item">{{item}}</el-breadcrumb-item>
      </transition-group>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: 'BaseBreadcrumb',
  props: {
    collapse: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style lang="scss" scoped>
.base-breadcrumb {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  .hamburger {
    color:#606266;
    font-size: 24px;
    cursor: pointer;
    margin: 0 6px 0 0;
  }
}
</style>
