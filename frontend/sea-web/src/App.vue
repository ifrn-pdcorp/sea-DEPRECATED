<template>
  <div id="app">
    <div id="nav">
      <NavMenuBar></NavMenuBar>
    </div>
    <router-view />
  </div>
</template>

<script>
import NavMenuBar from "@/components/NavMenuBar.vue";

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

/* ### INPUT COLORS ###*/
.form-control:focus {
  border: 1px solid #95389e;
  box-shadow: 0 0 0 0.2rem rgba(149, 56, 158, 0.25);
}

/* ### BUTTONS ###*/

.button {
  margin: 8px;
  background-color: #95389e;
  transition: background 0.6s;
  color: #ffffff;
}
.button:hover {
  color: #ffffff;
  opacity: 0.8;
}
.btn-cancel {
  background-color: #f1f1f1;
  border: 1px solid #95389e;
 
}
.btn-cancel a{
   color: #95389e;
}
.btn-cancel:hover {
  margin-top: 5%;
  color: #95389e;
}

.btn-group {
  width: 50%;
}


</style>
