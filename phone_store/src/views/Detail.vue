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

        <van-submit-bar
                :price="data.amount*100+data.freight*100"
                button-text="确认付款"
                @submit="onSubmit" />
    </div>

</template>

<script>
    export default {
        data(){
            return{
                data:''
            }
        },
        methods:{
            onSubmit(){
                axios.put(`http://localhost:8181/order/pay/${this.$route.query.orderId}`).then(response =>{
                    if (response.data.code === 0){
                        this.$router.push(`/success?orderId=${this.data.orderId}&amount=${this.data.amount+this.data.freight}`)
                    }
                })
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