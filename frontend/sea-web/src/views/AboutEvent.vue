<template>
  <div>
    <TitleEvent></TitleEvent>

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
import TitleEvent from "@/components/TitleEvent.vue";

export default {
  name: "AboutEvent",
  components: {
    CardAboutEvent,
    SiteBar,
    Modality,
    TitleEvent
  }
};
</script>

<style>
</style>