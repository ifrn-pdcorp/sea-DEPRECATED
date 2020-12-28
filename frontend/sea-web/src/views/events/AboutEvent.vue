<template>
  <div>
    <div class="container">
      <CardAboutEvent v-bind:event="event"></CardAboutEvent>
      <div class="row">
        <SiteBar></SiteBar>
        <Modality></Modality>
      </div>
    </div>
  </div>
</template>

<script>
import CardAboutEvent from "@/components/events/CardAboutEvent.vue";
import SiteBar from "@/components/events/SiteBar.vue";
import Modality from "@/components/events/Modality.vue";

import EventsService from "../../services/events";
import UploadService from "../../services/uploads";

export default {
  name: "AboutEvent",
  data() {
    return {
      event: {},
    };
  },
  components: {
    CardAboutEvent,
    SiteBar,
    Modality,
  },
  methods: {
    async getEvent() {
      var data;
      await EventsService.get(this.$route.params.id).then((response) => {
        data = response.data;
      });

      if (data.thumbPath) {
        await UploadService.getImage(data.thumbPath).then((response) => {
          data.thumbPathURL = URL.createObjectURL(new Blob([response.data]));
        });
      }

      this.event = data;
    },
  },
  async mounted() {
    await this.getEvent();
    console.log(this.event);
  },
};
</script>

<style>
</style>