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
import CardAboutEvent from "@/components/CardAboutEvent.vue";
import SiteBar from "@/components/SiteBar.vue";
import Modality from "@/components/Modality.vue";

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