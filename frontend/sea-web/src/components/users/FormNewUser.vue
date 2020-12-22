<template>
  <div class="container-form">
    
    <form @submit.prevent="save" :class="{ 'invalid-form': empty && formState === 'submit clicked' }">
      <h1>Cadastre-se</h1>
      <hr />

      <section v-if="formState === 'form submit'">
        <notification-card
        :id="9"
        state = "ok"
        msg= "Cadastro efetuado com sucesso!">
        </notification-card>
      </section>
      <section v-if="formState === 'submit clicked'">
        <notification-card
          :id="1"
          state="error"
          msg="Preencha o campo Nome"
          v-if="!$v.user.name.required"
        ></notification-card>
        <notification-card
          :id="2"
          state="error"
          v-if="!$v.user.email.required"
          msg="Preencha o campo Email"
          
        ></notification-card>
        <notification-card
          :id="3"
          state="error"
          msg="Email invalido"
          v-if="!$v.user.email.email"
        ></notification-card>
        <notification-card
          :id="4"
          state="error"
          msg="Preencha o campo Tipo de Usuario"
          v-if="!$v.user.type.required"
        ></notification-card>
        <notification-card
          :id="5"
          state="error"
          msg="Preencha o campo Senha"
          v-if="!$v.user.password.required"
        ></notification-card>
        <notification-card
          :id="6"
          state="error"
          msg="Confirme sua senha"
          v-if="!$v.checkPassword.required"
        ></notification-card>
        <notification-card
          :id="7"
          state="error"
          msg="Sua senha deve conter de 8 à 15 caracteres"
          v-if="!$v.user.password.minLength || !$v.user.password.minLength"
        ></notification-card>
        <notification-card
          :id="8"
          state="error"
          msg="As senhas não correspondem"
          v-if="$v.checkPassword.$model != $v.user.password.$model "
        ></notification-card>
      </section>

      <div class="row">
        <div class="col-3">
          <figure class="image">
            <img
              src="../../assets/picprofile.jpg"
              alt="Avatar"
              id="picprofile"
              class="imagefile"
            />

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
            <div
              class="col-12 field"
              style="margin: 0px"
              :class="{
                'invalid-field':
                  !$v.user.name.required && formState === 'submit clicked',
              }"
            >
              <label for="nome">Nome </label>
              <input
                v-model.lazy="$v.user.name.$model"
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
        <div
          class="col-6 field"
          :class="{
            'invalid-field':
              formState === 'submit clicked' &&
              (!$v.user.email.required || !$v.user.email.email),
          }"
        >
          <label for="email">Email</label>
          <input
            v-model.lazy="$v.user.email.$model"
            type="text"
            class="form-control form-control-lg"
            name="email"
            id="email"
            placeholder="exemplo@email.com"
          />
        </div>
        <div
          class="col-6 field"
          :class="{
            'invalid-field':
              formState === 'submit clicked' && !$v.user.type.required,
          }"
        >
          <label for="sel1">Tipo de usuário</label>
          <select
            class="form-control form-control-lg"
            id="sel1"
            v-model.lazy="$v.user.type.$model"
          >
            <option value>---</option>
            <option value="STUDENT">Aluno</option>
            <option value="PROFESSOR">Professor</option>
            <option value="CHEER">Coordenador</option>
          </select>
        </div>
      </div>

      <div class="row">
        <div
          class="col-6 field"
          :class="{
            'invalid-field':
              formState === 'submit clicked' && 
              (!$v.user.password.required || !$v.user.password.minLength || !$v.user.password.maxLength)}"
        >
          <label for="senha">Senha</label>
          <input
            v-model.lazy="$v.user.password.$model"
            type="password"
            class="form-control form-control-lg"
            name="senha"
            id="senha"
            placeholder="••••••"
          />
          <small id="passwordHelpBlock" class="form-text text-muted"
            >*Sua senha deve conter de 8-15 caracteres</small
          >
        </div>
        <div
          class="col-6 field"
          :class="{
            'invalid-field':
              formState === 'submit clicked' &&
              (!$v.checkPassword.required || $v.checkPassword.confirmPassword),
          }"
        >
          <label for="senha">Confirme a senha</label>
          <input
            v-model.lazy="$v.checkPassword.$model"
            type="password"
            class="form-control form-control-lg"
            name="senha"
            id="senha-confirm"
            placeholder="••••••"
          />
        </div>
      </div>

      <div class="row">
        <div class="btn-group">
          <button class="btn button btn-cancel">
            <router-link to="/">Voltar</router-link>
          </button>
        </div>
        <div class="btn-group">
          <button
            type="submit"
            class="btn button"
          >
            Cadastrar
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import UsersService from "../services/users";
import {
  required,
  email,
  minLength,
  maxLength,
  sameAs
} from "vuelidate/lib/validators";

import NotificationCard from ".././notifications/Card";

export default {
  name: "FormNewUser",
  components: {
    NotificationCard,
  },
  data() {
    return {
      formState: "submit not clicked",
      empty: true,
      

      user: {
        name: "",
        email: "",
        password: "",
        school: "",
        city: "",
        type: "",
        thumbPath: "",
      },
      checkPassword: "",
    };
  },
  validations: {
    user: {
      name: {
        required,
      },
      email: {
        required,
        email,
      },
      type: {
        required,
      },
      password: {
        required,
        minLength: minLength("8"),
        maxLength: maxLength("15"),
      },
    },
    checkPassword: {
      required,
      confirmPassword: sameAs ("user.password.$model")
    },
  },
  methods: {
    async save() {
      this.empty = !this.$v.user.$anyDirty;
      this.formState = "submit clicked";

      if (!this.$v.user.$invalid && this.empty === false) {

        var imageSaved = this.saveImage();
        this.user.thumbPath = imageSaved;

        console.log("Chamada para salvar na api");
        
        
        UsersService.save(this.user)
        .then(response => {
          console.log("Deu certo!");
          console.log(response);

          this.formState = "form submit";
          this.clearForm();
        })
        .catch(e => {
          console.log("Deu errado!");
          console.log(e.response);
          this.rollbackSaveImage(imageSaved);
        });
      }
  
    },


    clearForm() {
      console.log("Chamou o limpar");
      this.user = { type: "" };
      this.checkPassword = "";
      this.$v.$reset()
    },

    saveImage() {
      console.log("TODO: Salvar imagem na api");
      return "";
    },

    rollbackSaveImage(path) {
      console.log("TODO: Rotina de remoção da imagem" + path);
    },
  },
};
</script>

<style scoped>
@import url("../../styles/notifications/invalidField.css");

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