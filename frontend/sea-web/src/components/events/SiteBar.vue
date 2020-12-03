<template>
  <nav class="sitebar sticky-top">
    <h1>Modalidades</h1>
    <ul>
      <li>
        <a href="#palestras">Palestras</a>
      </li>
      <li>
        <a href="#mini-cursos">Mini-cursos</a>
      </li>
      <li>
        <a href="#torneios">Torneios</a>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "SiteBar"
};
</script>

<style>
/*## SITEBAR ##*/
.sticky-top {
  position: sticky;
  top: 54px;
}
.sitebar {
  padding: 20px;
  border-radius: 5px;
  width: 25%;
  background: transparent;
  height: 500px;
}
.sitebar h1 {
  font-size: 18pt;
  padding: 6px;
}
.sitebar ul {
  font-size: 15pt;
  list-style-type: none;
  margin: 0px;
  padding: 0px;
}
.sitebar ul li {
  margin-bottom: 15px;
  padding-top: 15px;
  border-top: 1px solid rgba(149, 56, 158, 0.4);
}
.sitebar ul li a {
  color: #222;
}
.sitebar ul li a:hover {
  color: rgba(149, 56, 158);
}
</style>