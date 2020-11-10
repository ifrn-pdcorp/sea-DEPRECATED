<template>
  <div class="container content">
    <div class="container-form">
      <form>
        <h1>Cadastrar Evento</h1>
        <hr />

        <div class="col-12 field">
          <label for="nome">Nome</label>
          <input
            type="text"
            class="form-control form-control-lg"
            name="nome"
            id="nome"
            placeholder="Semadec"
            required
          />
        </div>

        <div class="col-12 field">
          <label for="descricao">Descrição</label>
          <textarea class="form-control" id="descricao" name="descricao" rows="5"></textarea>
        </div>

        <div>
          <div class="col-12">
            <label>Periodo das inscrições</label>
          </div>
          <div class="row">
            <div class="col-6 field">
              <small class="form-text text-muted">Data inicial</small>
              <input
                type="date"
                class="form-control form-control-lg"
                name="datestart"
                id="datestart"
                required
              />
            </div>

            <div class="col-6 field">
              <small class="form-text text-muted">Data final</small>
              <input
                type="date"
                class="form-control form-control-lg"
                name="dateend"
                id="dateend"
                required
              />
            </div>
          </div>
        </div>

        <div class="row">
          <div class="btn-group">
          <button class="btn button btn-cancel">
            <router-link to="/events">Voltar</router-link>
          </button>
        </div>
          <div class="btn-group">
            <button class="btn button">Criar evento</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "FormNewEvent"
};
</script>

<style>
@import url("../styles/forms.css");
@import url("../styles/buttons.css");
</style>