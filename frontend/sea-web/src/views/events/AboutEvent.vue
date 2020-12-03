<template>
  <div>
    <div class="container">
      <CardAboutEvent></CardAboutEvent>
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

export default {
  name: "AboutEvent",
  components: {
    CardAboutEvent,
    SiteBar,
    Modality,
  }
};
</script>

<style>
</style>