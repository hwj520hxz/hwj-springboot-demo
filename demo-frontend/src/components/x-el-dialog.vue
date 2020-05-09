<template>
  <transition appear :enter-active-class="enterActiveClass" :leave-active-class="leaveActiveClass">
    <el-dialog
      :width="width"
      v-bind="$attrs"
      @open="$emit('open')"
      @opened="$emit('opened')"
      @close="$emit('close')"
      @closed="$emit('closed')"
      :visible.sync="_visible"
      :modal-append-to-body="modalAppendToBody"
      :close-on-click-modal="closeOnClickModal"
    >
      <template v-slot:title>
        <slot name="title"></slot>
      </template>
      <template v-slot:default>
        <slot name="default"></slot>
      </template>
      <template v-slot:footer>
        <slot name="footer"></slot>
      </template>
    </el-dialog>
  </transition>
</template>

<script>
export default {
  name: 'XElDialog',
  props: {
    enterActiveClass: {
      type: String,
      default: 'animated zoomInUp faster'
    },
    leaveActiveClass: {
      type: String,
      default: 'animated zoomOutDown faster'
    },
    width: {
      type: String,
      default: '50%'
    },
    visible: {
      type: Boolean,
      default: false
    },
    modalAppendToBody: {
      type: Boolean,
      default: false
    },
    closeOnClickModal: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    _visible: {
      get () {
        return this.visible
      },
      set (val) {
        this.$emit('update:visible', val)
      }
    }
  },
  beforeMount () {
    this.$emit('open')
  },
  mounted () {
    this.$emit('opened')
  },
  destroyed () {
    this.$emit('closed')
  }
}
</script>
