<template>
         <div class="nameEvent" id="nameEvent" >
			<h3>II Semadec</h3>
		</div>
</template>

<script>
export default {
    name: "TitleEvent",
}

window.onscroll = function() {eventname()};

function eventname() {

  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    document.getElementById("nameEvent").style.opacity = "1";
  } else {
    document.getElementById("nameEvent").style.opacity = "0";
  }

}
</script>

<style scoped>

.nameEvent {
	width: 100%;
	height: 54px;
	position: fixed;
	top: 0px;
	padding: 8px;
	text-align: center;
	z-index: 900;
	background-color: rgb(149, 56, 158);
	color:white;
	opacity: 0;
	transition: opacity 0.4s;
}

</style>