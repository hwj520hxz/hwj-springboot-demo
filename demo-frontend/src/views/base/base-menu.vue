<template>
  <div class="base-menu">
    <el-menu
      router
      :mode="mode"
      :collapse="collapse"
      :menu-trigger="menuTrigger"
      :default-active="defaultActive"
      :unique-opened="uniqueOpened"
      :text-color="textColor"
      :active-text-color="activeTextColor"
      :background-color="backgroundColor"
      :collapse-transition="false"
    >
      <template v-for="menu in menuData">
        <menu-item :menu="menu" :key="menu.code" :collapse="collapse" />
      </template>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'BaseMenu',
  components: {
    MenuItem: () => import('@/components/menu-item')
  },
  props: {
    mode: { // 模式，horizontal：横向 ， vertical：纵向
      type: String,
      default: 'vertical'
    },
    collapse: { // 是否水平折叠收起菜单（仅在 mode 为 vertical 时可用）
      type: Boolean,
      default: false
    },
    uniqueOpened: { // 是否只保持一个子菜单的展开（仅在 mode 为 vertical 时可用）
      type: Boolean,
      default: true
    },
    menuTrigger: { // 子菜单打开的触发方式(只在 mode 为 horizontal 时有效)，可选值：hover，click
      type: String,
      default: 'hover'
    },
    defaultActive: { // 当前激活菜单的 index
      type: String,
      default: ''
    },
    textColor: {
      type: String,
      default: '#f8f8f8'
    },
    activeTextColor: {
      type: String,
      default: '#ffd04b'
    },
    backgroundColor: { // 菜单背景颜色
      type: String,
      default: '#4B5C76'
    },
    menuData: { // 菜单数据
      type: Array
    }
  }
}
</script>

<style lang="scss" scoped>
.base-menu {
  width: 100%;
  height: 100%;
  .el-menu {
    border: none;
  }
  /deep/ {
    .el-menu--collapse {
      width: 80px;
    }
    .menu-item {
      &.collapse-menu {
        height: 80px;
        line-height: 80px;
        .el-tooltip {
          padding: 0px 27px !important;
        }
      }
    }
    .el-menu > .el-menu-item [class^="fa"],
    .el-menu > .el-submenu > .el-submenu__title [class^="fa"] {
      margin-right: 5px;
      width: 24px;
      text-align: center;
      font-size: 15px;
      vertical-align: middle;
    }
    .el-menu--collapse > .el-menu-item [class^="fa"],
    .el-menu--collapse > .el-submenu > .el-submenu__title [class^="fa"],
    .el-menu--collapse > .el-menu-item [class^="el-icon-"],
    .el-menu--collapse > .el-submenu > .el-submenu__title [class^="el-icon-"] {
      width: 35px;
      height: 35px;
      color: #f8f8f8;
      line-height: 35px;
      position: relative;
      border-radius: 5px;
    }
    .el-menu--collapse > .el-menu-item [class^="fa"],
    .el-menu--collapse > .el-menu-item [class^="el-icon-"] {
      right: 6px;
      bottom: 10px;
    }
    .el-menu--collapse > .el-submenu > .el-submenu__title [class^="fa"],
    .el-menu--collapse > .el-submenu > .el-submenu__title [class^="el-icon-"] {
      right: -2px;
      bottom: 11px;
    }
    $color-list: #985ce2, #6a9ae1, #45b1df, #48d4d7, #3089dc, #cc6699, #3398dc,
      #0092DB, #5baba0, #1aa6b7;
    @each $color in $color-list{
      $i: index($color-list,$color);
      .el-menu--collapse > .el-menu-item:nth-of-type(10n + #{$i}) [class^="fa"],
      .el-menu--collapse
        > .el-menu-item:nth-of-type(10n + #{$i})
        [class^="el-icon-"],
      .el-menu--collapse
        > .el-submenu:nth-of-type(10n + #{$i})
        > .el-submenu__title
        [class^="fa"],
      .el-menu--collapse
        > .el-submenu:nth-of-type(10n + #{$i})
        > .el-submenu__title
        [class^="el-icon-"] {
        background-color: #{$color};
      }
    }
  }
}
</style>
