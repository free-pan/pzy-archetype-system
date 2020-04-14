import commService from '@/services/CommService'

export default {
  namespace: 'GlobalModel',
  state: {},
  reducers: {},
  effects: {
    *login({payload: {loginParam, loginHeader}}, {call, put}) {
      const {data, headers} = yield call(commService.login$, {loginParam, loginHeader});
    }
  }
}