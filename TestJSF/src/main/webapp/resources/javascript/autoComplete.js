if (!com) var com = {}
if (!com.corejsf) {
   var focusLostTimeout
   com.corejsf = {   
      errorHandler: function(data) { 
            alert("Error occurred during Ajax call: " + data.description) 
      },

      // 向ajax请求添加两个参数，列表框左上角的x,y位置
      updateCompletionItems: function(input, event) { 
         var keystrokeTimeout

         jsf.ajax.addOnError(com.corejsf.errorHandler)
        
         var ajaxRequest = function() {          
            jsf.ajax.request(input, event, { 
               render: com.corejsf.getListboxId(input),
               x: Element.cumulativeOffset(input)[0],
               y: Element.cumulativeOffset(input)[1] + Element.getHeight(input)
            })
         }
        
         // 合并ajax请求
         // 用户输入停止达到350ms时触发一次ajax请求
         window.clearTimeout(keystrokeTimeout)
         keystrokeTimeout = window.setTimeout(ajaxRequest, 350)  //js定时器
      },

      // 输入失去焦点时隐藏列表框
      inputLostFocus: function(input) {       
         var hideListbox = function() {
            Element.hide(com.corejsf.getListboxId(input))
         }
      
         focusLostTimeout = window.setTimeout(hideListbox, 200)
      },
     
      getListboxId: function(input) {
         var clientId = new String(input.name)
         var lastIndex = clientId.lastIndexOf(":")
         return clientId.substring(0, lastIndex) + ":listbox"
      }
   } 
}
