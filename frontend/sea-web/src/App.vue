<template>
  <div id="app">
    <div id="nav">
      <NavMenuBar></NavMenuBar>
    </div>
    <router-view />
  </div>
</template>

<script>
import NavMenuBar from "@/components/menus/NavMenuBar.vue";

export default {
  name: "Events",
  components: {
    NavMenuBar
  }
};
</script>
<style>
body {
  background-color: #f7f7f7;
  height: 100vh;
}
hr {
  border-top-color: rgba(149, 56, 158, 0.6);
}
h1 {
  color: #95389e;
}
a {
  color: #ffffff;
}

a:hover {
  color: #ffffff;
  text-decoration: none;
}
.row {
  margin: 0px;
}
.content {
  display: flex;
  align-items: center;
  justify-content: center;
}

.container .title {
  margin-top: 3%;
  margin-left: 3%;
}



/* ### BUTTONS ###*/
@import url("styles/buttons.css");
</style>
