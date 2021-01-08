<template>
  <div class="about-event">
    <div class="row">
      <div class="col-3">
        <img v-if="event.thumbPathURL" v-bind:src="event.thumbPathURL" />
        <img v-else src="../../assets/picevent-peq.jpg" />
      </div>
      <div class="col-9">
        <h1>{{ event.name }}</h1>
        <h2>Diversidade, inclusão e sustentabilidade em tempos de crise</h2>

        <p>
          {{ event.summary }}
        </p>

        <div class="row data">
          <p>
            <i class="fas fa-map-marker-alt"></i> Local:
            <span>IFRN Campus avançado Lajes</span>
          </p>

          <p>
            <i class="fas fa-calendar-day"></i> Data do evento:
            <span>19/11</span>
          </p>

          <p>
            <i class="fas fa-calendar-alt"></i> Periodo de inscrições:
            <span
              >{{ formattDate(event.subscriptionStart) }} à
              {{ formattDate(event.subscriptionEnd) }}</span
            >
          </p>
        </div>
        <div class="row data">
          <p>
            <button
              v-if="!subscription"
              @click.prevent="saveSubscription"
              class="btn button"
            >
              Inscrever-se
            </button>
            <button
              v-else
              @click.prevent="deleteSubscription"
              class="btn button btn-cancel"
            >
              Cancelar Inscrição
            </button>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["event", "subscription"],
  name: "CardAboutEvent",
  methods: {
    async saveSubscription() {
      this.$emit("saveSubscription");
    },

    async deleteSubscription() {
      this.$emit("deleteSubscription");
    },

    formattDate(date) {
      var d = new Date(date);
      return d.toLocaleDateString();
    },
  },
};
</script>

<style>
.about-event {
  width: 100%;
  background-color: #fff;
  padding: 15px;
  border: 1px solid rgba(149, 56, 158, 0.8);

  border-radius: 5px;
  margin-top: 3%;
  margin-bottom: 3%;
}
.about-event h1 {
  font-size: 28pt;
}
.about-event h2 {
  font-size: 15pt;
}
.about-event p {
  font-size: 15px;
  text-align: justify;
}
.about-event img {
  width: 100%;
  height: 228px;
  box-shadow: 0px 0px 6px #d1d1d1;
  border-radius: 5px;
}
.about-event .data {
  padding-top: 12px;
  /*box-shadow: 0px 0px 6px #d1d1d1;*/
  /*border: 1px solid rgba(149, 56, 158, .8);*/
  border-radius: 5px;
}
.about-event .data p {
  text-align: left;
  margin-left: 10px;
  margin-right: 10px;
}
.about-event .data i {
  opacity: 0.8;
}
.about-event .data span {
  font-weight: bolder;
}
</style>