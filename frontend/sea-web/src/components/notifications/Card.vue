<template>
  <div>
    <section class="notification" :id="id" :class="state">
      <p>{{ msg }}</p>
      <button type="button" class="close" @click="close" :class="{ 'close-ok': state === 'ok' }" >x</button>
    </section>
  </div>
</template>

<script>
export default {
  name: "NotificationCard",
  props: {
    msg: String,
    id: Number,
    state: String
  },

  methods: {
    close() {
      var erro = document.getElementById(this.id);
      erro.style.display = "none";
    },
  },
};
</script>

<style>
@import url("../../styles/notifications/card.css");
</style>