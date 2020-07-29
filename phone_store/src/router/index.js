import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import AddressList from "../views/AddressList";
import AddAddress from "../views/AddAddress";
import UpdateAddress from "../views/UpdateAddress";
import Detail from "../views/Detail";
import Success from "../views/Success";
import Info from "../views/Info";

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/addressList',
    name: '管理收货地址',
    component: AddressList
  },
  {
    path: '/addAddress',
    name: '新增收货地址',
    component: AddAddress
  },
  {
    path: '/updateAddress',
    name: '修改收货地址',
    component: UpdateAddress
  },
  {
    path: '/detail',
    name: '订单详情',
    component: Detail
  },
  {
    path: '/success',
    name: '支付成功',
    component: Success
  },
  {
    path: '/info',
    name: '订单详情',
    component: Info
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
