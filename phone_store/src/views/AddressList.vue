<template>
    <van-address-list
            v-model="chosenAddressId"
            :list="list"
            @add="onAdd"
            @edit="onEdit"
            @select="onSelect"
    />

</template>

<script>
    export default {
        data() {
            return {
                chosenAddressId: '1',
                list: '',
            };
        },
        methods: {
            onAdd() {
                this.$router.push('/addAddress');
            },
            onEdit(item) {
                this.$router.push({
                    path:'/updateAddress',
                    query:{item:JSON.stringify(item)}
                });
            },
            onSelect(item){
                let orderFrom = {
                    name: item.name,
                    tel: item.tel,
                    address: item.address,
                    specsId: this.$store.state.specsId,
                    quantity: this.$store.state.quantity
                };
                axios.post('http://localhost:8181/order/create',orderFrom).then(response =>{
                    if (response.data.code === 0){
                        this.$router.push(`/detail?orderId=${response.data.data.orderId}`)
                    }
                })
            }
        },
        created() {
            axios.get('http://localhost:8181/address/list').then(response =>{
                this.list = response.data.data
            })
        }
    };
</script>

<style scoped>

</style>