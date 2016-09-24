   
   function Validatevisitorreg(theForm)
   {
      var regexp;
      if (theForm.Label6.value != theForm.Label5.value)
      {
         alert("Values must be identical");
         theForm.Label6.focus();
         return false;
      }
      return true;
   }
      
   $(document).ready(function()
   {
      $("#PanelMenu1").panel({animate: true, animationDuration: 200, animationEasing: 'linear', dismissible: true, display: 'overlay', position: 'right', overlay: true});
   });
   