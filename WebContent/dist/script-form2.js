const prevBtns = document.querySelectorAll(".btn-prev");
const progress = document.getElementById("progress");
const formSteps = document.querySelectorAll(".form-step");
const progressSteps = document.querySelectorAll(".progress-step");

let formStepsNum = 0;


 



function n1() {

var texto = $("#introduccion").val();
var fecha = $("#fi").val();
var fecha2 = $("#ff").val();
	if (texto.length < 50)  {
		input = "Ingrese al menos 50 caracteres en la introducción";
		salert2(input)
		return false;
	}
	
	
	
													$('#intro2').val(texto);
													$('#fecha1').val(fecha);
													$('#fecha2').val(fecha2);
	
	formStepsNum++;
	updateFormSteps();
	updateProgressbar();

}

function n2() {

    	 
	var numero = document.forms['form1']['cantidadO'];

if (numero.value <= 4)  {
		input = "Ingrese al menos 5 objetivos";
		salert2(input)
		return false;
	}
	formStepsNum++;
	updateFormSteps();
	updateProgressbar();

}


function n3() {

    	 	var numero = document.forms['form4']['cantidadt'];

if (numero.value <= 4)  {
		input = "Ingrese al menos 5 temas";
		salert2(input)
		return false;
	}

	formStepsNum++;
	updateFormSteps();
	updateProgressbar();

}


function n4() {

    	 

	formStepsNum++;
	updateFormSteps();
	updateProgressbar();

}
 
prevBtns.forEach((btn) => {
	btn.addEventListener("click", () => {
		formStepsNum--;
		updateFormSteps();
		updateProgressbar();
	});
});

function updateFormSteps() {
	formSteps.forEach((formStep) => {
		formStep.classList.contains("form-step-active") &&
			formStep.classList.remove("form-step-active");
	});

	formSteps[formStepsNum].classList.add("form-step-active");
}

function updateProgressbar() {
	progressSteps.forEach((progressStep, idx) => {
		if (idx < formStepsNum + 1) {
			progressStep.classList.add("progress-step-active");
		} else {
			progressStep.classList.remove("progress-step-active");
		}
	});

	const progressActive = document.querySelectorAll(".progress-step-active");

	progress.style.width =
		((progressActive.length - 1) / (progressSteps.length - 1)) * 100 + "%";
}