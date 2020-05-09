<template>
  <div class="transfer-tree">
    <div class="left">
      <div class="title">{{left.config.title}}</div>
      <div class="search" v-if="left.config.queryable">
        <el-input v-model.trim="leftTree.searcher" clearable placeholder="输入关键字进行搜索"></el-input>
      </div>

      <el-tree class="tree"
               :data="leftTree.data"
               :props="leftTree.data"
               ref="leftTree"
               @check="changeAddBtnStatus"
               :filter-node-method="filterNode"
               default-expand-all
               :show-checkbox="showCheckbox">
      </el-tree>
    </div>
    <div class="middle">
      <div class="addBtn">
        <el-button @click="add" type="primary" size="large" :disabled="isAddBtnDisabled"> &gt; </el-button>
      </div>
      <div class="removeBtn">
        <el-button @click="remove" type="primary" size="large" :disabled="isRemoveBtnDisabled"> &lt; </el-button>
      </div>
    </div>
    <div class="right">
      <div class="title">{{right.config.title}}</div>
      <div class="search" v-if="right.config.queryable">
        <el-input v-model.trim="rightTree.searcher" clearable placeholder="输入关键字进行搜索"></el-input>
      </div>
      <el-tree class="tree"
               :data="rightTree.data"
               :props="rightTree.data"
               ref="rightTree"
               :filter-node-method="filterNode"
               @check="changeRemoveBtnStatus"
               default-expand-all
               show-checkbox>
      </el-tree>
    </div>
  </div>
</template>

<script>
export default {
  name: 'transfer-tree',
  props: {
    treeConfig: Object,
    left: {
      data: [],
      config: {
        title: 'left-title',
        queryable: false,
        mode: 'tree'
      }
    },
    right: {
      data: [],
      config: {
        title: 'right-title',
        queryable: false,
        mode: 'tree'
      }
    }
  },
  data () {
    return {
      leftTree: {
        searcher: null,
        selectedData: null,
        data: [{
          id: '-1',
          label: '全部',
          children: []
        }]
      },
      rightTree: {
        searcher: null,
        selectedData: null,
        data: [{
          id: '-1',
          label: '全部',
          children: []
        }]
      },
      showCheckbox: true,
      billNumLimit: 10,
      isAddBtnDisabled: true,
      isRemoveBtnDisabled: true
    }
  },
  watch: {
    'leftTree.searcher': {
      handler: function () {
        if (this.left.config.queryable) {
          this.$refs.leftTree.filter(this.leftTree.searcher)
        }
      }
    },
    'rightTree.searcher': {
      handler: function () {
        if (this.right.config.queryable) {
          this.$refs.rightTree.filter(this.rightTree.searcher)
        }
      }
    }
  },
  created () {
    this.setMyConfig()
    this.setRightData().then(this.setLeftData)
  },
  methods: {
    /**
     * 设置配置
     */
    setMyConfig () {
      this.myConfig = {
        id: this.treeConfig ?.id || 'id',
        pid: this.treeConfig ?.pid || 'pid',
        code: this.treeConfig ?.code || 'code',
        name: this.treeConfig ?.name || 'name'
      }
    },
    /**
     * 数组转换成树型结构
     */
    transArrayToTree (data) {
      let { id, pid, code, name } = this.myConfig
      let map = {}
      let node
      let tree = []
      let i
      for (i = 0; i < data.length; i++) {
        map[data[i][id]] = data[i]
        data[i].children = []
        data[i].label = ((data[i][code] ? data[i][code] : '') + ' ' + (data[i][name] ? data[i][name] : ''))
      }
      for (i = 0; i < data.length; i += 1) {
        node = data[i]
        if (node[pid] !== '0' && pid in node) {
          map[node[pid]].children.push(node)
        } else {
          tree.push(node)
        }
      }
      return tree
    },
    formatArray (data) {
      let { id, pid, code, name } = this.myConfig
      data.forEach((item) => {
        item.label = ((item[code] ? item[code] : '') + ' ' + (item[name] ? item[name] : ''))
      })
      return data
    },
    /**
     查询 财政票据库存所有的票据种类
     */
    async setLeftData () {
      let data = this.left.data
      if (data.length > 0) {
        this.showCheckbox = true
        this.leftTree.data[0].children = this.left.config.mode === 'tree' ? this.transArrayToTree(data) : this.formatArray(data)
        this.rightTree.data[0].children.forEach((item) => {
          this.disableLeaf(this.leftTree.data[0], item)
        })
      } else {
        this.showCheckbox = false
      }
    },
    /**
     查询 列入监控的票据种类
     */
    async setRightData () {
      let data = this.right.data
      this.rightTree.data[0].children = []
      let bills = data
      if (bills && bills?.length > 0) {
        bills.forEach((item, index) => {
          this.rightTree.data[0].children.push({
            id: item.id,
            label: null
          })
        })
      }
    },
    /**
     禁用节点
     */
    disableLeaf (root, node) {
      if (root.children && root.children?.length !== 0) {
        for (let i = 0; i < root.children.length; i++) {
          this.disableLeaf(root.children[i], node)
        }
        // 判断子节点是否都已禁用
        let cnt = 0
        root.children.forEach((item) => {
          if (item.disabled) {
            cnt++
          }
        })
        // 父节点禁用
        if (cnt === root.children.length) {
          root.disabled = true
        }
      } else {
        if (root.id === node.id) {
          root.disabled = true
          // 获取对应的票据名称
          node.label = root.label
        }
      }
    },
    /**
     启用节点
     */
    enableLeaf (root, node) {
      if (root.children && root.children?.length !== 0) {
        for (let i = 0; i < root.children.length; i++) {
          this.enableLeaf(root.children[i], node)
        }
        // 判断子节点是否都已启用
        let cnt = 0
        root.children.forEach((item) => {
          if (!item.disabled) {
            cnt++
          }
        })
        // 父节点启用
        if (cnt === root.children.length) {
          root.disabled = false
        }
      } else {
        if (root.id === node.id) {
          root.disabled = false
        }
      }
    },
    /**
     * 过滤树节点
     */
    filterNode (value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    /**
     添加 监控票据
     */
    add (fromData, toData, obj) {
      let addBillsTmp = this.$refs.leftTree.getCheckedNodes()
      // 过滤非叶结点
      let addBills = []
      addBillsTmp.forEach((item) => {
        if (!item.children || item.children?.length === 0) {
          addBills.push({
            id: item.id,
            label: item.label
          })
        }
      })

      let leftDataTmp = JSON.parse(JSON.stringify(this.leftTree.data[0]))
      // 添加到右边穿梭框中，并禁用左边对应的节点
      addBills.forEach((item) => {
        this.rightTree.data[0].children.push(item)
        this.disableLeaf(leftDataTmp, item)
      })
      this.leftTree.data[0].children = leftDataTmp.children
      // 更新按钮状态
      this.isAddBtnDisabled = true
    },
    /**
     移除 监控票据
     */
    remove () {
      let removeBills = this.$refs.rightTree.getCheckedNodes()
      let leftDataTmp = JSON.parse(JSON.stringify(this.leftTree.data[0]))
      removeBills.forEach((item) => {
        // 从rightTree 中剔除
        let id = item.id
        this.rightTree.data[0].children.splice(this.rightTree.data[0].children.findIndex(item => item.id === id), 1)
        // 启用左边对应的节点
        this.enableLeaf(leftDataTmp, item)
      })
      this.leftTree.data[0].children = leftDataTmp.children
      // 更新按钮状态
      this.isAddBtnDisabled = true
      this.isRemoveBtnDisabled = true
    },
    /**
     根据是否有选择节点 更新添加按钮状态
     */
    changeAddBtnStatus (currentObj, treeStatus) {
      this.isAddBtnDisabled = !treeStatus.checkedKeys.length > 0
    },
    /**
     根据是否有选择节点 更新移除按钮状态
     */
    changeRemoveBtnStatus (currentObj, treeStatus) {
      this.isRemoveBtnDisabled = !treeStatus.checkedKeys.length > 0
    }
  }
}
</script>

<style lang="scss" scoped>
  .el-button {
    border-radius: 20px;
  }
  .title {
    height: 20px;
    width: 100%;
    text-align: center;
    border: 1px solid #a2a2a2;
    border-bottom: 0px;
    background-color: #eaeaea;
    padding: 10px 0;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
  }
  .search {
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 40px;
    border: 1px solid #a2a2a2;
    border-bottom: 0px;
    border-top: 0px;
    padding: 5px 0;
    .el-input {
      flex: 10;
    }
    .searchBtn {
      flex: 0.5;
      border-radius: 0px;
    }
  }
  .el-tree {
    min-width:100%;
    font-size:14px;
    display: inline-block;
  }
  .tree {
    overflow-y:scroll;
    overflow-x: scroll;
    height: 100%;
    padding: 8px 0;
    /*width:200px;*/
    border: 1px solid #a2a2a2;
    border-top: 0px;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px;
  }
  .transfer-tree {
    height: 100%;
    width: 98%;
    padding: 0 10px;
    display: flex;
    flex-direction: row;
    .left {
      display: flex;
      flex: 5;
      flex-direction: column;
    }
    .middle {
      display: flex;
      flex: 1;
      flex-direction: column;
    }
    .right {
      display: flex;
      flex: 5;
      flex-direction: column;
    }
    .addBtn {
      margin: 120% 20px 0 20px;
      padding: 15%
    }
    .removeBtn {
      margin: 10px 20px 0 20px;
      padding: 15%
    }
  }
</style>
