<template>
  <div class="content-event">
    <section id="palestras" class="activity">
      <h1>Palestras</h1>
      <hr />
      <div class="cards">
        <div class="card">
          <div class="data">
            <h1>Xxxx xxxx xxxxxxx xxxx</h1>
            <div class="row">
              <div class="col-5">
                <p>
                  <i class="fas fa-user"></i> Débora Lavínia
                </p>
              </div>
              <div class="col-3">
                <p>
                  <i class="fas fa-calendar-alt"></i> 20/11
                </p>
              </div>
              <div class="col-3">
                <p>
                  <i class="fas fa-clock"></i> 13:30
                </p>
              </div>
            </div>
            <p>xxxxxxxx xxxxx xxxxxxx xxxxx xxxxxxxxx xxxxxxxx xxxxxx xxxxxxxxx xxxxx xxxxxxxxx xxxxxxxx xxxxxxxxxxx x x</p>
          </div>
          <button class="btn button">Inscrever-se</button>
        </div>
      </div>
    </section>

    <section id="mini-cursos" class="activity">
      <h1>Mini-cursos</h1>
      <hr />
      <div class="cards">
        <div class="card">
          <div class="data">
            <h1>Xxxxx xxxx xxxxx</h1>
            <div class="row">
              <div class="col-5">
                <p>
                  <i class="fas fa-user"></i> Débora Lavínia
                </p>
              </div>
              <div class="col-3">
                <p>
                  <i class="fas fa-calendar-alt"></i> 22/11
                </p>
              </div>
              <div class="col-3">
                <p>
                  <i class="fas fa-clock"></i> 14:50
                </p>
              </div>
            </div>
            <p>Xxxx xx xxxxxx xx xxxxxxxx xx xxxxxx xxx xxxxxx xxxxxxxxxx xxxxxx xxxx xxxxxxxxx xxxxx xxxxxxxxxxx</p>
          </div>
          <button class="btn button">Inscrever-se</button>
        </div>
      </div>
    </section>
    <section id="torneios" class="activity">
      <h1>Torneios</h1>
      <hr />
      <!--aviso que está sem atividades cadastradas-->
      <div class="noActivity">
        <p>Ainda não há atividades nesta modalidade.</p>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "Modalidade"
};
</script>

<style scoped>
/*## CARDS AREA*/
.content-event {
  padding: 15px;
  width: 75%;
}

.activity {
  width: 100%;
}
.activity h1 {
  font-size: 32px;
  text-align: left;
}
.activity .cards {
  display: flex;
  flex-wrap: wrap;
}
.activity .cards .card {
  width: 47%;
  padding: 12px;
  border: 1px solid rgba(149, 56, 158, 0.4);
}
.activity .cards .card:hover {
  border: 1px solid rgb(149, 56, 158);
  box-shadow: 0px 0px 6px #d1d1d1;
}
.activity .cards .card h1 {
  font-size: 20pt;
  color: #1a1a1a;
}
.activity .cards .card [class*="col-"] {
  padding: 0px;
  color: #555;
}

.activity .noActivity {
  text-align: center;
  padding: 15px;
  margin: 15px;
  color: grey;
}
</style>