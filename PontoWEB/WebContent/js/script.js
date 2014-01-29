function mascara(o,f){
    v_obj=o;
    v_fun=f;
    setTimeout("execmascara()",1);
}


function mascara_data(data){
	
	              var mydata = '';
	
	              mydata = mydata + data;
	
	              if (mydata.length == 2){
	
	                  mydata = mydata + '/';
	
	                  document.forms[0].data.value = mydata;
	
	              }
	
	              if (mydata.length == 5){
	
	                  mydata = mydata + '/';
	
	                  document.forms[0].data.value = mydata;
	
	              }
	
	              if (mydata.length == 10){
	
	                  verifica_data();
	
	              }
	
	          }
	
	            
	
	          function verifica_data () {
	
	 
	
	            dia = (document.forms[0].data.value.substring(0,2));
	
	            mes = (document.forms[0].data.value.substring(3,5));
	
	            ano = (document.forms[0].data.value.substring(6,10));
	
	 
	
	            situacao = "";
	
	            // verifica o dia valido para cada mes
	
	            if ((dia < 01)||(dia < 01 || dia > 30) && (  mes == 04 || mes == 06 || mes == 09 || mes == 11 ) || dia > 31) {
	
	                situacao = "falsa";
		            }
	
	 
	
	            // verifica se o mes e valido
	
	            if (mes < 01 || mes > 12 ) {
	
	                situacao = "falsa";
	
	            }
	
	 
	
	            // verifica se e ano bissexto
	
	            if (mes == 2 && ( dia < 01 || dia > 29 || ( dia > 28 && (parseInt(ano / 4) != ano / 4)))) {
	
	                situacao = "falsa";
	
	            }
	
	     
	
	            if (document.forms[0].data.value == "") {
	
	                situacao = "falsa";
	
	            }
	
	     
	
	            if (situacao == "falsa") {
	
	               alert("Data inv�lida!");
	
	                document.forms[0].data.focus();
	
	            }
	
	          }
	
	 
	
	          function mascara_hora(hora){
	
	              var myhora = '';
	
	              myhora = myhora + hora;
	
	              if (myhora.length == 2){
	
	                  myhora = myhora + ':';
	
	                  document.forms[0].hora.value = myhora;
	
	              }
	
	              if (myhora.length == 5){
	
	                  verifica_hora();
	
	              }
	
	          }
	
	            
	
	          function verifica_hora(){
	
	              hrs = (document.forms[0].hora.value.substring(0,2));
		              min = (document.forms[0].hora.value.substring(3,5));
	
	              
	
	              alert('hrs '+ hrs);
	
	              alert('min '+ min);
	
	                
	
	              situacao = "";
	
	              // verifica data e hora
	
	              if ((hrs < 00 ) || (hrs > 23) || ( min < 00) ||( min > 59)){
	
	                  situacao = "falsa";
	
	              }
	
	                
	
	              if (document.forms[0].hora.value == "") {
	
	                  situacao = "falsa";
	
	              }
	
	 
	
	              if (situacao == "falsa") {
	
	                  alert("Hora inv�lida!");
	
	                  document.forms[0].hora.focus();
	
	              }
	
	          }
	
	       


 
function execmascara(){
    v_obj.value=v_fun(v_obj.value);
}

function cnpj(v){
    v=v.replace(/\D/g,"");                           //Remove tudo o que não é dígito
    v=v.replace(/^(\d{2})(\d)/,"$1.$2");             //Coloca ponto entre o segundo e o terceiro dígitos
    v=v.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3"); //Coloca ponto entre o quinto e o sexto dígitos
    v=v.replace(/\.(\d{3})(\d)/,".$1/$2");           //Coloca uma barra entre o oitavo e o nono dígitos
    v=v.replace(/(\d{4})(\d)/,"$1-$2");              //Coloca um hífen depois do bloco de quatro dígitos
    return v;
}

function numero(v){
	v=v.replace(/\D/g,"");
	return v;
}

function SomenteNumero(e){
    var tecla=(window.event)?event.keyCode:e.which;
    if((tecla > 47) && (tecla < 58)){
		return true;
    }
    else{
    if (tecla != 8) return false;
    else return true;
    }
}
