<template>
  <div class="container-form">
    <form @submit.prevent="save">
      <h1>Cadastre-se</h1>
      <hr />

      <div class="row">
        <div class="col-3">
          <figure class="image">
            <img src="../assets/picprofile.jpg" alt="Avatar" id="picprofile" class="imagefile" />

            <div class="overlay">
              <label class="text" for="editpiprofile">Escolher foto</label>
              <input
                type="file"
                name="editpiprofile"
                id="editpiprofile"
                onchange="showPicProfile(this)"
              />
            </div>
          </figure>
        </div>

        <div class="col-9">
          <div class="row field">
            <div class="col-12 field" style="margin: 0px;">
              <label for="nome">Nome</label>
              <input
                v-model="user.name"
                type="text"
                class="form-control form-control-lg"
                name="nome"
                id="nome"
                placeholder="João Silva"
              />
            </div>
          </div>

          <div class="row">
            <div class="col-6 field">
              <label for="escola">Escola</label>
              <input
                v-model="user.school"
                type="text"
                class="form-control form-control-lg"
                name="escola"
                id="escola"
                placeholder="Ex: IFRN - Campus Lajes"
              />
            </div>
            <div class="col-6 field">
              <label for="cidade">Cidade</label>
              <input
                v-model="user.city"
                type="text"
                class="form-control form-control-lg"
                name="cidade"
                id="cidade"
                placeholder="Ex: Lajes"
              />
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-6 field">
          <label for="email">Email</label>
          <input
            v-model="user.email"
            type="text"
            class="form-control form-control-lg"
            name="email"
            id="email"
            placeholder="Ex: IFRN - Campus Lajes"
          />
        </div>
        <div class="col-6 field">
          <label for="sel1">Tipo de usuário</label>
          <select class="form-control form-control-lg" id="sel1" v-model="user.type">
            <option value>---</option>
            <option value="STUDENT">Aluno</option>
            <option value="PROFESSOR">Professor</option>
            <option value="CHEER">Coordenador</option>
          </select>
        </div>
      </div>

      <div class="row">
        <div class="col-6 field">
          <label for="senha">Senha</label>
          <input
            v-model="user.password"
            type="password"
            class="form-control form-control-lg"
            name="senha"
            id="senha"
            placeholder="••••••"
          />
          <small
            id="passwordHelpBlock"
            class="form-text text-muted"
          >*Sua senha deve conter de 8-15 caracteres</small>
        </div>
        <div class="col-6 field">
          <label for="senha">Confirme a senha</label>
          <input
            v-model="checkPassword"
            type="password"
            class="form-control form-control-lg"
            name="senha"
            id="senha"
            placeholder="••••••"
          />
        </div>
      </div>

      <div class="row">
        <div class="btn-group">
          <router-link to="/">
            <button class="btn button btn-cancel">Voltar</button>
          </router-link>
        </div>
        <div class="btn-group">
          <button type="submit" class="btn button">Cadastrar</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import UsersService from "../services/users";
export default {
  name: "FormNewUser",
  data() {
    return {
      user: {
        name: "",
        email: "",
        password: "",
        school: "",
        city: "",
        type: ""
      },
      checkPassword: ""
    };
  },
  methods: {
    async save() {
      console.log("Chamou o método salvar");
      // Antes de salvar validar se senhas conferem
      if (
        this.user.password.length >= 8 &&
        this.user.password.length <= 15 &&
        this.user.password === this.checkPassword
      ) {
        UsersService.salvar(this.user)
          .then(response => {
            console.log(response);
          })
          .catch(e => {
            console.log(e.response);
          });
      } else {
        console.log("Senhas não conferem");
      }
    }
  },
  mounted() {
    //this.save();
  }
};
</script>

<style scoped>
@import url("../styles/forms.css");

/* ### PIC PROFILE ###*/
.container-form .image {
  width: 100%;
  min-height: 170px;
  max-height: 170px;
  height: auto;
  border-radius: 5%;
  overflow: hidden;
  box-shadow: 0px 0px 5px #888888;
  position: relative;
}

.container-form .image img {
  width: 100%;
  height: 170px;
  object-fit: cover;
}

.container-form .image .overlay {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 50%;
  width: 100%;
  opacity: 0;
  transition: opacity 0.5s;
  background-color: #95389e;
}
.container-form .image:hover .overlay {
  opacity: 0.7;
}
.container-form .overlay input {
  opacity: 0;
}
.container-form .overlay .edit {
  opacity: 0;
  position: absolute;
  z-index: -1;
}
.container-form .overlay .text {
  color: white;
  font-size: 15pt;
  line-height: 130%;
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  text-align: center;
  cursor: pointer;
}
</style>