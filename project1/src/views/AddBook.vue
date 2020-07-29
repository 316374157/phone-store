<template>
    <div>
        <el-form style="width: 50%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
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
            <el-form-item label="销量" prop="sales" hidden>
                <el-input type="number" v-model.number="ruleForm.sales" hidden readonly></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
                    title: '',
                    author: '',
                    price: '',
                    sales: 0,
                    stock: '',
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
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('http://localhost:8181/book/add',this.ruleForm).then(response =>{
                            this.$alert(`《${this.ruleForm.title}》${response.data.extend.msg}`, "提示", {
                                confirmButtonText: '确定',
                                type:'success',
                                callback: action => {
                                    this.$router.push('/bookManager')
                                }
                            });
                        })
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>

</style>