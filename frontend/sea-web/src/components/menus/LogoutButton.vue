<template>
  <div>
    <button @click="logout" class="button btn btn-menu">Logout</button>
  </div>
</template>

<script>
export default {
  name: "LogoutButton",
  methods: {
    logout() {
      sessionStorage.clear();
      this.$router.push("/");
    },
  },
};
</script>

<style scoped>
</style>