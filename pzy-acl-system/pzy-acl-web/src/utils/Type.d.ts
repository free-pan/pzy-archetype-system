export interface LoginParam {
    "email": string,
    "password": string,
    "rememberMe"?: boolean
}

export interface LoginHeader {
    pic_verification_code?: string,
    verification_code_client_id?: string
}

export interface ResetPasswordParam {
    /**
     * 确认密码
     */
    confirmPwd: string,
    /**
     * 邮箱
     */
    email: string,
    /**
     * 新密码
     */
    newPwd: string,
    /**
     * 验证码
     */
    verifyCode: string
}