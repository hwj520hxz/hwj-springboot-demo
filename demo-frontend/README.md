<!-- TOC -->

- [boss-admin-web](#boss-admin-web)
  - [目录结构](#目录结构)
  - [框架使用](#框架使用)
    - [接口配置](#接口配置)
    - [模块集成](#模块集成)
    - [菜单配置](#菜单配置)
  - [模块开发约定](#模块开发约定)
    - [属性注入](#属性注入)
    - [异步组件](#异步组件)
  - [框架功能](#框架功能)
    - [登录拦截](#登录拦截)
    - [接口请求](#接口请求)
    - [接口模拟](#接口模拟)
    - [接口代理](#接口代理)
    - [异常捕获](#异常捕获)
    - [在线换肤](#在线换肤)
  - [其他](#其他)
    - [资源引用](#资源引用)
    - [常见问题](#常见问题)
  - [Build Setup](#build-setup)

<!-- /TOC -->

# boss-admin-web

> 基于Vue开发的PC端通用项目框架模板。

* * *

## 目录结构

```
├── public
│ ├── index.html
│ ├── public
│   └── theme
│     └── default
│       ├── style.css
├── src
│ ├── api
│ ├── components
│ ├── App.vue
│ ├── main.js
│ ├── mock
│ ├── views
│ ├── router
│ ├── store
│ └── utils
```

## 框架使用

> 接口配置、模块集成、菜单配置、以及模块配置介绍。

### 接口配置

> 作用：作为前端项目接口注册的统一入口点，集中进行管理。

我们约定，在src/config/systemConfig.js文件中，api节点用来注册当前系统使用的接口，如：

```js
api: [{
    name: '通用查询接口',
    method: 'queryData',
    url: '/api/v1/basic/queryData'
  }, {
    name: '通用查询分页接口',
    method: 'pagingData',
    url: '/api/v1/basic/pagingData'
  }, {
    name: '通用新增接口',
    method: 'add',
    url: '/api/v1/basic/execute',
    type: 'post'
  }, {
    name: '通用删除接口',
    method: 'remove',
    url: '/api/v1/basic/execute',
    type: 'delete'
  }, {
    name: '通用修改接口',
    method: 'modify',
    url: '/api/v1/basic/execute',
    type: 'put'
  }]
```

配置之后，即可使用注册的方法来发起请求，具体使用方法可参考[接口请求](#%E6%8E%A5%E5%8F%A3%E8%AF%B7%E6%B1%82)一节中的`配置型接口请求`。
开发阶段，后端接口地址配置在.env.development文件中，如下：

```js
// API接口地址
VUE_APP_BASE_URL='http://localhost:10012'
```

### 模块集成

> 模块组件集成统一在异步组件库中注册一次即可。

我们约定，在src/store/components.js文件中，进行模块组件的注册，如：

```js
// 异步组件库
export default {
  namespaced: true,
  state: {
    asyncComponents: {
      'vue-iframe': () =>
      import('@/components/vue-iframe' /* webpackChunkName: "vue-iframe" */),
      'simple-grid': () =>
        import('@/views/demo/simple-grid' /* webpackChunkName: "simple-grid" */),
      'tree-simple-grid': () =>
        import('@/views/demo/tree-simple-grid' /* webpackChunkName: "tree-simple-grid" */),
      'simple-search-grid': () =>
        import('@/views/demo/simple-search-grid' /* webpackChunkName: "simple-search-grid" */),
      'tree-simple-search-grid': () =>
        import('@/views/demo/tree-simple-search-grid' /* webpackChunkName: "tree-simple-search-grid" */),
      'paging-grid': () =>
        import('@/views/demo/paging-grid' /* webpackChunkName: "paging-grid" */),
      'tree-paging-grid': () =>
        import('@/views/demo/tree-paging-grid' /* webpackChunkName: "tree-paging-grid" */)
    }
  }
}
```

### 菜单配置

> 定义菜单使用异步组件库中的哪个模块组件进行渲染。

目前，我们可以针对现有菜单表结构配置出三种类型的模块，具体配置如下：

```js
  // vue组件模块
  {
    id: 1, // 菜单id
    parentId: null, // 父级菜单id
    code: 'simple-grid', // 菜单路由名称
    name: '无分页无搜索列表', // 菜单名称
    target: 'component', // 导航目标，可选值：component（组件）、blank（打开新页签）
    component: 'simple-grid', // 组件名称，异步组件库中注册的模块组件名称
    sortCode: 1 // 菜单排序编码,
    config: '{"prop1": "组件外部化参数"}' // config字段作为模块组件外部化参数配置入口，可以对模块组件内定义prop进行配置,标准的json格式
  },
  // 内部链接模块
  {
    id: 2,
    parentId: null,
    code: 'baidu',
    name: '百度',
    target: 'component',
    component: 'vue-iframe',
    config: '{"src": "http://www.baidu.com"}'
  },
  // 外部链接模块（浏览器打开新窗口页签）
  {
    id: 3,
    parentId: null,
    code: 'iqiyi',
    name: '爱奇艺',
    target: 'blank',
    config: 'http://www.iqiyi.com'
  }
```

## 模块开发约定

### 属性注入

> 原理：利用路由配置节点的props属性，来进行注入。

在渲染模块组件前，框架会自动注入相关属性，模块组件可通过声明对应的props来接收框架自动注入的属性。目前，框架会自动注入的属性有：user（当前登录用户）、menu（当前命中的菜单对象）、theme（当前系统主题变量）。

模块组件通过声明对应的props来接收对应的参数，需要几个则声明几个，如：

```js
<template>
......
</template>

export default {
 props: {
  user: Object,
  menu: Object,
  theme: String
 }
}
```

### 异步组件

> 运行时，需要按需异步加载的组件，只有在组件被命中时，浏览器才发起异步请求来获取组件。

在项目框架中，会在src/sotre/components.js文件中统一注册异步组件，我们称之为异步组件库，并通过Vuex来进行管理。

在组件中通过`this.$store.state.components.asyncComponents['组件的注册名称']`来获取对应的组件，如：

```js
<template>
  <component :is="asyncComponents"/>
</template>

<script>
export default {
  data () {
    asyncComponents: null
  },
  mounted () {
    this.asyncComponents = this.$store.state.components.asyncComponents['组件的注册名称']
  }
}
```

## 框架功能

> 框架内置的基础功能介绍。

### 登录拦截

> 原理：通过注册路由钩子beforeEach，结合全局参数当前用户，并根据访问的目标路由，来处理登录逻辑。

- 系统用户登录后，会将当前用户存入全局参数（存在Vuex中），通过该标识来判断当前用户是否已登录；

- 在路由的beforeEach钩子函数中，判断用户将要访问的目标路由（除了登录路由）是否需要登录，如果需要登录并且当前用户未登录，则自动跳转到登录路由；

核心代码，如下：

```js
router.beforeEach((to, from, next) => {
  // 当前用户未登录时，访问需要登录的路由时
  if (to.meta.needLogin !== false && to.name !== 'login' && !store.state.user.userInfo) {
    next({ name: 'login', params: { module: to.path.substr(1) }, query: to.query })
    return
  }
  // 访问登录模块时
  if (to.name === 'login') {
    store.commit('user/setLoginUser', null) // 清空登录信息
    next()
    return
  }
  // 没有命中任何路由，默认跳转到第一个模块路由
  if (to.matched.length === 0) {
    next({ path: '/' + store.state.menu.firEndMenu ?. code })
    return
  }
  next()
})
```

- 如果模块无需登录，只需要手动配置路由，并将路由needLogin=false即可，如：

```js
// 路由配置
export default [{
  path: '/public',
  name: 'public',
  component: () => import('@/components/module-building'),
  meta: {
    needLogin: false
  }
}]
```

如以上配置之后，浏览器地址直接访问public路由，即可直接访问目标模块。

### 接口请求

> 基于axios封装的通用异步请求插件，在官方axios的功能之上，扩展了配置型接口请求、host配置等功能。

使用说明，请移步：[https://www.npmjs.com/package/vue-x-axios](https://www.npmjs.com/package/vue-x-axios)

### 接口模拟

> 当后端接口未准备就绪时，前端人员可以在前端模拟后端接口，而无需依赖后端接口开发完成。

1、使用方式：在src/mock/index.js文件中，注册你需要模拟的接口，如：
```js
Mock.mock(/\/api\/v1\/user\/login/, 'post', user.login)
Mock.mock(/\/api\/v1\/menu/, 'get', menu.menu)
```

第一个参数为接口地址（建议用正则表达式匹配），第二个参数为接口的httpmethod，第三个参数为一个函数，在函数中模拟真实接口的数据并将之返回；

2、在业务模块中正常发起请求，写法与请求一个现有接口的写法一模一样，这样当后端接口开发完成后，业务代码无需做任何调整改动；

3、当后端接口完成开发后，将模拟接口注释掉即可，如果后端接口已全部开发完成，请将入口文件main.js文件中`import '@/mock'`注释掉，这样会减小构建体积；

### 接口代理

> 利用webpack-dev-server来设置接口代理，这样做的好处是可以解决跨域问题，需要注意的是代理只在开发阶段生效。

接口代理功能是官方脚手架自带的功能，配置节点在根目录下vue.config.js文件中devServer节点，如：

```js
devServer: {
  ......
    proxy: 'https://some-domain.com' // 设置代理
}
```

这样在项目中发起的请求，如果不带IP地址，则代理就会生效，请求地址则会自动拼接上https://some-domain.com ，并且不会存在跨域问题。

你也可以匹配具体的某一类请求使用代理，如

```js
devServer: {
  ......
    proxy:{
      '/api': {
           target: 'https://some-domain.com'
        },
        '/extend': {
            target: 'https://some-domain.com'
        }
    } // 设置代理
}

```

### 异常捕获

> 原理：通过注册Vue.config.errorHandler钩子函数，在钩子函数中可获取错误信息和 Vue 实例。

除了不可预料到的异常外，组件内部也可以手动抛异常，如：

```js
<template>
......
</template>

export default {
  created () {
      throw new Error('手动抛异常！')
    }
}
```

框架会自动捕获组件的渲染和观察期间未捕获的错误，捕获到的错误会在控制台输出相关信息。

### 在线换肤

> 原理：资源引用通过切换主题变量来切换资源目录，样式通过动态创建样式表标签来进行样式覆盖。

我们约定，主题资源的存放目录按照以下目录结构来存放：

```
├── ......
└── public
    ├── ......
    └── theme
        └── default
            └── style.css
            └── assets
              ├── a.png
        └── blue
            └── style.css
            └── assets
              ├── a.png
        ......
```

如果组件中有img标签，要实现随着在线切换皮肤而变换图片，则需要用到框架自动注入到模块组件中的theme属性，通过theme变量来切换资源目录，如：

```js
<template>
  <div class="base-map-provider">
    <img :src="`theme/${theme}/assets/a.png`">
  </div>
</template>
```

## 其他

### 资源引用

> 静态资源引用的常见方式介绍。

如，要引用以下目录结构下的a.png

```
├── ......
└── public
    ├── ......
    └── theme
        └── default
            └── assets
              ├── a.png
        ......
```

单文件组件*.vue文件中，

- 在模板template中，使用public目录下的资源直接可以直接访问无需加以public开头，可以这样写：

```js
<template>
  <div class="test">
    <img src="theme/default/assets/a.png">
  </div>
</template>
```

- 在单文件组件style中，资源引用要以~public，如：

```js
<template>
  ...
</template>

<script>
...
</script>

<style lang="scss">
.my-component {
  background-image: url('~public/theme/default/assets/a.png');
}
</style>
```

当系统需要支持在线换肤时，需要把样式抽到对应皮肤目录下的style.css文件中去。

### 常见问题

> vue-cli从2.0升级到3.0后，避免了2.0的诸多问题，如构建性能、编译报uglifyjs错误等问题。

①为什么我构建出来的结果，在ie浏览器中报异常？

答：出现这个问题的一般是因为node_modules下的包没有做babel polyfill处理，在vue.config.js文件中，配置transpileDependencies即可，如：

```js
module.exports = {
    ......
    transpileDependencies: [
      'vue-x-axios', 
      'epic-spinners'
    ]
    ......
}
```

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run serve

# build for production with minification
npm run build
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
