<template>
  <div href="#" class="text">伟杰牛逼</div>
</template>

<script>
export default {
  name: 'demo'
}
</script>

<style lang="scss" scoped>
  .text {
    position: absolute;
    color: white;
    font-size: 400px;
    text-align: center;
    line-height: 400px;
    text-decoration: none;
    /*background: linear-gradient(to right, #73d13d, #36cfc9, #40a9ff, #597ef7, #9254de, #f759ab);*/
    background-size: 400%;
    z-index: 1; /*层叠定位*/
    animation: buttonA 2s linear infinite alternate;
  }
  @keyframes buttonA {
    0% {color: #73d13d;}
    30% {color: #36cfc9;}
    60% {color: #40a9ff;}
    90% {color: #597ef7;}
    100% {color: #9254de;}
  }
</style>
