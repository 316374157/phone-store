<template>
    <div>
        <van-cell-group class="goods-cell-group">
            <van-cell>
                <van-col span="14"><van-icon name="location-o"></van-icon>{{data.buyerName}}</van-col>
                <van-col span="10">{{data.tel}}</van-col>
                <van-col span="20">{{data.address}}</van-col>
            </van-cell>
        </van-cell-group>

        <van-card
                :num="data.num"
                :price="data.price"
                :desc="data.specs"
                :title="data.phoneName"
                :thumb="data.icon"
        />

        <van-cell-group class="goods-cell-group">
            <van-cell class="goods-express">
                <van-col span="16">配送方式</van-col>
                <van-col span="8">快递</van-col>
            </van-cell>
        </van-cell-group>

        <van-cell-group class="goods-cell-group">
            <van-cell class="goods-express">
                <van-col span="16" style="font-weight: bold">商品金额</van-col>
                <van-col span="8" style="color: red;font-weight: bold">￥{{data.amount}}</van-col>
            </van-cell>
        </van-cell-group>
        <van-cell-group class="goods-cell-group">
            <van-cell class="goods-express">
                <van-col span="16" style="font-weight: bold">运费</van-col>
                <van-col span="8" style="color: red;font-weight: bold">￥{{data.freight}}</van-col>
            </van-cell>
        </van-cell-group>
        <van-cell-group class="goods-cell-group">
            <van-cell class="goods-express">
                <van-col span="16" style="font-weight: bold">合计</van-col>
                <van-col span="8" style="color: red;font-weight: bold">￥{{data.amount+data.freight}}</van-col>
            </van-cell>
        </van-cell-group>
        <van-cell-group class="goods-cell-group">
            <van-cell class="goods-express">
                <van-col span="16" style="font-weight: bold">订单状态</van-col>
                <van-col span="8" style="color: red;font-weight: bold">{{data.payStatus === 0?"未支付":"已支付"}}</van-col>
            </van-cell>
        </van-cell-group>
        <br/>
        <br/>
        <van-button  plain type="info" size="large" @click="back">返回首页</van-button>
    </div>

</template>

<script>
    export default {
        data(){
            return{
                data:null,
            }
        },
        methods:{
            onSubmit(){
                this.$router.push(`/success?orderId=${this.data.orderId}&amount=${this.data.amount+this.data.freight}`)
            },
            back(){
                this.$router.push("/")
            }
        },
        created() {
            axios.get(`http://localhost:8181/order/detail/${this.$route.query.orderId}`).then(response =>{
                this.data = response.data.data
            })
        }
    }
</script>

<style scoped>

</style>