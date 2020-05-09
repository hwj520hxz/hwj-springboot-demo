import Vue from 'vue'

const components = require.context(
  '.',
  true,
  /\*.js/
)
components.keys().forEach((key) => {
  const component = components(key)
  Vue.filter(key, component.default)
})
