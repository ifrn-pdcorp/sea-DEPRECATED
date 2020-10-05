<template>
  <div class="formulario">
		<form>
			<h1>Entrar</h1>
			<hr>
			<label for="email">Email</label>
			<input type="text" 
			class="form-control form-control-lg" 
			name="email" 
			id="email" 
			placeholder="joao@exemplo.com"
			required>

			<label for="senha">Senha</label>
			<input type="password" 
			class="form-control form-control-lg" 
			name="senha" 
			id="senha" 
			placeholder="••••••"
			required>

             <router-link to="/events"> <button class=" button btn">Entrar </button> </router-link>
		</form>
	</div>
</template>

<script>
export default {
    name: 'FormLogin'

}
</script>

<style scoped>

.formulario{
	width: 30%;
	margin-left:5%;
}

form button{
	width: 100%;
	margin-top: 5%;
	margin-left: 0px;
}
</style>