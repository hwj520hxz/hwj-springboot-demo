<template>
  <div class="base-layout">
    <div class="sidebar-container" :class="{'collapse': leftPanelCollapse}" :style="sideBarStyle">
      <div class="logo-container" :style="logoContainerStyle">
        <slot name="logo-container" />
      </div>
      <div class="menu-panel" :style="menuPanelStyle">
        <slot name="menu-panel" />
      </div>
    </div>
    <div class="main-container">
      <div class="navbar-container" :style="navBarStyle">
        <slot name="navbar-container" />
      </div>
      <div class="tab-container">
        <slot name="tab-container" />
      </div>
      <div class="app-main">
        <transition name="fade-transform" mode="out-in">
          <keep-alive :include="cachedViews">
            <router-view :key="$route.path" />
          </keep-alive>
        </transition>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'BaseLayout',
  props: {
    cachedViews: {
      type: Array,
      default: () => []
    },
    // 头部
    navBarStyle: {
      type: Object,
      default () {
        return { height: '60px' }
      }
    },
    // 左侧面板
    sideBarStyle: {
      type: Object,
      default () {
        return {
          width: '250px',
          backgroundColor: ' #4B5C76'
        }
      }
    },
    logoContainerStyle: {
      type: Object,
      default () {
        return {
          height: '60px'
        }
      }
    },
    leftPanelCollapse: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    menuPanelStyle () {
      return { height: `calc(100% - ${this.logoContainerStyle.height}` }
    }
  }
}
</script>
<style lang="scss" scoped>
.base-layout {
  width: 100%;
  height: 100%;
  display: flex;
  background: linear-gradient(#fefefe, #fafafa);
  .sidebar-container {
    height: 100%;
    user-select: none;
    transition: width 0.25s cubic-bezier(0, 0, 0.2, 1);
    &.collapse {
      width: 80px !important;
    }
    .logo-container {
      box-sizing: border-box;
      border-bottom: 1px solid rgba(248, 248, 248, 0.1);
    }
    .menu-panel {
      overflow: auto;
      overflow-x: hidden;
    }
  }
  .main-container {
    flex: 1;
    display: flex;
    flex-direction: column;
    height: 100%;
    overflow: hidden;
    .app-main {
      flex: 1;
      margin: 10px;
      overflow-y: auto;
      overflow-x: hidden;
      border-radius: 3px;
    }
  }
}
</style>
