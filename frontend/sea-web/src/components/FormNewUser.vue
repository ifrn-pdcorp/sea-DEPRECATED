<template>
  <div class="container-form">
    <form>
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
                required
              />
            </div>
          </figure>
        </div>

        <div class="col-9">
          <div class="row field">
            <div class="col-12 field" style="margin: 0px;">
              <label for="nome">Nome</label>
              <input
                type="text"
                class="form-control form-control-lg"
                name="nome"
                id="nome"
                placeholder="João Silva"
                required
              />
            </div>
          </div>

          <div class="row">
            <div class="col-6 field">
              <label for="escola">Escola</label>
              <input
                type="text"
                class="form-control form-control-lg"
                name="escola"
                id="escola"
                placeholder="Ex: IFRN - Campus Lajes"
                required
              />
            </div>
            <div class="col-6 field">
              <label for="cidade">Cidade</label>
              <input
                type="text"
                class="form-control form-control-lg"
                name="cidade"
                id="cidade"
                placeholder="Ex: Lajes"
                required
              />
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-6 field">
          <label for="email">Email</label>
          <input
            type="text"
            class="form-control form-control-lg"
            name="email"
            id="email"
            placeholder="Ex: IFRN - Campus Lajes"
            required
          />
        </div>
        <div class="col-6 field">
          <label for="sel1">Tipo de usuário</label>
          <select class="form-control form-control-lg" id="sel1">
            <option>---</option>
            <option>Aluno</option>
            <option>Professor</option>
            <option>Coordenador</option>
          </select>
        </div>
      </div>

      <div class="row">
        <div class="col-6 field">
          <label for="senha">Senha</label>
          <input
            type="password"
            class="form-control form-control-lg"
            name="senha"
            id="senha"
            placeholder="••••••"
            required
          />
          <small
            id="passwordHelpBlock"
            class="form-text text-muted"
          >*Sua senha deve conter de 8-15 caracteres</small>
        </div>
        <div class="col-6 field">
          <label for="senha">Confirme a senha</label>
          <input
            type="password"
            class="form-control form-control-lg"
            name="senha"
            id="senha"
            placeholder="••••••"
            required
          />
        </div>
      </div>

      <div class="row">
        <div class="btn-group">
          <a class="btn button btn-cancel" href="index.html">Voltar</a>
        </div>
        <div class="btn-group">
          <button class="btn button">Cadastrar</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "FormNewUser"
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