<template>
  <header>
  <div id="head_bar">
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#">Carousel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse"
            aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active" v-on:click="returnHome">
          <a class="nav-link" href="#">Home
            <span class="sr-only">(current)</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Github</a>
        </li>
        <li class="nav-item" v-on:click="changeContent">
          <a class="nav-link" href="#">Quick Start</a>
        </li>
      </ul>
      <form class="form-inline mt-2 mt-md-0">
        <input id="Skey" class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
        <input type="button" class="btn btn-outline-success my-2 my-sm-0" value="Search" v-on:click="GSearch"/>
      </form>
    </div>
  </nav>
  </div>
  </header>
</template>

<script>
import '../assets/css/custom-css-bootstrap-magic-2018-06-16.css'

export default {
  name: 'headerBar',
  data: function () {
    return {
      keyValue: 'a'
    }
  },
  methods: {
    GSearch () {
      var searchKey = document.getElementById('Skey').value
      window.open('https://www.google.com/search?q=' + searchKey)
    },
    changeContent () {
      document.getElementById('home').style.display = 'none'
      document.getElementById('DV').style.display = 'block'
    },
    returnHome () {
      document.getElementById('DV').style.display = 'none'
      document.getElementById('home').style.display = 'block'
    }
  }
}
</script>

<style scoped>
</style>
