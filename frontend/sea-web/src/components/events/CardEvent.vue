<template>
  <div class="card">
    <div class="image">
      <img src="../assets/picevent.jpg" />
    </div>

    <div class="data">
      <h1>II Semadec</h1>
      <h2>Diversidade, inclusão e sustentabilidade em tempos de crise</h2>

      <div class="details">
        <p>
          <i class="fas fa-map-marker-alt"></i> Pau dos Ferros
        </p>
        <p>
          <i class="fas fa-calendar-alt"></i> De 19/11 á 23/11
        </p>
      </div>
    </div>

    <button class="btn button">
      <a href="programacao-evento.html">Saiba mais</a>
    </button>
  </div>
</template>

<script>
export default {
    name: 'CardEvent'
};
</script>

<style scoped>
/* ### CARDS ### */
.card {
  display: flex;
  padding: 12px;
  color: #1a1a1a;
  border: 1px solid rgba(149, 56, 158, 0.4);
  width: 31%;
  margin-left: 10px;
  margin-bottom: 15px;

  /*box-shadow: 1px 1px 20px rgba(149, 56, 158, .7);*/
}
.card:hover{
	border: 1px solid rgb(149, 56, 158);
	box-shadow: 0px 0px 6px #d1d1d1;

}
.card h1 {
  font-size: 28px;
 
}
.card h2 {
  font-size: 17px;
  color: #555;
}
.card .details {
  display: flex;
  color: #555;
  justify-content: space-around;
}

.card img {
  width: 100%;
  border-radius: 5px;
  max-height: 150px;
  box-shadow: 0px 0px 6px #d1d1d1;
}
</style>