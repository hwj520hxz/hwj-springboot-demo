<template>
  <div class="base-tab">
    <scroll-pane ref="scrollPane" class="tags-view-wrapper" style="box-sizing:border-box">
      <draggable v-model="visitedViews">
      <transition-group name="breadcrumb">
        <router-link
          v-for="tag in visitedViews"
          ref="tag"
          tag="span"
          :key="tag.path"
          :class="{'active': isActive(tag),'tags-view-item': true}"
          :to="{path: tag.path, query: tag.query, fullPath: tag.fullPath }"
          @click.middle.native="closeSelectedTag(tag)"
          @contextmenu.prevent.native="openMenu(tag,$event)"
        >
          <touch-ripple>
            &nbsp;
            <i v-if="isLocalImg(tag.meta.menu.icon)" class="el-icon- img-icon-menu" :style="{backgroundImage: `url(${tag.meta.menu.icon})`}"/>
            <i v-else :class="tag.meta.menu.icon || 'el-icon-menu'" />
            {{ tag.meta.menu.name }}
            <span
              v-if="!(tag.meta.menu.routeConfig || {}).affix"
              class="el-icon-close"
              @click.prevent.stop="closeSelectedTag(tag)"
            />
            &nbsp;
          </touch-ripple>
        </router-link>
      </transition-group>
      </draggable>
    </scroll-pane>
    <ul v-show="visible" :style="{left:left+'px',top:top+'px'}" class="contextmenu">
      <li @click="refreshSelectedTag(selectedTag)">刷新</li>
      <li
        v-if="!(((selectedTag.meta || {}).menu || {}).routeConfig || {}).affix"
        @click="closeSelectedTag(selectedTag)"
      >关闭</li>
      <li @click="closeOthersTags(selectedTag)">关闭其它</li>
      <li @click="closeAllTags(selectedTag)">全部关闭</li>
    </ul>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import Draggable from 'vuedraggable'
import { touchRipple } from 'vue-touch-ripple'
import ScrollPane from '@/components/scroll-panel'

export default {
  name: 'BaseTab',
  components: {
    Draggable,
    ScrollPane,
    touchRipple
  },
  props: {
    menuData: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {
      top: 0,
      left: 0,
      visible: false,
      affixTags: [],
      visitedViews: [],
      selectedTag: {}
    }
  },
  computed: {
    ...mapState({
      routes: state => state.router.routes,
      firEndMenu: state => state.menu.firEndMenu
    })
  },
  watch: {
    $route () {
      this.addTags()
      this.moveToCurrentTag()
    },
    visible (value) {
      if (value) {
        document.body.addEventListener('click', () => { this.visible = false })
      } else {
        document.body.removeEventListener('click', () => { this.visible = false })
      }
    }
  },
  mounted () {
    this.initTags()
    this.addTags()
  },
  methods: {
    /**
     * 当前页签是否激活
     */
    isActive (route) {
      return route.path === this.$route.path
    },
    /**
     * 筛选出固定的页签
     */
    filterAffixTags (menus) {
      let tags = []
      menus.forEach(menu => {
        if (menu.routeConfig.affix) {
          tags.push(this.routes.find(item => item.name === menu.code))
        }
      })
      return tags
    },
    initTags () {
      const affixTags = this.affixTags = this.filterAffixTags(this.menuData)
      for (const tag of affixTags) {
        this.visitedViews.push(tag)
      }
    },
    /**
     * 添加页签
     */
    addTags () {
      if (!this.$route.name) return
      this.$emit('addCacheView', this.$route)
      if (this.visitedViews.some(v => v.path === this.$route.path)) return
      this.visitedViews.push(this.$route)
    },
    /**
     * 选中当前激活的页签
     */
    moveToCurrentTag () {
      const tags = this.$refs.tag
      if (tags) {
        this.$nextTick(() => {
          for (const tag of tags) {
            if (tag.to.path === this.$route.path) {
              this.$refs.scrollPane.moveToTarget(tag)
              if (tag.to.fullPath !== this.$route.fullPath) {
                for (let v of this.visitedViews) {
                  if (v.path === this.$route.path) {
                    v = Object.assign(v, this.$route)
                    break
                  }
                }
              }
              break
            }
          }
        })
      }
    },
    /**
     * 刷新当前选中页签
     */
    refreshSelectedTag () {
      this.$emit('removeCacheView', this.$route)
      const { fullPath } = this.$route
      this.$nextTick(() => {
        this.$router.replace({
          path: '/redirect' + fullPath
        })
      })
    },
    /**
     * 关闭当前选中页签
     */
    closeSelectedTag (view) {
      this.$emit('removeCacheView', view)
      for (const [i, v] of this.visitedViews.entries()) {
        if (v.path === view.path) {
          this.visitedViews.splice(i, 1)
          break
        }
      }
      if (this.isActive(view)) {
        this.toLastView(this.visitedViews)
      }
    },
    /**
     * 关闭除此之外所有页签
     */
    closeOthersTags () {
      let view = this.selectedTag
      this.$router.push(view)
      this.$emit('removeOtherCacheView', view)
      this.visitedViews = this.visitedViews.filter(v => {
        return v.meta.menu.routeConfig.affix || v.path === view.path
      })
      this.moveToCurrentTag()
    },
    /**
     * 关闭所有页签
     */
    closeAllTags (view) {
      this.$emit('clearCacheView')
      this.visitedViews = this.visitedViews.filter(tag => tag.meta.menu.routeConfig.affix)
      this.toLastView(this.visitedViews)
    },
    /**
     * 定位到上一菜单页签
     */
    toLastView (visitedViews) {
      const latestView = visitedViews.slice(-1)[0]
      if (latestView) {
        this.$router.push(latestView)
      } else {
        this.firEndMenu && this.$router.replace({
          path: `/redirect/${this.firEndMenu.code}`
        })
      }
    },
    /**
     * 右键菜单
     */
    openMenu (tag, e) {
      const menuMinWidth = 105
      const offsetLeft = this.$el.getBoundingClientRect().left
      const offsetWidth = this.$el.offsetWidth
      const maxLeft = offsetWidth - menuMinWidth
      const left = e.clientX - offsetLeft + 10
      if (left > maxLeft) {
        this.left = maxLeft
      } else {
        this.left = left
      }
      this.top = e.clientY - 50
      this.visible = true
      this.$router.push(tag)
      this.selectedTag = tag
    },
    isLocalImg (iconStr) {
      return iconStr && (iconStr.indexOf('.') > -1 || iconStr.indexOf('/') > -1)
    }
  }
}
</script>

<style lang="scss" scoped>
.base-tab {
  height: 35px;
  width: 100%;
  position: relative;
  background-color: #f1f1f1;
  border-bottom: 1px solid #d8dce5;
  box-shadow: 0px 1px 4px rgba(0, 21, 41, 0.2) inset;
  .tags-view-wrapper {
    .tags-view-item {
      display: inline-block;
      position: relative;
      cursor: pointer;
      height: 26px;
      line-height: 26px;
      border: 1px solid #d8dce5;
      color: #495060;
      background: #fff;
      font-size: 12px;
      margin-left: 5px;
      margin-top: 4px;
      &:first-of-type {
        margin-left: 10px;
      }
      &:last-of-type {
        margin-right: 10px;
      }
      &.active {
        background-color: #42b983;
        color: #fff;
        border-color: #42b983;
      }
    }
  }
  .contextmenu {
    margin: 0;
    background: #fff;
    z-index: 3000;
    position: absolute;
    list-style-type: none;
    padding: 5px 0;
    border-radius: 4px;
    font-size: 12px;
    font-weight: 400;
    color: #333;
    box-shadow: 2px 2px 3px 0 rgba(0, 0, 0, 0.3);
    li {
      margin: 0;
      padding: 7px 16px;
      cursor: pointer;
      &:hover {
        background: #eee;
      }
    }
  }
}
</style>

<style lang="scss">
.tags-view-wrapper {
  .tags-view-item {
    .el-icon-close {
      width: 16px;
      height: 16px;
      position: relative;
      z-index: 1;
      vertical-align: 2px;
      border-radius: 50%;
      text-align: center;
      transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
      transform-origin: 100% 50%;
      &:before {
        transform: scale(0.6);
        display: inline-block;
        vertical-align: -3px;
      }
      &:hover {
        background-color: #b4bccc;
        color: #fff;
      }
    }
  }
  .img-icon-menu {
    width: 12px;
    height: 12px;
    background-position: center;
    background-repeat:no-repeat;
    background-size: contain;
  }
}
</style>
