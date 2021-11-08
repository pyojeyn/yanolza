

function setThumbnail(event) {
  for (var image of event.target.files) {
    var reader = new FileReader();
    var limitedImgWidth = "400px"; // Limited width of a image
    var limitedImgHeight = "400px"; // Limited height of a image
    reader.onload = function (event) {
      var img = document.createElement("img");
      img.setAttribute("src", event.target.result);





      document.querySelector("div#image_container").appendChild(img).className = "childImg";
      $(".childImg").css({height:'120px'});
      $(".childImg").css({width:'120px'});

      
    };
    console.log(image);
    reader.readAsDataURL(image);
  }
}

// 출처: https://sinna94.tistory.com/entry/JavaScript-이미지-업로드-미리보기-만들기 [Chungs]
