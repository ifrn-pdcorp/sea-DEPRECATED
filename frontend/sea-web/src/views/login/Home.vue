<template>
  <div>
    <div>
      <router-link to="/newuser">
        <button class="button btn btn-menu">Cadastre-se</button>
      </router-link>
    </div>
    <div class="content container">
      <div class="image-index"></div>
      <FormLogin></FormLogin>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import FormLogin from "@/components/login/FormLogin.vue";

export default {
  name: "Home",
  components: {
    FormLogin
  }
};
</script>
<style scoped>
.image-index {
  background-image: url("../../assets/sea.png");
  background-repeat: no-repeat;
  background-size: contain;
  min-width: 45%;
  height: 450px;

  float: right;
  margin-right: 5%;
  margin-top: 3%;
}
</style>
