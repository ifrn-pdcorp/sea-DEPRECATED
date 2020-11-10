<template>
  <div class="formulario">
    <form @submit.prevent="login">
      <h1>Entrar</h1>
      <hr />
      <label for="email">Email</label>
      <input
        v-model="credentials.user"
        type="email"
        class="form-control form-control-lg"
        name="email"
        id="email"
        placeholder="joao@exemplo.com"
        required
      />

      <label for="senha">Senha</label>
      <input
        v-model="credentials.password"
        type="password"
        class="form-control form-control-lg"
        name="senha"
        id="senhalogin"
        placeholder="••••••"
        required
      />

      <button type="submit" class="button btn">Entrar</button>
    </form>
  </div>
</template>

<script>
import LoginService from "../services/login";
export default {
  name: "FormLogin",
  data() {
    return {
      credentials: {
        user: "",
        password: ""
      }
    };
  },
  methods: {
    login() {
      console.log("Chamou o login");
      console.log(this.credentials);
      LoginService.login(this.credentials)
        .then(response => {
          console.log("Deu certo!");
          console.log(response);
        })
        .catch(e => {
          console.log("Deu errado!");
          console.log(e);
        });
      this.clearForm();
    },
    clearForm() {
      this.credentials = {};
    }
  }
};
</script>

<style scoped>
.formulario {
  width: 30%;
  margin-left: 5%;
}

form button {
  width: 100%;
  margin-top: 5%;
  margin-left: 0px;
}
</style>