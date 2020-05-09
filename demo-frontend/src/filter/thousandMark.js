export default function (value) {
  value += ''
  if (!value.includes('.')) value += '.'
  return value.replace(/(\d)(?=(\d{3})+\.)/g, function ($0, $1) {
    return $1 + ','
  }).replace(/\.$/, '')
}
