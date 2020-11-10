<template>

  <div class=" container">
     <router-link to="/newevent">
        <button class="button btn btn-menu"> Novo Evento</button>
      </router-link>
    <h1 class="title">Eventos</h1>
    <hr />
    <section class="events">
      <CardEvent></CardEvent>
      <CardEvent></CardEvent>
      <CardEvent></CardEvent>
       
    </section>
  </div>
</template>

<script>
import CardEvent from "@/components/CardEvent.vue";
export default {
  name: "Events",
  components: {
    CardEvent
  }
};
</script>

<style scoped>


.events {
  display: flex;
  flex-wrap: wrap;
  justify-content: justify;
  padding-left: 30px;
}

.container h1 {
  font-size: 50px;
}
</style>