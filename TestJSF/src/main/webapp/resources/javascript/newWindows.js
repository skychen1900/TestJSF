//------------------------
// jQueryキー制御サンプル
// return値falseによりキーキャンセル
//------------------------
$(function(){
  $(document).keydown(function(event){
    // クリックされたキーのコード
    var keyCode = event.keyCode;
    // Ctrlキーがクリックされたか (true or false)
    var ctrlClick = event.ctrlKey;
    // Altキーがクリックされたか (true or false)
    var altClick = event.altKey;
    // キーイベントが発生した対象のオブジェクト
    var obj = event.target;

    // ファンクションキーを制御する
    // 制限を掛けたくない場合は対象から外す
    if(keyCode == 112 // F1キーの制御
    	|| keyCode == 113 // F2キーの制御
    	|| keyCode == 114 // F3キーの制御
    	|| keyCode == 115 // F4キーの制御
    	|| keyCode == 116 // F5キーの制御
    	|| keyCode == 117 // F6キーの制御
    	|| keyCode == 122 // F11キーの制御
    	|| keyCode == 123 // F12キーの制御
      )
    {
      return false;
    }

    // バックスペースキーを制御する
    if(keyCode == 8){
      // テキストボックス／テキストエリアを制御する
      if((obj.tagName == "INPUT" && obj.type == "TEXT")
      	  || obj.tagName == "TEXTAREA"){
        // 入力できる場合は制御しない
        if(!obj.readOnly && !obj.disabled){
          return true;
        }
      }
      return false;
    }

    // Alt + ←を制御する
    if(altClick && (keyCode == 37 || keyCode == 39)){
      return false;
    }

    // Ctrl + Nを制御する
    if(ctrlClick && keyCode == 78){
      return false;
    }
  });
});

/* ------------------------------
 *
------------------------------ */
function openUrl(){
    var webOptionUrl = document.getElementById("websiteForm:webOptionHidden").value;
    if(webOptionUrl.length === 0){
         var message = 'Please select website';
         alert(message);
    } else {
    	$(function(){
    		   // 処理前に Loading 画像を表示
    		   dispLoading("処理中...");
    	});

         window.open(webOptionUrl, '', 'width=1024, height=720, status=no, scrollbars=1 menubar=no, toolbar=no');
    };
}

/* ------------------------------
非同期処理の組み込みイメージ
------------------------------ */
$(function () {
 $('#websiteForm\\:submitButton').click( function() {

   // 処理前に Loading 画像を表示
   dispLoading("処理中...");

   // 非同期処理
   $.ajax({
     url : "サーバーサイドの処理を行うURL",
     type:"GET",
     dataType:"json"
   })
   // 通信成功時
   .done( function(data) {
     showMsg("成功しました");
   })
   // 通信失敗時
   .fail( function(data) {
     showMsg("失敗しました");
   })
   // 処理終了時
   .always( function(data) {
     // Lading 画像を消す
     removeLoading();
   });
 });
});


/* ------------------------------
Loading イメージ表示関数
引数： msg 画面に表示する文言
------------------------------ */
function dispLoading(msg){
 // 引数なし（メッセージなし）を許容
 if( msg == undefined ){
   msg = "";
 }
 // 画面表示メッセージ
 var dispMsg = "<div class='loadingMsg'>" + msg + "</div>";
 // ローディング画像が表示されていない場合のみ出力
 if($("#loading").length == 0){
   $("body").append("<div id='loading'>" + dispMsg + "</div>");
 }
}

/* ------------------------------
Loading イメージ削除関数
------------------------------ */
function removeLoading(){
 $("#loading").remove();
}