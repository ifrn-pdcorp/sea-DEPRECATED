<template>
  <div class="card">
    <div class="image">
      <img v-if="event.thumbPathURL" v-bind:src="event.thumbPathURL" />
      <img v-else src="../../assets/picevent.jpg" />
    </div>

    <div class="data">
      <h1>{{ event.name }}</h1>
      <h2>{{ event.summary }}</h2>

      <div class="details">
        <p><i class="fas fa-map-marker-alt"></i> Pau dos Ferros</p>
        <p>
          <i class="fas fa-calendar-alt"></i> De
          {{ formattDate(event.subscriptionStart) }} à
          {{ formattDate(event.subscriptionEnd) }}
        </p>
      </div>
    </div>

    <router-link
      :to="{ name: 'EventDetail', params: { id: this.event.id } }"
      class="btn button"
    >
      Saiba mais
    </router-link>
  </div>
</template>

<script>
export default {
  props: ["event"],
  name: "CardEvent",
  methods: {
    formattDate(date) {
      var d = new Date(date);
      return d.toLocaleDateString();
    },
  },
};
</script>

<style scoped>
/* ### CARDS ### */
.card {
  display: flex;
  padding: 12px;
  color: #1a1a1a;
  border: 1px solid rgba(149, 56, 158, 0.4);
  width: 31%;
  margin-left: 10px;
  margin-bottom: 15px;

  /*box-shadow: 1px 1px 20px rgba(149, 56, 158, .7);*/
}
.card:hover {
  border: 1px solid rgb(149, 56, 158);
  box-shadow: 0px 0px 6px #d1d1d1;
}
.card h1 {
  font-size: 28px;
}
.card h2 {
  font-size: 17px;
  color: #555;
}
.card .details {
  display: flex;
  color: #555;
  justify-content: space-around;
}

.card img {
  width: 100%;
  border-radius: 5px;
  max-height: 150px;
  box-shadow: 0px 0px 6px #d1d1d1;
}
</style>