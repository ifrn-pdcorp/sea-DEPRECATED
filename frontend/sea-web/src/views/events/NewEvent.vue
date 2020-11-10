<template>
  <div class="container content">
    <FormNewEvent></FormNewEvent>
    
    <img class="img-background" id="left" src="../../assets/img-cadastro.png" />
    <img class="img-background" id="right" src="../../assets/img-cadastro.png" />

  </div>
</template>

<script>
import FormNewEvent from "@/components/events/FormNewEvent.vue"

export default {
  name: 'NewForm',
  components: {FormNewEvent}
};
</script>

<style scoped>

</style>