
function getData () {
  var temp
  var name1 = document.getElementById('N1').value
  var name2 = document.getElementById('N2').value
  var edge = parseInt(document.getElementById('E').value)
  console.log(edge)
  if (edge < 5){
    $.ajax({
      type: 'post',
      async: false,
      crossDomain: true,
      url: 'http://115.159.34.252:8088/bi/api/getData',
      beforeSend: function (xhr) {
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded')
      },
      data: {name_1: name1, name_2: name2, edge: edge},
      success: function (res) {
        temp = res
        console.log(res)
      },
      error: function (res) {
        console.log('A Something wrong!')
      }
    })
    var displayArea = document.getElementById('graph')
    displayArea.style.background = 'white'
    displayArea.style.border = '2px solid gray'
    return temp
  } else {
    var temp1, temp2
    $.ajax({
      type: 'post',
      async: false,
      crossDomain: true,
      url: 'http://115.159.34.252:8088/bi/api/getData',
      beforeSend: function (xhr) {
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded')
      },
      data: {name_1: name1, name_2: name2, edge: 4},
      success: function (res) {
        temp1 = res
        console.log(res)
      },
      error: function (res) {
        console.log('A Something wrong!')
      }
    })
    $.ajax({
      type: 'post',
      async: false,
      crossDomain: true,
      url: 'http://115.159.34.252:8088/bi/api/getDataPart',
      beforeSend: function (xhr) {
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded')
      },
      data: {name_1: name1, name_2: name2, edge_1: 5, edge_2: edge},
      success: function (res) {
        temp2 = res
        console.log(res)
      },
      error: function (res) {
        console.log('A Something wrong!')
      }
    })
    var displayArea = document.getElementById('graph')
    displayArea.style.background = 'white'
    displayArea.style.border = '2px solid gray'
    return temp
  }
}

export {getData}
