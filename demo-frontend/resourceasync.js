var fs = require('fs')
var path = require('path')
var copy = function (src, dst) {
  let paths = fs.readdirSync(src) // 同步读取当前目录
  paths.forEach(function (path, index) {
    var _src = src + '/' + path
    var _dst = dst + '/' + path
    fs.stat(_src, function (err, stats) { // stats  该对象 包含文件属性
      if (err) throw err
      if (stats.isFile()) { // 如果是个文件则拷贝
        let readable = fs.createReadStream(_src)// 创建读取流
        let writable = fs.createWriteStream(_dst)// 创建写入流
        readable.pipe(writable)
      } else if (stats.isDirectory()) { // 是目录则 递归
        checkDirectory(_src, _dst, copy)
      }
    })
  })
}
var checkDirectory = function (src, dst, callback) {
  fs.access(dst, fs.constants.F_OK, (err) => {
    if (err) {
      fs.mkdirSync(dst)
      callback(src, dst)
    } else {
      callback(src, dst)
    }
  })
}
console.time('耗时')
console.info('开始整合资源文件...')
checkDirectory('dist', path.resolve('../demo-user/demo-user-server/src/main/resources/static'), copy)
console.info('资源文件整合完成！')
console.timeEnd('耗时')
