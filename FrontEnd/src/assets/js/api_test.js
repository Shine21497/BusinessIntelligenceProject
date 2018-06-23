
function getData () {
  var temp
  var name1 = document.getElementById('N1').value
  var name2 = document.getElementById('N2').value
  var edge = parseInt(document.getElementById('E').value)
  $.ajax({
    type: 'post',
    async: false,
    crossDomain: true,
    url: 'http://localhost:8088/bbs/api/getData',
    beforeSend: function (xhr) {
      xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded')
    },
    data: {name_1: name1, name_2: name2},
    success: function (res) {
      temp = res
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

export {getData}
