<template>
  <div class="base-login">
    <div class="middle-panel">
      <h2 class="system-title">
        <img src="img/login-logo.png" />
        <div>
          <div class="title">PC端通用项目框架模板</div>
          <div class="sub-title">PC Universal Framework Of Project Template</div>
        </div>
      </h2>
      <div class="login-container hvr-overline-from-center">
        <el-form
          ref="form"
          :model="user"
          label-width="40px"
          style="width: 300px;"
          label-position="left"
        >
          <el-form-item label="账号">
            <el-input autofocus v-model.trim="user.account" @keyup.enter.native="login"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input type="password" v-model.trim="user.password" @keyup.enter.native="login"></el-input>
          </el-form-item>
          <el-form-item>
            <el-checkbox v-model="remember" label="记住密码"></el-checkbox>
          </el-form-item>
          <el-form-item>
            <el-button round @click="reset">重置</el-button>
            <el-button round type="primary" @click="login" :loading="loading">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="bottom-panel">
      <h6 class="browser-advice">建议浏览器：Chrome</h6>
      <h6 class="copyright">
        技术支持：福建博思软件股份有限公司
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        联系电话：0591-23537869
      </h6>
    </div>
    <div class="bg-footer"></div>
  </div>
</template>

<script>
import moment from 'moment'
import { mapMutations } from 'vuex'
export default {
  name: 'BaseLogin',
  data () {
    return {
      loading: false,
      user: {
        account: null,
        password: null
      },
      remember: false
    }
  },
  mounted () {
    this.loadAccount()
  },
  methods: {
    ...mapMutations({
      setLocalStorageItem: 'storage/setLocalStorageItem',
      removeLocalStorageItem: 'storage/removeLocalStorageItem'
    }),
    validBeforeLogin () {
      if (!this.user.account) {
        this.$notify.warning({ message: '请输入账号！' })
        return false
      }
      if (!this.user.password) {
        this.$notify.warning({ message: '请输入密码！' })
        return false
      }
      return true
    },
    async login () {
      this.loading = true
      this.$notify.closeAll()
      if (!this.validBeforeLogin()) {
        this.loading = false
        return false
      }
      try {
        let user = await this.$store
          .dispatch('user/loginByAccount', {
            account: this.user.account,
            password: this.user.password,
            encrypted: this.user.password.length > 30
          })
        this.loading = false
        if (this.remember) {
          this.setLocalStorageItem({ name: 'user', value: JSON.stringify(user) })
          this.setLocalStorageItem({ name: 'expireTime', value: moment().add(7, 'd').format('YYYY-MM-DD HH:mm:ss') })
        } else {
          this.setLocalStorageItem({ name: 'user', value: JSON.stringify(user) })
          this.setLocalStorageItem({ name: 'expireTime', value: moment().add(30, 'm').format('YYYY-MM-DD HH:mm:ss') })
        }
        let router = this.$router
        if (router.currentRoute.params.module) {
          router.replace({ path: '/' + router.currentRoute.params.module, query: router.currentRoute.query })
        } else {
          this.$store.getters['menu/firEndMenu'] && router.replace({ path: `/${this.$store.getters['menu/firEndMenu'].code}` })
        }
      } catch (e) {
        this.loading = false
        this.$notify.error({ message: e.message })
      }
    },
    reset () {
      this.user.account = null
      this.user.password = null
      this.remember = false
    },
    loadAccount () {
      this.$store.commit('storage/initStorage', null, { root: true })
      let localStorage = this.$store.state.storage.localStorage
      let remember = localStorage.remember
      this.remember = remember === 'true'
      if (this.remember) {
        let { account, password } = JSON.parse(localStorage.user || '{}')
        this.user.account = account
        this.user.password = password
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.base-login {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  background-size: cover;
  background-position: 50%;
  background-repeat: no-repeat;
  background-image: url("~public/img/login-bg.jpg");
  .middle-panel {
    flex: 5;
    z-index: 1;
    .system-title {
      display: flex;
      color: #515a6e;
      font-weight: 500;
      font-size: 32px;
      margin: 100px;
      letter-spacing: 2px;
      img {
        margin: 0 10px 0 0;
        vertical-align: bottom;
      }
      .sub-title {
        font-size: 12px;
        text-align: center;
        letter-spacing: 2.1px;
      }
    }
    .login-container {
      width: 500px;
      height: 350px;
      border-radius: 5px;
      margin: 0 90px;
      display: flex;
      align-items: center;
      justify-content: center;
      border: 1px solid #dedede;
      background: linear-gradient(rgba(253, 254, 254, 0.192), #fdfefe);
      .el-input {
        border: none;
        border-bottom: 1px solid #dedede;
      }
      /deep/ {
        input {
          border: none;
          background-color: transparent;
        }
        input:-webkit-autofill {
          box-shadow: 0 0 0px 1000px #fff inset;
        }
      }
    }
  }
  .bottom-panel {
    position: absolute;
    bottom: 10px;
    left: 50%;
    z-index: 1;
    margin-left: -210px;
    .browser-advice {
      color: #515a6e;
      text-align: center;
      font-weight: 500;
      font-size: 12px;
      margin: 0;
    }
    .copyright {
      color: #515a6e;
      text-align: center;
      font-weight: 500;
      font-size: 12px;
      margin: 10px 0 0 0;
    }
  }
  .bg-footer {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 260px;
    height: 90px;
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
    background-image: url("~public/img/login-footer.png");
  }
}
</style>
