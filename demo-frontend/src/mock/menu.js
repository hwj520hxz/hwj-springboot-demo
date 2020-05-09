// 菜单数据模拟
const menus = [{
  id: 1,
  parentId: null,
  code: 'demo',
  name: 'demo',
  target: 'component',
  component: 'demo',
  sortCode: 1
}]
export { menus }
export default {
  menu: config => {
    return {
      success: true,
      data: menus
    }
  }
}
