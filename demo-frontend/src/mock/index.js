import Mock from 'mockjs'
import user from './user'
import menu from './menu'

Mock.mock(/\/api\/v1\/user\/login/, 'post', user.login)
Mock.mock(/\/api\/v1\/menu/, 'get', menu.menu)
