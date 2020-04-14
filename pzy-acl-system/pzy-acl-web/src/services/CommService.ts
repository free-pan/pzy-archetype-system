import {LoginParam, LoginHeader, ResetPasswordParam} from '@/utils/Type'
import GlobalConstant from '@/utils/GlobalConstant'
import AjaxUtil from '@/utils/AjaxUtil'

const {apiPrefix} = GlobalConstant;

const basePath = `${apiPrefix}/pu`;
const apiUrl = {
    login: `${basePath}/login`,
    logout: `${basePath}/login`,
    resetPwd: `${basePath}/reset-pwd`,
    sendResetPwdVerifyCode: `${basePath}/send-reset-pwd-verify-code`
}

export default {
    /**
     * 登录
     * @param loginParam 登录参数
     * @param headers 请求头参数
     * @return {Promise<RequestResponse<any>>}
     */
    login$(loginParam: LoginParam, loginHeader: LoginHeader) {
        return AjaxUtil.bodyPost(apiUrl.login, loginParam, {headers: loginHeader})
    },

    /**
     * 登出
     * @return {Promise<RequestResponse<any>>}
     */
    logout$() {
        return AjaxUtil.bodyPost(apiUrl.logout)
    },

    /**
     * 重置密码
     * @param resetPasswordParam 请求参数
     */
    resetPwd$(resetPasswordParam: ResetPasswordParam) {
        return AjaxUtil.bodyPost(apiUrl.resetPwd, resetPasswordParam)
    },

    /**
     * 发送重置密码需要的验证码
     * @param email 接收验证码的邮箱
     */
    sendResetPwdVerifyCode$(email: string) {
        return AjaxUtil.formPost(apiUrl.sendResetPwdVerifyCode, {email: email})
    }
}