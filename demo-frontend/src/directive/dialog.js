import $ from 'jquery'
export default {
  bind (el, binding, vnode, oldVnode) {
    const dialogHeaderEl = el.querySelector('.el-dialog__header')
    const dragDom = el.querySelector('.el-dialog')
    const sty = (function () {
      if (document.body.currentStyle) {
        // 在ie下兼容写法
        return (dom, attr) => dom.currentStyle[attr]
      } else {
        return (dom, attr) => getComputedStyle(dom, false)[attr]
      }
    })()
    const dragFunc = e => {
      // 鼠标按下，计算当前元素距离可视区的距离
      const disX = e.clientX - dialogHeaderEl.offsetLeft
      const disY = e.clientY - dialogHeaderEl.offsetTop
      const screenWidth = document.body.clientWidth // body当前宽度
      const screenHeight = document.documentElement.clientHeight // 可见区域高度(应为body高度，可某些环境下无法获取)
      const dragDomWidth = dragDom.offsetWidth // 对话框宽度
      const dragDomheight = dragDom.offsetHeight // 对话框高度
      const minDragDomLeft = dragDom.offsetLeft
      const maxDragDomLeft = screenWidth - dragDom.offsetLeft - dragDomWidth
      const minDragDomTop = dragDom.offsetTop
      const maxDragDomTop = screenHeight - dragDom.offsetTop - dragDomheight
      // 获取到的值带px 正则匹配替换
      let styL = sty(dragDom, 'left')
      // 为兼容ie
      if (styL === 'auto') styL = '0px'
      let styT = sty(dragDom, 'top')
      // 注意在ie中 第一次获取到的值为组件自带50% 移动之后赋值为px
      if (styL.includes('%')) {
        styL = +document.body.clientWidth * (+styL.replace(/%/g, '') / 100)
        styT = +document.body.clientHeight * (+styT.replace(/%/g, '') / 100)
      } else {
        styL = +styL.replace(/px/g, '')
        styT = +styT.replace(/px/g, '')
      }
      document.onmousemove = function (e) {
        // 通过事件委托，计算移动的距离
        let left = e.clientX - disX
        let top = e.clientY - disY
        // 边界处理
        if (-(left) > minDragDomLeft) {
          left = -(minDragDomLeft)
        } else if (left > maxDragDomLeft) {
          left = maxDragDomLeft
        }
        if (-(top) > minDragDomTop) {
          top = -(minDragDomTop)
        } else if (top > maxDragDomTop) {
          top = maxDragDomTop
        }
        // 移动当前元素
        dragDom.style.cssText += `;left:${left + styL}px;top:${top + styT}px;`
      }
      document.onmouseup = function (e) {
        document.onmousemove = null
        document.onmouseup = null
      }
      return false
    }
    // 自定义属性，判断是否可拖拽
    if (binding.value ?.drag !== false) {
      dialogHeaderEl.style.cssText += ';cursor:move;'
      dragDom.style.cssText += ';top:0px;'
      dialogHeaderEl.onmousedown = dragFunc
    }
    if (binding.value ?.fullscreen !== false) {
      // 初始非全屏
      let isFullScreen = false
      // 当前样式
      let nowStyle
      let maxButton = document.createElement('i')
      maxButton.className = 'el-icon-full-screen'
      maxButton.style.cssText = 'top:21px;right:40px;cursor:pointer;position:absolute;color:#8492A6;'
      maxButton.onmouseover = function () {
        this.style.color = '#1AA1FF'
      }
      maxButton.onmouseout = function () {
        this.style.color = '#8492A6'
      }
      maxButton.onclick = (e) => {
        if (isFullScreen === false) {
          isFullScreen = true
          maxButton.className = 'fa fa-window-restore'
          $(dialogHeaderEl).find('.el-icon-minus').hide()
          dialogHeaderEl.style.cursor = 'initial'
          dialogHeaderEl.onmousedown = null
          nowStyle = dragDom.getAttribute('style')
          dragDom.style.cssText = 'left: 0;top: 0;height: 100VH;width: 100VW;margin-top: 0;'
        } else {
          isFullScreen = false
          maxButton.className = 'el-icon-full-screen'
          $(dialogHeaderEl).find('.el-icon-minus').show()
          if (binding.value ?.drag !== false) {
            dialogHeaderEl.style.cursor = 'move'
            dialogHeaderEl.onmousedown = dragFunc
          }
          dragDom.setAttribute('style', nowStyle)
        }
      }
      $(dialogHeaderEl).find('.el-dialog__headerbtn').before(maxButton)
    }
    if (binding.value ?.fullscreen !== false) {
      // 初始非最小化
      let isMiniScreen = false
      // 当前样式
      let nowStyle
      let minButton = document.createElement('i')
      minButton.className = 'el-icon-minus'
      minButton.style.cssText = 'top:21px;right:60px;cursor:pointer;position:absolute;color:#8492A6;'
      minButton.onmouseover = function () {
        this.style.color = '#1AA1FF'
      }
      minButton.onmouseout = function () {
        this.style.color = '#8492A6'
      }
      minButton.onclick = (e) => {
        if (isMiniScreen === false) {
          isMiniScreen = true
          minButton.style.right = '40px'
          minButton.className = 'fa fa-window-restore'
          $(dialogHeaderEl).find('.el-icon-full-screen').hide()
          $(dialogHeaderEl).find('.title').width(200).find('span').addClass('text-over')
          nowStyle = dragDom.getAttribute('style')
          dragDom.style.cssText = 'left:0;top:calc(100% - 50px);height: 50px;width: 300px;overflow: hidden;margin: 0;'
        } else {
          isMiniScreen = false
          minButton.style.right = '60px'
          minButton.className = 'el-icon-minus'
          dragDom.setAttribute('style', nowStyle)
          $(dialogHeaderEl).find('.el-icon-full-screen').show()
          $(dialogHeaderEl).find('.title').width('auto').find('span').removeClass('text-over')
        }
      }
      $(dialogHeaderEl).find('.el-dialog__headerbtn').before(minButton)
    }
    if (binding.value ?.resize === true) {
      dragDom.onmousemove = function (e) {
        if (e.clientX > dragDom.offsetLeft + dragDom.clientWidth - 10 || dragDom.offsetLeft + 10 > e.clientX) {
          dragDom.style.cursor = 'w-resize'
        } else if (el.scrollTop + e.clientY > dragDom.offsetTop + dragDom.clientHeight - 10) {
          dragDom.style.cursor = 's-resize'
        } else {
          dragDom.style.cursor = 'default'

          dragDom.onmousedown = null
        }
        dragDom.onmousedown = (e) => {
          let minWidth = dragDom.clientWidth
          let minHeight = dragDom.clientHeight
          const clientX = e.clientX
          const clientY = e.clientY
          let elW = dragDom.clientWidth
          let elH = dragDom.clientHeight
          let EloffsetLeft = dragDom.offsetLeft
          let EloffsetTop = dragDom.offsetTop
          dragDom.style.userSelect = 'none'
          let ELscrollTop = el.scrollTop
          // 判断点击的位置是不是为头部
          if (clientX > EloffsetLeft && clientX < EloffsetLeft + elW && clientY > EloffsetTop && clientY < EloffsetTop + 100) {
            // 如果是头部在此就不做任何动作，以上有绑定dialogHeaderEl.onmousedown = moveDown;
          } else {
            document.onmousemove = function (e) {
              e.preventDefault() // 移动时禁用默认事件
              // 左侧鼠标拖拽位置
              if (clientX > EloffsetLeft && clientX < EloffsetLeft + 10) {
                // 往左拖拽
                if (clientX > e.clientX) {
                  dragDom.style.width = elW + (clientX - e.clientX) * 2 + 'px'
                }
                // 往右拖拽
                if (clientX < e.clientX) {
                  if (dragDom.clientWidth < minWidth) {} else {
                    dragDom.style.width = elW - (e.clientX - clientX) * 2 + 'px'
                  }
                }
              }
              // 右侧鼠标拖拽位置
              if (clientX > EloffsetLeft + elW - 10 && clientX < EloffsetLeft + elW) {
                // 往左拖拽
                if (clientX > e.clientX) {
                  if (dragDom.clientWidth < minWidth) {} else {
                    dragDom.style.width = elW - (clientX - e.clientX) * 2 + 'px'
                  }
                }
                // 往右拖拽
                if (clientX < e.clientX) {
                  dragDom.style.width = elW + (e.clientX - clientX) * 2 + 'px'
                }
              }
              // 底部鼠标拖拽位置
              if (ELscrollTop + clientY > EloffsetTop + elH - 20 && ELscrollTop + clientY < EloffsetTop + elH) {
                // 往上拖拽
                if (clientY > e.clientY) {
                  if (dragDom.clientHeight < minHeight) {

                  } else {
                    dragDom.style.height = elH - (clientY - e.clientY) * 2 + 'px'
                  }
                }
                // 往下拖拽
                if (clientY < e.clientY) {
                  dragDom.style.height = elH + (e.clientY - clientY) * 2 + 'px'
                }
              }
            }
            // 拉伸结束
            document.onmouseup = function (e) {
              document.onmousemove = null
              document.onmouseup = null
            }
          }
        }
      }
    }
  }
}
