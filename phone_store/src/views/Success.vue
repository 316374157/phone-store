<template>
    <div>
        <van-image
                width="100%"
                height="300"
                src="../static/paySuccess.png"
        />
        <van-cell :title="amount" style="font-weight: bold"/>
        <van-cell-group class="goods-cell-group">
            <van-cell class="goods-express">
                <van-col span="16">配送方式</van-col>
                <van-col span="8">快递</van-col>
            </van-cell>
        </van-cell-group>
        <van-button type="info" size="large" @click="showInfo">查看订单信息</van-button>
        <br/>
        <br/>
        <van-button  plain type="info" size="large" @click="back">返回首页</van-button>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                amount:null,
                orderId:null,
            }
        },
        methods:{
            showInfo(){
                this.$router.push(`/info?orderId=${this.orderId}`)
            },
            back(){
                this.$router.push("/")
            }
        },
        created() {
            this.amount = '￥'+this.$route.query.amount;
            this.orderId = this.$route.query.orderId;
        }
    }
</script>

<style scoped>

</style>