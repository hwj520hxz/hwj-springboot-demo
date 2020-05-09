export const validateNumber = (rule, value, next) => {
  if (!value && value !== 0) {
    next()
  } else if (!/^(-?\d+)$/.test(value)) {
    next('请输入整数！')
  } else if (value <= 0) {
    next('请输入大于0的整数！')
  } else {
    next()
  }
}
