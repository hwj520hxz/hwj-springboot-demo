<template>
  <div class="iframe-container">
    <iframe class="iframe" frameborder="none" :src="src"></iframe>
    <div v-if="loading" class="loading-container">
      <looping-rhombuses-spinner :animation-duration="1500" :size="64" color="#2d8cf0"/>
    </div>
  </div>
</template>
<script>
import { LoopingRhombusesSpinner } from 'epic-spinners'
export default {
  name: 'VueIframe',
  components: {
    LoopingRhombusesSpinner
  },
  props: {
    src: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      loading: true
    }
  },
  mounted () {
    const iframe = this.$el.querySelector('iframe')
    if (iframe.attachEvent) {
      iframe.attachEvent('onload', () => {
        this.loading = false
      })
    } else {
      iframe.onload = () => {
        this.loading = false
      }
    }
  },
  deactivated () {
    this.loading = false
  }
}
</script>
<style lang="scss" scoped>
.iframe-container {
  width: 100%;
  height: 100%;
  position: relative;
  .iframe {
    width: 100%;
    height: 100%;
    display: block;
  }
  .loading-container {
    width: 100%;
    height: 100%;
    display: flex;
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    align-items: center;
    justify-content: center;
    background: rgba(250, 250, 250, 0.6);
  }
}
</style>
