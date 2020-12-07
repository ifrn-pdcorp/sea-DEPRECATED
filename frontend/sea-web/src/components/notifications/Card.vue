<template>
  <div>
    <section class="error" :id="id">
      <p>{{ msg }}</p>
      <button type="button" class="close" @click="fecharerro">x</button>
    </section>
  </div>
</template>

<script>
export default {
  name: "NotificationCard",
  props: {
    msg: String,
    id: Number,
  },

  methods: {
    fecharerro() {
      var erro = document.getElementById(this.id);
      erro.style.display = "none";
    },
  },
};
</script>

<style>
@import url("../../styles/notifications/error.css");
</style>