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
import UploadService from "../../services/uploads";
export default {
  name: "Events",
  data() {
    return {
      events: [],
    };
  },
  methods: {
    async getEvents() {
      var data;
      await EventsService.getAll().then((response) => {
        data = response.data;
      });

      for (let i = 0; i < data.length; i++) {
        var event = data[i];
        if (event.thumbPath) {
          await UploadService.getImage(event.thumbPath).then((response) => {
            event.thumbPathURL = URL.createObjectURL(
              new Blob([response.data])
            );
          });
        }
      }

      this.events = data;
    },
  },
  components: {
    CardEvent,
  },
  async mounted() {
    await this.getEvents();
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