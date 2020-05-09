<template>
  <div id="app">
    <base-layout :left-panel-collapse="collapse" :cached-views="cachedViews">
      <base-logo :collapse="collapse" slot="logo-container"></base-logo>
      <base-menu
        slot="menu-panel"
        :collapse="collapse"
        :menu-data="$store.getters['menu/treeMenu']"
        :default-active="$route.meta.menu && $route.meta.menu.code"
      />
      <base-navbar slot="navbar-container">
        <base-breadcrumb slot="breadcrumb-container" :collapse.sync="collapse" />
      </base-navbar>
      <base-tab
        slot="tab-container"
        :menu-data="$store.state.menu.rawMenu"
        @addCacheView="addCacheView"
        @removeCacheView="removeCacheView"
        @removeOtherCacheView="removeOtherCacheView"
        @clearCacheView="clearCacheView"
      />
    </base-layout>
  </div>
</template>
<script>
import {
  BaseTab,
  BaseLogo,
  BaseMenu,
  BaseLayout,
  BaseNavbar,
  BaseBreadcrumb
} from './views/base'
export default {
  components: {
    BaseTab,
    BaseLogo,
    BaseMenu,
    BaseLayout,
    BaseNavbar,
    BaseBreadcrumb
  },
  data () {
    return {
      collapse: true,
      cachedViews: []
    }
  },
  methods: {
    addCacheView (view) {
      if (this.cachedViews.includes(view.matched[0].components.default.name)) return
      this.cachedViews.push(view.matched[0].components.default.name)
    },
    removeCacheView (view) {
      for (const i of this.cachedViews) {
        if (i === view.matched[0].components.default.name) {
          const index = this.cachedViews.indexOf(i)
          this.cachedViews.splice(index, 1)
          break
        }
      }
    },
    removeOtherCacheView (view) {
      for (const i of this.cachedViews) {
        if (i === view.matched[0].components.default.name) {
          const index = this.cachedViews.indexOf(i)
          this.cachedViews = this.cachedViews.slice(index, index + 1)
          break
        }
      }
    },
    clearCacheView () {
      this.cachedViews = []
    }
  }
}
</script>
<style lang="scss">
html,
body {
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  position: absolute;
}
#app {
  width: 100%;
  height: 100%;
  position: absolute;
  /* breadcrumb transition */
  .breadcrumb-enter-active,
  .breadcrumb-leave-active {
    transition: all 0.5s;
  }

  .breadcrumb-enter,
  .breadcrumb-leave-active {
    opacity: 0;
    transform: translateX(20px);
  }

  .breadcrumb-move {
    transition: all 0.5s;
  }

  .breadcrumb-leave-active {
    position: absolute;
  }
  /* fade-transform */
  .fade-transform-leave-active,
  .fade-transform-enter-active {
    transition: all 0.5s;
  }

  .fade-transform-enter {
    opacity: 0;
    transform: translateX(-30px);
  }

  .fade-transform-leave-to {
    opacity: 0;
    transform: translateX(30px);
  }
}
#nprogress {
  div.bar {
    background: #74bcb5;
    box-shadow: 0 0 10px #74bcb5;
  }
}
</style>
