<template>
  <div class="container">
    <router-link to="/newevent">
      <button class="button btn btn-menu">Novo Evento</button>
    </router-link>
    <h1 class="title">Eventos</h1>
    <hr />
    <section class="events">
      <CardEvent v-for="e in events" :key="e.id" v-bind:event="e"></CardEvent>
    </section>
  </div>
</template>

<script>
import CardEvent from "@/components/events/CardEvent.vue";
import EventsService from "../../services/events";
export default {
  name: "Events",
  data() {
    return {
      events: [],
    };
  },
  methods: {
    async getEvents() {
      await EventsService.getAll().then((response) => {
        this.events = response.data;
      });
    },
  },
  components: {
    CardEvent,
  },
  mounted() {
    this.getEvents();
  },
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