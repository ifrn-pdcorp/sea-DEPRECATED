<template>
  <div>
    <div class="container">
      <CardAboutEvent
        v-bind:event="event"
        v-bind:subscription="subscription"
        @saveSubscription="this.saveSubscription"
        @deleteSubscription="this.removeSubscription"
      ></CardAboutEvent>
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
import SubscriptionService from "../../services/subscription";

export default {
  name: "AboutEvent",
  data() {
    return {
      event: {},
      subscription: null,
    };
  },
  components: {
    CardAboutEvent,
    SiteBar,
    Modality,
  },
  methods: {
    async loadData() {
      var eventLoaded;
      await EventsService.get(this.$route.params.id).then((response) => {
        eventLoaded = response.data;
      });

      if (eventLoaded.thumbPath) {
        await UploadService.getImage(eventLoaded.thumbPath).then((response) => {
          eventLoaded.thumbPathURL = URL.createObjectURL(
            new Blob([response.data])
          );
        });
      }

      var subscriptionLoaded;

      await SubscriptionService.getByEventId(eventLoaded.id).then(
        (response) => {
          subscriptionLoaded = response.data;
        }
      );

      this.subscription = subscriptionLoaded;
      this.event = eventLoaded;
    },

    async saveSubscription() {
      var newSubscription = {
        event: {
          id: this.event.id,
        },
      };

      await SubscriptionService.save(newSubscription)
        .then()
        .catch((e) => {
          alert(e.response.data);
        });

      this.loadData();
    },

    async removeSubscription() {
      await SubscriptionService.delete(this.subscription.id)
        .then()
        .catch((e) => {
          alert(e.response.data);
        });
      this.loadData();
    },
  },

  async mounted() {
    await this.loadData();
  },
};
</script>

<style>
</style>