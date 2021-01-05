<template>
  <div class="container content">
    <div class="container-form">
      <form
        @submit.prevent="save"
        :class="{ 'invalid-form': empty && formState === 'submit clicked' }"
      >
        <h1>Cadastrar Evento</h1>
        <hr />

        <section v-if="formState === 'submit clicked'">
          <notification-card
            :id="1"
            state="error"
            msg="Preencha o campo Nome"
            v-if="!$v.event.name.required"
          >
          </notification-card>
          <notification-card
            :id="2"
            state="error"
            msg="Preencha o campo Descrição"
            v-if="!$v.event.summary.required"
          >
          </notification-card>
          <notification-card
            :id="3"
            state="error"
            msg="Preencha a data de inicio das inscrições"
            v-if="!$v.event.subscriptionStart.required"
          >
          </notification-card>
          <notification-card
            :id="4"
            state="error"
            msg="Preencha a data final das inscrições"
            v-if="!$v.event.subscriptionEnd.required"
          >
          </notification-card>
        </section>
        <section v-if="formState === 'form submit'">
          <notification-card
            :id="5"
            state="ok"
            msg="Evento cadastrado com sucesso!"
          >
          </notification-card>
        </section>

        <div class="row" style="justify-content: center">
          <div class="field col-7">
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
        </div>
        <div class="col-12 field" :class="{
            'invalid-field':
              formState === 'submit clicked' &&
              (!$v.event.name.required),
          }">
          <label for="nome">Nome</label>
          <input
            v-model.lazy="$v.event.name.$model"
            type="text"
            class="form-control form-control-lg"
            name="nome"
            id="nome"
            placeholder="Semadec"
          />
        </div>

        <div class="col-12 field"  :class="{
            'invalid-field':
              formState === 'submit clicked' &&
              (!$v.event.summary.required),
          }">
          <label for="descricao">Descrição</label>
          <textarea
            v-model.lazy="$v.event.summary.$model"
            class="form-control"
            id="descricao"
            name="descricao"
            rows="5"
            placeholder="Digite uma descrição para o seu evento"
          ></textarea>
        </div>

        <div>
          <div class="col-12"  :class="{
            'invalid-field':
              formState === 'submit clicked' && (
              (!$v.event.subscriptionStart.required) ||  (!$v.event.subscriptionEnd.required)  ),
          }">
            <label>Periodo das inscrições</label>
          </div>
          <div class="row">
            <div class="col-6 field" :class="{
            'invalid-field':
              formState === 'submit clicked' &&
              (!$v.event.subscriptionStart.required),
          }">
              <small class="form-text text-muted">Data inicial</small>
              <input
                v-model.lazy="$v.event.subscriptionStart.$model"
                type="datetime-local"
                class="form-control form-control-lg"
                name="datestart"
                id="datestart"
              />
            </div>

            <div class="col-6 field" :class="{
            'invalid-field':
              formState === 'submit clicked' &&
              (!$v.event.subscriptionEnd.required),
          }">
              <small class="form-text text-muted">Data final</small>
              <input
                v-model.lazy="$v.event.subscriptionEnd.$model"
                type="datetime-local"
                class="form-control form-control-lg"
                name="dateend"
                id="dateend"
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
import { required } from "vuelidate/lib/validators";
import NotificationCard from "../notifications/Card";
export default {
  name: "FormNewEvent",
  components: {
    NotificationCard,
  },
  data() {
    return {
      formState: "submit not clicked",
      empty: true,

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
  validations: {
    event: {
      name: { required },
      summary: { required },
      subscriptionStart: { required },
      subscriptionEnd: { required },
    },
  },
  methods: {
    async save() {
      this.empty = !this.$v.event.$anyDirty;
      this.formState = "submit clicked";

      if (!this.$v.event.$invalid && this.empty === false) {
        // Salvar a imagem
        await UploadService.saveImage(this.thumbImage).then((response) => {
        this.event.thumbPath = response.data;
        console.log("salvou a imagem");
        });

        // Salvar o evento
         await EventService.save(this.event).then((response) => {
        console.log("Salvou o cara!");
        this.formState = "form submit";
        this.cleanForm();
        });
      }
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
@import url("../../styles/notifications/invalidField.css");
</style>