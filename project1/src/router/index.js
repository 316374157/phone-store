import Vue from 'vue';
import VueRouter from 'vue-router';
import BookManager from "../views/BookManager";
import AddBook from "../views/AddBook";
import index from "../components/index";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: '图书管理',
        component: index,
        redirect:"/bookManager",
        children: [
            {
                path: '/bookManager',
                name: '查询图书',
                component: BookManager
            },
            {
                path: '/addBook',
                name: '添加图书',
                component: AddBook
            }
        ]
    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router
