<template>
  <main style="text-align: center;" >
    <div id="bg">
    <h1>Data Visualization</h1>
    <nav id="typeChoose" class="navbar navbar-expand-md navbar-light bg-light rounded mb-3">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse2" aria-controls="navbarCollapse2"
              aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse2">
        <ul class="navbar-nav text-md-center nav-justified w-100">
          <li id="m_1" class="nav-item active" v-on:click="graphMode(1)">
            <a class="nav-link" href="#">Traditional</a>
          </li>
          <li id="m_2" class="nav-item" v-on:click="graphMode(2)">
            <a class="nav-link" href="#">Neo4j Style</a>
          </li>
          <li id="m_3" class="nav-item" v-on:click="graphMode(3)">
            <a class="nav-link" href="#">3D Style</a>
          </li>
        </ul>
      </div>
    </nav>
    <div id="3d-graph" style="display: none;"></div>
    <svg id="graph" width="1000" height="500"></svg>
    <label style="color:white; display: block;"><input style="width:240px; margin-top: 20pt;" type="range" min="0" max="0.5" step="any" value="0.25"> Link Strength</label>
    <form class="form-inline mt" >
      <div style="width:100%; align:center; margin-top: 20px;">
        <input id="N1" class="form-control mr-lg-2" type="text" placeholder="Name 1" aria-label="Name 1"/>
        <input id="N2" class="form-control mr-lg-2" type="text" placeholder="Name 2" aria-label="Name 2"/>
        <input id="E" class="form-control mr-sm-2" type="text" placeholder="Max Edges" aria-label="Max Edges"/>
        <input type="button" class="btn btn-primary my-2 " value="Search for Graph" v-on:click="getData()"/>
      </div>
    </form>
      <h2>Data Table</h2>
      <nav id="dataChoose" class="navbar navbar-expand-md navbar-light bg-secondary rounded mb-3">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse3" aria-controls="navbarCollapse3"
                aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse3">
          <ul class="navbar-nav text-md-center nav-justified w-100">
            <li id="d_1" class="nav-item active" v-on:click="dataType(1)">
              <a class="nav-link" href="#">Nodes</a>
            </li>
            <li id="d_2" class="nav-item" v-on:click="dataType(2)">
              <a class="nav-link" href="#">Links</a>
            </li>
          </ul>
        </div>
      </nav>
      <div id="t_1" class="table-responsive">
        <table id="nodeTable"  class="table table-striped">
          <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="item in nodes">
            <td>{{item.id}}</td>
            <td>{{item.name}}</td>
          </tr>
          </tbody>
        </table>
      </div>
      <div id="t_2" class="table-responsive" style="display: none">
        <table id="linkTable"  class="table table-striped">
          <thead>
          <tr>
            <th>Type</th>
            <th>Source Name</th>
            <th>Target Name</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="item in links">
            <td>{{item.type}}</td>
            <td>{{item.source.name}}</td>
            <td>{{item.target.name}}</td>
          </tr>
          </tbody>
        </table>
      </div>
      <div style="width: 100%; height: 20px; margin-top:40px; background: #5a5a5a;"></div>
    </div>
  </main>
</template>

<script>
import {showData} from '../assets/js/d3test.js'
import {getData} from '../assets/js/api_test.js'
import {showData2} from '../assets/js/N4jStyle.js'
import {show3d, show3d2} from '../assets/js/ThreeD.js'

export default {
  name: 'dataV',
  data: function () {
    return {
      mode: 1,
      type: 1,
      nodes: [],
      links: []
    }
  },
  methods: {
    graphMode: function (x) {
      document.getElementById('m_' + this.mode).className = 'nav-item'
      this.mode = x
      document.getElementById('m_' + this.mode).className = 'nav-item active'
      if (x === 3) {
        document.getElementById('graph').style.display = 'none'
        document.getElementById('3d-graph').style.display = 'block'
        console.log(this.nodes)
        show3d2(this.nodes, this.links)
      } else {
        document.getElementById('3d-graph').style.display = 'none'
        document.getElementById('graph').style.display = 'inline'
        this.display(this.mode)
      }
    },
    dataType: function (x) {
      document.getElementById('d_' + this.type).className = 'nav-item'
      document.getElementById('t_' + this.type).style.display = 'none'
      this.type = x
      document.getElementById('d_' + this.type).className = 'nav-item active'
      document.getElementById('t_' + this.type).style.display = 'block'
    },
    getData: function () {
      var temp = getData()
      this.nodes = temp.nodes
      this.links = temp.links
      var displayArea = document.getElementById('graph')
      displayArea.style.background = 'white'
      displayArea.style.border = '2px solid gray'
      this.display(this.mode)
    },
    display: function (x) {
      if (x === 1) {
        showData(this.nodes, this.links)
      } else if (x === 2) {
        showData2(this.nodes, this.links, false)
      } else {
        show3d2(this.nodes, this.links)
      }
    }
  }
}
</script>

<style scoped>
  h1 {
    color: white;
    font-weight: 200;
    padding-top: 80px;
  }
  h2 {
    margin-top: 50px;
    color: white;
    font-weight: 200;
  }
  table{
    background: white;
    max-width: 1000px;
    width:80%;
    margin: 40px auto;
    text-align: center;
    border-radius: 5px;
  }
  th{
    text-align: center;
  }
  svg{
    background-image: url("../../static/img/defaultBG.jpeg");
    background-position: center;
    max-width: 80%;
    margin-top: 40px;
    border-radius: 5px;
  }
  #bg{
    background-image: url("../assets/img/bg.jpg");
    background-position: center;
    background-blend-mode: darken;
    background-color: #6c757d;
    background-repeat: repeat-y;
    top:0;
    width:100%;
    min-height: 100%;
    background-attachment: fixed;
  }
  #typeChoose{
    margin: 0 auto;
    top: 20px;
    max-width: 1000px;
  }
  #dataChoose{
    margin: 40px auto;
    max-width: 1000px;
  }

  @media (min-width: 768px) {
    .navbar-nav {
      display: -ms-flexbox;
      display: flex;
    }

    .navbar-nav .nav-item {
      -ms-flex: 1 0 auto;
      flex: 1 0 auto;
    }
  }
</style>
