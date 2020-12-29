<template>
  <div class="container content">
    <div class="container-form">
      <form @submit.prevent="save">
        <h1>Cadastrar Evento</h1>
        <hr />

        <div class="class">
          <figure class="image">
            <img
              v-if="thumbImageURL"
              :src="thumbImageURL"
              id="picprofile"
              class="imagefile"
            />
            <img
              v-else
              src="../../assets/picevent.jpg"
              alt="Avatar"
              id="picprofile"
              class="imagefile"
            />
            <div class="overlay">
              <label class="text" for="editpiprofile">Escolher foto</label>
              <input
                type="file"
                accept="image/*"
                name="editpiprofile"
                id="editpiprofile"
                @change="onImage($event)"
              />
            </div>
          </figure>
        </div>
        <div class="col-12 field">
          <label for="nome">Nome</label>
          <input
            v-model="event.name"
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
          <textarea
            v-model="event.summary"
            class="form-control"
            id="descricao"
            name="descricao"
            rows="5"
            placeholder="Digite uma descrição para o seu evento"
          ></textarea>
        </div>

        <div>
          <div class="col-12">
            <label>Periodo das inscrições</label>
          </div>
          <div class="row">
            <div class="col-6 field">
              <small class="form-text text-muted">Data inicial</small>
              <input
                v-model="event.subscriptionStart"
                type="datetime-local"
                class="form-control form-control-lg"
                name="datestart"
                id="datestart"
                required
              />
            </div>

            <div class="col-6 field">
              <small class="form-text text-muted">Data final</small>
              <input
                v-model="event.subscriptionEnd"
                type="datetime-local"
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
            <router-link to="/events" class="button btn btn-cancel"
              >Voltar</router-link
            >
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
import EventService from "../../services/events";
import UploadService from "../../services/uploads";
export default {
  name: "FormNewEvent",
  data() {
    return {
      thumbImage: null,
      thumbImageURL: null,
      event: {
        name: "",
        summary: "",
        thumbPath: "",
        subscriptionStart: "",
        subscriptionEnd: "",
      },
    };
  },
  methods: {
    async save() {
      if (this.validate()) {
        // Salvar a imagem
        await UploadService.saveImage(this.thumbImage).then((response) => {
          this.event.thumbPath = response.data;
        });
        // Salvar o evento
        await EventService.save(this.event).then((response) => {
          this.cleanForm();
          console.log("Salvou o cara!" + response);
        });
      }
    },

    validate() {
      return true;
    },

    cleanForm() {
      this.event = {};
      this.thumbImage = null;
      this.thumbImageURL = null;
    },

    onImage(e) {
      e.stopPropagation();
      e.preventDefault();

      const file = e.target.files[0];
      if (!file.type.match("image.*")) {
        return;
      }
      this.thumbImage = file;
      this.thumbImageURL = URL.createObjectURL(file);
    },
  },
};
</script>

<style>
@import url("../../styles/forms.css");
@import url("../../styles/buttons.css");
</style>