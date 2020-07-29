<template>
    <div>
        <el-container style="height: auto; border: 1px solid #eee">


            <el-header style="text-align: right; font-size: 12px">
                <el-dropdown>
                    <i class="el-icon-setting" style="margin-right: 15px"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>查看</el-dropdown-item>
                        <el-dropdown-item>新增</el-dropdown-item>
                        <el-dropdown-item>删除</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span>王小虎</span>

            </el-header>

            <el-container>

                <el-aside width="18%" style="background-color: rgb(238, 241, 246)">

                    <el-menu router :default-openeds="['0']">

                        <el-submenu v-for="(item,index) in $router.options.routes" :index="index+''">
                            <template slot="title"><i class="el-icon-message"></i>{{item.name}}</template>
                            <el-menu-item v-for="child in item.children" :index="child.path"
                                          :class="$route.path === child.path?'is-active':''">{{child.name}}</el-menu-item>
                        </el-submenu>

                    </el-menu>

                </el-aside>

                <el-main>
                    <router-view></router-view>
                </el-main>

            </el-container>

        </el-container>
    </div>
</template>

<style>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }
</style>

<script>
    export default {
        data() {
            const item = {
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            };
            return {
                tableData: Array(20).fill(item)
            }
        }
    };
</script>