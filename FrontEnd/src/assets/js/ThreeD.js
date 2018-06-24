function show3d (nodes, links) {
  const elem = document.getElementById('3d-graph')
  const Graph = ForceGraph3D()
  (elem)
    .graphData({nodes, links})
    .nodeAutoColorBy('group')
    .linkWidth(1)
    .linkOpacity(0.5)
    .nodeAutoColorBy('id')
    .nodeThreeObject(
      node => {
        const sprite = new SpriteText(node.name)
        sprite.color = node.color
        sprite.textHeight = 8
        return sprite;
      }
    )
    .onNodeHover(node => elem.style.cursor = node ? 'pointer' : null)
    .onNodeClick(node => {
      // Aim at node from outside it
      const distance = 40;
      const distRatio = 1 + distance/Math.hypot(node.x, node.y, node.z);
      Graph.cameraPosition(
        { x: node.x * distRatio, y: node.y * distRatio, z: node.z * distRatio }, // new position
        node, // lookAt ({ x, y, z })
        3000  // ms transition duration
      )
    })
  // Spread nodes a little wider
  Graph.d3Force('charge').strength(-150);
  var threeDGraph = document.getElementById('3d-graph')
  document.onkeydown = function keyboard(event) {
    var e = event || window.event || arguments.callee.caller.arguments[0]
    if(e && e.keyCode === 27 ) {
      alert('Quit 3D Mode')
      threeDGraph.style.display = 'none'
    }
  }
}

function show3d2 (nodes, links) {
  console.log(2)
  const elem = document.getElementById('3d-graph')
  const Graph = ForceGraph3D()
  (elem)
    .graphData({nodes, links})
    .nodeLabel('name')
    .nodeAutoColorBy('id')
    .onNodeHover(node => elem.style.cursor = node ? 'pointer' : null)
    .onNodeClick(node => {
      // Aim at node from outside it
      const distance = 40
      const distRatio = 1 + distance/Math.hypot(node.x, node.y, node.z);
      Graph.cameraPosition(
        { x: node.x * distRatio, y: node.y * distRatio, z: node.z * distRatio }, // new position
        node, // lookAt ({ x, y, z })
        3000  // ms transition duration
      )
    })
  var threeDGraph = document.getElementById('3d-graph')
  document.onkeydown = function keyboard(event) {
    var e = event || window.event || arguments.callee.caller.arguments[0]
    if(e && e.keyCode === 27 ) {
      alert('Quit 3D Mode')
      threeDGraph.style.display = 'none'
    }
  }
}

export {show3d, show3d2}
