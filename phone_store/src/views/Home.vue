<template>
  <div>
    <van-tabs @click="chooseTag">
      <van-tab v-for="index in categories.length" :title="categories[index-1].name" class="tab"/>
    </van-tabs>
    <van-card v-for="(item,index) in phones"
            :price="item.price"
            :desc="item.desc"
            :title="item.title"
            :thumb="item.thumb"
    >
      <template #tags>
        <van-tag color="#f2826a" style="margin-left: 5px;" v-for="tag in item.tag">{{tag.name}}</van-tag>
      </template>
      <template #footer>
        <van-button round type="info" size="mini" @click="buy(index)">购买</van-button>
      </template>
    </van-card>

    <van-sku
            v-model="show"
            :sku="sku"
            :goods="goods"
            :hide-stock="sku.hide_stock"
            @buy-clicked="onBuyClicked"
    >
      <template #sku-actions="props">
        <div class="van-sku-actions">
          <van-button
                  square
                  size="large"
                  type="danger"
                  @click="props.skuEventBus.$emit('sku:buy')"
          >
            买买买
          </van-button>
        </div>
      </template>
    </van-sku>
  </div>
</template>

<script>
export default {
  data(){
    return{
      categories:'',
      phones:'',
      show: false,
      sku: '',
      goods: '',
    }
  },
  created() {
    axios.get('http://localhost:8181/phone/index').then(response =>{
      this.phones = response.data.data.phones;
      this.categories = response.data.data.categories;
    })
  },
  methods:{
    chooseTag(index){
      axios.get(`http://localhost:8181/phone/findByCategoryType/${this.categories[index].type}`).then(response =>{
        this.phones = response.data.data;
      })
    },
    buy(index){
      this.show = true;
      axios.get(`http://localhost:8181/phone/findSpecsByPhoneId/${this.phones[index].id}`).then(response =>{
        this.goods = response.data.data.goods;
        this.sku = response.data.data.sku;
      })
    },
    onBuyClicked(item){
      this.$store.state.specsId = item.selectedSkuComb.s1;
      this.$store.state.quantity = item.selectedNum;
      this.$router.push('/addressList');
    }
  }
}
</script>
