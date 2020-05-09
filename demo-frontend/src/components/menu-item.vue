<template>
  <el-menu-item
    class="menu-item"
    :class="{'collapse-menu': menu.level === 1 && collapse}"
    v-if="!menu.routeConfig.hidden && (!menu.childMenu || menu.childMenu.length === 0)"
    :index="menu.code"
    :key="menu.code"
    v-touch-ripple
  >
    <i v-if="isLocalImg(menu.icon)" class="el-icon- img-icon-menu" :style="{backgroundImage: `url(${menu.icon})`,backgroundSize: !collapse ? 'contain' : 'auto'}">
      <div v-if="menu.level === 1 && collapse" class="menu-title img-menu-title" v-html="menu.name"></div>
    </i>
    <i v-else-if="menu.icon ||  menu.level === 1" :class="menu.icon || 'el-icon-menu'">
      <div v-if="menu.level === 1 && collapse" class="menu-title" v-html="menu.name"></div>
    </i>
    <span slot="title" v-html="menu.name"></span>
  </el-menu-item>
  <el-submenu
    class="menu-item"
    :class="{'collapse-menu': menu.level === 1 && collapse}"
    v-else-if="!menu.routeConfig.hidden "
    :index="menu.code"
    :key="menu.code"
  >
    <template slot="title">
      <i v-if="isLocalImg(menu.icon)" :src="menu.icon" class="el-icon- img-icon-menu" :style="{backgroundImage: `url(${menu.icon})`,backgroundSize: !collapse ? 'contain' : 'auto'}">
        <div v-if="menu.level === 1 && collapse" class="menu-title img-menu-title" v-html="menu.name"></div>
      </i>
      <i v-else-if="menu.icon ||  menu.level === 1" :class="menu.icon || 'el-icon-menu'">
        <div v-if="menu.level === 1 && collapse" class="menu-title" v-html="menu.name"></div>
      </i>
      <span slot="title" v-html="menu.name"></span>
    </template>
    <menu-item v-for="item in menu.childMenu" :key="item.code" :menu="item"></menu-item>
  </el-submenu>
</template>

<script>
export default {
  name: 'MenuItem',
  props: {
    menu: {
      type: Object,
      required: true
    },
    collapse: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    isLocalImg (iconStr) {
      return iconStr && (iconStr.indexOf('.') > -1 || iconStr.indexOf('/') > -1)
    }
  }
}
</script>

<style lang="scss" scoped>
.menu-item {
  &.collapse-menu {
    height: 80px;
    line-height: 80px;
    /deep/ {
      .el-submenu__title {
        height: 80px;
        line-height: 80px;
      }
    }
  }
  .menu-title {
    top: -6px;
    width: 80px;
    right: 23px;
    color: #f8f8f8;
    font-size: 14px;
    overflow: hidden;
    position: relative;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  .img-icon-menu {
    height: 18px;
    background-position: center;
    background-repeat:no-repeat;
    .img-menu-title {
      top: 30px;
    }
  }
}
</style>
