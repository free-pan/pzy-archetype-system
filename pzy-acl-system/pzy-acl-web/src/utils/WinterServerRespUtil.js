import {
  warnNotification,
  errorNotification,
  successNotification,
  infoNotification,
  warn,
  error,
  success,
  info
} from './MessageTipsUtil'

const OK_CODE = '200';
const NEED_LOGIN_CODE = "SECURITY_UNAUTHORIZED_EXCEPTION"
/**
 * 默认分页大小
 * @type {number}
 */
const DEFAULT_SIZE = 10;
/**
 * 默认页号(默认页数)
 * @type {number}
 */
const DEFAULT_PAGE = 1;

function printFullResp(serverResp) {
  console.log('后端服务完整响应内容:', serverResp)
}

/**
 * 验证web api是否执行成功
 * @param serverResp web api服务完整响应内容
 * @param showErrorTips 当api未执行成功时, 是否显示错误提示. 默认true
 * @return {boolean} web api是否执行成功
 */
export function apiSuccess(serverResp, showErrorTips = true) {
  printFullResp(serverResp)
  if (!serverResp) {
    return false;
  }
  if (!serverResp.success) {
    // 显示错误提示
    error(serverResp.msgList)
  }
  return serverResp.success;
}

/**
 * 提取web api返回的数据结果
 * @param serverResp web api服务完整响应内容
 * @return 数据结果
 */
export function extractDataResult(serverResp) {
  if (!serverResp) {
    return;
  }
  return serverResp.resp
}

/**
 * 将 ant design 的 Pagination 组件的分页条件, 转换为 web api 需要要的分页数据结构
 * @param pagination
 * @return {{size: number, page: number}|{size: number, page: *}}
 */
export function pagination2WebApiPage(pagination) {
  if (!pagination) {
    return {
      page: DEFAULT_PAGE,
      size: DEFAULT_SIZE
    }
  } else {
    return {
      page: pagination.current,
      size: pagination.pageSize
    }
  }
}

/**
 * 将web api返回的分页结果, 转换为ant design的 Pagination 组件需要的分页数据结构
 * @param webApiPageResult
 */
export function convert2AntPagination(webApiPageResult) {
  if (!webApiPageResult) {
    return {
      /**
       * 当前页号(当前页数)
       */
      current: DEFAULT_PAGE,
      /**
       * 每页条数
       */
      pageSize: DEFAULT_SIZE,
      /**
       * 数据总数
       */
      total: 0
    }
  } else {
    return {
      /**
       * 当前页数
       */
      current: webApiPageResult.page,
      /**
       * 每页条数
       */
      pageSize: webApiPageResult.size,
      /**
       * 数据总数
       */
      total: webApiPageResult.total
    }
  }
}

/**
 * 当前是否需要先登录
 * @param serverResp
 * @return {boolean}
 */
export function isNeedLogin(serverResp) {
  return serverResp.code === NEED_LOGIN_CODE
}