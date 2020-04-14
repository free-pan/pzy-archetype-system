import React, {PureComponent} from 'react';
import {Tabs} from 'antd';

const TabPane = Tabs.TabPane;

import PropTypes from 'prop-types';
import styles from './LoginLayout.less'

import Login from '@/pages/Login'
import Register from '@/pages/Register'
import ForgetPassword from '@/pages/ForgetPassword'

class LoginLayout extends PureComponent {

  state = {
    model: 'normal'
  }
  onTabChange = (key) => {
  }

  onForgetPassword = ()=>{
    this.setState({model:'forgetPassword'});
  }

  onBackLogin = ()=>{
    this.setState({model:'normal'});
  }

  render() {
    const {model} = this.state;
    const tabPanelArr = [];
    let defaultActiveKey = '1';
    if (model === 'normal') {
      tabPanelArr.push(
        <TabPane tab="邮箱密码登录" key="1">
          <Login onForgetPassword={this.onForgetPassword}/>
        </TabPane>
      )
      tabPanelArr.push(
        <TabPane tab="使用邮箱注册" key="2">
          <Register/>
        </TabPane>
      )
    } else {
      defaultActiveKey = '3'
      tabPanelArr.push(
        <TabPane tab="忘记密码" key="3">
          <ForgetPassword onBackLogin={this.onBackLogin}/>
        </TabPane>
      )
    }
    return (
      <div className={styles.container}>
        <div className={styles.mainPanel}>
          <h1>基础权限系统骨架</h1>
          <small>让开发更快速, 工作更轻松, 代码更标准</small>
          <Tabs defaultActiveKey={defaultActiveKey} onChange={this.onTabChange} className={styles.customSwitchPanel}>
            {tabPanelArr}
          </Tabs>
        </div>
        <div className={styles.copyrightPanel}>Copyright &copy; 2019 By 潘志勇</div>
      </div>
    );
  }
}

LoginLayout.propTypes = {};

export default LoginLayout;