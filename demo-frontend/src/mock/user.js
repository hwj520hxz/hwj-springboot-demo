// 用户数据模拟
const user = {
  'id': 3,
  'email': '946424684@qq.com',
  'avatar': null,
  'phone': '13645001431',
  'gender': 1,
  'birthday': '1992-01-09 00:00:00',
  'userCode': 'lwh',
  'userName': '罗维辉',
  'password': 'ec3f009578a4d37d1b7ce0f0a60b02b2',
  'isDeleted': 0,
  'isEnabled': 1,
  'isLocked': 0,
  'remark': null,
  'createBy': '罗维辉',
  'createUserId': 3,
  'createTime': '2019-06-08 14:53:33',
  'updateBy': null,
  'updateUserId': null,
  'updateTime': null,
  'sortCode': 1
}

export default {
  login: config => {
    return {
      success: true,
      data: user
    }
  }
}
