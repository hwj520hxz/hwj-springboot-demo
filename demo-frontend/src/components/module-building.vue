<template>
  <div class="module-building">
    <img src="img/building.png" />
    <vue-typer :text="['模块正在建设中......','请耐心等候！','相信明天会更好！','敬请期待！','加油！！！']" erase-style="backspace"></vue-typer>
  </div>
</template>
<script>
import { VueTyper } from 'vue-typer'
export default {
  name: 'ModuleBuilding',
  components: {
    VueTyper
  },
  data () {
    return {
    }
  },
  created () {
  },
  mounted () {
  },
  methods: {
  }
}
</script>
<style lang="scss" scoped>
.module-building {
  width: 100%;
  height: 100%;
  display: flex;
  font-size: 30px;
  text-align: center;
  flex-direction: column;
  justify-content: center;
  img {
    display: block;
    margin: 0 auto;
  }
  .vue-typer .custom.char.typed {
    color: #fff;
  }
  .vue-typer .custom.caret {
    background-color: #fff;
  }
}
</style>
