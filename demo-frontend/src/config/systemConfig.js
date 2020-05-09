import api from '@/api'
export default {
  globalAxiosOptions: {
    baseURL: process.env.VUE_APP_BASE_URL
  },
  api
}
