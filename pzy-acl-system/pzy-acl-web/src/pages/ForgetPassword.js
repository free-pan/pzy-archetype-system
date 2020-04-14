import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';
import {Button, Checkbox, Form, Icon, Input} from "antd";
import styles from "@/layouts/LoginLayout";

class ForgetPassword extends PureComponent {

 handleSubmit = (e) => {
  e.preventDefault();
  this.props.form.validateFields((err, values) => {
   if (!err) {
    console.log('Received values of form: ', values);
   }
  });
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
       {getFieldDecorator('name', {
        rules: [{required: true, message: 'Please input your Password!'}],
       })(
         <Input prefix={<Icon type="user" style={{color: 'rgba(0,0,0,.25)'}}/>} placeholder="姓名"/>
       )}
      </Form.Item>
      <Form.Item>
       <Button type="primary" htmlType="submit" style={{width:'100%'}}>
        确定
       </Button>
       <Button type="dashed" style={{width:'100%'}} onClick={this.props.onBackLogin}>
        返回
       </Button>
      </Form.Item>
     </Form>
    </div>
  );
 }
}

ForgetPassword.propTypes = {
 onBackLogin: PropTypes.func.isRequired
};
const WrappedNormalForgetPasswordForm = Form.create()(ForgetPassword);

export default WrappedNormalForgetPasswordForm;