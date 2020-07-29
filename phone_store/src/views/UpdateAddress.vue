<template>
    <van-address-edit
            :area-list="areaList"
            :address-info="addressInfo"
            save-button-text="修改"
            show-delete
            :area-columns-placeholder="['请选择', '请选择', '请选择']"
            @save="onSave"
            @delete="onDelete"
    />
</template>

<script>
    import AreaList from '../api/area'
    export default {
        data() {
            return {
                areaList:AreaList,
                addressInfo:null
            };
        },
        methods: {
            onSave(item) {
                axios.put('http://localhost:8181/address/update',item).then(response =>{
                    if (response.data.code === 0){
                        this.$router.push('addressList')
                    }
                })
            },
            onDelete() {
                history.go(-1)
            }
        },
        created() {
            this.addressInfo = JSON.parse(this.$route.query.item);
            let index = JSON.parse(this.$route.query.item).address.indexOf('区');
            if (index<0) index = JSON.parse(this.$route.query.item).address.indexOf('县');
            this.addressInfo.addressDetail = JSON.parse(this.$route.query.item).address.substring(index+1)
        }
    };
</script>

<style scoped>

</style>