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
.img-background {
  position: absolute;
  width: 20%;
  z-index: -1;
  opacity: 0.5;
}

#left {
  left: 1%;
}
#right {
  right: 1%;
}
</style>