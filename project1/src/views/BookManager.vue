<template>
    <div>
        <!-- Form -->
        <el-dialog title="更新用户" :visible.sync="dialogFormVisible">
            <el-form style="width: 80%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="书名" prop="title">
                    <el-input v-model="ruleForm.title"></el-input>
                </el-form-item>
                <el-form-item label="作者" prop="author">
                    <el-input v-model="ruleForm.author"></el-input>
                </el-form-item>
                <el-form-item label="价格" prop="price">
                    <el-input type="float" v-model.float="ruleForm.price"></el-input>
                </el-form-item>
                <el-form-item label="库存" prop="stock">
                    <el-input type="number" v-model.number="ruleForm.stock"></el-input>
                </el-form-item>
                <el-form-item label="销量" prop="sales">
                    <el-input type="number" v-model.number="ruleForm.sales" readonly></el-input>
                </el-form-item>
                <el-form-item label="编号" prop="id" hidden>
                    <el-input type="number" v-model.number="ruleForm.id" readonly hidden></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">更新</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-table
                :data="books"
                border
                style="width: 100%">
            <el-table-column
                    fixed="left"
                    prop="id"
                    label="编号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="title"
                    label="书名"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="author"
                    label="作者"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="价格"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="sales"
                    label="销量"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="stock"
                    label="库存"
                    width="120">
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="editClick(scope.row)" type="primary" icon="el-icon-edit" circle size="mini"></el-button>
                    <el-button @click="deleteClick(scope.row)" type="danger" icon="el-icon-delete" circle size="mini"></el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination
                background
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :page-sizes="[5,10]"
                :page-size="pageSize"
                :current-page="pageNow"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        methods: {
            editClick(row) {
                this.dialogFormVisible=true;
                this.ruleForm.id = row.id;
                this.ruleForm.title = row.title;
                this.ruleForm.author = row.author;
                this.ruleForm.price = row.price;
                this.ruleForm.sales = row.sales;
                this.ruleForm.stock = row.stock;
            },
            deleteClick(row){
                this.$confirm('此操作将删除该书籍, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(() => {
                    axios.delete(`http://localhost:8181/book/delete/${row.id}`).then(response =>{
                        this.$alert(`《${row.title}》${response.data.extend.msg}`, "提示", {
                            confirmButtonText: '确定',
                            type:'success',
                            callback: action => {
                                this.getPage(this.pageNow,this.pageSize);
                            }
                        });
                    })
                }).catch(() => {});
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.getPage(this.pageNow,this.pageSize);
            },
            handleCurrentChange(page) {
                this.pageNow = page;
                this.getPage(this.pageNow,this.pageSize);
            },
            getPage(pageNow,size){
                axios.get(`http://localhost:8181/book/getPage/${pageNow}/${size}`).then(response => {
                    this.books = response.data.extend.bookPage.records;
                    this.total = response.data.extend.bookPage.total;
                    this.pageSize = response.data.extend.bookPage.size;
                    this.pageNow = response.data.extend.bookPage.current;
                })
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8181/book/update',this.ruleForm).then(response =>{
                            this.$alert(`《${this.ruleForm.title}》${response.data.extend.msg}`, "提示", {
                                confirmButtonText: '确定',
                                type:'success',
                                callback: action => {
                                    this.getPage(this.pageNow,this.pageSize);
                                    this.dialogFormVisible=false;
                                }
                            });
                        })
                    }
                });
            }
        },
        created() {
            this.getPage(1,5);
        },
        data() {
            return {
                total:null,
                pageSize:null,
                books: null,
                pageNow:null,
                dialogFormVisible:false,
                ruleForm: {
                    id:'',
                    title: '',
                    author: '',
                    price: '',
                    sales: '',
                    stock: ''
                },
                rules: {
                    title: [
                        { required: true, message: '请输入书名', trigger: 'blur' }
                    ],
                    author: [
                        { required: true, message: '请输入作者姓名', trigger: 'blur' }
                    ],
                    price: [
                        { required: true, message: '请输入价格', trigger: 'blur' },
                        {
                            validator(rule, value, callback) {
                                const reg = /^(0\.?\d{0,2}|[1-9]\d*\.?\d{0,2})$/;
                                if (reg.test(value)) {
                                    callback()
                                } else {
                                    callback(new Error('请输入非负数（保留两位小数）'))
                                }
                            },
                            trigger: 'blur'
                        }
                    ],
                    stock: [
                        { required: true, message: '请输入库存', trigger: 'blur' },
                        { type: 'number', min:0 , message: '库存必须为非负整数',trigger: 'blur'},
                    ]
                }
            }
        }
    }
</script>