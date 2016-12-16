(function ($) 
{

	$.fn.pickList = function (options) 
	{

		var opts = $.extend({}, $.fn.pickList.defaults, options);
		// Ponemos titulos a los cuadros. La variable 'leftTitle' va a tener el
		// titulo de la izquierda y 'rightTitle' el de la derecha.
		var leftTitle="";
		var rightTitle="";
		if(typeof opts.leftTitle!=='undefined')
		{
			leftTitle=opts.leftTitle;
		}
		if(typeof opts.rightTitle!=='undefined')
		{
			rightTitle=opts.rightTitle;
		}
      this.fill = function () {
         var option = '';

         $.each(opts.data, function (key, val) {
            option += '<option id=' + val.id + '>' + val.text + '</option>';
         });
         this.find('.pickData').append(option);
      };
      this.controll = function () {
         var pickThis = this;

         pickThis.find(".pAdd").on('click', function () {
            var p = pickThis.find(".pickData option:selected");
            p.clone().appendTo(pickThis.find(".pickListResult"));
            p.remove();
         });

         pickThis.find(".pAddAll").on('click', function () {
            var p = pickThis.find(".pickData option");
            p.clone().appendTo(pickThis.find(".pickListResult"));
            p.remove();
         });

         pickThis.find(".pRemove").on('click', function () {
            var p = pickThis.find(".pickListResult option:selected");
            p.clone().appendTo(pickThis.find(".pickData"));
            p.remove();
         });

         pickThis.find(".pRemoveAll").on('click', function () {
            var p = pickThis.find(".pickListResult option");
            p.clone().appendTo(pickThis.find(".pickData"));
            p.remove();
         });
      };

      this.getValues = function () {
         var objResult = [];
         this.find(".pickListResult option").each(function () {
            objResult.push({
               id: $(this).data('id'),
               text: this.text
            });
         });
         return objResult;
      };

      this.init = function () {

         this.fill();
         this.controll();
      };

      this.init();
      return this;
   };

   $.fn.pickList.defaults = {
      add: 'Add >',
      addAll: 'Add All >>',
      remove: '< Remove',
      removeAll: '<< Remove All'
   };


}(jQuery));
