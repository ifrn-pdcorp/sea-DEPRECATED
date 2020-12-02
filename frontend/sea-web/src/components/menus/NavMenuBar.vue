<template>
  <div>
    <nav class="navbar navbar-light" id="nav">
      <router-link to="/"
        ><img alt="Sea logo" src="../../assets/logo-peq.png" />
      </router-link>

      <logout-button></logout-button>
    </nav>
  </div>
</template>

<script>
import LogoutButton from "@/components/menus/LogoutButton.vue";

export default {
  name: "NavMenuBar",
  components: {
    LogoutButton,
  },
};
</script>

<style scoped>
nav {
  background-color: #43d8c9;
  z-index: 1;
  padding: 1%;
}

nav img {
  margin-left: 35%;
}
a {
  color: #ffffff;
}

a:hover {
  color: #ffffff;
  text-decoration: none;
}
</style>