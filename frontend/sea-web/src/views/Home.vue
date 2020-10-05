<template>
  <div class="content container">
    <div class="image-index"> </div>
    <FormLogin> </FormLogin>
  </div>
</template>

<script>
// @ is an alias to /src
import FormLogin from '@/components/FormLogin.vue'

export default {
  name: 'Home',
  components: {
    FormLogin
  }
}
</script>
<style scoped>
.image-index{
	background-image: url('../assets/sea.png');
	background-repeat: no-repeat;
  background-size: contain; 
	min-width: 45%;
	height: 450px;

	float: right;
	margin-right:5%;
	margin-top: 3%;
}
</style>
