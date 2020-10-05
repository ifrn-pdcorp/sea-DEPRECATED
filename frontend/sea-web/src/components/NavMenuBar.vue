<template>
  <div>
    <nav class="navbar navbar-light" id="nav"> 
        <router-link to="/"><img alt="Sea logo" src="../assets/logo-peq.png"> </router-link>
         <router-link to="/"> <button class=" button btn">Cadastre-se</button> </router-link>
	</nav>
  </div>
</template>

<script>
export default {
    name: 'NavMenuBar'
}
</script>

<style scoped>

nav{
	background-color: #43d8c9;
	z-index: 9999;
}
 
nav img{
	margin-left: 35%;
}
a{
	color: #ffffff;
}

a:hover{
	color: #ffffff;
	text-decoration: none;
}


</style>