<template>
    <van-address-edit
            :area-list="areaList"
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
            };
        },
        methods: {
            onSave(item) {
                axios.post('http://localhost:8181/address/create',item).then(response =>{
                    if (response.data.code === 0){
                        this.$router.push('addressList')
                    }
                })
            },
            onDelete() {
                history.go(-1)
            }
        },
    };
</script>

<style scoped>

</style>