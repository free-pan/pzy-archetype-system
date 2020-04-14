import React, {PureComponent} from 'react';
import PropTypes from 'prop-types';
import styles from '@/layouts/LoginLayout.less'

import {
  Form, Icon, Input, Button, Checkbox,
} from 'antd';

class Login extends PureComponent {

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
    });
  }

  onForgetPassword = ()=>{
    this.props.onForgetPassword();
  }

  render() {
    const {getFieldDecorator} = this.props.form;
    return (
      <div>
        <Form onSubmit={this.handleSubmit} className="login-form">
          <Form.Item>
            {getFieldDecorator('email', {
              rules: [{required: true, message: 'Please input your username!'}],
            })(
              <Input prefix={<Icon type="mail" style={{color: 'rgba(0,0,0,.25)'}}/>} placeholder="邮箱"/>
            )}
          </Form.Item>
          <Form.Item>
            {getFieldDecorator('password', {
              rules: [{required: true, message: 'Please input your Password!'}],
            })(
              <Input prefix={<Icon type="lock" style={{color: 'rgba(0,0,0,.25)'}}/>} type="password"
                     placeholder="密码"/>
            )}
          </Form.Item>
          <Form.Item>
            {getFieldDecorator('remember', {
              valuePropName: 'checked',
              initialValue: true,
            })(
              <Checkbox style={{float:'left'}}>自动登录</Checkbox>
            )}
            <a className={styles['login-form-forgot']} onClick={this.onForgetPassword}>忘记密码</a>
            <Button type="primary" htmlType="submit" className={styles['login-form-button']}>
              登录
            </Button>
          </Form.Item>
        </Form>
      </div>
    );
  }
}

Login.propTypes = {
  onForgetPassword: PropTypes.func.isRequired
}

const WrappedNormalLoginForm = Form.create()(Login);

export default WrappedNormalLoginForm;
