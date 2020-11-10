<template>
  <div class="container content">
    <FormNewUser></FormNewUser>
    
    <img class="img-background" id="left" src="../assets/img-cadastro.png" />
    <img class="img-background" id="right" src="../assets/img-cadastro.png" />

  </div>
</template>

<script>
import FormNewUser from "@/components/FormNewUser.vue"

export default {
  name: 'NewUser',
  components: {FormNewUser}
};
</script>

<style scoped>

</style>